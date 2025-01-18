package ar.com.tubanquito.validaciones.personas;

import ar.com.tubanquito.dto.request.PersonaRequest;
import ar.com.tubanquito.repositorios.PersonaRepositorio;
import ar.com.tubanquito.validaciones.Validador;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDniUnico implements Validador<PersonaRequest> {

    private final PersonaRepositorio personaRepositorio;

    public ValidadorDniUnico(PersonaRepositorio personaRepositorio) {
        this.personaRepositorio = personaRepositorio;
    }

    @Override
    public void validar(PersonaRequest datos) {
        if (personaRepositorio.findByDni(datos.dni()).isPresent()) {
            throw new RuntimeException("El DNI ya está registrado: " + datos.dni());
        }
    }
}
