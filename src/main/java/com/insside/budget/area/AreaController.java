package com.insside.budget.area;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insside.budget.service_budget.ServiceBudget;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@GetMapping("/areas")
	public List<Area> getAreas() {
		return areaService.getAreas();
	}
	
	@GetMapping("/areas/{id}/servicios")
	public List<ServiceBudget> getCities(@PathVariable Integer id) {
		Area area = areaService.getAreaById(id);
		List<ServiceBudget> services = area.getServiceBudgets();

		return services;
	}
	
}