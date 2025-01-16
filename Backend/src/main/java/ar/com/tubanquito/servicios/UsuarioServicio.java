package ar.com.tubanquito.servicios;

import ar.com.tubanquito.dto.request.UsuarioRequest;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServicio(ar.com.tubanquito.repositorios.UsuarioRepositorio usuarioRepositorio, PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario autenticarUsuario(String email, String password) {
        // Buscar usuario por email
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findByEmail(email);
        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOptional.get();

        // Verificar contraseña
        if (!passwordEncoder.matches(password, usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }

    public Usuario crearUsuario(UsuarioRequest request) {
        // Verificar si el email ya está registrado
        if (usuarioRepositorio.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Crear el objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setEmail(request.email());
        usuario.setContrasena(passwordEncoder.encode(request.contrasena())); // Encriptar la contraseña
        usuario.setTelefono(request.telefono());
        usuario.setTipoUsuario(request.tipoUsuario());
        usuario.setRol(null); // Si no se manejan roles aún, se puede dejar como `null`

        // Guardar en la base de datos
        return usuarioRepositorio.save(usuario);
    }
}
