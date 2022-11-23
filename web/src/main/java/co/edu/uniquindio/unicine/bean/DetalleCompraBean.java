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

@Component
@ViewScoped
public class DetalleCompraBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    @Value(value = "#{seguridadBean.cliente}")
    private Cliente cliente;

    @Value("#{param['compra_id']}")
    private String codigoCompra;

    @Getter @Setter
    private Compra compra;

    @Getter @Setter
    private Boolean clienteDiferente, compraEncontrada;

    @PostConstruct
    public void init() {
        if (codigoCompra != null && !codigoCompra.isEmpty()) {
            try {
                if (cliente != null) {
                    compra = clienteServicio.obtenerCompra(Integer.parseInt(codigoCompra));
                    compraEncontrada = true;
                    if (!compra.getCliente().equals(cliente)) clienteDiferente = true;
                } else {
                    clienteDiferente = true;
                }
            } catch (Exception e) {
                compraEncontrada = false;
                clienteDiferente = true;
            }
        }
    }

}
