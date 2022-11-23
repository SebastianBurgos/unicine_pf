package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorPlataformaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class FuncionBean implements Serializable {

    @Getter @Setter
    private Funcion funcion;

    @Getter @Setter
    private List<Funcion> funciones;

    @Getter @Setter
    private List<Sala> salas;

    @Getter @Setter
    private List<Horario> horarios;

    @Getter @Setter
    private List<Pelicula> peliculas;

    @Getter @Setter
    private List<Funcion> funcionesSeleccionadas;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdministradorPlataformaServicio administradorPlataformaServicio;

    private boolean editar;

    @PostConstruct
    public void init() {
        funcion = new Funcion();
        funciones = adminTeatroServicio.listarFunciones();
        horarios = adminTeatroServicio.listarHorario();
        salas = adminTeatroServicio.listarSalaTeatro(5);
        peliculas = administradorPlataformaServicio.listarPeliculas();
        funcionesSeleccionadas = new ArrayList<>();
        editar = false;
    }

    public void crearFuncion(){
        //Funcion de otro compañero
        try {

            if (!editar) {
                Funcion registro = adminTeatroServicio.registrarFuncion(funcion);
                funciones.add(registro);
                funcion = new Funcion();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcion registrada", "Se creó correctamente la funcion");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            } else {
                adminTeatroServicio.actualizarFuncion(funcion);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcion actualizada", "Se actualizó correctamente la funcion");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcion no creada", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }

    }

    public void eliminarFuncion() {
            try {
                for (Funcion f : funcionesSeleccionadas) {
                    adminTeatroServicio.elimiarFuncion(f.getCodigo());
                    funciones.remove(f);
                }
                funcionesSeleccionadas.clear();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcion/es eliminada/s", "Se eliminó correctamente las funciones seleccionadas.");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcion/es no eliminada/s", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
    }

    public String getMensajeBorrar() {
        if (funcionesSeleccionadas.size() == 0) {
            return "Borrar";
        } else if (funcionesSeleccionadas.size() == 1){
            return "Borrar ("+funcionesSeleccionadas.size()+" elemento)";
        } else {
            return "Borrar ("+funcionesSeleccionadas.size()+" elementos)";
        }
    }

    public String getMensajeDialogo(){
        if(editar){
            return "Editar funcion";
        } else {
            return "Crear funcion";
        }
    }

    public void seleccionarFuncion(Funcion funcion) {
        this.funcion = funcion;
        editar = true;
    }

    public void crearFuncionDialogo() {
        this.funcion = new Funcion();
        editar = false;
    }
}
