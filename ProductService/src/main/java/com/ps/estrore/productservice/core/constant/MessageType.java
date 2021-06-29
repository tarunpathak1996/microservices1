package com.ps.estrore.productservice.core.constant;

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
