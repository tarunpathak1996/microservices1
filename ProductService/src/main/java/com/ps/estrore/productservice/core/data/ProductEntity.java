package com.ps.estrore.productservice.core.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCTS")
public class ProductEntity implements Serializable {
	
	private static final long serialVersionUID = -1733701418112180103L;
	@Id
	private String productId;
	
	@Column(unique = true, nullable = false)
	private String title;
	
	private Double price;
	
	private Integer quantity;

}
