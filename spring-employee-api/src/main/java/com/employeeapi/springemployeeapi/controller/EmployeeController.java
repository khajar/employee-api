package com.employeeapi.springemployeeapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeapi.springemployeeapi.model.Employee;
import com.employeeapi.springemployeeapi.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired 
	private EmployeeService employeeService;
	
	@RequestMapping(value="/welcome")
	public static String welcome() {
		return "Welcome to Spring Boot";
	}
	
	@GetMapping("/emploee")	
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployees();
		 
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable long id)
	{
		return employeeService.getEmployee(id);
	}
	 
	@PostMapping(path="/employee",consumes="application/json")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		return this.employeeService.addEmployee(employee);
		
	}
	
	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee employee)
	{
		return this.employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable long id )
	{
		try {
			this.employeeService.deleteEmployee(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
