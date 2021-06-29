package com.ps.estrore.productservice.query.rest;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.estrore.productservice.query.FindProductsQuery;
import com.ps.estrore.productservice.query.rest.model.ProductRestModel;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	QueryGateway queryGateway;
	
	@GetMapping
	public List<ProductRestModel> getProducts(){
		
		FindProductsQuery query = new FindProductsQuery();
		List<ProductRestModel> products = 
				queryGateway.query(query, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
		return products;
	}
	

	@GetMapping("/about")
	public HttpEntity<String> about(){
		return new ResponseEntity<String>("Products about Handle: "+env.getProperty("local.server.port"), HttpStatus.CREATED);
	}
}
