package com.employeeapi.springemployeeapi.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.employeeapi.springemployeeapi.model.Employee;
import com.employeeapi.springemployeeapi.model.EmployeeDTO;

@Component
public class EmployeeConverter {
	
	public EmployeeDTO entityToDto(Employee employee)
	{
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId((int) employee.getId());
		dto.setName(employee.getName());
		dto.setEmail(employee.getEmail());
		dto.setPhone(employee.getPhone());
		dto.setCreated_by(employee.getCreated_by());
		
		return dto;
	}
	public List<EmployeeDTO> entityToDto(List<Employee> employee)
	{
		return employee.stream().map(this::entityToDto).collect(Collectors.toList());
	}
	public Employee dtoToEntity(EmployeeDTO dto)
	{
		Employee emp = new Employee();
		emp.setId(dto.getId());
		emp.setName(dto.getName());
		emp.setEmail(dto.getEmail());
		emp.setPhone(dto.getPhone());
		emp.setCreated_by(dto.getCreated_by());
		
		return emp;
		
	}
	public List<Employee> dtoToEntity(List<EmployeeDTO> dto)
	{
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
		
	}

}
