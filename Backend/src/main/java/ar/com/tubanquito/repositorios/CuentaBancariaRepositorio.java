package ar.com.tubanquito.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.tubanquito.entidades.CuentaBancaria.CuentaBancaria;



@Repository
public interface CuentaBancariaRepositorio extends JpaRepository<CuentaBancaria, Long>{
    List<CuentaBancaria> findByUsuarioId(Long usuarioId);
    Optional<CuentaBancaria> findByCbu(Long cbu);
    Optional<CuentaBancaria> findByAlias(String alias);
}
