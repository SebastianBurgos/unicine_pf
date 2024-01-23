package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.entidades.Horario;
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
public class HorarioBean implements Serializable {

    @Getter @Setter
    private Horario horario;

    @Getter @Setter
    private List<Horario> horarios;

    @Getter @Setter
    private List<Horario> horariosSeleccionados;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    private boolean editar;

    @PostConstruct
    public void init() {
        horario = new Horario();
        horarios = adminTeatroServicio.listarHorario();
        horariosSeleccionados = new ArrayList<>();
        editar = false;
    }

    public void crearHorario(){
        //Funcion de otro compañero
        try {

            if (!editar) {
                Horario registro = adminTeatroServicio.crearHorario(horario);
                horarios.add(registro);
                horario = new Horario();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Horario registrado", "Se creó correctamente el horario");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            } else {
                adminTeatroServicio.actualizarHorario(horario);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Horario actualizado", "Se actualizó correctamente el horario");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Horario no creado", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }

    }

    public void eliminarHorarios() {
            try {
                for (Horario t : horariosSeleccionados) {
                    adminTeatroServicio.eliminarHorario(t.getCodigo());
                    horarios.remove(t);
                }
                horariosSeleccionados.clear();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Horario/s eliminado/s", "Se eliminó correctamente los horarios seleccionados.");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Horario no eliminado", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
    }

    public String getMensajeBorrar() {
        if (horariosSeleccionados.size() == 0) {
            return "Borrar";
        } else if (horariosSeleccionados.size() == 1){
            return "Borrar ("+horariosSeleccionados.size()+" elemento)";
        } else {
            return "Borrar ("+horariosSeleccionados.size()+" elementos)";
        }
    }

    public String getMensajeDialogo(){
        if(editar){
            return "Editar horario";
        } else {
            return "Crear horario";
        }
    }

    public void seleccionarHorario(Horario horario) {
        this.horario = horario;
        editar = true;
    }

    public void crearHorarioDialogo() {
        this.horario = new Horario();
        editar = false;
    }
}
