package co.edu.uniquindio.unicine.converter;

import co.edu.uniquindio.unicine.entidades.DistribucionSillas;
import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class DistribucionSillasConverter implements Converter<DistribucionSillas> {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Override
    public DistribucionSillas getAsObject(FacesContext context, UIComponent component, String value) {

        DistribucionSillas distribucionSillas;
        try {
            distribucionSillas = adminTeatroServicio.buscarDistribucionSillas( Integer.parseInt(value) );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return distribucionSillas;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, DistribucionSillas value) {

        if (value!=null) {
            return ""+value.getCodigo();
        }
        return "";
    }
}
