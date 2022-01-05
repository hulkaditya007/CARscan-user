package com.assignment.user.exception;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

	 @ExceptionHandler({UserException.class})
	 protected ResponseEntity<Object> handleApiException(UserException ex)
	 {
		 ErrorDetails errorDetails = new ErrorDetails(ex.getStatusCode().getCode(),ex.getStatusCode(),ex.getMessage(),Instant.now());
		 return ResponseEntity.status(errorDetails.getStatus()).body(errorDetails);
	 }
	 
	 @ExceptionHandler({Exception.class})
	 protected ResponseEntity<Object> handleApiException(Exception ex)
	 {
		 ErrorDetails errorDetails = new ErrorDetails(500,StatusCode.GENERIC_EXCEPTION,ex.getMessage(),Instant.now());
		 return ResponseEntity.status(errorDetails.getStatus()).body(errorDetails);
	 }
}
