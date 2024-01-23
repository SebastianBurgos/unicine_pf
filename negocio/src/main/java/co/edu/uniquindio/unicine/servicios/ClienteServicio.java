package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.time.LocalDate;
import java.util.List;


public interface ClienteServicio {

    Cliente obtenerCliente(String cedula) throws Exception;

    Cliente registrarCliente(Cliente cliente) throws Exception;

    Cliente login(String cedula, String contrasenia) throws Exception;

    List<Pelicula> buscarPelicula(String nombrePelicula) throws Exception;

    Cliente actualizarCliente(Cliente cliente) throws Exception;

    void eliminarCliente(String cedulaCliente) throws Exception;

    List<Cliente> listarClientes() ;

    //TODO corregir el servicio
    List<Compra> listarHistorialCompras(String cedulaCliente) throws Exception;

    Compra realizarCompra(Compra compra) throws Exception;

    void cancelarCompra(Integer idCompra) throws Exception;

    void solicitarCambioContrasenia(String email) throws Exception;

    PQRS realizarPqrs(PQRS solicitudPqrs) throws Exception;

    List<Pelicula> listarPeliculasPorEstadoCiudad(String estado, Integer codigoCiudad) throws Exception;
    List<Pelicula> listarPeliculasPorEstado(String estado) throws Exception;

    Compra obtenerCompra(Integer codigoCompra) throws Exception;

    void activarCuenta(String param1) throws Exception;

    void reestablecerContrasenia(String correo, String contraseniaNueva) throws Exception;

    List<ProductoConfiteria> obtenerProductosConfiteria();

    List<Entrada> obtenerEntradasFuncion(Funcion funcion, LocalDate fechaFuncion) throws Exception;

    void guardarEntrada(Entrada entrada) throws Exception;

    void guardarCompraConfiteria(CompraConfiteria compraConfiteria);
}
