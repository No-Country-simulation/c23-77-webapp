package ar.com.tubanquito.dto.response;

import lombok.Builder;

@Builder
public record ErrorResponseDTO(
    String error
) {
    
}
