package com.employeeapi.springemployeeapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeapi.springemployeeapi.model.Employee;
import com.employeeapi.springemployeeapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees()
	{
		
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee getEmployee(long id)
	{
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if(optional.isPresent()) {
			employee = optional.get();
		}
		else {
			throw new RuntimeException("employee not found");
		}
		return employee;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}
	
	public void deleteEmployee(long id)
	{
		 employeeRepository.deleteById(id);
		 
	}
}
