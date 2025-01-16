package ar.com.tubanquito.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "direcciones")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String calle;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @Column(length = 100)
    private String estadoProvincia;

    @Column(length = 20)
    private String cp;

    @Column(length = 100, nullable = false)
    private String pais;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
