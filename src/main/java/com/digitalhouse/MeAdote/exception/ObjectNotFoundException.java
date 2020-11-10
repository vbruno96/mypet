package com.digitalhouse.MeAdote.exception;

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
