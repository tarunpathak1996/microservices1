package com.ps.estrore.userservice.query.controller;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.estrore.core.models.PaymentDetails;
import com.ps.estrore.core.query.FetchUserPaymentDetailsQuery;

@RestController
@RequestMapping("/users")
public class UserQueryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserQueryController.class);
	
	@Autowired
	private QueryGateway queryGateway;
	
	@GetMapping("/{userId}/payments-details")
	public HttpEntity<PaymentDetails> getUserPaymentsDetails(@PathVariable String userId) {
		
		LOGGER.info("getUserPaymentsDetails invoked with userId: "+userId);
		FetchUserPaymentDetailsQuery query = FetchUserPaymentDetailsQuery.builder().userId(userId).build();
		PaymentDetails paytmentDetails = queryGateway.query(query, ResponseTypes.instanceOf(PaymentDetails.class)).join();
		return new ResponseEntity<PaymentDetails>(paytmentDetails, HttpStatus.OK);
	}

}
