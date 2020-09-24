package com.insside.biller.budget;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.insside.biller.budget_detail.BudgetDetail;
import com.insside.biller.budget_discount_line.BudgetDiscountLine;
import com.insside.biller.company.Company;
import com.insside.biller.payment.Payment;
import com.insside.biller.payment_condition.PaymentCondition;

import lombok.Data;

@Data
@Entity
@Table(name="budget")
public class Budget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nro_budget")
	private Integer numberBudget;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
	@Column(name="expiration_days")
	private Integer expirationDays;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="id_company")
	private Company company;

	@Column(name="client_name")
	private String clientName;

	@OneToOne
	@JoinColumn(name="id_payment_condition")
	private PaymentCondition paymentCondition;

	@OneToOne
	@JoinColumn(name="id_payment")
	private Payment payment;

	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="id_budget")
	private List<BudgetDiscountLine> budgetDiscountLines = new ArrayList<>();

	public void add(BudgetDiscountLine budgetDiscountLine ) {
		budgetDiscountLines.add(budgetDiscountLine);
	}
	
	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="budget_id")
	private List<BudgetDetail> budgetDetails = new ArrayList<>();

	public void add(BudgetDetail budgetDetail ) {
		budgetDetails.add(budgetDetail);
	}
	
	@Column(name="total_amount")
	private Double totalAmount;

}