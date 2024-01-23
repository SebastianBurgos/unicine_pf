package co.edu.uniquindio.unicine.converter;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorPlataformaServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class ClienteConverter implements Converter<Cliente> {

    @Autowired
    private ClienteServicio clienteServicio;

    @Override
    public Cliente getAsObject(FacesContext context, UIComponent component, String value) {
        Cliente cliente;
        try {
            cliente = clienteServicio.obtenerCliente(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Cliente value) {
        if (value!=null) {
            return ""+value.getCedula();
        }
        return "";
    }
}
