package co.edu.uniquindio.unicine.bean;

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
public class GestionCuentaBean implements Serializable {

    @Value("#{param['p1']}")
    private String param1;

    @Getter @Setter
    private String mensaje = "Verificando su cuenta...";

    @Autowired
    private ClienteServicio clienteServicio;

    @PostConstruct
    public void init() {
        if(param1 != null && !param1.isEmpty()) {
            try {
                clienteServicio.activarCuenta(param1);

                mensaje = "Cuenta verificada, ¡redime el cupón de bienvenida agregado a tu cuenta! :D";

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
