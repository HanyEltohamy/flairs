package com.ft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleInvoiceNotFoundException(UserNotFoundException exc) {

		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(exc.getMessage(), HttpStatus.NOT_FOUND.value(), "DATA NOT FOUND FOR REQUESTED ID", "404"),
				HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {

		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value(), "Account NOT FOUND FOR REQUESTED ID", "404"),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException ex) {

		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value(), "User with this email already exists", "400"),
				HttpStatus.BAD_REQUEST);
	}
}
