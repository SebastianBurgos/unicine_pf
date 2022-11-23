package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.ProductoConfiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoConfiteriaRepo extends JpaRepository<ProductoConfiteria, Integer> {
    @Query("select pc from ProductoConfiteria pc where pc.precio >= :precioMinimo and pc.precio <= :precioMaximo")
    List<ProductoConfiteria> listarProductosConfiteriaRangoPrecio(Integer precioMinimo, Integer precioMaximo);
}
