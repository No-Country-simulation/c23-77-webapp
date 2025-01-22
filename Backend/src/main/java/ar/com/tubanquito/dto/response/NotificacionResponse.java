package ar.com.tubanquito.dto.response;

import java.time.LocalDateTime;

public record NotificacionResponse(
        Long id,
        String tipo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean leido
) {
}
