package com.ps.estrore.userservice.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetailsEntity, String> {
	
	public PaymentDetailsEntity findByUser(UserEntity user);

}
