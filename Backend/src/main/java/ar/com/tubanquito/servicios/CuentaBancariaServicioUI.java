package ar.com.tubanquito.servicios;

import java.util.List;

import ar.com.tubanquito.dto.request.AccountRequestEditDTO;
import ar.com.tubanquito.dto.request.CreateBankAccountDTO;
import ar.com.tubanquito.dto.response.AccountResponseDTO;
import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancaria;

public interface CuentaBancariaServicioUI {
    
    public List<CuentaBancaria> getAll();
    public CuentaBancaria getAccountById(Long idAccount);
    public List<CuentaBancaria> getAccountsByIdUser(Long idUser);
    
    public AccountResponseDTO createAccount(CreateBankAccountDTO account);
    public AccountResponseDTO editAccount(AccountRequestEditDTO account);

    public void deleteAccount(Long idUser, Long idAccount);

}
