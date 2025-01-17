package ar.com.tubanquito.dto.response;

public record UsuarioResponse(
        Long id,
        String nombre,
        String email,
        Long perfilId,
        Boolean status) {
}
