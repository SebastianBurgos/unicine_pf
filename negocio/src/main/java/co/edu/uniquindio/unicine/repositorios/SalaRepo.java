package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.entidades.TipoSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaRepo extends JpaRepository<Sala, Integer> {

  /*  @Query("select s from Sala s where s.tipoSala=:tiposala")
    List<Sala> obtenerSalasPortipo(TipoSala tipoSala);*/

    @Query("select s from Sala s where s.distribucionSillas=:distribucion")
    List<Sala> obtenerSalasPorDistribucion(Integer distribucion);

    @Query("select s from Sala s where s.codigo=:codigoSala")
    Sala obtenerSalaPorCodigo(Integer codigoSala);

    @Query("select s.funciones from Sala s where s.codigo=:codigoSala")
    List<Funcion> obtenerFuncionesSala(Integer codigoSala);
    Optional<Sala> findByCodigo(Integer integer);

    @Query("select s from Sala s where s.teatro.codigo=:codigoTeatro")
    List<Sala> obtenerSalasPorTeatro(Integer codigoTeatro);
}
