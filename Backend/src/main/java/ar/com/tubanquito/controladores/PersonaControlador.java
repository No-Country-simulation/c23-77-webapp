package ar.com.tubanquito.controladores;

import ar.com.tubanquito.dto.request.PersonaRequest;
import ar.com.tubanquito.dto.response.PersonaResponse;
import ar.com.tubanquito.servicios.PersonaServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaControlador {

    private final PersonaServicio personaServicio;

    public PersonaControlador(PersonaServicio personaServicio) {
        this.personaServicio = personaServicio;
    }

    @PostMapping
    public ResponseEntity<PersonaResponse> crearPersona(@RequestBody @Valid PersonaRequest request) {
        return ResponseEntity.ok(personaServicio.crearPersona(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponse> obtenerPersona(@PathVariable Long id) {
        return ResponseEntity.ok(personaServicio.obtenerPersonaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonaResponse>> listarPersonas() {
        return ResponseEntity.ok(personaServicio.listarPersonas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponse> actualizarPersona(@PathVariable Long id, @RequestBody @Valid PersonaRequest request) {
        return ResponseEntity.ok(personaServicio.actualizarPersona(id, request));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPersona(@PathVariable Long id) {
        personaServicio.eliminarPersona(id);
        return ResponseEntity.ok("Persona eliminada exitosamente");
    }
}
