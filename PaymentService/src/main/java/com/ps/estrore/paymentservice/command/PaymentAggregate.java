package com.ps.estrore.paymentservice.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.ps.estrore.core.commands.ProcessPaymentCommand;
import com.ps.estrore.core.events.PaymentProcessedEvent;

@Aggregate
public class PaymentAggregate {
	
	@AggregateIdentifier
	private String paymentId;
	private String orderId;
	
	@CommandHandler
	public PaymentAggregate(ProcessPaymentCommand command) {
		
		// validation
		if(command.getPaymentDetails() == null) {
			throw new IllegalArgumentException("Missing payment details");
		}
		PaymentProcessedEvent event = PaymentProcessedEvent.builder()
				.paymentId(command.getPaymentId())
				.orderId(command.getOrderId())
				.build();
		AggregateLifecycle.apply(event);
		
	}
	
	@EventSourcingHandler
	protected void on(PaymentProcessedEvent event) {
		this.paymentId = event.getPaymentId();
		this.orderId = event.getOrderId();
	}
	

}
