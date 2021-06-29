package com.ps.estrore.orderservice.query.rest;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.estrore.orderservice.query.FindOrderQuery;
import com.ps.estrore.orderservice.query.rest.model.OrderRestModel;

@RestController
@RequestMapping("/orders")
public class OrderQueryController {
	
	private final QueryGateway queryGateway;
	
	@Autowired
	public OrderQueryController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}
	
	@GetMapping
	public HttpEntity<Object> getOrders(){
		
		FindOrderQuery query = new FindOrderQuery();
		List<OrderRestModel> listOfOrders = queryGateway.query(query, ResponseTypes.multipleInstancesOf(OrderRestModel.class)).join();
		return new ResponseEntity<>(listOfOrders, HttpStatus.OK);
	}

}
