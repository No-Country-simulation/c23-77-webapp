package ar.com.tubanquito.Exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ar.com.tubanquito.dto.response.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Hidden;

@ControllerAdvice
@Hidden
public class GlobalException {
    
       
    @ExceptionHandler(BankAccountNotFoundException.class)
    public ResponseEntity<?> handleNullPointerException(BankAccountNotFoundException e){
        return ResponseEntity.badRequest().body(error(e));
    }

    private ErrorResponseDTO error(Exception e){
        return ErrorResponseDTO.builder().error(e.getMessage()).build();
    }
}
