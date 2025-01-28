package ar.com.tubanquito.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.tubanquito.entidades.Transferencia;
import java.util.List;
import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancaria;


public interface TransferenciaRepositorio extends JpaRepository<Transferencia, Long>{
    
    List<Transferencia> findByCuentaOrgien(CuentaBancaria cuentaOrgien);
}
