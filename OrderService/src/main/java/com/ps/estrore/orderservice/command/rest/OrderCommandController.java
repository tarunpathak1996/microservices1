package com.ps.estrore.orderservice.command.rest;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.estrore.orderservice.command.CreateOrderCommand;
import com.ps.estrore.orderservice.command.rest.model.OrderCreateRestModel;
import com.ps.estrore.orderservice.core.constants.OrderStatus;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

	private final CommandGateway commandGateway;

	@Autowired
	public OrderCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping
	public HttpEntity<String> createOrder(@RequestBody OrderCreateRestModel order) {

		String userId = "120177";

		CreateOrderCommand command = CreateOrderCommand.builder().addressId(order.getAddressId())
				.productId(order.getProductId()).userId(userId).quantity(order.getQuantity())
				.orderId(UUID.randomUUID().toString()).orderStatus(OrderStatus.CREATED).build();
		
		String returnValue = commandGateway.sendAndWait(command);

		return new ResponseEntity<String>(returnValue, HttpStatus.OK);
	}

}
