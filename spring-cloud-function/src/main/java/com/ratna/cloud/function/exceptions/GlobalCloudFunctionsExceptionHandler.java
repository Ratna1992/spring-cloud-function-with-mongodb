package com.ratna.cloud.function.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalCloudFunctionsExceptionHandler {

	@ExceptionHandler(value = BookNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException exception) {
		return new ResponseEntity<ErrorResponse>(getErrorResponse(exception), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handle(Exception exception) {
		if (exception instanceof IllegalStateException) {
			return new ResponseEntity<ErrorResponse>(getErrorResponse("No Object Specified in the request"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ErrorResponse>(getErrorResponse(exception), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorResponse getErrorResponse(Throwable exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(LocalDateTime.now());
		return errorResponse;
	}

	private ErrorResponse getErrorResponse(String message) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(message);
		errorResponse.setTimeStamp(LocalDateTime.now());
		return errorResponse;
	}

}
