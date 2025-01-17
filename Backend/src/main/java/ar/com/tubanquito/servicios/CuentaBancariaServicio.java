package ar.com.tubanquito.servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import ar.com.tubanquito.repositorio.CuentaBancariaRepositorio;
import ar.com.tubanquito.repositorio.UsuarioRepositorio;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Service
public class CuentaBancariaServicio implements CuentaBancariaServicioUI {


    @Autowired
    private CuentaBancariaRepositorio cuentasBancarias;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @Override
    public List<CuentaBancaria> getAll() {
       return cuentasBancarias.findAll();
    }

    @Override
    public CuentaBancaria getAccountById(Long idAccount) {
        CuentaBancaria account = cuentasBancarias.findById(idAccount)
        .orElseThrow(() -> new NullPointerException("account not found"));
        return account;
    }

    @Override
    public List<CuentaBancaria> getAccountsByIdUser(Long idUser) {
        List<CuentaBancaria> cuentas = cuentasBancarias.findByUsuarioId(idUser);
        return cuentas;
    }

    @Override
    public AccountResponseDTO createAccount(CreateBankAccountDTO account) {
        
        Usuario user  = usuarioRepositorio
        .findById(account.idUsuario())
        .orElseThrow(() -> new NullPointerException("USER not found"));

        CuentaBancaria cuentaBancaria = CuentaBancaria
        .builder()
        .usuario(user)
        .tipoCuenta(account.tipo().toString())
        .saldo(account.saldoInicial())
        .moneda("USD")
        .build();

        cuentasBancarias.save(cuentaBancaria);
        return toGetDTO(cuentaBancaria);
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

    
    private AccountResponseDTO toGetDTO(CuentaBancaria cuenta){

        return AccountResponseDTO.builder()
        .name(cuenta.getUsuario().getUsername())
        .status("CREATED")
        .saldo(cuenta.getSaldo())
        .tipo(cuenta.getTipoCuenta().toString())
        .build();
    }   
/** 
     
    @Column(length = 10, nullable = false)
    private String moneda;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal saldo;

    @Column(length = 50, nullable = false)
    private String tipoCuenta;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "banco_emisor")
    private String bancoEmisor;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Usuario usuario;


    @ManyToMany(mappedBy = "cuentaBancarias")
    private Set<Empresa> empresa;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private Set<HistorialTransacciones> historialTransacciones;
*/
    
}
