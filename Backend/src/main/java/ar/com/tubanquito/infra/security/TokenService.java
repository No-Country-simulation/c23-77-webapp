package ar.com.tubanquito.infra.security;

import ar.com.tubanquito.entidades.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final String apiScret = "clave_muy_secreta";

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiScret);
            return JWT.create()
                    .withIssuer("Tubanquito")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
//                    .withClaim("role", usuario.getRol().name()) // Incluye el rol
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token no proporcionado.");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiScret);
            return JWT.require(algorithm)
                    .withIssuer("Tubanquito")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            if (e.getMessage().contains("The Token has expired")) {
                throw new RuntimeException("El token ha expirado. Por favor, inicie sesión nuevamente.");
            }
            throw new RuntimeException("Token inválido: " + e.getMessage());
        }
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}