package ar.com.tubanquito.servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.tubanquito.dto.request.AccountRequestEditDTO;
import ar.com.tubanquito.dto.request.CreateBankAccountDTO;

import ar.com.tubanquito.dto.response.AccountResponseDTO;
import ar.com.tubanquito.entidades.Empresa;
import ar.com.tubanquito.entidades.HistorialTransacciones;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancaria;
import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancariaTipo;
import ar.com.tubanquito.infra.error.BankAccountNotFoundException;
import ar.com.tubanquito.mapper.CuentaBancariaMapper;
import ar.com.tubanquito.repositorios.CuentaBancariaRepositorio;
import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;

@Service
public class CuentaBancariaServicio implements CuentaBancariaServicioUI {


    @Autowired
    private CuentaBancariaRepositorio cuentasBancarias;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired 
    private CuentaBancariaMapper mapper;


    @Override
    public List<AccountResponseDTO> getAll() {
       return cuentasBancarias
       .findAll()
       .stream()
       .map(mapper::toGetDTO)
       .toList();
    }

    @Override
    public AccountResponseDTO getAccountById(Long idAccount) {
        CuentaBancaria account = cuentasBancarias.findById(idAccount)
        .orElseThrow(() -> new ar.com.tubanquito.infra.error.BankAccountNotFoundException("account not found"));
        return mapper.toGetDTO(account);
    }

    @Override
    public List<AccountResponseDTO> getAccountsByIdUser(Long idUser) {
        List<CuentaBancaria> cuentas = cuentasBancarias.findByUsuarioId(idUser);
        return cuentas.stream().map(mapper::toGetDTO).toList();
    }

    @Override
    public AccountResponseDTO createAccount(CreateBankAccountDTO account) throws Exception {   
        Usuario user  = usuarioRepositorio
        .findById(account.id())
        .orElseThrow(() -> new NullPointerException("USER not found"));

        CuentaBancaria cuentaBancaria = mapper.buildAccount(account, user);
        cuentasBancarias.save(cuentaBancaria);

        user.getCuentasBancarias().add(cuentaBancaria);
        usuarioRepositorio.save(user);
        return mapper.toGetDTO(cuentaBancaria);
    }



    @Override
    public AccountResponseDTO editAccount(Long idUser, Long idAccount, AccountRequestEditDTO accountR) {
        
        Usuario user = usuarioRepositorio
        .findById(idUser)
        .orElseThrow(() -> new NullPointerException("User not found"));

        CuentaBancaria account = user
        .getCuentasBancarias()
        .stream()
        .filter(a -> a.getId() == idAccount)
        .findFirst()
        .orElseThrow(() -> new BankAccountNotFoundException("account not found"));
        
        account = mapper.editAccount(account, accountR);
        cuentasBancarias.save(account);
        return mapper.toGetDTO(account);
       
        
    }

    @Override
    @Transactional
    public void deleteAccount(Long idUser, Long idAccount) throws Exception{
        Usuario user =  usuarioRepositorio.findById(idUser).orElseThrow();
        Iterator<CuentaBancaria> it = user
        .getCuentasBancarias()
        .iterator();

        while (it.hasNext()) {
            CuentaBancaria cuenta = it.next();
            if (cuenta.getId() == idAccount) {
                cuentasBancarias.deleteById(idAccount);
                user.getCuentasBancarias().remove(cuenta);
                break;
            } 
        }
        
        
    }
  

}
