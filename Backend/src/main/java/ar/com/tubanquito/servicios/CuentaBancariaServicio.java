package ar.com.tubanquito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.tubanquito.dto.request.AccountRequestEditDTO;
import ar.com.tubanquito.dto.request.CreateBankAccountDTO;

import ar.com.tubanquito.dto.response.AccountResponseDTO;
import ar.com.tubanquito.entidades.CuentaBancaria;
import ar.com.tubanquito.repositorio.CuentaBancariaRepositorio;

@Service
public class CuentaBancariaServicio implements CuentaBancariaServicioUI {


    @Autowired
    private CuentaBancariaRepositorio cuentasBancarias;

    
    @Override
    public List<CuentaBancaria> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public AccountResponseDTO editAccount(AccountRequestEditDTO account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editAccount'");
    }

    @Override
    public void deleteAccount(Long idUser, Long idAccount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccount'");
    }

    @Override
    public CuentaBancaria getAccountById(Long idAccount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountById'");
    }

    @Override
    public List<CuentaBancaria> getAccountsByIdUser(Long idUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountsByIdUser'");
    }

    @Override
    public AccountResponseDTO createAccount(CreateBankAccountDTO user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }

    

}
