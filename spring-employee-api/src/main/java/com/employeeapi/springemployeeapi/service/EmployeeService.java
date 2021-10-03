package com.employeeapi.springemployeeapi.service;

import java.util.List;

import com.employeeapi.springemployeeapi.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee getEmployee(long id);

	Employee addEmployee(Employee employee);

	Employee updateEmployee(Employee employee);
	 void deleteEmployee(long id);
	
}
