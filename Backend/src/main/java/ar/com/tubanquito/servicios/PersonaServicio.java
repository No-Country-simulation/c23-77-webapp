package ar.com.tubanquito.servicios;

import ar.com.tubanquito.dto.request.PersonaRequest;
import ar.com.tubanquito.dto.response.PersonaResponse;
import ar.com.tubanquito.entidades.Persona;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.mapper.PersonaMapper;
import ar.com.tubanquito.repositorios.PersonaRepositorio;
import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import ar.com.tubanquito.validaciones.Validador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServicio {

    private final PersonaRepositorio personaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final PersonaMapper personaMapper;
    private final List<Validador<PersonaRequest>> validadores;

    public PersonaServicio(PersonaRepositorio personaRepositorio, UsuarioRepositorio usuarioRepositorio, PersonaMapper personaMapper, List<Validador<PersonaRequest>> validadores) {
        this.personaRepositorio = personaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.personaMapper = personaMapper;
        this.validadores = validadores;
    }

    public PersonaResponse crearPersona(PersonaRequest request) {
        Usuario usuario = usuarioRepositorio.findById(request.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        validadores.forEach(v -> v.validar(request));

        Persona persona = personaMapper.toEntity(request, usuario);
        Persona guardada = personaRepositorio.save(persona);
        return personaMapper.toResponse(guardada);
    }

    public PersonaResponse obtenerPersonaPorId(Long id) {
        Persona persona = personaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        return personaMapper.toResponse(persona);
    }

    public List<PersonaResponse> listarPersonas() {
        return personaRepositorio.findAll()
                .stream()
                .map(personaMapper::toResponse)
                .toList();
    }

    public PersonaResponse actualizarPersona(Long id, PersonaRequest request) {
        Persona persona = personaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        persona.setNombre(request.nombre());
        persona.setApPaterno(request.apPaterno());
        persona.setApMaterno(request.apMaterno());
        persona.setSexo(request.sexo());
        persona.setLugarNacimiento(request.lugarNacimiento());
        persona.setFechaNacimiento(request.fechaNacimiento());
        persona.setDni(request.dni());

        Persona actualizada = personaRepositorio.save(persona);
        return personaMapper.toResponse(actualizada);
    }

    public void eliminarPersona(Long id) {
        Persona persona = personaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        personaRepositorio.delete(persona);
    }
}

