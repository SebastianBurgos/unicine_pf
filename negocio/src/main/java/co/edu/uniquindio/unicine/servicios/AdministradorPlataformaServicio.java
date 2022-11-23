package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface AdministradorPlataformaServicio {

    AdministradorPlataforma login(String email, String contrasenia);

    // Gestion de Administradores de Teatro

    AdministradorTeatro obtenerAdministradorTeatro(String cedulaAdministradorTeatro) throws Exception;

    AdministradorTeatro registrarAdministradorTeatro(AdministradorTeatro administradorTeatro) throws Exception;

    AdministradorTeatro actualizarAdministradorTeatro(AdministradorTeatro administradorTeatro) throws Exception;

    void eliminarAdministradorTeatro(String cedulaAdministradorTeatro) throws Exception;

    List<AdministradorTeatro> listarAdministradoresTeatro();

    AdministradorTeatro obtenerAdministradorPorTeatro(Integer codigoTeatro) throws Exception;

    // Gestion de Peliculas

    Pelicula obtenerPelicula(Integer codigo) throws Exception;

    Pelicula registrarPelicula(Pelicula pelicula) throws Exception;

    Pelicula actualizarPelicula(Pelicula pelicula) throws Exception;

    void eliminarPelicula(Integer codigo) throws Exception;

    List<Pelicula> listarPeliculas();

    List<Pelicula> listarPeliculasPorGenero(Genero genero);

    List<Pelicula> listarPeliculasPorEdadApropiada(Integer edadApropiada);

    List<Pelicula> listarPeliculasPorDirector(String nombreDirector);

    List<Pelicula> listarPeliculasPorEstudio(String nombreEstudio);

    // Gestion de cupones

    Cupon obtenerCupon(Integer codigo) throws Exception;

    Cupon registrarCupon(Cupon cupon);

    Cupon actualizarCupon(Cupon cupon) throws Exception;

    void eliminarCupon(Integer codigo) throws Exception;

    List<Cupon> listarCupones();

    List<Cupon> listarCuponesPorDescuento(Float descuento);

    ClienteCupon asignarCupon(ClienteCupon clienteCupon);

    List<ClienteCupon> listarClienteCupones();

    ClienteCupon obtenerClienteCupon(Integer codigo);

    // Gestion de Productos de Confiteria

    ProductoConfiteria obtenerProductoConfiteria(Integer codigo) throws Exception;

    ProductoConfiteria registrarProductoConfiteria(ProductoConfiteria productoConfiteria) throws Exception;

    ProductoConfiteria actualizarProductoConfiteria(ProductoConfiteria productoConfiteria) throws Exception;

    void eliminarProductoConfiteria(Integer codigo) throws Exception;

    List<ProductoConfiteria> listarProductosConfiteria();

    List<ProductoConfiteria> listarProductosConfiteriaPorRangoPrecio(Integer precioMinimo, Integer precioMaximo);

    // Gestion de Ciudades

    Ciudad obtenerCiudad(Integer codigo) throws Exception;

    Ciudad registrarCiudad(Ciudad ciudad) throws Exception;

    Ciudad actualizarCiudad(Ciudad ciudad) throws Exception;

    void eliminarCiudad(Integer codigo) throws Exception;

    List<Ciudad> listarCiudades();

    List<Funcion> obtenerFuncionesPorTeatroPelicula(Integer codigoPe, Integer codigoTeatro) throws Exception;
}
