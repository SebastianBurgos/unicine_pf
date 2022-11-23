package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.ClienteCupon;
import co.edu.uniquindio.unicine.entidades.Cupon;

public interface CuponServicio {
    Cupon obtenerCupon(Integer codigo) throws Exception;
    ClienteCupon obtenerClienteCupon(Integer codigoClienteCupon) throws Exception;
}
