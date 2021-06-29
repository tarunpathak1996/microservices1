package com.ps.estrore.orderservice.core.errorHandling;


import java.time.Instant;

import org.axonframework.commandhandling.CommandExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.ps.estrore.orderservice.core.constants.MessageType;

@ControllerAdvice
public class OrderServiceErrorHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceErrorHandler.class);
	
	@ExceptionHandler(value = {IllegalStateException.class})
	public HttpEntity<ErrorMessage> handleIllegalStateException(IllegalStateException ex, WebRequest req){
		
		LOGGER.error(String.format("IllegalStateException on request %s , exception is: %s",WebRequest.REFERENCE_REQUEST, ex.getMessage()));
		ErrorMessage errorMessage = new ErrorMessage(Instant.now(), MessageType.ERROR.getValue(), ex.getMessage() != null ? ex.getMessage() : ex.toString());
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {CommandExecutionException.class})
	public HttpEntity<ErrorMessage> handleCommandExecutionException(CommandExecutionException ex, WebRequest req){
		
		LOGGER.error(String.format("CommandExecutionException on request %s , exception is: %s",WebRequest.REFERENCE_REQUEST, ex.toString()));
		ErrorMessage errorMessage = new ErrorMessage(Instant.now(), MessageType.ERROR.getValue(), ex.getLocalizedMessage());
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value= {Exception.class})
	public HttpEntity<Object> handleException(Exception ex, WebRequest webReq){
		
		LOGGER.error(String.format("Exception on request %s , exception is: %s",WebRequest.REFERENCE_REQUEST, ex.getMessage()));
		ErrorMessage errorMessage = new ErrorMessage(Instant.now(), MessageType.ERROR.getValue(), ex.getMessage() != null ? ex.getMessage() : ex.toString());
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
