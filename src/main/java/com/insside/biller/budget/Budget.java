package com.insside.biller.budget;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.insside.biller.company.Company;
import com.insside.biller.discount.Discount;
import com.insside.biller.payment.Payment;
import com.insside.biller.service_budget.ServiceBudget;

import lombok.Data;

@Data
@Entity
@Table(name="budget")
public class Budget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer number;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
	@Column(name="expiration_days")
	private Integer expirationDays;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_company")
	private Company company;

	@Column(name="client_name")
	private String clientName;

	@Column(name="payment_condition")
	private Integer paymentCondition;

	@OneToOne
	@JoinColumn(name="id_payment")
	private Payment payment;

	@OneToOne
	@JoinColumn(name="id_discount")
	private Discount discount;
	
	@ManyToMany
	@JoinTable(
		name="service_budget",
		joinColumns=@JoinColumn(name="budget_id"),
		inverseJoinColumns=@JoinColumn(name="service_id") 
		)
	private List<ServiceBudget> serviceBudgets;

}