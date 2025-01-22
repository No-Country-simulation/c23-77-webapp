package ar.com.tubanquito.dto.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancariaTipo;
import ar.com.tubanquito.entidades.CuentaBancaria.Moneda;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record CreateBankAccountDTO(
    
    
 
    Long id,
    CuentaBancariaTipo tipo,
    Double saldoInicial,
    Moneda tipoMoneda
    
){

}

/** 
     
    @ManyToMany(mappedBy = "cuentaBancarias")
    private Set<Empresa> empresa;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private Set<HistorialTransacciones> historialTransacciones;
*/
    
