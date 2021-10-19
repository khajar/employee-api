package com.employeeapi.springemployeeapi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeapi.springemployeeapi.customException.BusinessException;
import com.employeeapi.springemployeeapi.model.Employee;
import com.employeeapi.springemployeeapi.model.EmployeeDTO;
import com.employeeapi.springemployeeapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees()
	{
		if(employeeRepository.findAll().isEmpty())
		{
			throw new BusinessException("604","list is empty");
		}
		
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee getEmployee(Integer id)
	{

		try {
		return employeeRepository.findById(id).get();
		}
		catch(NoSuchElementException e)
		{
			throw new BusinessException("604","given employee id  does not exist"+e.getMessage());
		}
		
	}
		

	@Override
	public Employee addEmployee(Employee employee)
	{
		if(employee.getName().isEmpty()||employee.getName().length()==0)
		{
			throw new BusinessException("601","please send proper name,it is blank");
		}
		try {
		employeeRepository.save(employee);
		return employee;
		}
		catch(Exception e)
		{
			throw new BusinessException("603","something went wrong in service layer"+e.getMessage());
		}
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}
	
	@Override
	public void deleteEmployee(Integer id)
	{
		try {
		 employeeRepository.deleteById(id);
		
		}
		catch(IllegalArgumentException e)
		{
			throw new BusinessException("606","givem employee is blank"+e.getMessage());
		}
		//employeeRepository.deleteById(id);	 
	}



	
}
