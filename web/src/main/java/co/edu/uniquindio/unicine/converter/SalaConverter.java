package co.edu.uniquindio.unicine.converter;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorPlataformaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class SalaConverter implements Converter<Sala> {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Override
    public Sala getAsObject(FacesContext context, UIComponent component, String value) {

        Sala sala;
        try {
            sala = adminTeatroServicio.buscarSalaCodigo( Integer.parseInt(value) );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sala;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Sala value) {

        if (value!=null) {
            return ""+value.getCodigo();
        }
        return "";
    }
}
