package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.InformacionComprasCliente;
import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.repositorios.CompraRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerEntradas() {
        List<Entrada> entradas = compraRepo.obtenerEntradas(1);
        entradas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComprasConfiteria() {
        List<CompraConfiteria> comprasConfiteria = compraRepo.obtenerComprasConfiteria(1);
        comprasConfiteria.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerDistribucionSillas() {
        String distribucionSillas = compraRepo.obtenerDistribucionSillas(1);
        System.out.println(distribucionSillas);
    }

    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void calcularPrecioCompras() {
        Float totalGastado = compraRepo.calcularPrecioCompras("1");
        Assertions.assertEquals(143800, totalGastado);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompraMasCostosa() {
        List<Object[]> compra = compraRepo.calcularCompraMasCostosa();
        compra.forEach(o ->
                System.out.println(
                        o[0] + ", " + o[1]
                ));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void mostrarInformacionComprasCliente() {
        List<Object[]> informacionComprasClientes = compraRepo.mostrarInformacionCompras("1");
        informacionComprasClientes.forEach(o->
                System.out.println(
                        o[0] + ", " + o[1] + ", " + o[2] + ", " + o[3] + ", " + o[4]
                ));
    }

     */
}
