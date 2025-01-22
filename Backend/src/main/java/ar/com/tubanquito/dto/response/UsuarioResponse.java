package ar.com.tubanquito.dto.response;

public record UsuarioResponse(
        Long id,
        String email,
        String telefono,
        String rol
) {}
