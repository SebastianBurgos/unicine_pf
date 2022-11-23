package co.edu.uniquindio.unicine.converter;

import co.edu.uniquindio.unicine.entidades.ClienteCupon;
import co.edu.uniquindio.unicine.entidades.ClienteCupon;
import co.edu.uniquindio.unicine.servicios.AdministradorPlataformaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class ClienteCuponConverter implements Converter<ClienteCupon> {

    @Autowired
    private AdministradorPlataformaServicio adminServicio;

    @Override
    public ClienteCupon getAsObject(FacesContext context, UIComponent component, String value) {

        ClienteCupon cupon;
        try {
            cupon = adminServicio.obtenerClienteCupon( Integer.parseInt(value) );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cupon;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ClienteCupon value) {

        if (value!=null) {
            return ""+value.getCodigo();
        }
        return "";
    }
}
