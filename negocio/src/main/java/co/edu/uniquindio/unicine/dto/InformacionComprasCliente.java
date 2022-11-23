package co.edu.uniquindio.unicine.dto;

import co.edu.uniquindio.unicine.entidades.Funcion;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class InformacionComprasCliente {
    private Float precioTotal;
    private LocalDateTime fechaCompra;
    private Funcion funcion;
    private Integer totalConfiteria;
    private Integer totalEntradas;

}
