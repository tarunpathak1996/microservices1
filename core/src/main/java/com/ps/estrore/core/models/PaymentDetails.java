package com.ps.estrore.core.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaymentDetails {
	
	private String cardNumber;
	private String name;
	private String cvv;
	private String validUntilMonth;
	private String validUntilYear;

}
