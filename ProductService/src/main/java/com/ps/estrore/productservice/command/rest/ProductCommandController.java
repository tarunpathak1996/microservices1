package com.ps.estrore.productservice.command.rest;

import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.estrore.productservice.command.CreateProductCommand;
import com.ps.estrore.productservice.command.rest.model.CreateProductRestModel;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

	private CommandGateway commandGateway;
	
	@Autowired
	public ProductCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	
	@PostMapping
	public HttpEntity<String> createProduct(@Valid @RequestBody CreateProductRestModel createProductModel){
		
		CreateProductCommand createProductCommand = CreateProductCommand.builder()
				.price(createProductModel.getPrice())
				.quantity(createProductModel.getQuantity())
				.title(createProductModel.getTitle())
				.productId(UUID.randomUUID().toString())
				.build();
		String returnValue =  commandGateway.sendAndWait(createProductCommand);
		/*
		 * try { returnValue = commandGateway.sendAndWait(createProductCommand); }catch
		 * (Exception e) { returnValue = e.getLocalizedMessage(); }
		 */
		return new ResponseEntity<String>(returnValue, HttpStatus.CREATED);
	}
	
	
}
