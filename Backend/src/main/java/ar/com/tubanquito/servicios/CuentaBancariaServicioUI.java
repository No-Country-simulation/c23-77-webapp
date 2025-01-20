package ar.com.tubanquito.servicios;

import java.util.List;

import ar.com.tubanquito.dto.request.AccountRequestEditDTO;
import ar.com.tubanquito.dto.request.CreateBankAccountDTO;
import ar.com.tubanquito.dto.response.AccountResponseDTO;
import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancaria;

public interface CuentaBancariaServicioUI {
    
    public List<AccountResponseDTO> getAll();
    public AccountResponseDTO getAccountById(Long idAccount);
    public List<AccountResponseDTO> getAccountsByIdUser(Long idUser);
    
    public AccountResponseDTO createAccount(CreateBankAccountDTO account) throws Exception;
    public AccountResponseDTO editAccount(Long idUser, Long idAccount, AccountRequestEditDTO account);

    public void deleteAccount(Long idUser, Long idAccount) throws Exception;

}
