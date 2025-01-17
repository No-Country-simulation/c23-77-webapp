package ar.com.tubanquito.dto.response;

import java.time.LocalDate;

public record PersonaResponse(
        Long id,
        String nombre,
        String apPaterno,
        String apMaterno,
        String sexo,
        String lugarNacimiento,
        LocalDate fechaNacimiento,
        String dni,
        Long usuarioId
) {}
