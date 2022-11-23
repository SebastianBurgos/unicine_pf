package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class DistribucionSillas implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private Integer filas;

    @Column(nullable = false)
    private Integer columnas;

    @Column(nullable = false)
    private Integer totalSillas;

    @Column(nullable = false)
    private String esquemaSillas;

    //Relaciones

    @ToString.Exclude
    @OneToMany(mappedBy = "distribucionSillas")
    private List<Sala> salas;

    @Builder
    public DistribucionSillas(Integer filas, Integer columnas, Integer totalSillas, String esquemaSillas) {
        this.filas = filas;
        this.columnas = columnas;
        this.totalSillas = totalSillas;
        this.esquemaSillas = esquemaSillas;
    }
}
