package com.ps.estrore.productservice.core.errorHandling;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
	
	private Instant time;
	private String type;
	private String message;

}
