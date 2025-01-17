package ar.com.tubanquito.dto.request;

import jakarta.validation.constraints.NotBlank;

public record NotificacionRequest(
    @NotBlank Long id,
    @NotBlank String tipo,
    @NotBlank String mensaje
) { }
