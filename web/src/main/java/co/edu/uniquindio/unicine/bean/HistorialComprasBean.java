package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class HistorialComprasBean implements Serializable {

    @Value("#{seguridadBean.cliente}")
    private Cliente cliente;

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    private List<Compra> comprasCliente;

    @PostConstruct
    public void init() {
        if (cliente != null) {
            try {
                comprasCliente = clienteServicio.listarHistorialCompras(cliente.getCedula());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
