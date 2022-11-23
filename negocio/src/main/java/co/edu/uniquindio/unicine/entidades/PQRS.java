package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class PQRS implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String motivo;

    @Column(nullable = false)
    private LocalDateTime fecha;

    //Relaciones
    @ToString.Exclude
    @ManyToOne
    private Cliente cliente;

    @Builder
    public PQRS(String mensaje, String motivo, Cliente cliente) {
        this.fecha = LocalDateTime.now();
        this.mensaje = mensaje;
        this.motivo = motivo;
        this.cliente = cliente;
    }
}
