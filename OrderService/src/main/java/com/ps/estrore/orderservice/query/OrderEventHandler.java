package com.ps.estrore.orderservice.query;


import java.util.Optional;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ps.estrore.orderservice.core.data.OrderEntity;
import com.ps.estrore.orderservice.core.data.repo.OrderRepository;
import com.ps.estrore.orderservice.core.events.OrderApprovedEvent;
import com.ps.estrore.orderservice.core.events.OrderCreatedEvent;

@Component
@ProcessingGroup("order-group")
public class OrderEventHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventHandler.class);
	
	private final OrderRepository orderRepository;
	
	public OrderEventHandler(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@EventHandler
	public void on(OrderCreatedEvent event) {
		
		LOGGER.info("OrderCreatedEvent event handled");
		OrderEntity entity = new OrderEntity();
		BeanUtils.copyProperties(event, entity);
		orderRepository.save(entity);
		LOGGER.info("OrderCreatedEvent event handled end and order persisted in DB");
		
	}
	
	@EventHandler
	public void on(OrderApprovedEvent event) {
		
		LOGGER.info("OrderApprovedEvent event handled");
		OrderEntity orderEntity = null;
		Optional<OrderEntity> optionalOrder = orderRepository.findById(event.getOrderId());
		if(optionalOrder.isPresent()) {
			orderEntity= optionalOrder.get();
		} else {
			// to do 
		}
		orderEntity.setOrderStatus(event.getOrderStatus());
		orderRepository.save(orderEntity);
		LOGGER.info("OrderApprovedEvent event handled end and order updated (approved) in DB");
		
	}
	
	@ExceptionHandler(resultType = Exception.class)
	public void handle(Exception ex) throws Exception {
		throw ex;
	}

}
