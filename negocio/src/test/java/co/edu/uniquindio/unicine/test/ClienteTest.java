package co.edu.uniquindio.unicine.test;

import ch.qos.logback.core.CoreConstants;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.repositorios.ClienteRepo;
import co.edu.uniquindio.unicine.servicios.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {

        ArrayList<String> telefonos = new ArrayList<>();
        telefonos.add("3127588409");

        Cliente cliente = new Cliente("1004916493", "Juan Pablo Loaiza Nieto", 20,
                "direcccion casa calle", "jloaizanieto@gmail.com", telefonos, "url imagen", "204060120Gato");
        Cliente guardado = clienteRepo.save(cliente);

        //Assertions.assertEquals("Juan Pablo Loaiza Nieto", guardado.getNombre_completo());
        Assertions.assertNotNull(guardado);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar() {
        Cliente buscado = clienteRepo.findById("5").orElse(null);
        if (buscado != null) {
            clienteRepo.delete(buscado);
        }
        Assertions.assertNull(clienteRepo.findById("5").orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar() {
        Cliente guardado = clienteRepo.findById("5").orElse(null);

        if (guardado != null) {
            guardado.setEmail("juanpepe@gmail.com");
            Cliente nuevo = clienteRepo.save(guardado);
            Assertions.assertEquals("juanpepe@gmail.com", nuevo.getEmail());
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener() {
        Optional<Cliente> buscado = clienteRepo.findById("4");
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPorCorreo() {
        Cliente buscado = clienteRepo.obtenerPorCorreo("juanpepe@gnlkk.com");
        Assertions.assertNotNull(buscado);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void comprobarAutenticacion() {
        Cliente buscado = clienteRepo.comprobarAutenticacion("1", "pepe1");
        Assertions.assertNotNull(buscado);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCuponesCliente() {
        List<Cupon> cupones = clienteRepo.obtenerCuponesCliente("1");
        Assertions.assertEquals(2, cupones.size());
    }



    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void paginador() {
        List<Cliente> clientes = clienteRepo.findAll(PageRequest.of(0, 2)).toList();
        clientes.forEach(System.out::println);
    }
     */

    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void paginadorEstado() {
        List<Cliente> clientes = clienteRepo.obtenerClientesPorEstado("PRUEBA", PageRequest.of(0, 3) );
        clientes.forEach(System.out::println);
    }
     */

    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void ordenarRegistros() {
        List<Cliente> clientes = clienteRepo.findAll(PageRequest.of(0, 3, Sort.by("nombreCompleto").ascending() ) ).toList();
        clientes.forEach(System.out::println);
    }
     */

    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComprasPorEmailCliente() {
        List<Compra> compras = clienteRepo.obtenerComprasPorCorreoCliente("juanpepe@gnlkk.com");
        compras.forEach(System.out::println);
    }
    */

    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerNumeroCuponesRedimidosPorCadaCliente() {

        List<Object[]> cuponesRedimidos = clienteRepo.obtenerNumeroCuponeRedimidosPorCadaCliente();
        cuponesRedimidos.forEach(o ->
                System.out.println(o[0] + ", " + o[1])
        );
    }
     */
}
