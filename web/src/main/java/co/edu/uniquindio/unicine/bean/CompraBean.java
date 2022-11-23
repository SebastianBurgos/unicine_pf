package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.FuncionServicio;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@ViewScoped
public class CompraBean implements Serializable {

    @Getter @Setter
    @Value(value = "#{seguridadBean.cliente}")
    private Cliente cliente;

    @Value("#{param['id_funcion']}")
    private String codigoFuncion;

    @Value("#{param['fecha_funcion']}")
    @Getter
    private String fechaFuncion;

    @Getter
    @Setter
    private Funcion funcion;

    @Autowired
    private FuncionServicio funcionServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter
    @Setter
    private Compra compra;

    @Getter
    @Setter
    private List<Character[]> sillas;

    @Getter
    @Setter
    private Integer numeroEntradas;

    private List<Entrada> sillasOcupadasFuncion;

    @Getter
    @Setter
    private List<Entrada> entradasSeleccionadas;

    @Getter
    @Setter
    private List<ProductoConfiteria> confiteria;

    @Getter
    @Setter
    private List<CompraConfiteria> productosSeleccionados;

    @Getter @Setter
    private List<String> mediosPagoDisponibles;

    @Getter @Setter
    private String medioPagoSeleccionado;
    @Getter @Setter
    private MedioPago medioPago;

    @Getter @Setter
    private String codigoCupon;

    @Getter @Setter
    private ClienteCupon cupon;

    @PostConstruct
    public void init() {

        mediosPagoDisponibles = new ArrayList<>();
        compra = Compra.builder().build(); //Inicializo la compra

        for (MedioPago medioPagoAux : MedioPago.values()) {
            mediosPagoDisponibles.add(medioPagoAux.toString());
        }

        //Para las entradas
        numeroEntradas = 0;
        sillas = new ArrayList<>(); //Las sillas que se muestran
        sillasOcupadasFuncion = new ArrayList<>(); //Sillas ocupadas de otras compras de la funcion
        entradasSeleccionadas = new ArrayList<>();

        //Para la confiteria
        confiteria = clienteServicio.obtenerProductosConfiteria(); //Para el carrusel de productos de confiteria
        productosSeleccionados = new ArrayList<>();

        try {
            if (codigoFuncion != null && !codigoFuncion.isEmpty()) { //Obtengo la funcion y a partir de ella el esquema de sillas
                funcion = funcionServicio.obtenerFuncion(Integer.parseInt(codigoFuncion));
                crearLista(funcion.getSala().getDistribucionSillas().getEsquemaSillas());
            }
            if (funcion != null && fechaFuncion != null) { //Obtengo las sillas ocupadas de otras funciones
                sillasOcupadasFuncion = clienteServicio.obtenerEntradasFuncion(funcion, LocalDate.parse(fechaFuncion));
            }
        } catch (Exception e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("growl", facesMessage);
        }
    }

    public void actualizarCompra() {
        if (medioPagoSeleccionado != null){
            medioPago = MedioPago.valueOf(medioPagoSeleccionado);
        }
        System.out.println(medioPagoSeleccionado);
        compra = Compra.builder().funcion(funcion).fechaFuncion(LocalDate.parse(fechaFuncion)).cliente(cliente).comprasConfiteria(productosSeleccionados).entradas(entradasSeleccionadas).medioDePago(medioPago).clienteCupon(cupon).build();
        System.out.println(compra);
    }

    //metodos de las entradas

    private void crearLista(String esquema) {
        String[] filas = esquema.split("\n");
        for (String fila : filas) {
            Character[] charObjectArray = fila.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
            sillas.add(charObjectArray);
        }
    }

    public void seleccionarSilla(int fila, int col) {

        if (numeroEntradas != 0) {

            if (numeroEntradas > entradasSeleccionadas.size()) {

                Character letraFila = obtenerLetraFila(fila);
                if (existeSilla(fila, col)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Asiento ocupado");
                    FacesContext.getCurrentInstance().addMessage("growl", facesMessage);
                } else {
                    Entrada entrada = Entrada.builder().fila(letraFila).columna(col).compra(compra).build();
                    entradasSeleccionadas.add(entrada);
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Asiento escogido: " + letraFila + "-" + col);
                    FacesContext.getCurrentInstance().addMessage("growl", facesMessage);
                    System.out.println(entrada);
                    actualizarCompra();
                }

            } else {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Ya has elegido tus asientos");
                FacesContext.getCurrentInstance().addMessage("growl", facesMessage);
            }
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Escoge cuántas entradas quieres: ");
            FacesContext.getCurrentInstance().addMessage("growl", facesMessage);
        }
    }

    public boolean existeSilla(int fila, int col) {
        for (Entrada entrada : sillasOcupadasFuncion) {
            if (entrada.getFila() == obtenerLetraFila(fila) && entrada.getColumna() == col) {
                return true;
            }
        }
        for (Entrada entrada : entradasSeleccionadas) {
            if (entrada.getFila() == obtenerLetraFila(fila) && entrada.getColumna() == col) {
                return true;
            }
        }
        return false;
    }

    public Character obtenerLetraFila(Integer fila) {
        if (fila == 0) return 'A';
        if (fila == 1) return 'B';
        if (fila == 2) return 'C';
        if (fila == 3) return 'D';
        if (fila == 4) return 'E';
        if (fila == 5) return 'F';
        if (fila == 6) return 'G';
        if (fila == 7) return 'H';
        if (fila == 8) return 'I';
        if (fila == 9) return 'J';
        if (fila == 10) return 'K';
        if (fila == 11) return 'L';
        return null;
    }

    public void reducirEntradas() {

        entradasSeleccionadas.clear();
        actualizarCompra();

        if (numeroEntradas > 0) {
            numeroEntradas--;
        }
        if (numeroEntradas != 0) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Vuelve a seleccionar las sillas");
            FacesContext.getCurrentInstance().addMessage("growl", facesMessage);
        }
    }

    public void aumentarEntradas() {
        if (numeroEntradas < 99) numeroEntradas++;
    }


    //Métodos de confiteria
    public Integer obtenerUnidadesProductoConfiteria(ProductoConfiteria productoConfiteria) {

        if (!productosSeleccionados.isEmpty()) {
            for (CompraConfiteria producto : productosSeleccionados) {
                if(producto.getProductoConfiteria().equals(productoConfiteria)) {
                    return producto.getUnidades();
                }
            }
        }
        return 0;
    }

    public void reducirProducto(ProductoConfiteria productoConfiteria) {

        if (!productosSeleccionados.isEmpty()) {
            for (CompraConfiteria compraConfiteria : productosSeleccionados) {
                if (compraConfiteria.getProductoConfiteria().equals(productoConfiteria)) {
                    compraConfiteria.setUnidades(compraConfiteria.getUnidades()-1);
                    if (compraConfiteria.getUnidades() == 0) {
                        productosSeleccionados.remove(compraConfiteria);
                        actualizarCompra();
                    } else {
                        compraConfiteria.actualizarPrecio();
                        actualizarCompra();
                    }
                    break;
                }
            }
        }
    }

    public void aumentarProducto(ProductoConfiteria productoConfiteria) {

        boolean encontrado = false;
        if (productosSeleccionados.isEmpty()) {
            CompraConfiteria compraConfiteria = CompraConfiteria.builder().compra(compra).productoConfiteria(productoConfiteria).unidades(1).build();
            productosSeleccionados.add(compraConfiteria);
        } else {
            for (CompraConfiteria compraConfiteria : productosSeleccionados) {
                if (compraConfiteria.getProductoConfiteria().equals(productoConfiteria)) {
                    encontrado = true;
                    compraConfiteria.setUnidades(compraConfiteria.getUnidades()+1);
                    compraConfiteria.actualizarPrecio();
                    break;
                }
            }
            if(!encontrado) {
                CompraConfiteria compraConfiteria = CompraConfiteria.builder().compra(compra).productoConfiteria(productoConfiteria).unidades(1).build();
                productosSeleccionados.add(compraConfiteria);
            }
        }
        actualizarCompra();
    }


    //Realizar compra
    public String realizarCompra() {

        if (cliente != null && funcion != null && fechaFuncion != null && entradasSeleccionadas.size() != 0) {
            actualizarCompra();
            System.out.println();
            try {
                Compra compraRealizada = clienteServicio.realizarCompra(compra);

                for (Entrada entrada : entradasSeleccionadas) {
                    entrada.setCompra(compraRealizada);
                    entrada.setPrecio(funcion.getPrecio());
                    clienteServicio.guardarEntrada(entrada);
                }
                for (CompraConfiteria compraConfiteria : productosSeleccionados) {
                    compraConfiteria.setCompra(compraRealizada);
                    compraConfiteria.actualizarPrecio();
                    clienteServicio.guardarCompraConfiteria(compraConfiteria);
                }

                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación", "¡Compra realizada con éxito!");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);

                return "/cliente/detalle_compra?faces-redirect=true&amp;compra_id=" + compraRealizada.getCodigo();

            } catch (Exception e) {
                e.printStackTrace();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No es posible realizar la compra", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible realizar la compra", "No existe la información suficiente para realizar la compra");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return "";
    }
}
