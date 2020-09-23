package com.insside.biller.budget_discount_line;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.insside.biller.discount.Discount;

import lombok.Data;

@Data
@Entity
@Table(name="budget_discount_line")
public class BudgetDiscountLine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_discount")
	private Discount discount;
	
	@Column(name="amount_with_discount")
	private Double amountWithDiscount;

}
