package com.ps.estrore.productservice.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.estrore.core.events.ProductReservedEvent;
import com.ps.estrore.productservice.core.data.ProductEntity;
import com.ps.estrore.productservice.core.data.ProductRepository;
import com.ps.estrore.productservice.core.event.ProductCreatedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventsHandler.class);

	private final ProductRepository productRepo;

	@Autowired
	public ProductEventsHandler(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@EventHandler
	public void on(ProductCreatedEvent event) throws Exception {

		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(event, productEntity);
		productRepo.save(productEntity);
	}
	
	@EventHandler
	public void on(ProductReservedEvent event) throws Exception {
		LOGGER.info("ProductReservedEvent handling...");
		ProductEntity productEntity = productRepo.findByProductId(event.getProductId());
		productEntity.setQuantity(productEntity.getQuantity() - event.getQuantity());
		productRepo.save(productEntity);
	}

	@ExceptionHandler(resultType = IllegalArgumentException.class)
	public void handle(IllegalArgumentException ex) {
		throw ex;
	}
	
	@ExceptionHandler(resultType = Exception.class)
	public void handle(Exception ex) throws Exception {
		throw ex;
	}

}
