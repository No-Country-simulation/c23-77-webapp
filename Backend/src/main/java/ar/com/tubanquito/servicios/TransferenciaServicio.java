package ar.com.tubanquito.servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.tubanquito.dto.request.CreateTransferDTO;
import ar.com.tubanquito.entidades.Transferencia;
import ar.com.tubanquito.entidades.TransferenciaEstado;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancaria;
import ar.com.tubanquito.infra.error.BankAccountNotFoundException;
import ar.com.tubanquito.mapper.CuentaBancariaMapper;
import ar.com.tubanquito.mapper.TransferenciaMapper;
import ar.com.tubanquito.repositorios.CuentaBancariaRepositorio;
import ar.com.tubanquito.repositorios.TransferenciaRepositorio;
import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;

@Service
public class TransferenciaServicio {

    @Autowired
    private TransferenciaRepositorio transferenciaRepositorio;

    @Autowired
    private CuentaBancariaRepositorio cuentas;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private CuentaBancariaMapper mapper;

    @Autowired
    private TransferenciaMapper transferenciaMapper;

    public List<Transferencia> getTransferenciasByIdUsuario(Long idUsuario, Long idCuenta) {

        return null;

    }

    @Transactional
    public Object create(CreateTransferDTO transfer) {

        if (transfer.aliasDestino() == null && transfer.cbuDestino() == null) {
            throw new NullPointerException("Proporcionar al menos 1 dato de destino");
        }

        Long idUser = transfer.idUsuarioOrigen();
        Usuario userOrigen = usuarioRepositorio
                .findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CuentaBancaria cuentaOrigen = userOrigen
                .getCuentasBancarias()
                .stream()
                .filter(new Predicate<CuentaBancaria>() {

                    @Override
                    public boolean test(CuentaBancaria t) {
                        return t.getCbu().equals(transfer.cbuOrigen());
                    }

                })
                .findFirst()
                .orElseThrow(() -> new BankAccountNotFoundException("Account not found in origin"));

        CuentaBancaria cuentaDestino = (transfer.cbuDestino() != null)
                ? findAccountByCBU(transfer.cbuDestino(), new Error("Account not found in destiny"))
                : findAccountByALIAS(transfer.aliasDestino(), new Error("Account not found in destiny"));

        cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(BigDecimal.valueOf(transfer.monto())));
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(BigDecimal.valueOf(transfer.monto())));

        Transferencia transferencia = Transferencia
        .builder()
        .fechaCreacion(LocalDate.now())
        .cuentaDestino(cuentaDestino)
        .cuentaOrgien(cuentaOrigen)
        .montoTransferido(transfer.monto())
        .estado(TransferenciaEstado.COMPLETE.toString())
        .build();

        transferenciaRepositorio.save(transferencia);

        cuentas.save(cuentaOrigen);
        cuentas.save(cuentaDestino);

        return transferenciaMapper.elaborateTransferResponse(transferencia);

    }

    private CuentaBancaria findAccountByALIAS(String aliasDestino, Error error) {
        return cuentas
            .findByAlias(aliasDestino)
            .orElseThrow(() -> new BankAccountNotFoundException(error.getMessage()));
    }

    private CuentaBancaria findAccountByCBU(Long cbu, Error error) {
        return cuentas
                .findByCbu(cbu)
                .orElseThrow(() -> new BankAccountNotFoundException(error.getMessage()));
    }



}
