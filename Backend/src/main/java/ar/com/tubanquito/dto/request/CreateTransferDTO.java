package ar.com.tubanquito.dto.request;

import java.util.UUID;

public record CreateTransferDTO(
    Long idUsuarioOrigen,
    Long cbuOrigen,

    Long cbuDestino,
    String aliasDestino,
    
    Double monto,
    String descripcion
) {
    
}
