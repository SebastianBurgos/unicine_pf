package co.edu.uniquindio.unicine.converter;

import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.entidades.TipoSala;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class TipoSalaConverter implements Converter<TipoSala> {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Override
    public TipoSala getAsObject(FacesContext context, UIComponent component, String value) {

        TipoSala tipoSala;
        try {
            tipoSala = adminTeatroServicio.buscarTipoSala( Integer.parseInt(value) );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tipoSala;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, TipoSala value) {

        if (value!=null) {
            return ""+value.getCodigo();
        }
        return "";
    }
}
