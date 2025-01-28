package ar.com.tubanquito.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tubanquito.dto.request.AccountRequestEditDTO;
import ar.com.tubanquito.dto.request.CreateBankAccountDTO;
import ar.com.tubanquito.dto.response.AccountResponseDTO;
import ar.com.tubanquito.dto.response.TransferResponseDTO;
import ar.com.tubanquito.entidades.Transferencia;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancaria;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class CuentaBancariaMapper {

    @Autowired
    private Validator validator;

    public AccountResponseDTO toGetDTO(CuentaBancaria cuenta) {

        return AccountResponseDTO.builder()
                .email(cuenta.getUsuario().getUsername())
                .status("CREATED")
                .saldo(cuenta.getSaldo())
                .tipo(cuenta.getTipoCuenta().toString())
                .alias(cuenta.getAlias())
                .cbu(cuenta.getCbu())
                .build();
    }

    public CuentaBancaria buildAccount(CreateBankAccountDTO account, Usuario user) throws Exception {
        // verifyData(account);

        return CuentaBancaria
                .builder()
                .usuario(user)
                .tipoCuenta(account.tipo().toString())
                .saldo(BigDecimal.valueOf(account.saldoInicial()))
                .moneda(account.tipoMoneda())
                .fechaCreacion(LocalDate.now())
                .cbu(generateCBU())
                .alias(generateAlias(user.getEmail()))
                .build();

    }

    private String generateAlias(String email) {
        String local = email.split("@")[0];
        local = local.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String alias = local + "." + generateRandomNumbers(4);
        return alias;
    }

    private static String generateRandomNumbers(int length) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            result.append(random.nextInt(10));
        }

        return result.toString();
    }

    private Long generateCBU() {
        String key = "";
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            int digito = random.nextInt(10); // Genera un número entre 0 y 9
            key += digito;
        }
        return Long.valueOf(key);
    }

    public CuentaBancaria editAccount(CuentaBancaria account, AccountRequestEditDTO edit) {
        account.setSaldo(edit.saldo());
        return account;
    }


}
