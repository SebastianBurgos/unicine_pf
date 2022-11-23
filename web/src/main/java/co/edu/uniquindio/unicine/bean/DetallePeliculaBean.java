package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorPlataformaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetallePeliculaBean implements Serializable {

    @Getter
    @Setter
    @Value("#{param['pelicula_id']}")
    private String peliculaCodigo;

    @Getter
    @Setter
    private Pelicula pelicula;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private List<Funcion> funciones;

    @Getter
    @Setter
    private List<Funcion> funcionesFiltradas;

    @Getter
    @Setter
    @Value(value = "#{seguridadBean.ciudad}")
    private Ciudad ciudad;

    @Getter
    @Setter
    @Value(value = "#{seguridadBean.cliente}")
    private Cliente cliente;

    @Autowired
    private AdministradorPlataformaServicio adminServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Getter
    @Setter
    private List<LocalDate> fechas;

    @Getter @Setter
    private LocalDate fecha;

    @Getter @Setter
    private LocalDate fechaMinima, fechaMaxima;

    @Getter
    @Setter
    private List<Teatro> teatros;

    @Getter
    @Setter
    private Teatro teatro;

    @PostConstruct
    public void init() {
        teatros = new ArrayList<>();
        ciudades = adminServicio.listarCiudades();
        funciones = new ArrayList<>();
        funcionesFiltradas = new ArrayList<>();
        fechas = new ArrayList<>();
        fecha = LocalDate.now();
        fechaMinima = LocalDate.now();
        fechaMaxima = LocalDate.now().plusDays(15);

        for(int i = 0; i < 5; i++){
            fechas.add( fecha.plusDays(i) );
        }

        if (ciudad != null) {
            try {
                teatros = adminTeatroServicio.listarTeatrosCiudad(ciudad.getCodigo());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        try {
            if (peliculaCodigo != null && !peliculaCodigo.isEmpty()) {
                pelicula = adminServicio.obtenerPelicula(Integer.parseInt(peliculaCodigo));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getGenerosPelicula() {
        if (pelicula != null) {
            String generos = pelicula.getGeneros().toString();
            generos = generos.replace('[', ' ');
            generos = generos.replace(']', ' ');
            generos = generos.replace('_', ' ');
            return generos;
        }
        return "";
    }

    public void actualizarTeatros() {
        try {
            if (ciudad != null) {
                teatros = adminTeatroServicio.listarTeatrosCiudad(ciudad.getCodigo());
                funciones = new ArrayList<>();
                funcionesFiltradas = new ArrayList<>();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarFunciones() {
        try {
            if (teatro != null && ciudad != null) {
                funciones = adminServicio.obtenerFuncionesPorTeatroPelicula(pelicula.getCodigo(), teatro.getCodigo());
                filtrarFunciones(LocalDate.now());
            } else {
                funciones = new ArrayList<>();
                funcionesFiltradas = new ArrayList<>();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void filtrarFunciones(LocalDate fecha){

        funcionesFiltradas.clear();

        if (teatro != null && ciudad != null) {
            try {

                funciones = adminServicio.obtenerFuncionesPorTeatroPelicula(pelicula.getCodigo(), teatro.getCodigo());

                funciones.forEach( f -> {
                    if( f.getHorario().getFechaInicio().isBefore(fecha) && f.getHorario().getFechaFin().isAfter(fecha) ){
                        funcionesFiltradas.add(f);
                    }
                });
                System.out.println(funcionesFiltradas);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String realizarProcesoCompra(Funcion funcion) {

        if (cliente != null && funcion != null && fecha != null) {
            return "/cliente/proceso_compra?faces-redirect=true&amp;id_funcion=" + funcion.getCodigo()+"&amp;fecha_funcion="+fecha;
        }
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "No has ingresado sesión", "Ingresa sesión para realizar el proceso de compra");
        FacesContext.getCurrentInstance().addMessage("funciones", fm);
        return "";
    }

}
