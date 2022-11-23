package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.ClienteCupon;
import co.edu.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuponRepo extends JpaRepository<Cupon, Integer> {


    @Query("select c from Cupon c where c.descuento = :descuento")
    List<Cupon> listarCuponesPorDescuento(Float descuento);

    @Query("select cup from ClienteCupon cup where cup.codigo = :codigoClienteCupon")
    ClienteCupon obtenerClienteCupon(Integer codigoClienteCupon);
}
