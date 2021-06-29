package com.ps.estrore.productservice.core.event;

import lombok.Data;

@Data
public class ProductCreatedEvent {
	
	private String productId;
	private String title;
	private Double price;
	private Integer quantity;

}
