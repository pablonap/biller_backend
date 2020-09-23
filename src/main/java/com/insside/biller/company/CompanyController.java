package com.insside.biller.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@GetMapping("/companies")
	public List<Company> getCompanies() {
		return companyService.getCompanies();
	}

}
