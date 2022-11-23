package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Entrada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    @ToString.Exclude
    private Integer precio;

    @Column(nullable = false)
    private Character fila;

    @Column(nullable = false)
    private Integer columna;

    @ToString.Exclude
    @ManyToOne
    private Compra compra;

    @Builder
    public Entrada(Character fila, Integer columna, Compra compra) {
        this.fila = fila;
        this.columna = columna;
        this.compra = compra;
    }
}
