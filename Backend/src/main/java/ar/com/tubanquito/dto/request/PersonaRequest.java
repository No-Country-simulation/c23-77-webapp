package ar.com.tubanquito.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PersonaRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre,

        @NotBlank(message = "El apellido paterno no puede estar vacío")
        @Size(max = 100, message = "El apellido paterno no puede superar los 100 caracteres")
        String apPaterno,

        @NotBlank(message = "El apellido materno no puede estar vacío")
        @Size(max = 100, message = "El apellido materno no puede superar los 100 caracteres")
        String apMaterno,

        @Pattern(regexp = "Masculino|Femenino", message = "El sexo debe ser 'Masculino' o 'Femenino'")
        String sexo,

        @Size(max = 100, message = "El lugar de nacimiento no puede superar los 100 caracteres")
        String lugarNacimiento,

        @NotNull(message = "La fecha de nacimiento no puede estar vacía")
        LocalDate fechaNacimiento,

        @NotBlank(message = "El DNI no puede estar vacío")
        @Size(max = 20, message = "El DNI no puede superar los 20 caracteres")
        String dni,

        @NotNull(message = "El ID de usuario asociado no puede estar vacío")
        Long usuarioId
) {}
