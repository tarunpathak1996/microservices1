package com.ps.estrore.orderservice.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApproveOrderCommand {
	
	@TargetAggregateIdentifier
	private final String orderId;

}
