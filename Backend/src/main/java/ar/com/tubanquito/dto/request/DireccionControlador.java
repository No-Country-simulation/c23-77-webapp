package ar.com.tubanquito.dto.request;

import ar.com.tubanquito.dto.response.DireccionResponse;
import ar.com.tubanquito.servicios.DireccionServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direcciones")
public class DireccionControlador {

    private final DireccionServicio direccionServicio;

    public DireccionControlador(DireccionServicio direccionServicio) {
        this.direccionServicio = direccionServicio;
    }

    @PostMapping
    public ResponseEntity<DireccionResponse> crearDireccion(@RequestBody @Valid DireccionRequest request) {
        DireccionResponse direccion = direccionServicio.crearDireccion(request);
        return ResponseEntity.ok(direccion);
    }
}
