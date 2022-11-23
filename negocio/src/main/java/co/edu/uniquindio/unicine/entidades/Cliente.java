package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cliente implements Serializable {

    //Atributos

    @Id
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private String cedula;

    @NonNull
    @Column(nullable = false, length = 60)
    private String nombreCompleto;

    @NonNull
    @Column(nullable = false)
    private Integer edad;

    @NonNull
    @Column(nullable = false, length = 55)
    private String direccion;

    @Email
    @NonNull
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @ElementCollection
    @Column(unique = true, length = 10)
    @ToString.Exclude
    private List<String> telefonos;

    @ToString.Exclude
    @NonNull
    private String imagen_perfil;

    @Column(length = 25)
    private String estado = "NO_VERIFICADO";

    @Column(nullable = false, length = 200)
    @ToString.Exclude
    private String contrasenia;

    //Relaciones

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<ClienteCupon> clienteCupones;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<PQRS> pqrs;

    @Builder
    public Cliente(String cedula, String nombre_completo, Integer edad, String direccion, String email,
                   List<String> telefonos, String imagen_perfil,String contrasenia) {
        this.cedula = cedula;
        this.nombreCompleto = nombre_completo;
        this.edad = edad;
        this.direccion = direccion;
        this.email = email;
        this.telefonos = telefonos;
        this.imagen_perfil = imagen_perfil;
        this.contrasenia = contrasenia;
    }
}
