package com.digitalhouse.MeAdote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Object not found")
public class ObjectNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6603961104050659391L;

	public ObjectNotFoundException() {
		super("O registro não existe");
	}
	
	public ObjectNotFoundException(String object) {
		super("O registro " + object + " não existe");
	}
}
