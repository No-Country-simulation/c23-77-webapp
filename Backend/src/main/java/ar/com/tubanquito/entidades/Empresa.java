package ar.com.tubanquito.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table(name = "empresas")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToMany()
    @JoinTable(
            name = "Empresas_Cuentas",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "cuenta_id")
    )
    private Set<CuentaBancaria> cuentaBancarias;

    @ManyToMany()
    @JoinTable(
            name = "Empresas_Empleados",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "persona_id")
    )
    private Set<Persona> personas;
}
