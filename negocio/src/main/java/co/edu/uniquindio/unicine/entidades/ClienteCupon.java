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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ClienteCupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    @NonNull
    private Cliente cliente;

    @ManyToOne
    @NonNull
    private Cupon cupon;

    @Column(nullable = false, length = 25)
    private String estado;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @ToString.Exclude
    @OneToOne (mappedBy = "clienteCupon")
    private Compra compra;

    @Builder
    public ClienteCupon(Cliente cliente, Cupon cupon, LocalDate fechaVencimiento) {
        this.cliente = cliente;
        this.cupon = cupon;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = "ACTIVO";
    }
}
