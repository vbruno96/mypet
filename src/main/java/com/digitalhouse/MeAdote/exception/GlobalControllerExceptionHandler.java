package com.digitalhouse.MeAdote.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation() {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Data integrity violation", "Violação da integridade de dados");
        
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Object> handleInvalidCredentials() {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Invalid credentials", "Usuário e/ou senha inválidos");
        
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> handleObjectNotFound() {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Object not found", "Objeto não encontrado");
        
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
	
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(ObjectAlreadyExistsException.class)
	public ResponseEntity<Object> handleObjectAlreadyExists() {
		ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, "Object already exists", "O objeto já existe");
        
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
