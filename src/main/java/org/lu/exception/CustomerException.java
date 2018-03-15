package org.lu.exception;

public class CustomerException extends RuntimeException {

	private static final long serialVersionUID = -8122574379186221768L;

	public CustomerException(String message) {
		super(message);
	}

	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerException(Throwable cause) {
		super(cause);
	}
}
