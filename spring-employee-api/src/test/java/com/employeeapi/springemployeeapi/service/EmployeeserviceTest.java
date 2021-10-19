package com.employeeapi.springemployeeapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeeapi.springemployeeapi.model.Employee;
import com.employeeapi.springemployeeapi.model.EmployeeDTO;
import com.employeeapi.springemployeeapi.repository.EmployeeRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class EmployeeserviceTest {

	@InjectMocks
	EmployeeServiceImpl empservice;
	
	@Mock
    EmployeeRepository employeeRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	
	@Test
	 void testGetAllEmployees()throws Exception
	{
		List<Employee> emplist = new ArrayList<>();
		Employee emp =new Employee();
		
		emp.setId(1);
		emp.setName("kk");
		emp.setEmail("kk@gmail.com");
		emp.setPhone(846757585);
		emp.setCreated_by("krishna");
		
		emplist.add(emp);
		Mockito.when(employeeRepository.findAll()).thenReturn(emplist);
		List<Employee> emplist1 =empservice.getAllEmployees();
		assertEquals(emplist1.get(0),emplist.get(0));
	}
	
	@Test
	 void testGetAllEmployeesException()throws Exception
	{
		List<Employee> emplist = new ArrayList<>();
		Employee emp =new Employee();
		
		emp.setName("");
		emp.setEmail("");
		emp.setEmail("");
		emp.setPhone(846757585);
		emp.setCreated_by("");
		
		emplist.add(emp);
		Mockito.when(employeeRepository.findAll()).thenReturn(emplist);
		List<Employee> emplist1 =empservice.getAllEmployees();
		assertEquals(emplist1.get(0),emplist.get(0));
	}
	
	

	@Test
	void testGetEmployee()throws Exception
	{
		
		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setName("kk");
		emp1.setEmail("kk@gmail.com");
		emp1.setPhone(846757585);
		emp1.setCreated_by("krishna");
  				 	     
        Mockito.when(employeeRepository.findById((Integer) 1)).thenReturn(Optional.ofNullable(emp1));
        assertThat(empservice.getEmployee(emp1.getId())).isEqualTo(emp1);
        
	}
	
	@Test
	void testGetEmployeeException()throws Exception
	{
		
		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setName("");
		emp1.setEmail("");
  				 	     
        Mockito.when(employeeRepository.findById((Integer) 1)).thenReturn(Optional.ofNullable(emp1));
        assertThat(empservice.getEmployee(emp1.getId())).isEqualTo(emp1);
        
	}


	@Test
	 void testAddEmployee()throws Exception
	{
	Employee emp1 = new Employee();
	emp1.setId(1);
	emp1.setName("kk");
	emp1.setEmail("kk@gmail.com");
	emp1.setPhone(846757585);
	emp1.setCreated_by("krishna");
	 Employee emp2 = employeeRepository.save(emp1);
	 
	
	  Mockito.when(employeeRepository.save(emp2)).thenReturn(emp1);
     Employee Empl= empservice.addEmployee(emp1);
     assertEquals(Empl,emp1);
    	
	}
	
	@Test
	 void testUpdateEmployee()throws Exception
	{
	Employee emp1 = new Employee();
	emp1.setId(1);
	emp1.setName("kk");
	emp1.setEmail("kk@gmail.com");
	emp1.setPhone(846757585);
	emp1.setCreated_by("krishna");
	 Employee emp2 = employeeRepository.save(emp1);
	 
	
	  Mockito.when(employeeRepository.save(emp2)).thenReturn(emp1);
    Employee Empl= empservice.updateEmployee(emp1);
    assertEquals(Empl,emp1);
   	
	}
	
	



	@Test
	 void testDeleteEmployee()throws Exception 
	{
	
		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setName("kk");
		emp1.setEmail("kk@gmail.com");
		emp1.setPhone(846757585);
		emp1.setCreated_by("krishna");
		 
		 
		 employeeRepository.deleteById(emp1.getId());
		 assertThat(employeeRepository.count()).isEqualTo(0);
	       	
	}
	@Test
	 void testDeleteEmployeeException()throws Exception 
	{
	
		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setName("kk");
		emp1.setEmail("kk@gmail.com");
		emp1.setPhone(846757585);
		emp1.setCreated_by("krishna");	
		 
		 
		 employeeRepository.deleteById(emp1.getId());
		 assertThat(employeeRepository.count()).isEqualTo(0);
	       	
	}
	
}
