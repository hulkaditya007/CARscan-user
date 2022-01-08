package com.assignment.user.exception;

import java.time.Instant;

/**
 * This is a helper class used in UserExceptionHandler.java.
 *
 */
public class ErrorDetails {

	private final int status;
	private StatusCode error;
	private final String message;
	private final Instant timestamp;

	public ErrorDetails(int status, StatusCode error, String message, Instant timestamp) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
		this.timestamp = timestamp;
	}

	public StatusCode getError() {
		return error;
	}

	public void setError(StatusCode error) {
		this.error = error;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

}
