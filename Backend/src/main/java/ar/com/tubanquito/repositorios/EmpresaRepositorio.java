package ar.com.tubanquito.repositorios;

import ar.com.tubanquito.entidades.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
    Optional<Object> findByNombre(String nombre);
}
