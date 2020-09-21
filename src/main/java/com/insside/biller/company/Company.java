package com.insside.biller.company;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insside.biller.budget.Budget;

import lombok.Data;

@Data
@Entity
@Table(name="company")
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String address;
	private String email;
	private String phone;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="id_company")
	@JsonIgnore
	private List<Budget> budgets = new ArrayList<>();
	
	public void add(Budget budget) {
		budgets.add(budget);
	}
}
