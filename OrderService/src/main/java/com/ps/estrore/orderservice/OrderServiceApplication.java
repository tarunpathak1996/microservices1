package com.ps.estrore.orderservice;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ps.estrore.orderservice.core.errorHandling.OrderServiceEventsErrorHandler;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	
	@Autowired
	public void configure(EventProcessingConfigurer config) {
		// config.registerListenerInvocationErrorHandler("order-group", conf -> PropagatingErrorHandler.instance());
		config.registerListenerInvocationErrorHandler("order-group", conf -> new OrderServiceEventsErrorHandler());
	}

}
