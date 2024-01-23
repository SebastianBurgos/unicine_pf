package co.edu.uniquindio.unicine.bean;


import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
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
public class PeliculaBean implements Serializable {

    @Getter @Setter
    private Pelicula pelicula;

    @Getter @Setter
    private ArrayList<Pelicula> peliculas;

    @Getter @Setter
    private ArrayList<Pelicula> peliculasSeleccionadas;

    @Autowired
    private AdministradorPlataformaServicio administradorPlataformaServicio;

    @Setter @Getter
    private List<Genero> generos;


    private Map<String, String> imagenes;

    @Autowired
    private CloudinaryService cloudinaryService;

    private boolean editar;

    @PostConstruct
    public void init() {
        pelicula = new Pelicula();
        peliculas = (ArrayList<Pelicula>) administradorPlataformaServicio.listarPeliculas();
        peliculasSeleccionadas = new ArrayList<Pelicula>();
        generos = Arrays.asList(Genero.values());
        imagenes = new HashMap<>();
        editar = false;
    }

    public void crearPelicula() {
        try {
            if (!imagenes.isEmpty()) {
                if (!editar) {
                    pelicula.setImagenes(imagenes);
                    pelicula.setEstadoPelicula("PROXIMAMENTE");
                    administradorPlataformaServicio.registrarPelicula(pelicula);
                    pelicula = new Pelicula();
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pelicula creada", "Has creado una nueva pelicula");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                } else {
                    pelicula.setImagenes(imagenes);
                    pelicula.setEstadoPelicula("PROXIMAMENTE");
                    administradorPlataformaServicio.actualizarPelicula(pelicula);
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pelicula actualizada", "Se ha actualizado la pelicula");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                }
            }else{
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pelicula no creada", "No se ha subido ninguna imagen");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pelicula no creada", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void eliminarPeliculas() {
        try {
            for (Pelicula p : peliculasSeleccionadas) {
                administradorPlataformaServicio.eliminarPelicula(p.getCodigo());
                peliculas.remove(p);
            }
            peliculasSeleccionadas.clear();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pelicula/s eliminada/s", "Se eliminó correctamente las películas seleccionadas.");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pelicula no eliminada", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void subirImagenes(FileUploadEvent event){
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryService.subirImagen(imagenFile, "peliculas");
            imagenes.put( resultado.get("public_id").toString(), resultado.get("url").toString() );
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pelicula no creada", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public File convertirUploadedFile(UploadedFile imagen) throws IOException {
        File file = new File(imagen.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getContent());
        fos.close();
        return file;
    }

    public String getMensajeBorrar() {
        if (peliculasSeleccionadas.size() == 0) {
            return "Borrar";
        } else if (peliculasSeleccionadas.size() == 1){
            return "Borrar ("+peliculasSeleccionadas.size()+" elemento)";
        } else {
            return "Borrar ("+peliculasSeleccionadas.size()+" elementos)";
        }
    }

    public String getMensajeDialogo(){
        if(editar){
            return "Editar película";
        } else {
            return "Crear película";
        }
    }

    public void seleccionarPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
        editar = true;
    }

    public void crearPeliculaDialogo() {
        this.pelicula = new Pelicula();
        editar = false;
    }
}
