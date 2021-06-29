package com.ps.estrore.userservice.data;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="APP_USER")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {
	
	@Id
	@EqualsAndHashCode.Include
	private String userId;
	private String firstName;
	private String lastName;
	@OneToMany(mappedBy = "user" )
	private Set<PaymentDetailsEntity> paymentsDetails;

}
