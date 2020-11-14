package com.digitalhouse.MeAdote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Data integrity violated")
public class DataIntegrityViolationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1575746303061795904L;

	public DataIntegrityViolationException() {
		super("Erro de integridade de dados");
	}
	
	public DataIntegrityViolationException(String error) {
		super("Erro de integridade de dados: " + error);
	}

}
