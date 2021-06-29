package com.ps.estrore.productservice.command.rest.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRestModel {
	
	@NotBlank(message = "Product title is required field")
	private String title;
	
	@Min(value = 1, message = "Price cannot be lower then 1")
	private Double price;
	
	@Min(value = 1, message = "Quantity cannot be lower than 1")
	@Max(value = 5, message = "Quantity cannot be greater than 5")
	private Integer quantity;

}
