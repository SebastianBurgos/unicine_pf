package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Funcion implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private Integer precio;
    //Relaciones
    @ToString.Exclude
    @ManyToOne
    private Sala sala;

    @ToString.Exclude
    @ManyToOne
    @NonNull
    private Horario horario;

    @ToString.Exclude
    @ManyToOne
    @NonNull
    private Pelicula pelicula;

    @ToString.Exclude
    @OneToMany(mappedBy = "funcion")
    @NonNull
    private List<Compra> compras;

    @Builder
    public Funcion(Integer precio ,Sala sala, Horario horario, Pelicula pelicula) {
        this.precio = precio;
        this.sala = sala;
        this.horario = horario;
        this.pelicula = pelicula;
    }
}
