package com.digitalhouse.MeAdote.exception;

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
