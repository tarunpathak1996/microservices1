package com.ps.estrore.userservice.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "PAYMENT_DTAILS")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentDetailsEntity {
	
	@Id
	@EqualsAndHashCode.Include
	private String cardNumber;
	private String name;
	private String cvv;
	private String validUntilMonth;
	private String validUntilYear;
	private Integer prefrence;
	@ManyToOne
	@JoinColumn(name = "userId" , nullable =  false)
	private UserEntity user;

}
