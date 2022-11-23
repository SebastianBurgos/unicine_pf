package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private FuncionServicio funcionServicio;

    @Autowired
    private EntradaServicio entradaServicio;

    @Autowired
    private CuponServicio cuponServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private EmailService emailService;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarClienteTest() {
        Cliente cliente = Cliente.builder().nombre_completo("Juanito").contrasenia("1234").email("juanp.loaizan@uqvirtual.edu.co")
                .cedula("1004916494").edad(20).direccion("Casa 1").imagen_perfil("url_imagen").build();

        try {
            Cliente nuevo = clienteServicio.registrarCliente(cliente);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void login() {
        try {
            Cliente cliente = clienteServicio.login("1", "pepe1");
            Assertions.assertNotNull(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculas() {
        try {
            List<Pelicula> lista = clienteServicio.buscarPelicula("Las Aventuras");
            Assertions.assertEquals(1, lista.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void realizarCompra() {

        Compra compra;
        try {
            compra = compraServicio.obtenerCompra(9);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Compra nuevaCompra = clienteServicio.realizarCompra(compra);
            System.out.println(nuevaCompra);
            Assertions.assertNotNull(nuevaCompra);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cancelarCompra() {
        Compra compra;
        try {
            compra = compraServicio.obtenerCompra(9);
            clienteServicio.cancelarCompra(9);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerHistorialCompras() {
        Cliente cliente = null;
        try {
            cliente = clienteServicio.obtenerCliente("1");
            System.out.println(clienteServicio.listarHistorialCompras("1"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void solicitarCambioContrasenia() {

        try {
            clienteServicio.solicitarCambioContrasenia("juanp.loaizan@uqvirtual.edu.co");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarClienteTest() {

        Cliente cliente = null;
        try {
            cliente = clienteServicio.obtenerCliente("1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            cliente.setNombreCompleto("Nombre largo de prueba");
            Cliente nuevo = clienteServicio.actualizarCliente(cliente);
            Assertions.assertEquals("Nombre largo de prueba", nuevo.getNombreCompleto());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarClienteTest() {
        try {
            clienteServicio.eliminarCliente("1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void realizarPQRSTest() {
        try {
            Cliente cliente = clienteServicio.obtenerCliente("1");
            PQRS pqrs = PQRS.builder().cliente(cliente).mensaje("Mensaje de prueba").motivo("Motivo de pureba").build();

            PQRS pqrs1 = clienteServicio.realizarPqrs(pqrs);
            System.out.println(pqrs1);
            Assertions.assertNotNull(pqrs1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarClientesTest() {
        List<Cliente> lista = clienteServicio.listarClientes();
        lista.forEach(System.out::println);
    }

    @Test
    public void enviarCorreoTest() {
        emailService.enviarEmail("Prueba de envío", "Este es un mensaje de prueba", "juanp.loaizan@uqvirtual.edu.co");
    }





}