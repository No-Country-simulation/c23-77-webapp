package ar.com.tubanquito.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.tubanquito.entidades.Transferencia;

public interface TransferenciaRepositorio extends JpaRepository<Transferencia, Long>{
    
}
