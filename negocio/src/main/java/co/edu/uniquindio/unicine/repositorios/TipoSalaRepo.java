package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.TipoSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoSalaRepo extends JpaRepository<TipoSala, Integer> {

}
