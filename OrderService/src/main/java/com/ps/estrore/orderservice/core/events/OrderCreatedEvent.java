package com.ps.estrore.orderservice.core.events;

import com.ps.estrore.orderservice.core.constants.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
	
	private String orderId;
	private String userId;
	private String productId;
	private Integer quantity;
	private String  addressId;
	private OrderStatus orderStatus;

}
