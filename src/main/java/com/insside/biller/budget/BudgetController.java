package com.insside.biller.budget;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insside.biller.budget_detail.BudgetDetail;
import com.insside.biller.company.Company;
import com.insside.biller.company.CompanyService;
import com.insside.biller.service_budget.ServiceBudget;
import com.insside.biller.service_budget.ServiceBudgetService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class BudgetController {
	
	@Autowired
	private BudgetService budgetService;
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private ServiceBudgetService serviceBudgetService;

	@GetMapping("/budgets")
	public List<Budget> getBudgets() {
		return budgetService.getAllBudgets();
	}

	@PostMapping("/budgets")
	public void save(@RequestBody Budget budget) throws Exception {
		Integer newNumber = budgetService.calculateNewNumber();
		
		budget.setNumberBudget(newNumber);

		Company companyDb = 
				companyService.getCompanyById(budget.getCompany().getId());
		
		if (companyDb == null) {
			throw new Exception("Company not found");
		}
		
		budget.setCompany(companyDb);
		budget.setCreationDate(LocalDate.now());
		
		for(BudgetDetail bd : budget.getBudgetDetails()) {
			ServiceBudget serviceBudgetDb = 
					serviceBudgetService.getService(bd.getServiceBudget().getId());

			bd.setServiceDescription(serviceBudgetDb.getDetail());
			bd.setUnitPrice(serviceBudgetDb.getPrice());
		}
		
		budgetService.save(budget);
	}

}
