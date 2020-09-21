package com.insside.biller.payment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public List<Payment> getPayments() {
		return paymentRepository.findAll();
	}
	
	public Payment getPaymentById(long id) {
		Optional<Payment> paymentTemp = paymentRepository.findById(id);
		Payment payment = null;
		
		if (paymentTemp.isPresent()) {
			payment = paymentTemp.get();
		}

		return payment;
	}
}
