package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.CompraConfiteria;

public interface CompraServicio {

    Compra obtenerCompra(Integer idCompra) throws Exception;

    CompraConfiteria obtenerCompraConfiteria(Integer codigoCompraConfiteria) throws Exception;
}
