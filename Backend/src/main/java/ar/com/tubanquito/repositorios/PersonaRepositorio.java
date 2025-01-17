package ar.com.tubanquito.repositorios;

import ar.com.tubanquito.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
    Optional<Object> findByDni(String dni);
}
