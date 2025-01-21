package ar.com.tubanquito.validaciones.usuarios;

import ar.com.tubanquito.dto.request.UsuarioRequest;
import ar.com.tubanquito.entidades.Rol;
import ar.com.tubanquito.validaciones.Validador;
import org.springframework.stereotype.Component;

@Component
public class ValidadorRol implements Validador<UsuarioRequest> {

    @Override
    public void validar(UsuarioRequest request) {
        try {
            Rol.valueOf(request.rol().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("El rol proporcionado no es válido: " + request.rol());
        }
    }
}
