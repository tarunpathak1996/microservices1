package com.ps.estrore.orderservice.query.rest.model;

import com.ps.estrore.orderservice.core.constants.OrderStatus;

import lombok.Data;

@Data
public class OrderRestModel {
	

	private String orderId;
	private String userId;
	private String productId;
	private Integer quantity;
	private String  addressId;
	private OrderStatus orderStatus;

}
