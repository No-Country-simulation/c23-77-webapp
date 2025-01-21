package ar.com.tubanquito.dto.response;

public record DireccionResponse(
        Long id,
        String calle,
        String ciudad,
        String estadoProvincia,
        String cp,
        String pais,
        Long usuarioId // ID del usuario asociado
) {}
