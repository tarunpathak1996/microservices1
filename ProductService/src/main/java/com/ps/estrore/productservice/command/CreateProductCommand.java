package com.ps.estrore.productservice.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateProductCommand {
	
	@TargetAggregateIdentifier
	private final String productId;
	private final String title;
	private final Double price;
	private final Integer quantity;

}
