package com.insside.biller.discount;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insside.biller.budget_discount_line.BudgetDiscountLine;

import lombok.Data;

@Data
@Entity
@Table(name="discount")
public class Discount {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Double value;
	private String description;
	
	@OneToMany(mappedBy="discount")
	@JsonIgnore
	private List<BudgetDiscountLine> budgetDiscountLines = new ArrayList<>();

	public void add(BudgetDiscountLine budgetDiscountLine ) {
		budgetDiscountLines.add(budgetDiscountLine);
		budgetDiscountLine.setDiscount(this);
	}
}