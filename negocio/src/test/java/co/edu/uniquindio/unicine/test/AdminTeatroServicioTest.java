package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@Transactional
public class AdminTeatroServicioTest {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void login(){
        try {
            AdministradorTeatro admin = adminTeatroServicio.login("jloasiisi@email.com","1234");
            Assertions.assertNotNull(admin);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarFuncionTest() {
        Sala     s1      = null;
        Horario  h1      = null;
        Pelicula p1      = null;
        try {
            s1 = adminTeatroServicio.buscarSalaCodigo(1);
            h1 = adminTeatroServicio.buscarHorarioCodigo(1);
            p1 = adminTeatroServicio.obtenerPeliculaCodigo(1);

        }catch (Exception e){
            e.printStackTrace();
        }
        Funcion funcion = Funcion.builder().precio(9).sala(s1).pelicula(p1).horario(h1).build();
        try {
            Funcion nueva = adminTeatroServicio.registrarFuncion(funcion);
            Assertions.assertNotNull(nueva);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarFuncionTest(){
        try {
            Funcion f1 = adminTeatroServicio.buscarFuncionCodigo(1);
            f1.setPrecio(200);
            adminTeatroServicio.actualizarFuncion(f1);
            Assertions.assertEquals(200,f1.getPrecio());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFuncionTest(){
        try{
            adminTeatroServicio.elimiarFuncion(1);
        }catch (Exception e){
            Assertions.assertTrue(false);
        }

        try{
            Funcion funcion = adminTeatroServicio.buscarFuncionCodigo(1);
        }catch (Exception e){
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarFuncionCodigoTest(){
        try{
            Funcion funcion = adminTeatroServicio.buscarFuncionCodigo(1);
            Assertions.assertNotNull(funcion);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void precioFuncionTest(){
        try{
            Integer precioFuncion = adminTeatroServicio.precioFuncion(1);
            Assertions.assertNotNull(precioFuncion);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarFuncionesHorarioTest(){
        try{
            List<Funcion> funcion = adminTeatroServicio.buscarFuncionPorHorario(1);
            Assertions.assertNotNull(funcion);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionTest(){
        List<Funcion> lista = adminTeatroServicio.listarFunciones();
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarSalaTest(){
        Sala sala = Sala.builder().numero(10).build();
        try {
            Sala nueva = adminTeatroServicio.registrarSala(sala);
            Assertions.assertNotNull(nueva);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSalaTest(){
        try {
            Sala s1 = adminTeatroServicio.buscarSalaCodigo(1);
            s1.setNumero(15);
            adminTeatroServicio.actualizarSala(s1);
            Assertions.assertEquals(15,s1.getTeatro());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSalaTest(){
        try{
            adminTeatroServicio.eliminarSala(1);
        }catch (Exception e){
            Assertions.assertTrue(false);
        }

        try{
            Sala s1 = adminTeatroServicio.buscarSalaCodigo(1);
        }catch (Exception e){
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarSalaTest(){
        try{
            Sala sala = adminTeatroServicio.buscarSalaCodigo(1);
            Assertions.assertNotNull(sala);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalaTest(){
        List<Sala> lista = adminTeatroServicio.listarSalas();
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPQRSTest(){
        List<PQRS> lista = adminTeatroServicio.listarPQRS();
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPQRSMotivoTest(){
        try {
            List<PQRS> lista = adminTeatroServicio.listarPQRSMotivo("Otros");
            lista.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPQRSFechaTest(){
        LocalDateTime fecha = LocalDateTime.now();
        try {
            List<PQRS> lista = adminTeatroServicio.listarPQRSFecha(fecha);
            lista.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPQRSCedulaClienteTest(){
        try {
            List<PQRS> lista = adminTeatroServicio.listarPQRSCliente("3");
            lista.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    @Sql("classpath:dataset.sql")
    public void crearHorarioTest(){
        Horario horario = Horario.builder().dia("31").build();
        try {
            Horario nueva = adminTeatroServicio.crearHorario(horario);
            Assertions.assertNotNull(nueva);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarHorarioTest(){
        try {
            Horario h1 = adminTeatroServicio.buscarHorarioCodigo(1);
            h1.setDia("29");
            adminTeatroServicio.actualizarHorario(h1);
            Assertions.assertEquals(29,h1.getDia());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHorarioTest(){
        try{
            adminTeatroServicio.eliminarHorario(1);
        }catch (Exception e){
            Assertions.assertTrue(false);
        }

        try{
            Horario h1 = adminTeatroServicio.buscarHorarioCodigo(1);
        }catch (Exception e){
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarHorarioTest(){
        try{
            Horario h1= adminTeatroServicio.buscarHorarioCodigo(1);
            Assertions.assertNotNull(h1);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHorarioTest(){
        List<Horario> lista = adminTeatroServicio.listarHorario();
        lista.forEach(System.out::println);

    }

}
