package ar.com.tubanquito.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import ar.com.tubanquito.entidades.Empresa;
import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancariaTipo;

public record CreateBankAccountDTO(
    Long idUsuario,
    CuentaBancariaTipo tipo,
    BigDecimal saldoInicial
    
){

}
