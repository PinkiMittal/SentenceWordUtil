package com.ubs.exceptions;

public class InvalidInputException extends RuntimeException {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 1561323384495048609L;
	
	public InvalidInputException() {
		super();
	}
	
	public InvalidInputException(String message) {
		super(message);
	}
}
