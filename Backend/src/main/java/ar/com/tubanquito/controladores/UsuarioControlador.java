package ar.com.tubanquito.controladores;

import ar.com.tubanquito.dto.request.UsuarioRequest;
import ar.com.tubanquito.dto.response.UsuarioResponse;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.servicios.UsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponse> registrarUsuario(@RequestBody @Valid UsuarioRequest request) {
        UsuarioResponse usuario = usuarioServicio.crearUsuario(request);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioServicio.obtenerUsuarioPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioServicio.listarUsuarios());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioRequest request) {
        UsuarioResponse usuarioActualizado = usuarioServicio.actualizarUsuario(id, request);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN') or #id == principal.id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        usuarioServicio.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado exitosamente");
    }
}
