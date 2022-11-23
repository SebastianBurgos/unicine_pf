package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class ReestablecerContraseniaBean implements Serializable {

    @Value("#{param['p1']}")
    private String correoEncriptado;

    @Getter @Setter
    private String nuevaContresenia;

    @Getter @Setter
    private String confirmarContrasenia;

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    private String mensaje;

    @PostConstruct
    public void init() {

        System.out.println(correoEncriptado);

    }

    public void reestablecerContrasenia() {

        if (nuevaContresenia != null && !nuevaContresenia.isEmpty()) {
            if(nuevaContresenia.equals(confirmarContrasenia)) {
                if (correoEncriptado != null && !correoEncriptado.isEmpty()) {
                    try {
                        clienteServicio.reestablecerContrasenia(correoEncriptado, nuevaContresenia);
                        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Has cambiado satisfactoriamente tu contraseña");
                        FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                    } catch (Exception e) {
                        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                        FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                    }
                } else {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No se envió ningún correo como parámetro");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                }
            } else {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Las contraseñas no coinciden");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        }
    }

}
