package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.ClienteCupon;
import co.edu.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteCuponRepo extends JpaRepository<ClienteCupon, Integer> {

    @Query("select c.clienteCupon from Compra c where c.codigo = :codigoCompra")
    ClienteCupon obtenerClienteCupon(Integer codigoCompra);

    @Query("select c from Cupon c where c.codigo = :codigo")
    Cupon obtenerCupon(Integer codigo);
}
