package ar.com.tubanquito.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancariaTipo;
import lombok.Builder;

@Builder
public record AccountResponseDTO(
    String email,
    BigDecimal saldo,
    String tipo,
    String status,
    String alias,
    Long cbu
) {

}
