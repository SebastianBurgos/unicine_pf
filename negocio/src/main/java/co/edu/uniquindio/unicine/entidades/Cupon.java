package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cupon implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private Float descuento;

    @Column(nullable = false)
    private String concepto;

    //Relaciones

    @ToString.Exclude
    @OneToMany(mappedBy = "cupon")
    private List<ClienteCupon> clienteCupones;

    @Builder
    public Cupon(Float descuento, String concepto) {
        this.descuento = descuento;
        this.concepto = concepto;
    }
}
