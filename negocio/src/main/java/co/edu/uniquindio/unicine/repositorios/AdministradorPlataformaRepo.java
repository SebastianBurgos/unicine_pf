package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.AdministradorPlataforma;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministradorPlataformaRepo extends JpaRepository<AdministradorPlataforma, String> {

    @Query("select ap from AdministradorPlataforma ap where ap.email = :email and ap.contrasenia = :contrasenia")
    AdministradorPlataforma comprobarAutenticacion(String email, String contrasenia);

}
