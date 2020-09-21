package com.insside.biller.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public List<Company> getCompanies() {
		return companyRepository.findAll();
	}
	
	public Company getCompanyById(long id) {
		Optional<Company> companyTemp = companyRepository.findById(id);
		Company company = null;
		
		if (companyTemp.isPresent()) {
			company = companyTemp.get();
		}

		return company;
	}

}
