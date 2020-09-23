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

import com.insside.biller.company.Company;
import com.insside.biller.company.CompanyService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class BudgetController {
	
	@Autowired
	private BudgetService budgetService;
	
	@Autowired
	private CompanyService companyService;


	@GetMapping("/budgets")
	public List<Budget> getBudgets() {
		return budgetService.getAllBudgets();
	}

	@PostMapping("/budgets")
	public void save(@RequestBody Budget budget) throws Exception {
		Integer newNumber = budgetService.calculateNewNumber();
		
		budget.setNumberBudget(newNumber);

		budget.setCreationDate(LocalDate.now());

		Company companyDb = 
				companyService.getCompanyById(budget.getCompany().getId());
		
		budget.setCompany(companyDb);
		
		budgetService.setBudgetDetailFields(budget.getBudgetDetails());
		
		budgetService.setTotalAmmount(budget);
		
		budgetService.setBudgetDiscountLineFields(budget);
		
		budgetService.save(budget);
	}

}
