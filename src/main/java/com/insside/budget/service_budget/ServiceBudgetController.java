package com.insside.budget.service_budget;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ServiceBudgetController {

	@Autowired
	private ServiceBudgetService serviceBudgetService;
	
	@GetMapping("/services")
	public List<ServiceBudget> getServiceBudgets() {

		return serviceBudgetService.getAllServiceBudgets();
	}
	
	@PostMapping("/services")
	public void createServiceBudget(@RequestBody ServiceBudget serviceBudget) {
		serviceBudgetService.save(serviceBudget);
	}
	
}