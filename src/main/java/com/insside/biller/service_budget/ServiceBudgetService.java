package com.insside.biller.service_budget;

import java.util.List;
import java.util.Optional;

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
	
	public void delete(int serviceId) {
		serviceBudgetRepository.deleteById(serviceId);
	}

	public ServiceBudget getService(int idServiceBudget) {
		Optional<ServiceBudget> serviceTemp = serviceBudgetRepository.findById(idServiceBudget);
		ServiceBudget serviceBudget = null;
		if (serviceTemp.isPresent()) {
			serviceBudget = serviceTemp.get();
		}
		
		return serviceBudget;
	}
	
}