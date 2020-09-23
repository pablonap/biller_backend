package com.insside.biller.payment_condition;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentConditionService {
	
	@Autowired
	private PaymentConditionRepository pcRepository;
	
	public List<PaymentCondition> getPaymentConditions() {
		return pcRepository.findAll();
	}
	
	public PaymentCondition getPaymentConditionById(long id) {
		Optional<PaymentCondition> pcTemp = pcRepository.findById(id);
		PaymentCondition pm = null;
		
		if (pcTemp.isPresent()) {
			pm = pcTemp.get();
		}

		return pm;
	}

}
