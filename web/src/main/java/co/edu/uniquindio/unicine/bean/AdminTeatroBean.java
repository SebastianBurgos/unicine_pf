package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdministradorPlataformaServicio;
import co.edu.uniquindio.unicine.servicios.CloudinaryService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Component
@ViewScoped
public class AdminTeatroBean implements Serializable {

    @Getter @Setter
    private AdministradorTeatro administradorTeatro;

    @Autowired
    private AdministradorPlataformaServicio administradorPlataformaServicio;

    @Setter @Getter
    private List<AdministradorTeatro> administradorTeatroList;

    @Setter @Getter
    private List<AdministradorTeatro> administradoresTeatroSeleccionados;

    private boolean editar;

    @PostConstruct
    public void init() {
        administradorTeatro = new AdministradorTeatro();
        administradoresTeatroSeleccionados = new ArrayList<>();
        administradorTeatroList = administradorPlataformaServicio.listarAdministradoresTeatro();
        editar = false;
    }

    public void crearAdminTeatro() {
        try {
            if (!editar){
                AdministradorTeatro registro = administradorPlataformaServicio.registrarAdministradorTeatro(administradorTeatro);
                administradorTeatroList.add(registro);
                administradorTeatro = new AdministradorTeatro();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de Teatro Creado", "Has registrado un nuevo Administrador de Teatro");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else{
                administradorPlataformaServicio.actualizarAdministradorTeatro(administradorTeatro);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador de Teatro Actualizado", "Se Actualizó el Administrador de Teatro");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Administrador de Teatro no Creado", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void eliminarAdminTeatro() {
        try {
            for (AdministradorTeatro at : administradoresTeatroSeleccionados) {
                administradorPlataformaServicio.eliminarAdministradorTeatro(at.getCedula());
                administradorTeatroList.remove(at);
            }
            administradoresTeatroSeleccionados.clear();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador/es de Teatro eliminado/s", "Se eliminó correctamente los administradores de teatro seleccionados.");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador/es de Teatro no eliminado/s", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMensajeBorrar() {
        if (administradoresTeatroSeleccionados.size() == 0) {
            return "Borrar";
        } else if (administradoresTeatroSeleccionados.size() == 1){
            return "Borrar ("+administradoresTeatroSeleccionados.size()+" elemento)";
        } else {
            return "Borrar ("+administradoresTeatroSeleccionados.size()+" elementos)";
        }
    }

    public String getMensajeDialogo(){
        if(editar){
            return "Editar Admin Teatro";
        } else {
            return "Crear Admin Teatro";
        }
    }

    public void seleccionarAdminTeatro(AdministradorTeatro adminTeatro) {
        this.administradorTeatro = adminTeatro;
        editar = true;
    }

    public void crearAdminTeatroDialogo() {
        this.administradorTeatro = new AdministradorTeatro();
        editar = false;
    }

}
