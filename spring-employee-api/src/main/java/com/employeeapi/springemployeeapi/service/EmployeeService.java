package com.employeeapi.springemployeeapi.service;

import java.util.List;

import org.apache.catalina.connector.Response;

import com.employeeapi.springemployeeapi.model.Employee;
import com.employeeapi.springemployeeapi.model.EmployeeDTO;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee addEmployee(Employee employee);

	Employee updateEmployee(Employee employee);
	
    void deleteEmployee(Integer id);

	Employee getEmployee(Integer id);
	
}
