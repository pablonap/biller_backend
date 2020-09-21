package com.insside.biller.budget;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
	
	@Autowired
	BudgetRepository budgetRepository;
	
	public void save(Budget budget) {
		budgetRepository.save(budget);
	}
	
	public List<Budget> getAllBudgets() {
		return budgetRepository.findAll();
	}
	

}