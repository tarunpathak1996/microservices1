package com.ps.estrore.orderservice.core.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ps.estrore.orderservice.core.constants.OrderStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "ORDERS")
public class OrderEntity implements Serializable {
	
	private static final long serialVersionUID = 7798531446602014307L;
	@Id
	private String orderId;
	private String userId;
	private String productId;
	private Integer quantity;
	private String  addressId;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	
	

}
