package com.ps.estrore.productservice;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import com.ps.estrore.productservice.command.interceptor.CreateProductCommandInterceptor;
import com.ps.estrore.productservice.core.errorHandling.ProductsServiceEventErrorHandler;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Autowired
	public void registerCreateProductCommandInterceptor(ApplicationContext content, CommandBus commandBus) {
		commandBus.registerDispatchInterceptor(content.getBean(CreateProductCommandInterceptor.class));

	}

	@Autowired
	public void configure(EventProcessingConfigurer config) {
		
		config.registerListenerInvocationErrorHandler("product-group", conf -> new ProductsServiceEventErrorHandler());
		
		// config.registerListenerInvocationErrorHandler("product-group", conf -> PropagatingErrorHandler.instance());
	}

}
