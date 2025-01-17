package ar.com.tubanquito.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancaria;
import io.micrometer.common.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Table(name = "usuarios")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements UserDetails {

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

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private Set<Direccion> direccion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private Set<CuentaBancaria> cuentasBancaria;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notificacion> notificaciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Archivo> archivosSubidos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.contrasena;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
