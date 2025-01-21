package ar.com.tubanquito.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DireccionRequest(
        @NotBlank(message = "La calle no puede estar vacía")
        @Size(max = 255, message = "La calle no puede superar los 255 caracteres")
        String calle,

        @NotBlank(message = "La ciudad no puede estar vacía")
        @Size(max = 100, message = "La ciudad no puede superar los 100 caracteres")
        String ciudad,

        @Size(max = 100, message = "El estado/provincia no puede superar los 100 caracteres")
        String estadoProvincia,

        @Size(max = 20, message = "El código postal no puede superar los 20 caracteres")
        String cp,

        @NotBlank(message = "El país no puede estar vacío")
        @Size(max = 100, message = "El país no puede superar los 100 caracteres")
        String pais,

        Long usuarioId // Relación opcional con el usuario
) {}
