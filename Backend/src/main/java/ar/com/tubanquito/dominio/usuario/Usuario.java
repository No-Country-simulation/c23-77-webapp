package ar.com.tubanquito.dominio.usuario;

import ar.com.tubanquito.dominio.notificacion.Notificacion;
import ar.com.tubanquito.dominio.archivo.ArchivosSubidos;
import ar.com.tubanquito.dominio.cuenta.CuentaBancaria;
import ar.com.tubanquito.dominio.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table(name = "Usuario")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    @Column(length = 20)
    private String telefono;

    @Column(name = "tipo_usuario", nullable = false, length = 50)
    private String tipoUsuario;

    @Column(length = 512)
    private String token;

    @Column(length = 50)
    private String rol;

    @OneToMany(mappedBy = "persona")
    private Set<Direccion> direccion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private Set<CuentaBancaria> cuentasBancaria;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notificacion> notificaciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ArchivosSubidos> archivosSubidos;
}
