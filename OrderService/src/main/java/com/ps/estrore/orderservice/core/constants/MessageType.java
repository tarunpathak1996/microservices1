package com.ps.estrore.orderservice.core.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum MessageType {
	
	INFO("Information"), ERROR("Error"), WARNING("Warning");
	
	private String value;

}
