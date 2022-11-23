package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
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
public class TeatroBean implements Serializable {

    @Getter @Setter
    private Teatro teatro;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private Ciudad ciudadTeatro;

    @Getter @Setter
    private List<Teatro> teatros;

    @Getter @Setter
    private List<Teatro> teatrosSeleccionados;

    @Autowired
    private AdministradorPlataformaServicio adminServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    private boolean editar;

    @PostConstruct
    public void init() {
        teatro = new Teatro();
        ciudades = adminServicio.listarCiudades();
        teatros = adminTeatroServicio.listarTeatros();
        teatrosSeleccionados = new ArrayList<>();
        editar = false;
    }

    public void crearTeatro(){
        //Funcion de otro compañero
        try {

            if (!editar) {
                teatro.setAdministradorTeatro(null);
                Teatro registro = adminTeatroServicio.crearTeatro(teatro);
                teatros.add(registro);
                teatro = new Teatro();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Teatro registrado", "Se creó correctamente el teatro");
                FacesContext.getCurrentInstance().addMessage(null, fm);
            } else {
                adminTeatroServicio.actualizarTeatro(teatro);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Teatro actualizado", "Se actualizó correctamente el teatro");
                FacesContext.getCurrentInstance().addMessage(null, fm);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Teatro no creado", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }

    }

    public void eliminarTeatros() {
            try {
                for (Teatro t : teatrosSeleccionados) {
                    adminTeatroServicio.eliminarTeatro(t.getCodigo());
                    teatros.remove(t);
                }
                teatrosSeleccionados.clear();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Teatro/s eliminado/s", "Se eliminó correctamente los teatros seleccionados.");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Teatro no eliminado", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
    }

    public String getMensajeBorrar() {
        if (teatrosSeleccionados.size() == 0) {
            return "Borrar";
        } else if (teatrosSeleccionados.size() == 1){
            return "Borrar ("+teatrosSeleccionados.size()+" elemento)";
        } else {
            return "Borrar ("+teatrosSeleccionados.size()+" elementos)";
        }
    }

    public String getMensajeDialogo(){
        if(editar){
            return "Editar teatro";
        } else {
            return "Crear teatro";
        }
    }

    public void seleccionarTeatro(Teatro teatro) {
        this.teatro = teatro;
        editar = true;
    }

    public void crearTeatroDialogo() {
        this.teatro = new Teatro();
        editar = false;
    }
}
