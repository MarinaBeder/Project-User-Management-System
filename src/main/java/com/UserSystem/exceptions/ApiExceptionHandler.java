package com.UserSystem.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { UnauthorizedException.class })
	public ResponseEntity<Object> handleApiRequestNotFoundException(UnauthorizedException e) {
		ApiExcepion apiException = new ApiExcepion(e.getMessage(),

				HttpStatus.UNAUTHORIZED, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<Object>(apiException, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(value = { NonUniqueEmailException.class })
	public ResponseEntity<Object> handleApiRequestNonUniqueUsernameException(NonUniqueEmailException e) {
		ApiExcepion apiException = new ApiExcepion(e.getMessage(),

				HttpStatus.CONFLICT, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<Object>(apiException, HttpStatus.CONFLICT);
	}
}
