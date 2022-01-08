package com.assignment.user.exception;

/**
 * To define custom exception if any user related exception is found.
 */
public class UserException extends Exception {

	private static final long serialVersionUID = 1L;

	private final StatusCode statusCode;

	public UserException(StatusCode statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public UserException(String message, Throwable cause, StatusCode statusCode) {
		super(message, cause);
		this.statusCode = statusCode;
	}

	public UserException(String message, StatusCode statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public StatusCode getStatusCode() {
		return this.statusCode;
	}

}
