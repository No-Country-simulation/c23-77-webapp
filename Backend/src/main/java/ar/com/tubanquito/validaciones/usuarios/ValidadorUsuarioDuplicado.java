package ar.com.tubanquito.validaciones.usuarios;

import ar.com.tubanquito.dto.request.UsuarioRequest;
import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import ar.com.tubanquito.validaciones.Validador;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioDuplicado implements Validador<UsuarioRequest> {

    private final UsuarioRepositorio usuarioRepositorio;

    public ValidadorUsuarioDuplicado(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public void validar(UsuarioRequest datos) {
        if (usuarioRepositorio.findByEmail(datos.email()).isPresent()) {
            throw new RuntimeException("El email ya está registrado: " + datos.email());
        }
    }
}
