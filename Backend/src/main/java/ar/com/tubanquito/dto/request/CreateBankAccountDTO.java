package ar.com.tubanquito.dto.request;

import java.math.BigDecimal;
import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancariaTipo;
import ar.com.tubanquito.entidades.CuentaBancaria.Moneda;

public record CreateBankAccountDTO(
    Long idUsuario,
    CuentaBancariaTipo tipo,
    BigDecimal saldoInicial,
    Moneda tipoMoneda
    
){

}

/** 
     
    @ManyToMany(mappedBy = "cuentaBancarias")
    private Set<Empresa> empresa;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private Set<HistorialTransacciones> historialTransacciones;
*/
    
