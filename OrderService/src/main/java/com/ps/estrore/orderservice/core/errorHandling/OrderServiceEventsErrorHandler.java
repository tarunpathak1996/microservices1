package com.ps.estrore.orderservice.core.errorHandling;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderServiceEventsErrorHandler implements ListenerInvocationErrorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceEventsErrorHandler.class);

	@Override
	public void onError(Exception exception, EventMessage<?> event, EventMessageHandler eventHandler) throws Exception {

		String message = String.format(
				"Exception on Event: eventType [ %s ] , eventIdentifier [ %s], and Exception is : %s",
				event.getPayloadType(), event.getIdentifier(),
				exception.getMessage() != null ? exception.getMessage() : exception.toString());
		
		LOGGER.error(message);
		throw exception;

	}

}
