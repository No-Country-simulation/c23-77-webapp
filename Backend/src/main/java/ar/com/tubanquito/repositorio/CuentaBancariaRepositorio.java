package ar.com.tubanquito.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.tubanquito.entidades.CuentaBancaria;

@Repository
public interface CuentaBancariaRepositorio extends JpaRepository<CuentaBancaria, Long>{

}
