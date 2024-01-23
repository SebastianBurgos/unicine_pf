package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.ProductoConfiteria;
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
public class ConfiteriaBean implements Serializable {

    @Getter @Setter
    private ProductoConfiteria confiteria;

    @Getter @Setter
    private ArrayList<ProductoConfiteria> confiterias;

    @Getter @Setter
    private ArrayList<ProductoConfiteria> confiteriasSeleccionadas;

    @Autowired
    private AdministradorPlataformaServicio administradorPlataformaServicio;

    private Map<String, String> imagenes;

    @Autowired
    private CloudinaryService cloudinaryService;

    private boolean editar;

    @PostConstruct
    public void init() {
        confiteria = new ProductoConfiteria();
        confiterias = (ArrayList<ProductoConfiteria>) administradorPlataformaServicio.listarProductosConfiteria();
        confiteriasSeleccionadas = new ArrayList<ProductoConfiteria>();
        imagenes = new HashMap<>();
        editar = false;
    }

    public void crearConfiteria() {
        try {
            if (!imagenes.isEmpty()) {
                if (!editar) {
                    confiteria.setImagen_producto(imagenes);
                    administradorPlataformaServicio.registrarProductoConfiteria(confiteria);
                    confiteria = new ProductoConfiteria();
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto de Confiteria creado", "Has creado un nuevo Producto de Confiteria");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                } else {
                    confiteria.setImagen_producto(imagenes);
                    administradorPlataformaServicio.actualizarProductoConfiteria(confiteria);
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto de Confiteria actualizado", "Se ha actualizado el Producto de Confiteria");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                }
            }else{
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto de Confiteria no creado", "No se ha subido ninguna imagen");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto de Confiteria no creado", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void eliminarConfiterias() {
        try {
            for (ProductoConfiteria pc : confiteriasSeleccionadas) {
                administradorPlataformaServicio.eliminarProductoConfiteria(pc.getCodigo());
                confiterias.remove(pc);
            }
            confiteriasSeleccionadas.clear();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto de Confiteria/s eliminada/s", "Se eliminó correctamente los Productos de Confiteria.");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto de Confiteria no eliminada", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void subirImagenes(FileUploadEvent event){
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryService.subirImagen(imagenFile, "confiteria");
            imagenes.put( resultado.get("public_id").toString(), resultado.get("url").toString() );
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto de confiteria no creada", e.getMessage());
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
        if (confiteriasSeleccionadas.size() == 0) {
            return "Borrar";
        } else if (confiteriasSeleccionadas.size() == 1){
            return "Borrar ("+confiteriasSeleccionadas.size()+" elemento)";
        } else {
            return "Borrar ("+confiteriasSeleccionadas.size()+" elementos)";
        }
    }

    public String getMensajeDialogo(){
        if(editar){
            return "Editar confiteria";
        } else {
            return "Crear confiteria";
        }
    }

    public void seleccionarConfiteria(ProductoConfiteria confiteria) {
        this.confiteria = confiteria;
        editar = true;
    }

    public void crearConfiteriaDialogo() {
        this.confiteria = new ProductoConfiteria();
        editar = false;
    }
}
