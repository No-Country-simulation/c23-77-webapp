package ar.com.tubanquito.servicios;

import ar.com.tubanquito.dto.request.UsuarioRequest;
import ar.com.tubanquito.dto.response.UsuarioResponse;
import ar.com.tubanquito.entidades.Rol;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.mapper.UsuarioMapper;
import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import ar.com.tubanquito.validaciones.Validador;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;
    private final List<Validador<UsuarioRequest>> validadores;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper, List<Validador<UsuarioRequest>> validadores) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
        this.validadores = validadores;
    }

    public UsuarioResponse autenticarUsuario(String email, String password) {
        Usuario usuario = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuarioMapper.toResponse(usuario);
    }

    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        validadores.forEach(v -> v.validar(request)); // Ejecuta todas las validaciones


        // Mapear el DTO a la entidad
        Usuario usuario = usuarioMapper.toEntity(request);

        // Encriptar la contraseña antes de guardar
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        usuario.setRol(Rol.PARTICULAR); // Asignar un rol por defecto
        Usuario guardado = usuarioRepositorio.save(usuario);

        return usuarioMapper.toResponse(guardado);
    }

    public UsuarioResponse obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuarioMapper.toResponse(usuario);
    }

    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepositorio.findAll()
                .stream()
                .map(usuarioMapper::toResponse)
                .toList();
    }

    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setEmail(request.email());
        usuario.setTelefono(request.telefono());

        // Encriptar la contraseña solo si se actualiza
        if (request.contrasena() != null && !request.contrasena().isBlank()) {
            usuario.setContrasena(passwordEncoder.encode(request.contrasena()));
        }

        Usuario actualizado = usuarioRepositorio.save(usuario);
        return usuarioMapper.toResponse(actualizado);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuarioAEliminar = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioRepositorio.delete(usuarioAEliminar);
    }
}
