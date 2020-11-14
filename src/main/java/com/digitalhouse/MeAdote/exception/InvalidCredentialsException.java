package com.digitalhouse.MeAdote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Invalid login credentials")
public class InvalidCredentialsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2073476396443456095L;

	public InvalidCredentialsException() {
		super("Credenciais de login inválidas");
	}

}
