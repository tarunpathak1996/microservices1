package com.ps.estrore.productservice.command;

import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.ps.estrore.core.commands.ReserveProductCommand;
import com.ps.estrore.core.events.ProductReservedEvent;
import com.ps.estrore.productservice.core.event.ProductCreatedEvent;

@Aggregate
public class ProductAggregate {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductAggregate.class);

	@AggregateIdentifier
	private String productId;
	private String title;
	private Double price;
	private Integer quantity;

	public ProductAggregate() {

	}

	@CommandHandler
	public ProductAggregate(CreateProductCommand createProductCommand) throws Exception {
		// validate create product command

		if (createProductCommand.getPrice().compareTo(0D) <= 0)
			throw new IllegalArgumentException("Price cannot be less or equal to zero");

		if (StringUtils.isEmpty(createProductCommand.getTitle()))
			throw new IllegalArgumentException("Title can not be empty");

		// create the product

		ProductCreatedEvent event = new ProductCreatedEvent();
		BeanUtils.copyProperties(createProductCommand, event);
		AggregateLifecycle.apply(event);
	}

	@CommandHandler
	public void handle(ReserveProductCommand command) {
		
		LOGGER.info("ReserveProductCommand handling ...");

		if (this.quantity < command.getQuantity()) {
			throw new IllegalArgumentException("Insufficient number of Iteams in stock");
		}

		ProductReservedEvent event = ProductReservedEvent.builder().productId(command.getProductId())
				.quantity(command.getQuantity()).userId(command.getUserId()).orderId(command.getUserId()).build();

		AggregateLifecycle.apply(event);
	}

	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		this.productId = productCreatedEvent.getProductId();
		this.price = productCreatedEvent.getPrice();
		this.title = productCreatedEvent.getTitle();
		this.quantity = productCreatedEvent.getQuantity();
	}

	@EventSourcingHandler
	public void on(ProductReservedEvent productReservedEvent) {
		this.quantity = this.quantity - productReservedEvent.getQuantity();
	}

}
