package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Sala implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private Integer numero;
    //Relaciones
    @ToString.Exclude
    @ManyToOne
    private Teatro teatro;

    @ToString.Exclude
    @ManyToOne
    private TipoSala tipoSala;

    @ToString.Exclude
    @ManyToOne
    private DistribucionSillas distribucionSillas;

    @ToString.Exclude
    @OneToMany(mappedBy = "sala")
    private List<Funcion> funciones;

    @Builder
    public Sala(Integer numero, Teatro teatro, TipoSala tipoSala, DistribucionSillas distribucionSillas) {
        this.numero = numero;
        this.teatro = teatro;
        this.tipoSala = tipoSala;
        this.distribucionSillas = distribucionSillas;
    }
}
