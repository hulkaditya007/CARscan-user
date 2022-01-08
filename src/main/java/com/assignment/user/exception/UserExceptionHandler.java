package com.assignment.user.exception;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class is created to handle the error thrown from controller class.
 */
@ControllerAdvice
public class UserExceptionHandler {

	/**
	 * This method is created to handle UserException, Which is user define
	 * exception.
	 *
	 * @param ex- UserException object
	 * @return - It will return the response entity with specific error status
	 *         code and error body.
	 */
	@ExceptionHandler({ UserException.class })
	protected ResponseEntity<Object> handleApiException(UserException ex) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getStatusCode().getCode(), ex.getStatusCode(), ex.getMessage(),
				Instant.now());
		return ResponseEntity.status(errorDetails.getStatus()).body(errorDetails);
	}

	/**
	 * This method will handle all the generic exception
	 *
	 * @param ex-Exception object
	 * @return - It will return the response entity with its status code and
	 *         error body.
	 */
	@ExceptionHandler({ Exception.class })
	protected ResponseEntity<Object> handleApiException(Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(500, StatusCode.GENERIC_EXCEPTION, ex.getMessage(), Instant.now());
		return ResponseEntity.status(errorDetails.getStatus()).body(errorDetails);
	}
}
