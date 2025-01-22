package ar.com.tubanquito.mapper;

import ar.com.tubanquito.dto.request.UsuarioRequest;
import ar.com.tubanquito.dto.response.UsuarioResponse;
import ar.com.tubanquito.entidades.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.email());
        usuario.setContrasena(request.contrasena()); // Contraseña sin encriptar
        usuario.setTelefono(request.telefono());
        return usuario;
    }

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getRol() != null ? usuario.getRol().name() : "SIN_ROL"
        );
    }
}
