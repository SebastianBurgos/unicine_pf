package co.edu.uniquindio.unicine.bean;

import ch.qos.logback.core.net.server.Client;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.ClienteCupon;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.servicios.AdministradorPlataformaServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class CuponBean implements Serializable {

    @Getter @Setter
    private Cupon cupon;

    @Autowired
    private AdministradorPlataformaServicio administradorPlataformaServicio;

    @Setter @Getter
    private List<Cupon> cuponList;

    @Setter @Getter
    private Cliente cliente;

    @Setter @Getter
    private List<Cliente> clientes;

    @Setter @Getter
    private List<Cupon> cuponesSeleccionados;

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    private LocalDate fechaVencimiento;

    private boolean editar;

    @PostConstruct
    public void init() {
        cupon = new Cupon();
        cuponesSeleccionados = new ArrayList<>();
        cliente = new Cliente();
        clientes = clienteServicio.listarClientes();
        cuponList = administradorPlataformaServicio.listarCupones();
        editar = false;
    }

    public void crearCupon() {
        try {
            if (!editar){
                Cupon registro = administradorPlataformaServicio.registrarCupon(cupon);
                cuponList.add(registro);
                cupon = new Cupon();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cupon Creado", "Has registrado un nuevo Cupon");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else{
                administradorPlataformaServicio.actualizarCupon(cupon);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cupon Actualizado", "Se Actualizó el Cupon");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cupon no Creado", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void asignarCupon(Cupon cupon){
       try{
           ClienteCupon cuponAsignar = ClienteCupon.builder().cupon(cupon).cliente(cliente).fechaVencimiento(fechaVencimiento).build();
           ClienteCupon asignado = administradorPlataformaServicio.asignarCupon(cuponAsignar);
           cuponAsignar = new ClienteCupon();
           FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cupon Asignado", "Has asignado el Cupon");
           FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
       }catch (Exception e) {
           FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cupon no Asignado", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
       }
    }

    public void eliminarCupon() {
        try {
            for (Cupon c : cuponesSeleccionados) {
                administradorPlataformaServicio.eliminarCupon(c.getCodigo());
                cuponList.remove(c);
            }
            cuponesSeleccionados.clear();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cupon/es eliminado/s", "Se eliminó correctamente los cupones.");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cupon/es no eliminado/s", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMensajeBorrar() {
        if (cuponesSeleccionados.size() == 0) {
            return "Borrar";
        } else if (cuponesSeleccionados.size() == 1){
            return "Borrar ("+cuponesSeleccionados.size()+" elemento)";
        } else {
            return "Borrar ("+cuponesSeleccionados.size()+" elementos)";
        }
    }

    public String getMensajeDialogo(){
        if(editar){
            return "Editar Cupon";
        } else {
            return "Crear Cupon";
        }
    }

    public void seleccionarCupon(Cupon cupon) {
        this.cupon = cupon;
        editar = true;
    }

    public void crearCuponDialogo() {
        this.cupon = new Cupon();
        editar = false;
    }

}
