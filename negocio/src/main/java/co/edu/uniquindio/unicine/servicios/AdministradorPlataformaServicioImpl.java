package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorPlataformaServicioImpl implements AdministradorPlataformaServicio{

    private final AdministradorTeatroRepo administradorTeatroRepo;
    private final PeliculaRepo peliculaRepo;
    private final CuponRepo cuponRepo;
    private final ProductoConfiteriaRepo productoConfiteriaRepo;
    private final CiudadRepo ciudadRepo;

    private final ClienteRepo clienteRepo;
    private final FuncionRepo funcionRepo;

    private final ClienteCuponRepo clienteCuponRepo;
    private final AdministradorPlataformaRepo administradorPlataformaRepo;

    public AdministradorPlataformaServicioImpl(AdministradorTeatroRepo administradorTeatroRepo, PeliculaRepo peliculaRepo, CuponRepo cuponRepo, ProductoConfiteriaRepo productoConfiteriaRepo, CiudadRepo ciudadRepo, ClienteRepo clienteRepo, FuncionRepo funcionRepo, ClienteCuponRepo clienteCuponRepo, AdministradorPlataformaRepo administradorPlataformaRepo) {
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.peliculaRepo = peliculaRepo;
        this.cuponRepo = cuponRepo;
        this.productoConfiteriaRepo = productoConfiteriaRepo;
        this.ciudadRepo = ciudadRepo;
        this.clienteRepo = clienteRepo;
        this.funcionRepo = funcionRepo;
        this.clienteCuponRepo = clienteCuponRepo;
        this.administradorPlataformaRepo = administradorPlataformaRepo;
    }

    public AdministradorPlataforma login(String email, String contrasenia){
        AdministradorPlataforma admin = administradorPlataformaRepo.comprobarAutenticacion(email, contrasenia);
        if(admin == null){
            return null;
        }
        return admin;
    }

    @Override
    public AdministradorTeatro obtenerAdministradorTeatro(String cedulaAdministradorTeatro) throws Exception {
        Optional<AdministradorTeatro> guardado = administradorTeatroRepo.findById(cedulaAdministradorTeatro);

        if (!guardado.isPresent()) {
            throw new Exception("El administrador de teatro no existe.");
        } else {
            return guardado.get();
        }
    }

    @Override
    public AdministradorTeatro registrarAdministradorTeatro(AdministradorTeatro administradorTeatro) throws Exception {
        boolean correoExiste = verificarExistenciaAdministradorTeatroCorreo(administradorTeatro.getEmail());
        Optional<AdministradorTeatro> administradorTeatroVerificadoCedula = administradorTeatroRepo.findById(administradorTeatro.getCedula());

        if (correoExiste) {
            throw new Exception("El correo ingresado ya está siendo usado por otro usuario.");
        }
        if (administradorTeatroVerificadoCedula.isPresent()) {
            throw new Exception("Ya hay un administrador de teatro registrado con la cedula ingresada.");
        }

        return administradorTeatroRepo.save(administradorTeatro);
    }

    @Override
    public ClienteCupon asignarCupon(ClienteCupon clienteCupon) {
        return clienteCuponRepo.save(clienteCupon);
    }

    private boolean verificarExistenciaAdministradorTeatroCorreo(String correo) {
        AdministradorTeatro administradorTeatro = administradorTeatroRepo.obtenerPorCorreo(correo);
        return !(administradorTeatro == null);
    }

    @Override
    public AdministradorTeatro actualizarAdministradorTeatro(AdministradorTeatro administradorTeatro) throws Exception {
        Optional<AdministradorTeatro> guardado = administradorTeatroRepo.findById(administradorTeatro.getCedula());

        if (!guardado.isPresent()) {
            throw new Exception("El administrador de teatro no existe.");
        } else {
            return administradorTeatroRepo.save(administradorTeatro);
        }
    }

    @Override
    public void eliminarAdministradorTeatro(String cedulaAdministradorTeatro) throws Exception {
        Optional<AdministradorTeatro> guardado = administradorTeatroRepo.findById(cedulaAdministradorTeatro);

        if (!guardado.isPresent()) {
            throw new Exception("El administrador de teatro no existe.");
        } else {
            administradorTeatroRepo.delete(guardado.get());
        }
    }

    @Override
    public List<AdministradorTeatro> listarAdministradoresTeatro() {
        return administradorTeatroRepo.findAll();
    }

    @Override
    public AdministradorTeatro obtenerAdministradorPorTeatro(Integer codigoTeatro) throws Exception{
        AdministradorTeatro admin = administradorTeatroRepo.obtenerPorTeatro(codigoTeatro);
        if(admin == null) throw new Exception("El administrador buscado no existe.");
        return admin;
    }

    @Override
    public Pelicula obtenerPelicula(Integer codigo) throws Exception {
        Optional<Pelicula> guardada = peliculaRepo.findById(codigo);

        if (!guardada.isPresent()) {
            throw new Exception("La pelicula no existe.");
        } else {
            return guardada.get();
        }
    }

    @Override
    public Pelicula registrarPelicula(Pelicula pelicula) throws Exception {

        if (pelicula.getCodigo() == null) {
            return peliculaRepo.save(pelicula);
        }

        return null;
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {
        Optional<Pelicula> guardada = peliculaRepo.findById(pelicula.getCodigo());

        if (!guardada.isPresent()) {
            throw new Exception("La pelicula no existe.");
        } else {
            return peliculaRepo.save(pelicula);
        }
    }

    @Override
    public void eliminarPelicula(Integer codigo) throws Exception {
        Optional<Pelicula> guardada = peliculaRepo.findById(codigo);

        if (!guardada.isPresent()) {
            throw new Exception("La pelicula no existe.");
        } else {
            peliculaRepo.delete(guardada.get());
        }
    }

    @Override
    public List<Pelicula> listarPeliculas() {
        return peliculaRepo.findAll();
    }

    @Override
    public List<Pelicula> listarPeliculasPorGenero(Genero genero) {
        return peliculaRepo.listarPeliculasPorGenero(genero);
    }

    @Override
    public List<Pelicula> listarPeliculasPorEdadApropiada(Integer edadApropiada) {
        return peliculaRepo.listarPeliculasPorEdadApropiada(edadApropiada);
    }

    @Override
    public List<Pelicula> listarPeliculasPorDirector(String nombreDirector) {
        return peliculaRepo.listarPeliculasPorDirector(nombreDirector);
    }

    @Override
    public List<Pelicula> listarPeliculasPorEstudio(String nombreEstudio) {
        return peliculaRepo.listarPeliculasPorEstudio(nombreEstudio);
    }

    @Override
    public Cupon obtenerCupon(Integer codigo) throws Exception {
        Optional<Cupon> guardado = cuponRepo.findById(codigo);

        if (!guardado.isPresent()) {
            throw new Exception("El cupon no existe.");
        } else {
            return guardado.get();
        }
    }

    @Override
    public Cupon registrarCupon(Cupon cupon) {
        return cuponRepo.save(cupon);
    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {
        Optional<Cupon> guardado = cuponRepo.findById(cupon.getCodigo());

        if (!guardado.isPresent()) {
            throw new Exception("El cupon no existe.");
        } else {
            return cuponRepo.save(cupon);
        }
    }

    @Override
    public void eliminarCupon(Integer codigoCupon) throws Exception {
        Optional<Cupon> guardado = cuponRepo.findById(codigoCupon);

        if (!guardado.isPresent()) {
            throw new Exception("El cupon no existe.");
        } else {
            cuponRepo.delete(guardado.get());
        }
    }

    @Override
    public List<Cupon> listarCupones() {
        return cuponRepo.findAll();
    }

    @Override
    public List<Cupon> listarCuponesPorDescuento(Float descuento) {
        return cuponRepo.listarCuponesPorDescuento(descuento);
    }

    @Override
    public List<ClienteCupon> listarClienteCupones() {
        return clienteCuponRepo.findAll();
    }

    public ClienteCupon obtenerClienteCupon(Integer codigo) {
        return clienteCuponRepo.findById(codigo).orElse(null);
    }

    @Override
    public ProductoConfiteria obtenerProductoConfiteria(Integer codigo) throws Exception {
        Optional<ProductoConfiteria> guardado = productoConfiteriaRepo.findById(codigo);

        if (!guardado.isPresent()) {
            throw new Exception("El producto de confitería no existe.");
        } else {
            return guardado.get();
        }
    }

    @Override
    public ProductoConfiteria registrarProductoConfiteria(ProductoConfiteria productoConfiteria) throws Exception {
        if (productoConfiteria.getCodigo() == null) {
            return productoConfiteriaRepo.save(productoConfiteria);
        }
        return null;
    }

    @Override
    public ProductoConfiteria actualizarProductoConfiteria(ProductoConfiteria productoConfiteria) throws Exception {
        Optional<ProductoConfiteria> guardado = productoConfiteriaRepo.findById(productoConfiteria.getCodigo());

        if (!guardado.isPresent()) {
            throw new Exception("El producto de confiteria no existe.");
        } else {
            return productoConfiteriaRepo.save(productoConfiteria);
        }
    }

    @Override
    public void eliminarProductoConfiteria(Integer codigo) throws Exception {
        Optional<ProductoConfiteria> guardado = productoConfiteriaRepo.findById(codigo);

        if (!guardado.isPresent()) {
            throw new Exception("El producto de confiteria no existe.");
        } else {
            productoConfiteriaRepo.delete(guardado.get());
        }
    }

    @Override
    public List<ProductoConfiteria> listarProductosConfiteria() {
        return productoConfiteriaRepo.findAll();
    }

    @Override
    public List<ProductoConfiteria> listarProductosConfiteriaPorRangoPrecio(Integer precioMinimo, Integer precioMaximo) {
        return productoConfiteriaRepo.listarProductosConfiteriaRangoPrecio(precioMinimo, precioMaximo);
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigo) throws Exception {
        Optional<Ciudad> guardada = ciudadRepo.findById(codigo);

        if (!guardada.isPresent()) {
            throw new Exception("La ciudad no se encuentra registrada.");
        } else {
            return guardada.get();
        }
    }

    @Override
    public Ciudad registrarCiudad(Ciudad ciudad) throws Exception {
        Optional<Ciudad> ciudadExiste = ciudadRepo.findById(ciudad.getCodigo());

        if (ciudadExiste.isPresent()) {
            throw new Exception("El codigo ingresado ya está siendo usado para otra ciudad.");
        } else {
            return ciudadRepo.save(ciudad);
        }
    }

    @Override
    public Ciudad actualizarCiudad(Ciudad ciudad) throws Exception {
        Optional<Ciudad> guardada = ciudadRepo.findById(ciudad.getCodigo());

        if (!guardada.isPresent()) {
            throw new Exception("La ciudad no se encuentra registrada.");
        } else {
            return ciudadRepo.save(ciudad);
        }
    }

    @Override
    public void eliminarCiudad(Integer codigo) throws Exception {
        Optional<Ciudad> guardada = ciudadRepo.findById(codigo);

        if (!guardada.isPresent()) {
            throw new Exception("La ciudad no se encuentra registrada.");
        } else {
            ciudadRepo.delete(guardada.get());
        }
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public List<Funcion> obtenerFuncionesPorTeatroPelicula(Integer codigoPelicula, Integer codigoTeatro) throws Exception {
        return funcionRepo.obtenerFuncionesPorTeatroPelicula(codigoPelicula, codigoTeatro);
    }

}
