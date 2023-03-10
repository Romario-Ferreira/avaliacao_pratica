package com.francaemp.avaliacao_pratica.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.francaemp.avaliacao_pratica.services.exceptions.AddressException;
import com.francaemp.avaliacao_pratica.services.exceptions.MainAddressException;
import com.francaemp.avaliacao_pratica.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound (ObjectNotFoundException e, HttpServletRequest request){
		var standardError = new StandardError(Instant.now(),HttpStatus.NOT_FOUND.value(),e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);		
	}
	
	@ExceptionHandler(MainAddressException.class)
	public ResponseEntity<StandardError> objectNotFound (MainAddressException e, HttpServletRequest request){
		var standardError = new StandardError(Instant.now(),HttpStatus.NOT_FOUND.value(),e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);		
	}

	@ExceptionHandler(AddressException.class)
	public ResponseEntity<StandardError> objectNotFound (AddressException e, HttpServletRequest request){
		var standardError = new StandardError(Instant.now(),HttpStatus.NOT_FOUND.value(),e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);		
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> dateTimeParse (HttpMessageNotReadableException e, HttpServletRequest request){
		String error = "birthDate format invalid, try dd/mm/yyyy";
		var standardError = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), error, request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}
}
