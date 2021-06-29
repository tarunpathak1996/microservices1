package com.ps.estrore.orderservice.core.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.estrore.orderservice.core.data.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
	

}
