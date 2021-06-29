package com.ps.estrore.productservice.command;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.estrore.productservice.core.data.ProductLookupEntity;
import com.ps.estrore.productservice.core.data.ProductLookupRepository;
import com.ps.estrore.productservice.core.data.ProductRepository;
import com.ps.estrore.productservice.core.event.ProductCreatedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventHandler {
	
	private final ProductLookupRepository productLookupRepository;
	
	@Autowired
	public ProductLookupEventHandler(ProductLookupRepository productLookupRepository) {
		this.productLookupRepository = productLookupRepository;
	}
	
	@EventHandler
	public void on(ProductCreatedEvent event) {
		
		ProductLookupEntity entity = new ProductLookupEntity(event.getProductId(), event.getTitle());
		productLookupRepository.save(entity);
		
		
	}

}
