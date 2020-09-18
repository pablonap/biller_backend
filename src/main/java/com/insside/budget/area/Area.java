package com.insside.budget.area;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.insside.budget.service_budget.ServiceBudget;

import lombok.Data;

@Data
@Entity
@Table(name="area")
public class Area {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy="area", 
				cascade={CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	private List<ServiceBudget> serviceBudgets = new ArrayList<>();
	
	public void add(ServiceBudget serviceBudget) {
		serviceBudgets.add(serviceBudget);
		serviceBudget.setArea(this);
	}
}