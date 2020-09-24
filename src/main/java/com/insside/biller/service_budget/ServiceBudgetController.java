package com.insside.biller.service_budget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.insside.biller.area.Area;
import com.insside.biller.area.AreaService;
import com.insside.biller.shared.ApiError;
import com.insside.biller.shared.GenericResponse;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ServiceBudgetController {

	@Autowired
	private ServiceBudgetService serviceBudgetService;

	@Autowired
	private AreaService areaService;
	
	@GetMapping("/services")
	public List<ServiceBudget> getServiceBudgets() {

		return serviceBudgetService.getAllServiceBudgets();
	}
	
	@PostMapping("/services")
	public GenericResponse createServiceBudget(@Valid @RequestBody ServiceBudget serviceBudget) {
		Area areaDb = areaService.getAreaById(serviceBudget.getArea().getId());
		serviceBudget.setArea(areaDb);
		serviceBudgetService.save(serviceBudget);
		
		return new GenericResponse("Service saved");
	}
	
	@GetMapping("/services/{id}")
	public ServiceBudget getServiceById(@PathVariable int id) {
		return serviceBudgetService.getService(id);
	}

	@PutMapping("/services/{id}")
	public void updateServiceBudget(@RequestBody ServiceBudget serviceBudget) {
		Area areaDb = areaService.getAreaById(serviceBudget.getArea().getId());
		serviceBudget.setArea(areaDb);
		serviceBudgetService.save(serviceBudget);
	}
	
	@DeleteMapping("/services/{id}")
	public void deleteServiceBudget(@PathVariable int id) {
		
		ServiceBudget serviceDb = serviceBudgetService.getService(id);

		if (serviceDb != null) {
			serviceBudgetService.delete(id);
		}
		
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
		ApiError apiError = new ApiError(400, "Validation error", request.getServletPath());
		
		BindingResult result = exception.getBindingResult();
		Map<String, String> validationErrors = new HashMap<>();
		
		for(FieldError fieldError : result.getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		apiError.setValidationErrors(validationErrors);
		
		return apiError;
	}
	
}