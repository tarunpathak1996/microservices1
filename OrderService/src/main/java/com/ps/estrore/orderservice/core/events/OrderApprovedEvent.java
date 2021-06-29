package com.ps.estrore.orderservice.core.events;



import com.ps.estrore.orderservice.core.constants.OrderStatus;

import lombok.Value;

@Value
public class OrderApprovedEvent {
	
	private final String orderId;
	private final OrderStatus orderStatus = OrderStatus.APPROVED;

}
