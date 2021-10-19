package com.employeeapi.springemployeeapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employeeapi.springemployeeapi.converter.EmployeeConverter;
import com.employeeapi.springemployeeapi.customException.BusinessException;
import com.employeeapi.springemployeeapi.customException.ControllerException;
import com.employeeapi.springemployeeapi.model.Employee;
import com.employeeapi.springemployeeapi.model.EmployeeDTO;
import com.employeeapi.springemployeeapi.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired 
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeConverter employeeconverter;
	
	
	@GetMapping("/employee")	
	public List<EmployeeDTO> getAllEmployees()
	{
		List<Employee> findAll=employeeService.getAllEmployees();
		return employeeconverter.entityToDto(findAll);	 
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable Integer id)
	{
		
		try {
		 Employee orElse=employeeService.getEmployee(id);
		 EmployeeDTO dto = employeeconverter.entityToDto(orElse);
		 return new ResponseEntity<EmployeeDTO>(dto,HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			 ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			 ControllerException ce = new ControllerException("608","Something went wrong");
				return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
			
	}
	 
	@PostMapping(path="/employee",consumes="application/json")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employeedto)
	{
		try {
		Employee employee = employeeconverter.dtoToEntity(employeedto);
		employee = employeeService.addEmployee(employee);
		return new ResponseEntity<EmployeeDTO>(HttpStatus.CREATED);
		}
		catch(BusinessException e)
		{
			 ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			 ControllerException ce = new ControllerException("607","Something went wrong");
				return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/employee")
	@ResponseStatus(code = HttpStatus.OK, reason = "OK")
	public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeedto)
	{
	Employee employee = employeeconverter.dtoToEntity(employeedto);
	employee = employeeService.updateEmployee(employee);
	return ResponseEntity.ok(employeedto);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer id )
	{
		try
		{
			this.employeeService.deleteEmployee(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			ControllerException ce = new ControllerException("608","invalid data");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
