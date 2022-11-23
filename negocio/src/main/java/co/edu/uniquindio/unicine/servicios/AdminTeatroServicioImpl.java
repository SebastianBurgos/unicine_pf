package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
public class AdminTeatroServicioImpl implements AdminTeatroServicio{

    private final SalaRepo salaRepo;
    private final FuncionRepo funcionRepo;
    private final AdministradorTeatroRepo admiTeatroRepo;
    private final PQRSRepo PQRSRepo;
    private final HorarioRepo horarioRepo;

    private final PeliculaRepo peliculaRepo;

    private final TeatroRepo teatroRepo;

    private final DistribucionSillasRepo distribucionSillasRepo;

    private final TipoSalaRepo tipoSalaRepo;

    public AdminTeatroServicioImpl(SalaRepo salaRepo, FuncionRepo funcionRepo,
                                   AdministradorTeatroRepo admiTeatroRepo, PQRSRepo PQRSRepo,
                                   HorarioRepo horarioRepo, PeliculaRepo peliculaRepo,
                                   TeatroRepo teatroRepo, DistribucionSillasRepo distribucionSillasRepo,
                                   TipoSalaRepo tipoSalaRepo) {
        this.salaRepo = salaRepo;
        this.funcionRepo = funcionRepo;
        this.admiTeatroRepo = admiTeatroRepo;
        this.PQRSRepo = PQRSRepo;
        this.horarioRepo = horarioRepo;
        this.peliculaRepo = peliculaRepo;
        this.teatroRepo = teatroRepo;
        this.distribucionSillasRepo = distribucionSillasRepo;
        this.tipoSalaRepo = tipoSalaRepo;
    }

    @Override
    public AdministradorTeatro login(String email, String contrasenia){
        AdministradorTeatro admin = admiTeatroRepo.comprobarAutenticacion(email, contrasenia);
        if(admin == null){
            return null;
        }
        return admin;
    }

    //Servicios funcion
    @Override
    public Funcion registrarFuncion(Funcion funcion)throws Exception {

        if(!verificarOcupacionSala(funcion.getSala(), funcion.getHorario())){
            throw new Exception("La sala esta ocupada en el horario de esta funcio");
        }
        if(!verificarCodigoHorario(funcion.getHorario().getCodigo())){
            throw new Exception("El horario no existe");
        }
        if(!verificarPelicula(funcion.getPelicula().getCodigo())){
            throw new Exception("La pelicula no existe");
        }
        return funcionRepo.save(funcion);
    }

    private boolean verificarPelicula(Integer codigo) {
        return peliculaRepo.findById(codigo).orElse(null)!=null;
    }

    private boolean verificarOcupacionSala(Sala sala,Horario horario) {
        if(sala==null){
            return false;
        }
        for (Funcion f: sala.getFunciones()) {
            if(f.getHorario().equals(horario)){
                return false;
            }
        }
        return true;

    }

    private boolean verificarCodigoFuncion(Integer codigo) {
       return funcionRepo.findById(codigo).orElse(null) != null;
    }

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws Exception {
        Optional<Funcion> guardado = funcionRepo.findById(funcion.getCodigo());
        if(!guardado.isPresent()){
            throw new Exception("la funcion no existe");
        }
        return funcionRepo.save(funcion);
    }

    @Override
    public void elimiarFuncion(Integer codigoFuncion) throws Exception{
        Optional<Funcion> guardado = funcionRepo.findById(codigoFuncion);
        if(!guardado.isPresent()){
            throw new Exception("la funcion no existe");
        }
        funcionRepo.delete(guardado.get());
    }

    @Override
    public Funcion buscarFuncionCodigo(Integer codigoFuncion)throws Exception {
        Funcion funcion = funcionRepo.obtenerFuncionCodigo(codigoFuncion);
        if(funcion==null){
            throw new Exception("Funcion no encontrada, codigo invalido");
        }
        return funcion;

    }

    @Override
    public  List<Funcion> buscarFuncionPorSala(Integer codigoSala)throws Exception {
        boolean existe = verificarCodigoSala(codigoSala);
        if(existe){
            List<Funcion> funcion = funcionRepo.obtenerFuncionCodigoSala(codigoSala);
            if (funcion!=null){
                return funcion;
            }else{
                throw new Exception("La sala no tiene asociada ninguna función");
            }
        }
        throw new Exception("No existe sala asociada a dicho codigo");
    }

    @Override
    public Integer precioFuncion(Integer codigoFuncion) throws Exception {
        boolean existe = verificarCodigoFuncion(codigoFuncion);
        if (existe){
            return funcionRepo.obtenerPrecionFuncion(codigoFuncion);
        }
        throw new Exception("No existe funcion con este codigo");
    }

    @Override
    public List<Funcion> buscarFuncionPorHorario(Integer codigohorario)throws Exception {
        boolean existe = verificarCodigoHorario(codigohorario);
        if(existe){
            List<Funcion> funcion = funcionRepo.obtenerFuncionesPorHorario(codigohorario);
            if (funcion!=null){
                return funcion;
            }else{
                throw new Exception("No hay funciones asociadas a este horario");
            }
        }
        throw new Exception("No existe un horario asociado a dicho codigo");

    }

    private boolean verificarCodigoHorario(Integer codigohorario) {
        return horarioRepo.findByCodigo(codigohorario).orElse(null)!=null;
    }

    @Override
    public List<Funcion> listarFunciones() {
        return funcionRepo.findAll();
    }

    //Servicios Sala
    private boolean verificarCodigoSala(Integer codigoSala) {
        return salaRepo.findByCodigo(codigoSala).orElse(null)!=null;
    }

    @Override
    public Sala registrarSala(Sala sala)throws Exception {
        boolean existe = verificarCodigoSala(sala.getCodigo());
        if(existe){
            throw new Exception("Ya existe esta sala");
        }
        return salaRepo.save(sala);
    }

    @Override
    public Sala buscarSalaCodigo(Integer codigoSala) throws Exception {
        Sala s1 = salaRepo.obtenerSalaPorCodigo(codigoSala);
        if(s1!=null){
            return s1;
        }
        throw new Exception("Sala no existe");
    }

    @Override
    public List<Sala> listarSalaTeatro(Integer codigoTeatro) {
        return salaRepo.obtenerSalasPorTeatro(codigoTeatro);
    }

    @Override
    public Sala actualizarSala(Sala sala)throws Exception {
        Optional<Sala> guardado = salaRepo.findByCodigo(sala.getCodigo());
        if(!guardado.isPresent()){
            throw new Exception("La sala no existe");
        }
        return salaRepo.save(sala);
    }

    @Override
    public void eliminarSala(Integer codigoSala)throws Exception {
        Optional<Sala> guardado = salaRepo.findByCodigo(codigoSala);
        if(!guardado.isPresent()){
            throw new Exception("La sala no existe");
        }
        salaRepo.delete(guardado.get());

    }

    @Override
    public List<Sala> listarSalas() {
        return salaRepo.findAll();
    }

    //Servicios horarios
    @Override
    public Horario crearHorario(Horario horario)throws Exception{
        boolean existencia = verificarCodigoHorario(horario.getCodigo());

        if(existencia){
            throw new Exception("El horario ya existe con este código");
        }
        return horarioRepo.save(horario);
    }

    @Override
    public Horario actualizarHorario(Horario horario) throws Exception {
        Optional<Horario> guardado = horarioRepo.findByCodigo(horario.getCodigo());
        if(!guardado.isPresent()){
            throw new Exception("la funcion no existe");
        }
        return horarioRepo.save(horario);
    }

    @Override
    public void eliminarHorario(Integer horario)throws Exception {
        Optional<Horario> guardado = horarioRepo.findByCodigo(horario);
        if(!guardado.isPresent()){
            throw new Exception("la funcion no existe");
        }
        horarioRepo.delete(guardado.get());
    }

    @Override
    public Horario buscarHorarioCodigo(Integer horario) throws Exception {
        Optional<Horario> h1 = horarioRepo.findByCodigo(horario);
        if(!h1.isPresent()){
            throw new Exception("Sala no existe");
        }
        return h1.get();
    }

    @Override
    public List<Horario> listarHorario() {
        return horarioRepo.findAll();
    }

    //Servicios PQRS
    @Override
    public List<PQRS> listarPQRS() {
        return PQRSRepo.findAll();
    }

    @Override
    public List<PQRS> listarPQRSMotivo(String motivo) throws Exception{
        List<PQRS> pqrsList = PQRSRepo.obtenerPQRSMotivo(motivo);
        if (pqrsList.isEmpty()){
            throw new Exception("No existen PQRS con este motivo");
        }
        return pqrsList;
    }

    @Override
    public List<PQRS> listarPQRSFecha(LocalDateTime fecha) throws Exception {
        List<PQRS> pqrsList = PQRSRepo.obtenerPQRSFecha(fecha);
        if (pqrsList.isEmpty()){
            throw new Exception("No existen PQRS con esta fecha");
        }
        return pqrsList;
    }

    @Override
    public List<PQRS> listarPQRSCliente(String cedulaCliente) throws Exception {
        List<PQRS> pqrsList = PQRSRepo.obtenerPQRSCedulaCliente(cedulaCliente);
        if (pqrsList.isEmpty()){
            throw new Exception("No existen PQRS con esta cedula");
        }
        return pqrsList;
    }

    @Override
    public Pelicula obtenerPeliculaCodigo(Integer codigoPelicula) throws Exception {
        Optional<Pelicula> guardado = peliculaRepo.findById(codigoPelicula);
        if(!guardado.isPresent()){
            throw new Exception("La pelicula no existe");
        }
        return guardado.get();
    }

    @Override
    public List<Teatro> listarTeatros() {
        return teatroRepo.findAll();
    }

    @Override
    public void eliminarTeatro(Integer codigo) throws Exception {
        Optional<Teatro> teatro = teatroRepo.findById(codigo);
        if(!teatro.isPresent()){
            throw new Exception("El teatro no existe");
        }
        teatroRepo.delete(teatro.get());
    }

    @Override
    public Teatro crearTeatro(Teatro teatro) throws Exception {

        if (teatro.getCodigo() == null){
            return teatroRepo.save(teatro);
        }
        Optional<Teatro> teatro1 = teatroRepo.findById(teatro.getCodigo());
        if (!teatro1.isPresent()) {
            return teatroRepo.save(teatro);
        } else {
            throw new Exception("Ya existe un teatro con este código");
        }
    }

    @Override
    public void actualizarTeatro(Teatro teatro) throws Exception {
        Optional<Teatro> guardado = teatroRepo.findById(teatro.getCodigo());
        if (!guardado.isPresent()) {
            throw new Exception("El teatro no existe");
        }
        teatroRepo.save(teatro);
    }

    @Override
    public List<Teatro> listarTeatrosCiudad(Integer codigoCiudad) throws Exception {
        return teatroRepo.listarTeatrosCiudad(codigoCiudad);
    }

    @Override
    public Teatro obtenerTeatro(int codigoTeatro) throws Exception {
        return teatroRepo.findById(codigoTeatro).orElse(null);
    }

    @Override
    public DistribucionSillas buscarDistribucionSillas(Integer codigo) {
        return distribucionSillasRepo.findById(codigo).orElse(null);
    }

    @Override
    public List<DistribucionSillas> listarDistribucionesSillas() {
        return distribucionSillasRepo.findAll();
    }

    @Override
    public TipoSala buscarTipoSala(Integer codigo) {
        return tipoSalaRepo.findById(codigo).orElse(null);
    }

    @Override
    public List<TipoSala> listarTiposSala() {
        return tipoSalaRepo.findAll();
    }


}
