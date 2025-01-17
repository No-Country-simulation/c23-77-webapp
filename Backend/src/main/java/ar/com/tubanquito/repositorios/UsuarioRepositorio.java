package ar.com.tubanquito.repositorios;

import ar.com.tubanquito.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    // Método para lógica de negocio
    Optional<Usuario> findByEmail(String email);

    // Método para Spring Security (puede usar el mismo nombre si ambos son necesarios)
    default UserDetails findUserDetailsByEmail(String email) {
        return (UserDetails) findByEmail(email).orElseThrow(() ->
                new RuntimeException("Usuario no encontrado")
        );
    }
}
