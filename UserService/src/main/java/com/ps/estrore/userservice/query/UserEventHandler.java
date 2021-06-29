package com.ps.estrore.userservice.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.estrore.core.models.PaymentDetails;
import com.ps.estrore.core.query.FetchUserPaymentDetailsQuery;
import com.ps.estrore.userservice.data.PaymentDetailsEntity;
import com.ps.estrore.userservice.data.PaymentDetailsRepository;
import com.ps.estrore.userservice.data.UserEntity;
import com.ps.estrore.userservice.data.UserRepository;

import javassist.NotFoundException;

@Component
public class UserEventHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserEventHandler.class);
	
	
	@Autowired
	private PaymentDetailsRepository paymentDetailsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@QueryHandler
	public PaymentDetails findUserPaymentDetails(FetchUserPaymentDetailsQuery query) throws NotFoundException {
		
		LOGGER.info("findUserPaymentDetails invoked with userId: "+query.getUserId());
		List<PaymentDetails> models = new ArrayList<PaymentDetails>();
		UserEntity user = null;
		Optional<UserEntity> optionalUser = userRepository.findById(query.getUserId());
		if(optionalUser.isPresent()) {
			user = optionalUser.get();
			Set<PaymentDetailsEntity> paymentDetails = user.getPaymentsDetails();
			paymentDetails.forEach(paymentDetais ->{
				PaymentDetails model = new PaymentDetails();
				BeanUtils.copyProperties(paymentDetais, model);
				models.add(model);
			});
			LOGGER.info("findUserPaymentDetails | returing payments details");
			return models.get(0);
		}else {
			LOGGER.error("findUserPaymentDetails |"+"User with id: "+query.getUserId()+" Not found");
			throw new NotFoundException("User with id: "+query.getUserId()+" Not found" );
		}
		
		
	}

}
