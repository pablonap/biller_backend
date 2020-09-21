package com.insside.biller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.insside.biller.budget.Budget;
import com.insside.biller.budget.BudgetService;
import com.insside.biller.discount.DiscountService;
import com.insside.biller.payment.PaymentService;
import com.insside.biller.service_budget.ServiceBudget;
import com.insside.biller.service_budget.ServiceBudgetService;

@SpringBootApplication
public class BudgetApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetApplication.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner run(
			BudgetService budgetService, 
			PaymentService ps, 
			DiscountService ds,
			ServiceBudgetService sbs) {
		return (args) -> {
			Budget budget = new Budget();
			budget.setNumber(2);
			budget.setCreationDate(LocalDate.now());
			budget.setExpirationDays(15);
			budget.setClientName("luis");
			budget.setPaymentCondition(5);
			budget.setPayment(ps.getPaymentById(1));
			budget.setDiscount(ds.getDiscountById(1));
			
			List<ServiceBudget> sbList = sbs.getAllServiceBudgets();
			budget.setServiceBudgets(sbList);

			budgetService.save(budget);
		};
	}

}
