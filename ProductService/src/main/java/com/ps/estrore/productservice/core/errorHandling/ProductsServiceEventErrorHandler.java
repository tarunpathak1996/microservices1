package com.ps.estrore.productservice.core.errorHandling;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductsServiceEventErrorHandler implements ListenerInvocationErrorHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductsServiceEventErrorHandler.class);

	@Override
	public void onError(Exception exception, EventMessage<?> event, EventMessageHandler eventHandler) throws Exception {
		
		LOGGER.error("Exception on event: event Type ["+event.getPayloadType()+"], event Identifier ["+event.getIdentifier()+"], Exception: "+exception.getMessage());
		throw exception;
		// TODO Auto-generated method stub
		
	}

}
