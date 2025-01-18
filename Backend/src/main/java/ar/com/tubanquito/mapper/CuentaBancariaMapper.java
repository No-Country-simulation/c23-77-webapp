package ar.com.tubanquito.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import ar.com.tubanquito.dto.request.CreateBankAccountDTO;
import ar.com.tubanquito.dto.response.AccountResponseDTO;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancaria;

@Component
public class CuentaBancariaMapper {
    
     public AccountResponseDTO toGetDTO(CuentaBancaria cuenta){

        return AccountResponseDTO.builder()
        .name(cuenta.getUsuario().getUsername())
        .status("CREATED")
        .saldo(cuenta.getSaldo())
        .tipo(cuenta.getTipoCuenta().toString())
        .build();
    }   

    public CuentaBancaria buildAccount(CreateBankAccountDTO account, Usuario user) throws Exception{
        return CuentaBancaria
        .builder()
        .usuario(user)
        .tipoCuenta(account.tipo().toString())
        .saldo(account.saldoInicial())
        .moneda(account.tipo().toString())
        .fechaCreacion(LocalDate.now())
        .build();
    }
}
