package com.insside.budget.service_budget;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ServiceBudgetService {

	ServiceBudgetRepository serviceBudgetRepository;
	

	public ServiceBudgetService(ServiceBudgetRepository serviceBudgetRepository) {
		this.serviceBudgetRepository = serviceBudgetRepository;
	}
	
	public List<ServiceBudget> getAllServiceBudgets() {
		return serviceBudgetRepository.findAll();
	}

	public ServiceBudget save(ServiceBudget serviceBudget) {
		return serviceBudgetRepository.save(serviceBudget);
	}

}