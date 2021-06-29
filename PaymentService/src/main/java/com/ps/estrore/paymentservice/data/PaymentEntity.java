package com.ps.estrore.paymentservice.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PAYMENT")
@Data
public class PaymentEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String paymentId;
	private String orderId;
	

}
