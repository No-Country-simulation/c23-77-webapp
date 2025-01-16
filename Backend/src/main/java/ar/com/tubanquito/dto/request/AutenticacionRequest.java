package ar.com.tubanquito.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutenticacionRequest(
        @NotBlank @Email String email,
        @NotBlank String password
) {}
