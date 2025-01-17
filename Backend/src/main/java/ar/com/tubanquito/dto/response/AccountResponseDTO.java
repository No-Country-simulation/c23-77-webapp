package ar.com.tubanquito.dto.response;

import java.math.BigDecimal;

import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancariaTipo;
import lombok.Builder;

@Builder
public record AccountResponseDTO(
    String name,
    BigDecimal saldo,
    String tipo,
    String status
) {

}
