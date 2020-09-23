package com.insside.biller.payment_condition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class PaymentConditionController {

	@Autowired
	PaymentConditionService discountService ;
	
	@GetMapping("/payment-conditions")
	public List<PaymentCondition> getPaymentConditions() {
		return discountService.getPaymentConditions();
	}

}