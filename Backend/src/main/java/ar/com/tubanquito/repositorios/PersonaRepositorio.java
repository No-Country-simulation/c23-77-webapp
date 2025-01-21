package ar.com.tubanquito.repositorios;

import ar.com.tubanquito.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
    Optional<Object> findByDni(String dni);
}
