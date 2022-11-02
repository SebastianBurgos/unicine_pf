package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.repositorios.ClienteRepo;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;
    private final PeliculaRepo peliculaRepo;
    private final CompraRepo compraRepo;
    private final EmailService emailService;
    private final FuncionRepo funcionRepo;
    private final ClienteCuponRepo clienteCuponRepo;

    private final PQRSRepo pqrsRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, CompraRepo compraRepo, EmailService emailService, FuncionRepo funcionRepo, ClienteCuponRepo clienteCuponRepo, PQRSRepo pqrsRepo) {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.compraRepo = compraRepo;
        this.emailService = emailService;
        this.funcionRepo = funcionRepo;
        this.clienteCuponRepo = clienteCuponRepo;
        this.pqrsRepo = pqrsRepo;
    }

    //Implementaciones

    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception {

        Optional<Cliente> clienteExiste =  clienteRepo.findById(cliente.getCedula());
        if (clienteExiste.isPresent()) throw new Exception("Ya existe un usuario con la cédula ingresada.");

        boolean correoExiste = verificarExistenciaCorreo(cliente.getEmail());
        if (correoExiste) throw new Exception("El correo ingresado ya está siendo usado por otro usuario.");

        Cliente registro = clienteRepo.save(cliente);
        emailService.enviarEmail("¡Bienvenido a Unicine!",
                "Te has registrado en nuestra plataforma. Para confirmar tu correo ingresa al siguiente link: ..." + "Aquí iría el enlace", cliente.getEmail());
        return registro;
    }

    private boolean verificarExistenciaCorreo(String correo){
        Cliente cliente = clienteRepo.obtenerPorCorreo(correo);
        return !(cliente == null);
    }

    @Override
    public Cliente obtenerCliente(String cedula) throws Exception {

        Optional<Cliente> guardado = clienteRepo.findById(cedula);
        if (!guardado.isPresent()) throw new Exception("El cliente no existe.");
        return guardado.get();
    }

    @Override
    public Cliente login(String cedula, String contrasenia) throws Exception {

        Cliente cliente = clienteRepo.comprobarAutenticacion(cedula, contrasenia);

        if (cliente == null) throw new Exception("Los datos de autenticación no son válidos.");

        return cliente;
    }

    @Override
    public List<Pelicula> buscarPelicula(String nombrePelicula) throws Exception {

        List<Pelicula> peliculas = peliculaRepo.buscarPeliculasPorNombre(nombrePelicula);
        if (peliculas.isEmpty()) throw new Exception("No se encontró ninguna pelicula.");

        return peliculas;
    }

    @Override
    public Compra iniciarCompra(Cliente cliente, Funcion funcion) throws Exception {

        if (!verificarExistenciaCliente(cliente.getCedula())) throw new Exception("La compra no tiene asociado un cliente válido.");
        if (!verificarExistenciaFuncion(funcion)) throw new Exception("La funcion escogida no existe o no es válida.");
        Compra compra = Compra.builder().cliente(cliente).funcion(funcion).build();
        compra.setEstado(1);
        return compraRepo.save(compra);
    }

    private boolean verificarExistenciaCliente(String cedula) {
        Optional<Cliente> cliente = clienteRepo.findById(cedula);
        return (cliente.isPresent());
    }

    private boolean verificarExistenciaFuncion(Funcion funcion) {
        Funcion funcionExiste = funcionRepo.verificarFuncion(funcion);
        return (funcionExiste != null);
    }

    @Override
    public Compra asignarEntrada(Compra compra, List<Entrada> entradas) throws Exception {

        if (compra.getEstado() < 1 || compra.getEstado() == 5) throw new Exception("La compra no ha sido asignada a ninguna función o cliente.");
        if (entradas.isEmpty()) throw new Exception("No se seleccionó ningún asiento");

        boolean entradasValidas = verificarEntradas(compra.getFuncion(), entradas);

        if (!entradasValidas) throw new Exception("Las sillas escogidas no son válidas");

        compra.setEntradas(entradas);
        compra.setEstado(2);
        compra.actualizarPrecioTotal();
        return compraRepo.save(compra);
    }

    private boolean verificarEntradas(Funcion funcion, List<Entrada> entradas) throws Exception{

        List<Entrada> entradasFuncion = funcionRepo.obtenerEntradasFuncion(funcion.getCodigo());
        if (entradasFuncion.isEmpty()) return true;

        char fila;
        int columna;
        for (Entrada entrada : entradasFuncion) {
            for (Entrada entradaEscogida : entradas) {
                if (entradaEscogida == null) return false;
                fila = entradaEscogida.getFila();
                columna = entradaEscogida.getColumna();
                if (entrada.getFila() == fila && entrada.getColumna() == columna)
                    return false;
            }
        }
        return true;
    }

    private Integer hallarNumeroFila(Character letra, Integer columna) {
        if (letra == 'A') return 0;
        if (letra == 'B') return columna + 1;
        if (letra == 'C') return (columna*2) + 2;
        if (letra == 'D') return (columna*3) + 3;
        if (letra == 'E') return (columna*4) + 4;
        if (letra == 'F') return (columna*5) + 5;
        if (letra == 'G') return (columna*6) + 6;
        if (letra == 'H') return (columna*7) + 7;
        if (letra == 'I') return (columna*8) + 8;
        if (letra == 'J') return (columna*9) + 9;
        return null;
    }

    @Override
    public Compra asignarComprasConfiteria(Compra compra, List<CompraConfiteria> comprasConfiteria) throws Exception {

        if (compra.getEstado() < 2) throw new Exception("Ninguna silla ha sido escogida en la compra.");

        if (comprasConfiteria.isEmpty()) {
            compra.setComprasConfiteria(null);
            compra.setEstado(3);
            return compraRepo.save(compra);
        }

        boolean comprasConfiteriaValidas = verificarProductosConfiteria(comprasConfiteria);
        if (!comprasConfiteriaValidas) throw new Exception("Los productos de la confiteria seleccionados no existen");

        compra.setComprasConfiteria(comprasConfiteria);
        compra.setEstado(3);
        compra.actualizarPrecioTotal();
        return compraRepo.save(compra);
    }

    private boolean verificarProductosConfiteria(List<CompraConfiteria> comprasConfiteria) {

        for (CompraConfiteria compraConfiteria : comprasConfiteria) {
            ProductoConfiteria productoConfiteria = compraRepo.obtenerProductoConfiteria(compraConfiteria.getCodigo());
            if (productoConfiteria == null) return false;
        }
        return true;
    }

    @Override
    public Compra asignarPago(Compra compra, MedioPago medioPago, ClienteCupon clienteCupon) throws Exception {

        if(compra.getEstado() < 3 || compra.getEstado() == 5) throw new Exception("No se ha realizado correctamente el proceso de compra.");

        boolean medioPagoValido = verificarMedioPago(medioPago);
        if (!medioPagoValido) throw new Exception("El medio de pago seleccionado no es correcto.");

        boolean cuponValido =verificarValidezCupon(clienteCupon);
        if (!cuponValido) throw new Exception("El cupón seleccionado para redimir no es válido.");

        if(clienteCupon != null) clienteCupon.setEstado("REDIMIDO");

        compra.setMedioDePago(medioPago);
        compra.setClienteCupon(clienteCupon);
        compra.actualizarPrecioTotal() ;
        compra.setEstado(4);

        return compraRepo.save(compra);
    }

    private boolean verificarMedioPago(MedioPago medioPago) {
        if (medioPago == null) return false;

        for (MedioPago medioPago1 : MedioPago.values()) {
            if (medioPago.equals(medioPago1)) return true;
        }

        return false;
    }

    private boolean verificarValidezCupon(ClienteCupon cupon) {

        if (cupon == null) return true;
        Cupon cuponCliente = clienteCuponRepo.obtenerCupon(cupon.getCodigo());
        return (cuponCliente != null && cupon.getEstado().equals("ACTIVO"));
    }

    @Override
    public Compra realizarCompra(Compra compra) throws Exception {

        if(compra.getEstado() < 4 || compra.getEstado() == 5) throw new Exception("No se ha realizado correctamente el proceso de compra.");

        boolean clienteExiste = verificarExistenciaCliente(compra.getCliente().getCedula());
        if (!clienteExiste) throw new Exception("La compra no tiene asociado un cliente válido.");

        boolean productosConfiteriaExisten = verificarProductosConfiteria(compra.getComprasConfiteria());
        if (!productosConfiteriaExisten) throw new Exception("Los productos de confitería escogidos no existen.");

        boolean funcionValida = verificarExistenciaFuncion(compra.getFuncion()) ;
        if (!funcionValida) throw new Exception("La funcion escogida no existe o no es válida.");

        boolean cuponValido = verificarValidezCupon(compra.getClienteCupon());
        if (!cuponValido) throw new Exception("El cupón ingresado no es válido");

        compra.setFechaCompra(LocalDateTime.now());
        compra.setEstado(5);
        compra.actualizarPrecioTotal();

        emailService.enviarEmail("¡Compra realizada con éxito!",
                "La información de tu compra: <br>" + compra, compra.getCliente().getEmail());
        return compraRepo.save(compra);
    }
    //Verificar que las entradas estén disponibles
    @Override
    public void cancelarCompra(Integer idCompra) throws Exception {

        Optional<Compra> compra = compraRepo.findById(idCompra);

        if (!compra.isPresent()) throw new Exception("La compra no fue encontrada.");

        if (compra.get().getEstado() < 5) {
            compraRepo.delete(compra.get());
        } else {
            throw new Exception("La compra ya fue realizada, no es posible cancelarla");
        }
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws Exception{

        Optional<Cliente> guardado = clienteRepo.findById(cliente.getCedula());

        if (!guardado.isPresent()) {
            throw new Exception("El cliente no existe.");
        } else {
            return clienteRepo.save(cliente);
        }
    }

    @Override
    public void eliminarCliente(String cedulaCliente) throws Exception{

        Optional<Cliente> guardado = clienteRepo.findById(cedulaCliente);

        if (!guardado.isPresent()) {
            throw new Exception("El cliente no existe.");
        } else {
            clienteRepo.delete(guardado.get());
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    //TODO
    @Override
    public List<Compra> listarHistorialCompras(String cedulaCliente) throws Exception{

        Optional<Cliente> cliente = clienteRepo.findById(cedulaCliente);

        if (!cliente.isPresent()) throw new Exception("El cliente solicitado no existe.");

        return clienteRepo.obtenerCompras(cliente.get().getCedula());
    }

    @Override
    public void solicitarCambioContrasenia(String email) throws Exception {

        Cliente cliente = clienteRepo.obtenerClientePorCorreo(email);

        if (cliente == null) throw new Exception("El cliente solicitado no existe.");

        emailService.enviarEmail("Cambio de contraseña",
                "Para reestablecer tu contraseña, accede al siguiente enlace de recuperación: <br> aquí iría el link", cliente.getEmail());
    }

    @Override
    public PQRS realizarPqrs(PQRS solicitudPqrs) throws Exception {

        if(solicitudPqrs.getCliente() == null) throw new Exception("El PQRS no tiene asociado ningún cliente.");

        Optional<Cliente> clienteExiste =  clienteRepo.findById(solicitudPqrs.getCliente().getCedula());
        if (!clienteExiste.isPresent()) throw new Exception("El cliente asociado al PQRS no existe.");

        PQRS registro = pqrsRepo.save(solicitudPqrs);
        emailService.enviarEmail("Solicitud PQRS",
                "Has realizado una solicitud de pqrs. En aproximadamente 48 horas hábiles te estaremos dando una respuesta.", solicitudPqrs.getCliente().getEmail());
        return registro;
    }
}
