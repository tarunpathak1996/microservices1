package com.ps.estrore.orderservice.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.ps.estrore.orderservice.core.constants.OrderStatus;
import com.ps.estrore.orderservice.core.events.OrderApprovedEvent;
import com.ps.estrore.orderservice.core.events.OrderCreatedEvent;

import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor
public class OrderAggregate {
	
	@AggregateIdentifier
	private String orderId;
	private String userId;
	private String productId;
	private Integer quantity;
	private String  addressId;
	private OrderStatus orderStatus;
	
	@CommandHandler
	public OrderAggregate(CreateOrderCommand command) throws Exception {
		
		OrderCreatedEvent event = new OrderCreatedEvent();
		BeanUtils.copyProperties(command, event);
		AggregateLifecycle.apply(event);
	}
	
	@EventSourcingHandler
	public void on(OrderCreatedEvent event) {
		this.orderId = event.getOrderId();
		this.userId = event.getUserId();
		this.productId = event.getProductId();
		this.addressId = event.getAddressId();
		this.quantity = event.getQuantity();
		this.orderStatus = event.getOrderStatus();
	}
	
	@CommandHandler
	public void handle(ApproveOrderCommand command) throws Exception {
		
		OrderApprovedEvent event = new OrderApprovedEvent(command.getOrderId());
		AggregateLifecycle.apply(event);
	}
	
	@EventSourcingHandler
	public void on(OrderApprovedEvent event) {
		this.orderStatus = event.getOrderStatus();
		
	}

}
