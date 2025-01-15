package ar.com.tubanquito.dominio.persona;


import ar.com.tubanquito.dominio.empresa.Empresa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Table(name = "Personas")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nombres;

    @Column(length = 100, nullable = false, name = "apellido_paterno")
    private String apPaterno;

    @Column(length = 100, nullable = false, name = "apellido_materno")
    private String apMaterno;

    @Column(length = 10)
    private String sexo;

    @Column(name = "lugar_nacimiento", length = 100)
    private String lugarNacimiento;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(unique = true, length = 20)
    private String dni;

    @ManyToMany(mappedBy = "personas")
    private Set<Empresa> empresas;
}
