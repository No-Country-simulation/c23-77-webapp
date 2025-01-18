package ar.com.tubanquito.validaciones.personas;

import ar.com.tubanquito.dto.request.PersonaRequest;
import ar.com.tubanquito.validaciones.Validador;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidadorFechaNacimiento implements Validador<PersonaRequest> {

    @Override
    public void validar(PersonaRequest datos) {
        if (datos.fechaNacimiento().isAfter(LocalDate.now())) {
            throw new RuntimeException("La fecha de nacimiento no puede ser en el futuro");
        }
    }
}
