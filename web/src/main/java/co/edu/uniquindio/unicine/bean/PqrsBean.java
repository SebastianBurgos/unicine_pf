package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.PQRS;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class PqrsBean implements Serializable {

    @Value("#{seguridadBean.cliente}")
    private Cliente cliente;

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    private String motivo;

    @Getter @Setter
    private String mensaje;

    @PostConstruct
    public void init() {

    }

    public void realizarPqrs() {

        if (cliente != null) {
            PQRS solicitud = PQRS.builder().motivo(motivo).mensaje(mensaje).cliente(cliente).build();
            try {
                clienteServicio.realizarPqrs(solicitud);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "PQRS realizado con éxito");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No hay un cliente asociado al PQRS");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }
}
