package com.ps.estrore.paymentservice.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.estrore.core.events.PaymentProcessedEvent;
import com.ps.estrore.paymentservice.data.PaymentEntity;
import com.ps.estrore.paymentservice.data.PaymentRepository;

@Component
public class PaymentEventsHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentEventsHandler.class);
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	public void on(PaymentProcessedEvent event) {
		
		 LOGGER.info("PaymentProcessedEvent is called for orderId: " + event.getOrderId());
		 PaymentEntity entity = new PaymentEntity();
		 BeanUtils.copyProperties(event, entity);
		 paymentRepo.save(entity);
	}

}
