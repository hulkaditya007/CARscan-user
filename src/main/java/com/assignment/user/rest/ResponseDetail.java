package com.assignment.user.rest;

import java.time.Instant;

import com.assignment.user.exception.StatusCode;

/**
 * This class is a POJO class for response body.
 *
 */
public class ResponseDetail {

	private final int status;
	private StatusCode message;
	private final Object responseBody;
	private final Instant timestamp;

	public ResponseDetail(int status, StatusCode statusCode, Object responseBody, Instant timestamp) {
		super();
		this.status = status;
		this.message = statusCode;
		this.responseBody = responseBody;
		this.timestamp = timestamp;
	}

	public StatusCode getMessage() {
		return message;
	}

	public void setMessage(StatusCode message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

}
