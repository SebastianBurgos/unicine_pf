package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Pelicula;
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
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    private List<Pelicula> peliculas;

    @PostConstruct
    public void init() {
        if (busquedaParam != null && !busquedaParam.isEmpty()){
            try {
                peliculas = clienteServicio.buscarPelicula(busquedaParam);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Notificacion", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, fm);
            }
        }
    }
    public String buscar() {
        if (!busqueda.isEmpty()) {
            return "/resultados_busqueda?faces-redirect=true&amp;busqueda=" + busqueda;
        }
        return "";
    }

}
