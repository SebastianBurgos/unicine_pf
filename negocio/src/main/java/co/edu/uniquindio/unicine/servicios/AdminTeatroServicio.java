package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminTeatroServicio {

    AdministradorTeatro login(String email, String contrasenia);

    //Servicios sobre las "Funciones"
    Funcion registrarFuncion(Funcion funcion) throws Exception;

    Funcion actualizarFuncion(Funcion funcion) throws Exception;

    void elimiarFuncion(Integer codigoFuncion) throws Exception;

    Funcion buscarFuncionCodigo(Integer codigoFuncion) throws Exception;

    List<Funcion> buscarFuncionPorSala(Integer codigoSala) throws Exception;

    Integer precioFuncion(Integer codigoFuncion) throws Exception;

    List<Funcion> buscarFuncionPorHorario(Integer codigoHorario) throws Exception;

    List<Funcion> listarFunciones();

    //Servicios sobre las "Salas"
    Sala registrarSala(Sala sala) throws Exception;

    Sala buscarSalaCodigo(Integer codigoSala) throws Exception;

    List<Sala> listarSalaTeatro(Integer codigoTeatro);

    Sala actualizarSala(Sala sala) throws Exception;

    void eliminarSala(Integer codigoSala) throws Exception;

    List<Sala> listarSalas();

    //List<Sala> listarSalaPorTipo(TipoSala tipoSala);

    //Servicios sobre los "horarios"
    Horario crearHorario(Horario horario) throws Exception;

    Horario actualizarHorario(Horario horario) throws Exception;

    void eliminarHorario(Integer horario) throws Exception;

    Horario buscarHorarioCodigo(Integer horario) throws Exception;

    List<Horario> listarHorario();

    //Servicios sobre las "PQRS"
    List<PQRS> listarPQRS();

    List<PQRS> listarPQRSMotivo(String motivo) throws Exception;

    List<PQRS> listarPQRSFecha(LocalDateTime fecha) throws Exception;

    List<PQRS> listarPQRSCliente(String  cedulaCliente) throws Exception;

    //Servicios sobre las "Peliculas"
    Pelicula obtenerPeliculaCodigo(Integer codigoPelicula) throws Exception;

    List<Teatro> listarTeatros();


    void eliminarTeatro(Integer codigo) throws Exception;

    Teatro crearTeatro(Teatro teatro) throws Exception;

    void actualizarTeatro(Teatro teatro) throws Exception;

    List<Teatro> listarTeatrosCiudad(Integer codigo) throws Exception;

    Teatro obtenerTeatro(int parseInt) throws Exception;

    DistribucionSillas buscarDistribucionSillas(Integer codigo);

    List<DistribucionSillas> listarDistribucionesSillas();

    TipoSala buscarTipoSala(Integer codigo);

    List<TipoSala> listarTiposSala();
}
