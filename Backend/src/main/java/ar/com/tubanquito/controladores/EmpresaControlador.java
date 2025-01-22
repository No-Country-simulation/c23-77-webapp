package ar.com.tubanquito.controladores;

import ar.com.tubanquito.dto.request.EmpresaRequest;
import ar.com.tubanquito.dto.response.EmpresaResponse;
import ar.com.tubanquito.entidades.Empresa;
import ar.com.tubanquito.servicios.EmpresaServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaControlador {

    private final EmpresaServicio empresaServicio;

    public EmpresaControlador(EmpresaServicio empresaServicio) {
        this.empresaServicio = empresaServicio;
    }

    @PostMapping
    public ResponseEntity<EmpresaResponse> crearEmpresa(@RequestBody @Valid EmpresaRequest request) {
        return ResponseEntity.ok(empresaServicio.crearEmpresa(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponse> obtenerEmpresa(@PathVariable Long id) {
        return ResponseEntity.ok(empresaServicio.obtenerEmpresaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return ResponseEntity.ok(empresaServicio.listarEmpresas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        return ResponseEntity.ok(empresaServicio.actualizarEmpresa(id, empresa));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpresa(@PathVariable Long id) {
        empresaServicio.eliminarEmpresa(id);
        return ResponseEntity.ok("Empresa eliminada exitosamente");
    }
}
