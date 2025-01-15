package ar.com.tubanquito.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.tubanquito.entidades.Transferencia;

public interface TransferenciaRepositorio extends JpaRepository<Long, Transferencia>{
    
}
