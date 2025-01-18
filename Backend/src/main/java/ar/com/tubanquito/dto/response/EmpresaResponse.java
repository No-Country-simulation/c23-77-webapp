package ar.com.tubanquito.dto.response;

public record EmpresaResponse(
        Long id,
        String nombre,
        Long usuarioId
) {}
