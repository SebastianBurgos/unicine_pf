package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pelicula implements Serializable {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Length(max = 65)
    @Column(nullable = false, length = 65)
    private String nombre;

    @Lob
    @Column(nullable = false)
    private String sinopsis;

    @Column(nullable = false)
    private Integer duracionMinutos;

    @Column(nullable = false)
    private Integer edadApropiada;

    @Length(max = 65)
    @Column(nullable = false, length = 65)
    private String nombreDirector;

    @Length(max = 65)
    @Column(nullable = false, length = 65)
    private String nombreEstudio;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private List<Reparto> reparto;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;

    @Column(nullable = false, unique = true)
    private String trailer;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private List<Genero> generos;

    @Column(nullable = false, length = 40)
    private String estadoPelicula = "CREADA";

    //Relaciones

    @ToString.Exclude
    @OneToMany(mappedBy = "pelicula")
    private List<Funcion> funciones;

    @Builder
    public Pelicula(String nombre, String sinopsis, Integer duracion_minutos, Integer edad_apropiada,
                    String nombre_director, String nombre_estudio, List<Reparto> reparto, String trailer, List<Genero> generos) {
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.duracionMinutos = duracion_minutos;
        this.edadApropiada = edad_apropiada;
        this.nombreDirector = nombre_director;
        this.nombreEstudio = nombre_estudio;
        this.reparto = reparto;
        this.trailer = trailer;
        this.generos = generos;
        this.estadoPelicula = "CREADO";
    }

    public String getImagenPrincipal() {
        if (!imagenes.isEmpty()){
            String primera = imagenes.keySet().toArray()[0].toString();
            return imagenes.get(primera);
        }
        return "";
    }

    public String getImagenSecundaria() {
        if (!imagenes.isEmpty()){
            String segunda = imagenes.keySet().toArray()[1].toString();
            return imagenes.get(segunda);
        }
        return "";
    }

    public String getSinopsisCorta(){
        return sinopsis.substring(0, 200)+"...";
    }
}
