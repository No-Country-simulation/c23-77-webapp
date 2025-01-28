package ar.com.tubanquito.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe ser válido")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String contrasena,

        @NotBlank(message = "El teléfono no puede estar vacío")
        @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
        String telefono,

        @NotNull(message = "El rol no puede estar vacío")
        String rol // "ADMIN", "PARTICULAR", "EMPLEADO", etc.
) {}
