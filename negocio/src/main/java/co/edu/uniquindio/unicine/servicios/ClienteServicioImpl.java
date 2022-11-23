package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.repositorios.ClienteRepo;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
    private final CiudadRepo ciudadRepo;
    private final PQRSRepo pqrsRepo;

    private final CompraConfiteriaRepo compraConfiteriaRepo;

    private final EntradaRepo entradaRepo;
    private final ProductoConfiteriaRepo productoConfiteriaRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, CompraRepo compraRepo, EmailService emailService, FuncionRepo funcionRepo, ClienteCuponRepo clienteCuponRepo, CiudadRepo ciudadRepo, PQRSRepo pqrsRepo, CompraConfiteriaRepo compraConfiteriaRepo, EntradaRepo entradaRepo, ProductoConfiteriaRepo productoConfiteriaRepo) {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.compraRepo = compraRepo;
        this.emailService = emailService;
        this.funcionRepo = funcionRepo;
        this.clienteCuponRepo = clienteCuponRepo;
        this.ciudadRepo = ciudadRepo;
        this.pqrsRepo = pqrsRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
        this.entradaRepo = entradaRepo;
        this.productoConfiteriaRepo = productoConfiteriaRepo;
    }

    //Implementaciones

    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception {

        Optional<Cliente> clienteExiste =  clienteRepo.findById(cliente.getCedula());
        if (clienteExiste.isPresent()) throw new Exception("Ya existe un usuario con la cédula ingresada.");

        boolean correoExiste = verificarExistenciaCorreo(cliente.getEmail());
        if (correoExiste) throw new Exception("El correo ingresado ya está siendo usado por otro usuario.");

        StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
        cliente.setContrasenia( spe.encryptPassword(cliente.getContrasenia()) );

        cliente.setEstado("NO_VERIFICADO");

        Cliente registro = clienteRepo.save(cliente);

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("teclado");
        String param1 = textEncryptor.encrypt(registro.getEmail());

        emailService.enviarEmail("¡Bienvenido a Unicine!",
                "¡Te has registrado en nuestra plataforma! Para confirmar tu correo, ingresa al siguiente link: " + "http://localhost:8081/activar_cuenta.xhtml?p1="+param1, cliente.getEmail());
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

        Cliente cliente = clienteRepo.findById(cedula).orElse(null);

        if (cliente == null) throw new Exception("No existe un usuario con la cédula ingresada");
        if (cliente.getEstado().equals("NO_VERIFICADO")) throw new Exception("La cuenta del usuario está inactiva; ésta debe ser verificada a través del enlace enviado al correo");

        StrongPasswordEncryptor spe = new StrongPasswordEncryptor();

        if (!spe.checkPassword( contrasenia, cliente.getContrasenia())) throw new Exception("La contraseña ingresada es incorrecta");

        return cliente;
    }

    @Override
    public List<Pelicula> buscarPelicula(String nombrePelicula) throws Exception {

        List<Pelicula> peliculas = peliculaRepo.buscarPeliculasPorNombre(nombrePelicula);
        if (peliculas.isEmpty()) throw new Exception("No se encontró ninguna pelicula.");

        return peliculas;
    }

    @Override
    public Compra realizarCompra(Compra compra) throws Exception {

        if(!compra.getEstado().equals("CREADA")) throw new Exception("No se ha realizado correctamente el proceso de compra.");

        boolean clienteExiste = verificarExistenciaCliente(compra.getCliente().getCedula());
        if (!clienteExiste) throw new Exception("La compra no tiene asociado un cliente válido.");

        boolean productosConfiteriaExisten = verificarProductosConfiteria(compra.getComprasConfiteria());
        if (!productosConfiteriaExisten) throw new Exception("Los productos de confitería escogidos no existen.");

        boolean funcionValida = verificarExistenciaFuncion(compra.getFuncion()) ;
        if (!funcionValida) throw new Exception("La funcion escogida no existe o no es válida.");

        boolean cuponValido = verificarValidezCupon(compra.getClienteCupon());
        if (!cuponValido) throw new Exception("El cupón ingresado no es válido");

        boolean medioPagoValido = verificarMedioPago(compra.getMedioDePago());
        if (!medioPagoValido) throw new Exception("El medio de pago seleccionado no es correcto.");


        if(compra.getClienteCupon() != null) compra.getClienteCupon().setEstado("REDIMIDO");

        compra.setFechaCompra(LocalDateTime.now());
        compra.setEstado("COMPLETADA");
        compra.actualizarPrecioTotal();

        Compra compraRealizada = compraRepo.save(compra);

        //TODO redireccionar al detalle de la compra
        emailService.enviarEmail("¡Compra realizada con éxito!",
                "La información de tu compra: \n" + "Código de la compra: " + compraRealizada.getCodigo() + "; " +
                "función: " + compraRealizada.getFuncion().getPelicula().getNombre() + "; " +
                        "ubicación: " + compraRealizada.getFuncion().getSala().getTeatro().getNombre() + ", "+ compraRealizada.getFuncion().getSala().getTeatro().getDireccion() + "; " +
                "total: " + compraRealizada.getPrecioTotal(),
                compra.getCliente().getEmail());
        return compraRealizada;
    }

    private boolean verificarExistenciaCliente(String cedula) {
        Optional<Cliente> cliente = clienteRepo.findById(cedula);
        return (cliente.isPresent());
    }

    private boolean verificarExistenciaFuncion(Funcion funcion) {
        Funcion funcionExiste = funcionRepo.verificarFuncion(funcion);
        return (funcionExiste != null);
    }

    private boolean verificarProductosConfiteria(List<CompraConfiteria> comprasConfiteria) {

        if (comprasConfiteria.isEmpty()) {
            return true;
        } else {
            for (CompraConfiteria compraConfiteria : comprasConfiteria) {
                ProductoConfiteria productoConfiteria = compraRepo.obtenerProductoConfiteria(compraConfiteria.getProductoConfiteria().getCodigo());
                if (productoConfiteria == null) return false;
            }
        }
        return true;
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
        return (cuponCliente != null && cupon.getEstado().equals("ACTIVO")); //TODO validar fecha vencimiento y setearlo
    }

    //Verificar que las entradas estén disponibles
    @Override
    public void cancelarCompra(Integer idCompra) throws Exception {

        Optional<Compra> compra = compraRepo.findById(idCompra);

        if (!compra.isPresent()) throw new Exception("La compra no fue encontrada.");

        if (!compra.get().getEstado().equals("COMPLETADA")) {
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

    //TODO realizar la consulta para obtener las compras del cliente
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

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("teclado");
        String param1 = textEncryptor.encrypt(cliente.getEmail());

        System.out.println("Recién encriptado: " + param1);

        emailService.enviarEmail("Cambio de contraseña",
                "Para reestablecer tu contraseña, accede al siguiente enlace de recuperación: http://localhost:8081/reestablecer_contrasenia.xhtml?p1="+param1, cliente.getEmail());
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

    @Override
    public List<Pelicula> listarPeliculasPorEstadoCiudad(String estado, Integer codigoCiudad) throws Exception {

        Optional<Ciudad> guardada = ciudadRepo.findById(codigoCiudad);
        if (!guardada.isPresent()) {
            throw new Exception("La ciudad buscada no se encuentra registrada.");
        }
        return peliculaRepo.buscarPeliculasPorEstadoCiudad(estado, codigoCiudad);
    }

    @Override
    public List<Pelicula> listarPeliculasPorEstado(String estado) throws Exception {
        return peliculaRepo.buscarPeliculasPorEstado(estado);
    }

    @Override
    public Compra obtenerCompra(Integer codigoCompra) throws Exception {
        return compraRepo.findById(codigoCompra).orElseThrow( () -> new Exception("No se encontró la compra"));
    }

    @Override
    public void activarCuenta(String param1) throws Exception {

        param1 = param1.replace(" ", "+");

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("teclado");
        String correoDes = textEncryptor.decrypt(param1);


        Cliente guardado = clienteRepo.obtenerPorCorreo(correoDes);

        if (guardado == null) throw new Exception("El cliente no existe");

        if (guardado.getEstado().equals("VERIFICADO")) throw new Exception("Esta cuenta ya fue verificada");

        guardado.setEstado("VERIFICADO");
        clienteRepo.save(guardado);

        //TODO crear el cupon de bienvenida para el cliente guardado
    }

    @Override
    public void reestablecerContrasenia(String param1, String param2) throws Exception {

        System.out.println("Lo que recibo: " + param1);

        param1 = param1.replace(" ","+");

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("teclado");
        String correo = textEncryptor.decrypt(param1);

        System.out.println("Ya desencriptado: " + correo);

        Cliente guardado = clienteRepo.obtenerPorCorreo(correo);
        if (guardado == null) throw new Exception("El cliente no existe");

        StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
        guardado.setContrasenia( spe.encryptPassword(param2) );

        clienteRepo.save(guardado);
    }

    @Override
    public List<ProductoConfiteria> obtenerProductosConfiteria() {
        return productoConfiteriaRepo.findAll();
    }

    @Override
    public List<Entrada> obtenerEntradasFuncion(Funcion funcion, LocalDate fechaFuncion) throws Exception {

        Optional<Funcion> funcionAux = funcionRepo.findById(funcion.getCodigo());
        if (!funcionAux.isPresent()) throw new Exception("La función enviada no existe");

        return funcionRepo.obtenerEntradasFuncion(funcion.getCodigo(), fechaFuncion);
    }

    @Override
    public void guardarEntrada(Entrada entrada) throws Exception {
        entradaRepo.save(entrada);
    }

    @Override
    public void guardarCompraConfiteria(CompraConfiteria compraConfiteria){
        compraConfiteriaRepo.save(compraConfiteria);
    }


}
