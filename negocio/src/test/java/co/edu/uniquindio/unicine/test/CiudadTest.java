package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repositorios.CiudadRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest{

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatrosPorNombreCiudad() {
        List<Teatro> teatros = ciudadRepo.teatrosPorNombreCiudad("Armenia");
        teatros.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void contarTeatrosPorCiudad() {
        List<Object[]> teatrosCiudad = ciudadRepo.contarNumeroTeatrosPorCiudad();
        teatrosCiudad.forEach(o ->
                System.out.println(
                        o[0] + ", " + o[1]
                ));
    }


}
