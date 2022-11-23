package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.DistribucionSillas;
import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionRepo extends JpaRepository<Funcion, Integer> {

    @Query("select f.pelicula.nombre from Funcion f where f.codigo=:codigoFuncion")
    String obtenerNombrePeliculaPorID(Integer codigoFuncion);

    @Query("select f from Funcion f join f.sala.teatro t where t.codigo=:codigoTeatro and f.compras is empty")
    List<Funcion> funcionesSinCompras(Integer codigoTeatro);
    
    @Query("select f from Funcion f where f = :funcion")
    Funcion verificarFuncion(Funcion funcion);

    @Query("select ds from Funcion f join f.sala.distribucionSillas ds where f.codigo = :codigoFuncion")
    DistribucionSillas obtenerDistribucionSillas(Integer codigoFuncion);
    
    @Query("select f from Funcion f where f.horario=:codigoHorario")
    List<Funcion> obtenerFuncionesPorHorario(Integer codigoHorario);

   @Query("select f from Funcion f where f.codigo=:codigoFuncion")
    Funcion obtenerFuncionCodigo(Integer codigoFuncion);

   @Query("select f from Funcion f join f.sala s where s.codigo=:codigoSala")
   List<Funcion> obtenerFuncionCodigoSala(Integer codigoSala);

   @Query("select f.sala from Funcion f where f.codigo=:codigoFuncion")
   Sala obtenerSalaFuncion(Integer codigoFuncion);

   @Query("select f.precio from Funcion f where f.codigo=:codigoFuncion")
   Integer obtenerPrecionFuncion(Integer codigoFuncion);
   @Query("select e from Compra c join c.entradas e where c.funcion.codigo = :codigoFuncion and c.fechaFuncion = :fechaFuncion")
   List<Entrada> obtenerEntradasFuncion(Integer codigoFuncion, LocalDate fechaFuncion);

   @Query("select f from Funcion f where f.pelicula.codigo = :codigoPelicula and f.sala.teatro.codigo = :codigoTeatro")
   List<Funcion> obtenerFuncionesPorTeatroPelicula(Integer codigoPelicula, Integer codigoTeatro);
}
