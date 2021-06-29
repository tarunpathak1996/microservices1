package com.ps.estrore.orderservice.query;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.estrore.orderservice.core.data.OrderEntity;
import com.ps.estrore.orderservice.core.data.repo.OrderRepository;
import com.ps.estrore.orderservice.query.rest.model.OrderRestModel;

@Component
public class OrderQueryHandler {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@QueryHandler
	public List<OrderRestModel> findOrders(FindOrderQuery query){
		
		 List<OrderRestModel> responseList = new ArrayList<OrderRestModel>();
		 List<OrderEntity> orderEntities =  orderRepository.findAll();
		 orderEntities.forEach(order ->{
			 OrderRestModel model = new OrderRestModel();
			 BeanUtils.copyProperties(order, model);
			 responseList.add(model);
		 });
		 return responseList;
		 
		
	}
	

	
	

}
