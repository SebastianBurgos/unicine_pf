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
public class TipoSala implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, unique = true, length = 30)
    private String nombre;

    @Column(nullable = false)
    private Float precio_adicional;

    //Relaciones

    @ToString.Exclude
    @OneToMany(mappedBy = "tipoSala")
    private List<Sala> salas;

    @Builder
    public TipoSala(String nombre, Float precio_adicional) {
        this.nombre = nombre;
        this.precio_adicional = precio_adicional;
    }
}
