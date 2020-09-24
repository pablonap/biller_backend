package com.insside.biller.budget;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insside.biller.budget_detail.BudgetDetail;
import com.insside.biller.budget_discount_line.BudgetDiscountLine;
import com.insside.biller.discount.Discount;
import com.insside.biller.discount.DiscountService;
import com.insside.biller.service_budget.ServiceBudget;
import com.insside.biller.service_budget.ServiceBudgetService;

@Service
public class BudgetService {
	
	@Autowired
	BudgetRepository budgetRepository;

	@Autowired
	private ServiceBudgetService serviceBudgetService;

	@Autowired
	private DiscountService discountService;
	
	public void save(Budget budget) {
		budgetRepository.save(budget);
	}
	
	public List<Budget> getAllBudgets() {
		return budgetRepository.findAll();
	}
	
	public Budget getBudgetById(long id) {
		Optional<Budget> budgetTemp = budgetRepository.findById(id);
		Budget budget = null;
		
		if (budgetTemp.isPresent()) {
			budget = budgetTemp.get();
		}

		return budget;
	}

	public Integer calculateNewNumber() {
		Budget budget = budgetRepository.findTopByOrderByIdDesc();
		
		if (budget == null) {
			return 1;
		}
		
		return budget.getNumberBudget() + 1;
	}

	public void setBudgetDetailFields(List<BudgetDetail> budgetDetails) {

		for(BudgetDetail bd : budgetDetails) {
			ServiceBudget serviceBudgetDb = 
					serviceBudgetService.getService(bd.getServiceBudget().getId());

			bd.setServiceDescription(serviceBudgetDb.getDetail());
			bd.setUnitPrice(serviceBudgetDb.getPrice());
			
			bd.setTotalAmount(calculateTotalAmountPerBudgetDetail(bd));
			
			bd.getServiceBudget().setOptional(serviceBudgetDb.getOptional());
			
		}
		
	}
	
	private Double calculateTotalAmountPerBudgetDetail(BudgetDetail bd) {
		return bd.getUnitPrice() * bd.getAmount();
	}

	public void setTotalAmmount(Budget budget) {

		Double totalAmount = new Double(0);

		for(BudgetDetail bd : budget.getBudgetDetails()) {
			if (bd.getServiceBudget().getOptional() == false) {
				totalAmount += bd.getTotalAmount();
			}
		}
		
		budget.setTotalAmount(totalAmount);
		
	}

	public void setBudgetDiscountLineFields(Budget budget) {

		for(BudgetDiscountLine bdl : budget.getBudgetDiscountLines()) {
			Discount discountDb = 
					discountService.getDiscountById(bdl.getDiscount().getId());

			bdl.setAmountWithDiscount(
					calculateAmountWithDiscount(
							discountDb.getValue(), 
							budget.getTotalAmount()));
			
		}
		
	}
	
	private Double calculateAmountWithDiscount(Double percentageDiscount, 
			Double totalAmount) {
		Double discount = percentageDiscount / 100;

		return totalAmount - (discount * totalAmount);
	}

	public void delete(long id) {
		budgetRepository.deleteById(id);
	}

}