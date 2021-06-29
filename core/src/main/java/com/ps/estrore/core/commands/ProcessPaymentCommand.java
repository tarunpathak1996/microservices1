package com.ps.estrore.core.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.ps.estrore.core.models.PaymentDetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessPaymentCommand {
	
	@TargetAggregateIdentifier
	private final String paymentId;
	private final String orderId;
	private final PaymentDetails paymentDetails;

}
