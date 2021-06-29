package com.ps.estrore.productservice.query.rest.model;

import lombok.Data;

@Data	
public class ProductRestModel {
	
	private String productId;
	private String title;
	private Double price;
	private Integer quantity;

}
