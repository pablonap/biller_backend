package com.insside.biller.budget;

import org.springframework.beans.factory.annotation.Autowired;

public class BudgetService {
	
	@Autowired
	BudgetRepository budgetRepository;
	
	public void save(Budget budget) {
		budgetRepository.save(budget);
	}

}