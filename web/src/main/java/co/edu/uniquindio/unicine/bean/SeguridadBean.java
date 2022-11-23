package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorPlataforma;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorPlataformaServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String cedula, password;

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private String tipoSesion;

    @Getter @Setter
    private Ciudad ciudad;
    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    private AdministradorTeatro administradorTeatro;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Getter @Setter
    private AdministradorPlataforma administradorPlataforma;

    @Autowired
    private AdministradorPlataformaServicio administradorPlataformaServicio;

    @Email
    @Getter @Setter
    private String email;

    @PostConstruct
    public void init() {
        autenticado = false;
    }

    public String iniciarSesionCliente() {

        if (!cedula.isEmpty() && !password.isEmpty()) {
            try {
                cliente = clienteServicio.login(cedula, password);
                tipoSesion = "cliente";
                autenticado = true;
                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificacion", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificacion", "La cedula y la contraseña son obligatorios");
            FacesContext.getCurrentInstance().addMessage("login-bean", fm);
        }
        return null;
    }

    public String cerrarSesion() {
        String tipo = tipoSesion;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        if (tipo.equals("cliente")) {
            autenticado = false;
            return "/index?faces-redirect=true";
        }
        if (tipo.equals("admin_teatro") || tipo.equals("admin_plataforma")) {
            autenticado = false;
            return "/index_admin?faces-redirect=true";
        }
        return "/index_admin?faces-redirect=true";
    }

    public void seleccionarCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String iniciarSesionAdmin() {
        String redirect = "/admin/gestionar_administradores_teatro?faces-redirect=true";
        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                administradorTeatro = adminTeatroServicio.login(email, password);
                if (administradorTeatro == null) {
                    administradorPlataforma = administradorPlataformaServicio.login(email, password);
                    tipoSesion = "admin_plataforma";
                } else {
                    tipoSesion = "admin_teatro";
                    redirect = "/admin_teatro/gestionar_teatro?faces-redirect=true";
                }
                autenticado = true;

                return redirect;
            }catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, fm);
            }
        }else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación", "El campo del correo o la contraseña son inválidos");
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
        return null;
    }

    public void reestablecerContrasenia(){

        if (email != null && !email.isEmpty()) {
            try {
                clienteServicio.solicitarCambioContrasenia(email);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación", "Se envió correctamente el correo de recuperación");
                FacesContext.getCurrentInstance().addMessage("recuperacion", fm);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, fm);
            }
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación", "El campo del correo es inválido");
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }
}
