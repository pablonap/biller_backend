package com.insside.biller.budget_detail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.insside.biller.service_budget.ServiceBudget;

import lombok.Data;

@Data
@Entity
@Table(name="budget_detail")
public class BudgetDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Integer amount;
	
	@Column(name="unit_price")
	private Double unitPrice;
	
	@ManyToOne
	@JoinColumn(name="service_id")
	private ServiceBudget serviceBudget;

}
