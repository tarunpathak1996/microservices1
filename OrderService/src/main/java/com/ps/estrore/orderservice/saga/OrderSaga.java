package com.ps.estrore.orderservice.saga;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ps.estrore.core.commands.ReserveProductCommand;
import com.ps.estrore.core.events.PaymentProcessedEvent;
import com.ps.estrore.core.events.ProductReservedEvent;
import com.ps.estrore.core.models.PaymentDetails;
import com.ps.estrore.core.query.FetchUserPaymentDetailsQuery;
import com.ps.estrore.orderservice.command.ApproveOrderCommand;
import com.ps.estrore.orderservice.core.events.OrderApprovedEvent;
import com.ps.estrore.orderservice.core.events.OrderCreatedEvent;

@Saga
public class OrderSaga {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderSaga.class);

	@Autowired
	private transient CommandGateway commandGateway;

	@Autowired
	private transient QueryGateway queryGateway;

	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void handle(OrderCreatedEvent event) {

		LOGGER.info("OrderCreatedEvent for orderId :" + event.getOrderId() + " and productId: " + event.getProductId());
		LOGGER.info("ReserveProductCommand  raised");
		ReserveProductCommand command = ReserveProductCommand.builder().productId(event.getProductId())
				.userId(event.getUserId()).quantity(event.getQuantity()).orderId(event.getOrderId()).build();

		commandGateway.send(command, new CommandCallback<ReserveProductCommand, Object>() {
			@Override
			public void onResult(CommandMessage<? extends ReserveProductCommand> commandMessage,
					CommandResultMessage<? extends Object> commandResultMessage) {

				if (commandResultMessage.isExceptional()) {
					// start my compensating transation
				}

			}

		});
	}

	@SagaEventHandler(associationProperty = "orderId")
	public void handle(ProductReservedEvent event) {

		LOGGER.info(
				"ProductReservedEvent for orderId :" + event.getOrderId() + " and productId: " + event.getProductId()); // process
																														// user
																														// payment
		FetchUserPaymentDetailsQuery query = FetchUserPaymentDetailsQuery.builder().userId(event.getUserId()).build();
		PaymentDetails paymentDetails = null;
		try {
			paymentDetails = queryGateway.query(query, ResponseTypes.instanceOf(PaymentDetails.class)).join();
		} catch (Exception e) {
			LOGGER.error("Unable to fecth payment details for orderId: " + event.getOrderId() + ",  userId: "
					+ event.getUserId()+", Exception is: "+e.getMessage()); // start compensating transaction 
		}
		LOGGER.info("Successfully fetched payment details for order: " + event.getOrderId() + " and productId: "
				+ event.getProductId());
	} 

	@SagaEventHandler(associationProperty = "orderId")
	public void handle(PaymentProcessedEvent event) {
		LOGGER.info(
				"PaymentProcessedEvent for orderId :" + event.getOrderId() + " and paymentId: " + event.getPaymentId());
		// make approve order command
		ApproveOrderCommand command = ApproveOrderCommand.builder().orderId(event.getOrderId()).build();
		commandGateway.send(command);
	}

	@EndSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void handle(OrderApprovedEvent event) {
		LOGGER.info("OrderApprovedEvent for orderId :" + event.getOrderId() + " and status: " + event.getOrderStatus());
		// make approve order command
		// SagaLifecycle.end();
	}

}
