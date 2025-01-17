package ar.com.tubanquito.controladores;

import ar.com.tubanquito.dto.request.AutenticacionRequest;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.infra.security.DatosJWTToken;
import ar.com.tubanquito.infra.security.TokenService;
import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping("/login")
public class AutenticacionControlador {

    private final AuthenticationManager manager;

    private final TokenService tokenService;


    public AutenticacionControlador(AuthenticationManager manager, TokenService tokenService, UsuarioRepositorio usuarioRepositorio) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DatosJWTToken> realizarLogin(@RequestBody @Valid AutenticacionRequest datos) {
        var authToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        var usuarioAutenticado = manager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
