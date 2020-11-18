package com.digitalhouse.MeAdote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="User already exists")
public class ObjectAlreadyExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4704747562345109202L;

	public ObjectAlreadyExistsException() {
		super("O objeto já existe.");
	}
}
