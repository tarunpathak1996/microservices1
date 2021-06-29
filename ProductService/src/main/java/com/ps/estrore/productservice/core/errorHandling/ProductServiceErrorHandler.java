package com.ps.estrore.productservice.core.errorHandling;

import java.time.Instant;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ps.estrore.productservice.core.constant.MessageType;

@ControllerAdvice
public class ProductServiceErrorHandler {
	
	@ExceptionHandler(value= {IllegalStateException.class})
	public HttpEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest webReq){
		
		ErrorMessage errorMessage = new ErrorMessage(Instant.now(), MessageType.ERROR.getValue(), ex.getMessage() != null ? ex.getMessage() : ex.toString());
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(value= {CommandExecutionException.class})
	public HttpEntity<Object> handleCommandExecutionException(CommandExecutionException ex, WebRequest webReq){
		
		ErrorMessage errorMessage = new ErrorMessage(Instant.now(), MessageType.ERROR.getValue(), ex.getMessage() != null ? ex.getMessage() : ex.toString());
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(value= {Exception.class})
	public HttpEntity<Object> handleException(Exception ex, WebRequest webReq){
		
		ErrorMessage errorMessage = new ErrorMessage(Instant.now(), MessageType.ERROR.getValue(), ex.getMessage() != null ? ex.getMessage() : ex.toString());
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

}
