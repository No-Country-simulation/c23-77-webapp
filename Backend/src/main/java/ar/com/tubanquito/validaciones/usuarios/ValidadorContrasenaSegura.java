package ar.com.tubanquito.validaciones.usuarios;

import ar.com.tubanquito.dto.request.UsuarioRequest;
import ar.com.tubanquito.validaciones.Validador;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidadorContrasenaSegura implements Validador<UsuarioRequest> {

    @Override
    public void validar(UsuarioRequest datos) {
        String contrasena = datos.contrasena();
        List<String> errores = new ArrayList<>();

        if (contrasena.length() < 8) {
            errores.add("La contraseña debe tener al menos 8 caracteres.");
        }

        if (!contrasena.matches(".*\\d.*")) {
            errores.add("La contraseña debe contener al menos un número.");
        }

        if (!contrasena.matches(".*[A-Z].*")) {
            errores.add("La contraseña debe contener al menos una letra mayúscula.");
        }

        if (!errores.isEmpty()) {
            throw new RuntimeException(String.join(" ", errores));
        }
    }
}
