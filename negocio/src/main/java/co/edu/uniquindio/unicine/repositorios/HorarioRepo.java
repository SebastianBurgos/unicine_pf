package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HorarioRepo extends JpaRepository<Horario, Integer> {

    Optional<Horario> findByCodigo(Integer codigo);
}
