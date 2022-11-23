package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface AdministradorTeatroRepo extends JpaRepository<AdministradorTeatro, String> {


    @Query("select at from AdministradorTeatro at where at.email = :correo")
    AdministradorTeatro obtenerPorCorreo(String correo);

    @Query("select at from Teatro t, in (t.administradorTeatro) at where t.codigo = :codigo")
    AdministradorTeatro obtenerPorTeatro(Integer codigo);

    @Query("select a from AdministradorTeatro  a where a.email = :email and a.contrasenia=:contrasenia")
    AdministradorTeatro comprobarAutenticacion(String email, String contrasenia);

}
