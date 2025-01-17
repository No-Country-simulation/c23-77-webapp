package ar.com.tubanquito.controladores;

import ar.com.tubanquito.dto.request.UsuarioRequest;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.servicios.UsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody @Valid UsuarioRequest request) {
        try {
            Usuario usuario = usuarioServicio.crearUsuario(request);
            return ResponseEntity.ok("Usuario registrado exitosamente: " + usuario.getEmail());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
