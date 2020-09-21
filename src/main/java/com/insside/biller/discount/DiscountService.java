package com.insside.biller.discount;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
	
	@Autowired
	private DiscountRepository discountRepository;
	
	public List<Discount> getDiscounts() {
		return discountRepository.findAll();
	}
	
	public Discount getDiscountById(long id) {
		Optional<Discount> discountTemp = discountRepository.findById(id);
		Discount discount = null;
		
		if (discountTemp.isPresent()) {
			discount = discountTemp.get();
		}

		return discount;
	}

}
