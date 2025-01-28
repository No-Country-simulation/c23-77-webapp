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
public class TransferenciaMapper {
    
    public Object elaborateTransferResponse(Transferencia transferencia) {
        return TransferResponseDTO.builder().cbuOrigen(transferencia.getCuentaOrgien().getCbu()
        ).cbuDestino(transferencia.getCuentaDestino().getCbu()
        ).monto(transferencia.getMontoTransferido()
        ).fecha(transferencia.getFechaCreacion()
        ).estado(transferencia.getEstado()
        ).build();
    }
}
