package ar.com.tubanquito.validaciones.usuarios;

import ar.com.tubanquito.dto.request.UsuarioRequest;
import ar.com.tubanquito.validaciones.Validador;
import org.springframework.stereotype.Component;

@Component
public class ValidadorContrasenaSegura implements Validador<UsuarioRequest> {

    @Override
    public void validar(UsuarioRequest datos) {
        String contrasena = datos.contrasena();
        if (contrasena.length() < 8 || !contrasena.matches(".*\\d.*") || !contrasena.matches(".*[A-Z].*")) {
            throw new RuntimeException("La contraseña no cumple los requisitos de seguridad");
        }
    }
}
