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
public class Ciudad implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 60)
    private String nombre;

    //Relaciones

    @ToString.Exclude
    @OneToMany(mappedBy = "ciudad")
    private List <Teatro> teatros;

    @Builder
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
}
