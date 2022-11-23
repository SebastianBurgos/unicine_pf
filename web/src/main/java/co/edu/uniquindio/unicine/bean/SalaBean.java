package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
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
public class SalaBean implements Serializable {

    @Getter @Setter
    private Sala sala;

    @Getter @Setter
    private List<Sala> salas;

    @Getter @Setter
    private List<Sala> salasSeleccionadas;

    @Getter @Setter
    private List<Teatro> teatros;

    @Getter @Setter
    private List<TipoSala> tiposSala;

    @Getter @Setter
    private List<DistribucionSillas> distribucionesSillas;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    private boolean editar;

    @PostConstruct
    public void init() {
        sala = new Sala();
        salas = adminTeatroServicio.listarSalaTeatro(5);
        teatros = adminTeatroServicio.listarTeatros();
        tiposSala = adminTeatroServicio.listarTiposSala();
        distribucionesSillas = adminTeatroServicio.listarDistribucionesSillas();
        salasSeleccionadas = new ArrayList<>();
        editar = false;
    }

    public void crearSala(){
        //Funcion de otro compañero
        try {

            if (!editar) {
                Sala registro = adminTeatroServicio.registrarSala(sala);
                salas.add(registro);
                sala = new Sala();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sala registrada", "Se creó correctamente la sala");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            } else {
                adminTeatroServicio.actualizarSala(sala);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sala actualizada", "Se actualizó correctamente la sala");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sala no creada", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }

    }

    public void eliminarSalas() {
            try {
                for (Sala t : salasSeleccionadas) {
                    adminTeatroServicio.eliminarSala(t.getCodigo());
                    salas.remove(t);
                }
                salasSeleccionadas.clear();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sala/s eliminada/s", "Se eliminó correctamente los salas seleccionadas.");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sala no eliminada", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
    }

    public String getMensajeBorrar() {
        if (salasSeleccionadas.size() == 0) {
            return "Borrar";
        } else if (salasSeleccionadas.size() == 1){
            return "Borrar ("+salasSeleccionadas.size()+" elemento)";
        } else {
            return "Borrar ("+salasSeleccionadas.size()+" elementos)";
        }
    }

    public String getMensajeDialogo(){
        if(editar){
            return "Editar sala";
        } else {
            return "Crear sala";
        }
    }

    public void seleccionarSala(Sala sala) {
        this.sala = sala;
        editar = true;
    }

    public void crearSalaDialogo() {
        this.sala = new Sala();
        editar = false;
    }
}
