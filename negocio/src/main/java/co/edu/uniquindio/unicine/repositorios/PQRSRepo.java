package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.PQRS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PQRSRepo extends JpaRepository<PQRS, Integer> {

    @Query("select pq from PQRS pq where pq.motivo=:motivo")
    List<PQRS> obtenerPQRSMotivo(String motivo);
    @Query("select pq from PQRS pq where pq.fecha=:fecha")
    List<PQRS> obtenerPQRSFecha(LocalDateTime fecha);
    @Query("select pq from PQRS pq where pq.cliente.cedula=:cedula")
    List<PQRS> obtenerPQRSCedulaCliente(String cedula);





}
