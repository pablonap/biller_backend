package com.insside.biller.service_budget;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insside.biller.budget.Budget;

import lombok.Data;

@Data
@Entity
@Table(name="service")
public class ServiceBudget {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "{budget.constraints.code.NotNull.message}")
	@Size(min = 4, max = 5)
	private String code;

	@NotNull(message = "{budget.constraints.name.NotNull.message}")
	@Size(min = 4, max = 15)
	private String name;

	@NotNull(message = "{budget.constraints.detail.NotNull.message}")
	@Size(min = 4, max = 255)
	private String detail;

	@NotNull(message = "{budget.constraints.price.NotNull.message}")
	private double price;
	
	@NotNull
	private Boolean optional;

	@ManyToMany
	@JoinTable(
		name="service_budget",
		joinColumns=@JoinColumn(name="service_id"),
		inverseJoinColumns=@JoinColumn(name="budget_id") 
		)
//	@JsonIgnore
	private List<Budget> budgets;
	
}