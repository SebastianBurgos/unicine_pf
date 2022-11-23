package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeatroRepo extends JpaRepository<Teatro, Integer> {
    @Query("select t from Teatro t where t.ciudad.codigo = :codigoCiudad")
    List<Teatro> listarTeatrosCiudad(Integer codigoCiudad);
}
