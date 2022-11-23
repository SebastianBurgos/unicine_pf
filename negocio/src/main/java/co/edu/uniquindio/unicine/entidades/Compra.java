package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private Float precioTotal;

    @Column(nullable = false)
    private LocalDateTime fechaCompra;

    @Column(nullable = false)
    private LocalDate fechaFuncion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private MedioPago medioDePago;

    @Column(nullable = false)
    private String estado;

    //Relaciones

    @OneToOne
    private ClienteCupon clienteCupon;

    @OneToMany(mappedBy = "compra")
    private List<Entrada> entradas;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Funcion funcion;

    @OneToMany(mappedBy = "compra")
    private List<CompraConfiteria> comprasConfiteria;

    @Builder
    public Compra(MedioPago medioDePago, LocalDate fechaFuncion, ClienteCupon clienteCupon, List<Entrada> entradas, Cliente cliente,
                  Funcion funcion, List<CompraConfiteria> comprasConfiteria) {
        this.fechaCompra = LocalDateTime.now();
        this.medioDePago = medioDePago;
        this.fechaFuncion = fechaFuncion;
        this.clienteCupon = clienteCupon;
        this.entradas = entradas;
        this.cliente = cliente;
        this.funcion = funcion;
        this.comprasConfiteria = comprasConfiteria;
        this.estado = "CREADA";
        actualizarPrecioTotal();
    }

    public void actualizarPrecioTotal() {

        float valorEntradas = 0;
        float valorConfiteria = 0;

        if (funcion != null) {
            valorEntradas = entradas.size() * funcion.getPrecio();
        }

        if (comprasConfiteria != null) {
            for (CompraConfiteria compraConfiteria : comprasConfiteria) {
                valorConfiteria += compraConfiteria.getPrecio();
            }
        }


        if (clienteCupon != null) {
            this.precioTotal = (valorEntradas + valorConfiteria) - (((valorEntradas + valorConfiteria)*clienteCupon.getCupon().getDescuento())/100);
        } else {
            this.precioTotal = valorEntradas + valorConfiteria;
        }


    }
}
