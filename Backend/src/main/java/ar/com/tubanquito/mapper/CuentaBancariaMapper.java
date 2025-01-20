package ar.com.tubanquito.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tubanquito.dto.request.CreateBankAccountDTO;
import ar.com.tubanquito.dto.response.AccountResponseDTO;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancaria;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class CuentaBancariaMapper {

    @Autowired
    private Validator validator;
    
     public AccountResponseDTO toGetDTO(CuentaBancaria cuenta){

        return AccountResponseDTO.builder()
        .name(cuenta.getUsuario().getUsername())
        .status("CREATED")
        .saldo(cuenta.getSaldo())
        .tipo(cuenta.getTipoCuenta().toString())
        .build();
    }   

    public CuentaBancaria buildAccount(CreateBankAccountDTO account, Usuario user) throws Exception{
        System.out.println("ID ES :" + user.getId());
        //verifyData(account);
        
        return CuentaBancaria
        .builder()
        .usuario(user)
        .tipoCuenta(account.tipo().toString())
        .saldo(BigDecimal.valueOf(account.saldoInicial()))
        .moneda(account.tipoMoneda())
        .fechaCreacion(LocalDate.now())
        .build();
       
    }

    private void verifyData(CreateBankAccountDTO account) throws Exception{
          // Validar el DTO
          Set<ConstraintViolation<CreateBankAccountDTO>> violations = validator.validate(account);
          if (!violations.isEmpty()) {
              // Si hay errores de validación, lanzar una excepción o manejar el error
              StringBuilder errorMessage = new StringBuilder("Errores de validación: ");
              for (ConstraintViolation<CreateBankAccountDTO> violation : violations) {
                  errorMessage.append(violation.getMessage()).append(" ");
              }
              throw new Exception(errorMessage.toString());
          }
    }
}
