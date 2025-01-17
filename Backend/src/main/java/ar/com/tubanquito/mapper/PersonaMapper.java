package ar.com.tubanquito.mapper;

import ar.com.tubanquito.dto.request.PersonaRequest;
import ar.com.tubanquito.dto.response.PersonaResponse;
import ar.com.tubanquito.entidades.Persona;
import ar.com.tubanquito.entidades.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    public Persona toEntity(PersonaRequest request, Usuario usuario) {
        Persona persona = new Persona();
        persona.setNombre(request.nombre());
        persona.setApPaterno(request.apPaterno());
        persona.setApMaterno(request.apMaterno());
        persona.setSexo(request.sexo());
        persona.setLugarNacimiento(request.lugarNacimiento());
        persona.setFechaNacimiento(request.fechaNacimiento());
        persona.setDni(request.dni());
        persona.setUsuario(usuario);
        return persona;
    }

    public PersonaResponse toResponse(Persona persona) {
        return new PersonaResponse(
                persona.getId(),
                persona.getNombre(),
                persona.getApPaterno(),
                persona.getApMaterno(),
                persona.getSexo(),
                persona.getLugarNacimiento(),
                persona.getFechaNacimiento(),
                persona.getDni(),
                persona.getUsuario().getId()
        );
    }
}
