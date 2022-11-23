package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Pelicula;
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
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    private List<Pelicula> peliculasCartelera;

    @Getter @Setter
    private List<Pelicula> peliculasProximamente;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Pelicula> peliculasCarrusel;

    @Autowired
    private AdministradorPlataformaServicio adminServicio;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<String> imagenes;

    @PostConstruct
    public void init() {
        try {
            imagenes = new ArrayList<>();
            peliculasCarrusel = new ArrayList<>();
            peliculasCartelera = clienteServicio.listarPeliculasPorEstado("EN_CARTELERA");
            peliculasProximamente = clienteServicio.listarPeliculasPorEstado("PROXIMAMENTE");
            ciudades = adminServicio.listarCiudades();

            imagenes.add(peliculasCartelera.get(0).getImagenSecundaria());
            imagenes.add(peliculasCartelera.get(1).getImagenSecundaria());
            peliculasCarrusel.addAll(peliculasCartelera);
            peliculasCarrusel.addAll(peliculasProximamente);

        } catch (Exception e) {
            e.printStackTrace();
            //FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Peliculas no encontradas", e.getMessage());
            //FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }

    public void elegirCiudad() {
        try {
            if(ciudad != null) {
                peliculasCartelera = clienteServicio.listarPeliculasPorEstadoCiudad("EN_CARTELERA", ciudad.getCodigo());
                peliculasProximamente = clienteServicio.listarPeliculasPorEstado("PROXIMAMENTE");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Peliculas no encontradas", e.getMessage());
            //FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }
}
