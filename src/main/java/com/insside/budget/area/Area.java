package com.insside.budget.area;

import java.io.Serializable;
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
import com.insside.budget.service_budget.ServiceBudget;

import lombok.Data;

@Data
@Entity
@Table(name="area")
public class Area implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="area_id")
	@JsonIgnore
	private List<ServiceBudget> serviceBudgets = new ArrayList<>();
	
	public void add(ServiceBudget serviceBudget) {
		serviceBudgets.add(serviceBudget);
	}

}