package ar.com.tubanquito.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancaria;

@Entity
@Table(name = "empresas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "empresas_cuentas", // Nombre de la tabla debe respetar snake_case
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "cuenta_id")
    )
    private Set<CuentaBancaria> cuentasBancarias;

    @ManyToMany
    @JoinTable(
            name = "empresas_empleados",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "persona_id")
    )
    private Set<Persona> personas;
}
