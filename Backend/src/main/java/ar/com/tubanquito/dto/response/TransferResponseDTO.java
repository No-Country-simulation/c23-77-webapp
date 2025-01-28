package ar.com.tubanquito.dto.response;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record TransferResponseDTO(
    Long cbuOrigen,
    Long cbuDestino,
    String aliasDestino,
    Double monto,
    LocalDate fecha,
    String estado
) {
    
}
