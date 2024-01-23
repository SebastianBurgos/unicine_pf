package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdministradorPlataformaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class AdministradorPlataformaServicioTest {

    @Autowired
    private AdministradorPlataformaServicio administradorPlataformaServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void comprobarAutenticacion(){

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdministradorTeatroTest() {
        try {
            AdministradorTeatro administradorTeatro = administradorPlataformaServicio.obtenerAdministradorTeatro("1000");
            Assertions.assertNotNull(administradorTeatro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarAdministradorTeatroTest() {
        AdministradorTeatro administradorTeatro = AdministradorTeatro.builder().nombre_completo("Sebastian Burgos")
                .telefono("3012392831").email("sebasb@gmail.com").cedula("1005090418").contrasenia("123123").imagen_perfil("urlasd").build();

        try {
            AdministradorTeatro nuevo = administradorPlataformaServicio.registrarAdministradorTeatro(administradorTeatro);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarAdministradorTeatroTest() {

        AdministradorTeatro administradorTeatro;
        try {
            administradorTeatro = administradorPlataformaServicio.obtenerAdministradorTeatro("1000");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            administradorTeatro.setNombre_completo("Martin Tiringuin");
            AdministradorTeatro nuevo;
            nuevo = administradorPlataformaServicio.actualizarAdministradorTeatro(administradorTeatro);
            Assertions.assertEquals("Martin Tiringuin", nuevo.getNombre_completo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarAdministradorTeatroTest() {
        try {
            administradorPlataformaServicio.eliminarAdministradorTeatro("1000");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradoresTeatroTest() {
        List<AdministradorTeatro> lista = administradorPlataformaServicio.listarAdministradoresTeatro();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdministradorPorTeatro() {
        try {
            AdministradorTeatro administradorTeatro = administradorPlataformaServicio.obtenerAdministradorPorTeatro(3);
            Assertions.assertNotNull(administradorTeatro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPelicula() {
        try {
            Pelicula pelicula = administradorPlataformaServicio.obtenerPelicula(1);
            Assertions.assertNotNull(pelicula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarPelicula() {
        Pelicula pelicula = Pelicula.builder().nombre("Harry Potter y El Prisionero de Askaban")
                .duracion_minutos(180).edad_apropiada(12)
                .nombre_director("Jk Rowling").nombre_estudio("Warner").sinopsis("Un batman pero mago")
                .trailer("urltrailer").build();
        pelicula.setCodigo(10);

        try {
            Pelicula nueva = administradorPlataformaServicio.registrarPelicula(pelicula);
            Assertions.assertNotNull(nueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarPelicula() {

        Pelicula pelicula;
        try {
            pelicula = administradorPlataformaServicio.obtenerPelicula(1);
            pelicula.setNombre("Efecto Mariposa");
            Pelicula nueva;
            nueva = administradorPlataformaServicio.actualizarPelicula(pelicula);
            Assertions.assertEquals("Efecto Mariposa", nueva.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarPeliculaTest() {
        try {
            administradorPlataformaServicio.eliminarPelicula(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculasTest() {
        List<Pelicula> lista = administradorPlataformaServicio.listarPeliculas();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculasPorGeneroTest() {
        Genero genero = Genero.ACCION;
        List<Pelicula> lista = administradorPlataformaServicio.listarPeliculasPorGenero(genero);
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculasPorEdadApropiadaTest() {
        List<Pelicula> lista = administradorPlataformaServicio.listarPeliculasPorEdadApropiada(14);
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculasPorDirectorTest() {
        List<Pelicula> lista = administradorPlataformaServicio.listarPeliculasPorDirector("Pepe Aguilar");
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculasPorEstudioTest() {
        List<Pelicula> lista = administradorPlataformaServicio.listarPeliculasPorEstudio("Disney");
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCupon() {
        try {
            Cupon cupon = administradorPlataformaServicio.obtenerCupon(1);
            Assertions.assertNotNull(cupon);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarCuponTest() {
        Cupon cupon = Cupon.builder().concepto("Descuento por gigachad").descuento(25F).build();

        try {
            Cupon nuevo = administradorPlataformaServicio.registrarCupon(cupon);
            System.out.println(nuevo.getCodigo());
            Assertions.assertNotNull(cupon);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCuponTest() {

        Cupon cupon;
        try {
            cupon = administradorPlataformaServicio.obtenerCupon(1);
            cupon.setConcepto("Descuento por votacion");
            Cupon nuevo;
            nuevo = administradorPlataformaServicio.actualizarCupon(cupon);
            Assertions.assertEquals("Descuento por votacion", nuevo.getConcepto());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCuponTest() {
        try {
            administradorPlataformaServicio.eliminarCupon(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCuponesTest() {
        List<Cupon> lista = administradorPlataformaServicio.listarCupones();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCuponesPorDescuentoTest() {
        List<Cupon> lista = administradorPlataformaServicio.listarCuponesPorDescuento(10F);
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerProductoConfiteriaTest() {
        try {
            ProductoConfiteria productoConfiteria = administradorPlataformaServicio.obtenerProductoConfiteria(1);
            Assertions.assertNotNull(productoConfiteria);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarProductoConfiteriaTest() {
        ProductoConfiteria productoConfiteria = ProductoConfiteria.builder().nombre("Patatas Alibañesas")
                        .precio(240000).extras("Salsa Alibañesa").build();
        productoConfiteria.setCodigo(10);

        try {
            ProductoConfiteria nuevo = administradorPlataformaServicio.registrarProductoConfiteria(productoConfiteria);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarProductoConfiteriaTest() {

        ProductoConfiteria productoConfiteria;
        try {
            productoConfiteria = administradorPlataformaServicio.obtenerProductoConfiteria(1);
            productoConfiteria.setNombre("Fetuccini A La Carbonara");
            ProductoConfiteria nuevo;
            nuevo = administradorPlataformaServicio.actualizarProductoConfiteria(productoConfiteria);
            Assertions.assertEquals("Fetuccini A La Carbonara", nuevo.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarProductoConfiteriaTest() {
        try {
            administradorPlataformaServicio.eliminarProductoConfiteria(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosConfiteriaTest() {
        List<ProductoConfiteria> lista = administradorPlataformaServicio.listarProductosConfiteria();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosConfiteriaPorRangoPrecioTest() {
        List<ProductoConfiteria> lista = administradorPlataformaServicio.listarProductosConfiteriaPorRangoPrecio(12000, 22000);
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCiudadTest() {
        try {
            Ciudad ciudad = administradorPlataformaServicio.obtenerCiudad(1);
            Assertions.assertNotNull(ciudad);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarCiudadTest() {
        Ciudad ciudad = Ciudad.builder().nombre("Tangamandapio").build();
        ciudad.setCodigo(10);

        try {
            Ciudad nuevo = administradorPlataformaServicio.registrarCiudad(ciudad);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCiudadTest() {

        Ciudad ciudad;
        try {
            ciudad = administradorPlataformaServicio.obtenerCiudad(1);
            ciudad.setNombre("Jonia");
            Ciudad nuevo;
            nuevo = administradorPlataformaServicio.actualizarCiudad(ciudad);
            Assertions.assertEquals("Jonia", nuevo.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCiudadTest() {
        try {
            administradorPlataformaServicio.eliminarCiudad(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCiudadTest() {
        List<Ciudad> lista = administradorPlataformaServicio.listarCiudades();
        lista.forEach(System.out::println);
    }
}
