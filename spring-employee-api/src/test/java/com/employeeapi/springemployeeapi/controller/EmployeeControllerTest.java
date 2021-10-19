package com.employeeapi.springemployeeapi.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeeapi.springemployeeapi.converter.EmployeeConverter;
import com.employeeapi.springemployeeapi.customException.BusinessException;
import com.employeeapi.springemployeeapi.model.Employee;
import com.employeeapi.springemployeeapi.model.EmployeeDTO;
import com.employeeapi.springemployeeapi.repository.EmployeeRepository;
import com.employeeapi.springemployeeapi.service.EmployeeService;
import com.employeeapi.springemployeeapi.service.EmployeeServiceImpl;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {
	@InjectMocks
	EmployeeController empcontroller;
	
	@Mock
	EmployeeService employeeService;
	
	@Mock
	EmployeeConverter employeeconverter;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	 void testGetAllEmployees()throws Exception
	{
		List<Employee> emplist = new ArrayList<>();
		Employee emp =new Employee();
		
		emp.setName("kk");
		emp.setEmail("kk@gmail.com");
		emp.setPhone(846757585);
		emp.setCreated_by("krishna");
		
		emplist.add(emp);
		
		List<EmployeeDTO> emplist1 = new ArrayList<>();
		EmployeeDTO emp1 =new EmployeeDTO();
		
		emp1.setName("kk");
		emp1.setEmail("kk@gmail.com");
		emp1.setPhone(846757585);
		emp1.setCreated_by("krishna");
		
		emplist1.add(emp1);
		
		Mockito.when(employeeService.getAllEmployees()).thenReturn(emplist);
		Mockito.when(employeeconverter.entityToDto(emplist)).thenReturn(emplist1);
		List<EmployeeDTO> emplist2 =empcontroller.getAllEmployees();
		assertEquals(emplist2.get(0),emplist1.get(0));
	}
	
	@Test
	 void testGetAllEmployeesException()throws Exception
	{
		List<Employee> emplist = new ArrayList<>();
		Employee emp =new Employee();
		
		emp.setName("");
		emp.setEmail("");
		emp.setPhone(846757585);
		emp.setCreated_by("");
		
		emplist.add(emp);
		
		List<EmployeeDTO> emplist1 = new ArrayList<>();
		EmployeeDTO emp1 =new EmployeeDTO();
		
		emp1.setName("");
		emp1.setEmail("");
		emp1.setPhone(846757585);
		emp1.setCreated_by("");
		
		emplist1.add(emp1);
		
		Mockito.when(employeeService.getAllEmployees()).thenReturn(emplist);
		Mockito.when(employeeconverter.entityToDto(emplist)).thenReturn(emplist1);
		List<EmployeeDTO> emplist2 =empcontroller.getAllEmployees();
		assertEquals(emplist2.get(0),emplist1.get(0));
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
  				 	     
		EmployeeDTO emp2 = new EmployeeDTO();
		emp2.setId(1);
		emp2.setName("kk");
		emp2.setEmail("kk@gmail.com");
		emp2.setPhone(846757585);
		emp2.setCreated_by("krishna");
		
		ResponseEntity<?> expected = new ResponseEntity<>(emp2,HttpStatus.OK);
		
        Mockito.when(employeeService.getEmployee((Integer) 1)).thenReturn(emp1);
        Mockito.when(employeeconverter.entityToDto(emp1)).thenReturn(emp2);
        ResponseEntity<?> res=empcontroller.getEmployee(emp1.getId());
        assertEquals(res,expected);
        
	}
	
	@Test
	void testGetEmployeeException()throws Exception
	{
		
		Employee emp1 = new Employee();
				 	     
		EmployeeDTO emp2 = new EmployeeDTO();
		
		ResponseEntity<?> expected = new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		
        Mockito.when(employeeService.getEmployee((Integer) 1)).thenReturn(null);
        Mockito.when(employeeconverter.entityToDto(emp1)).thenReturn(emp2);
        ResponseEntity<?> res=empcontroller.getEmployee(emp1.getId());
        assertEquals(res.getBody(),expected.getBody());
        
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
	 Employee emp2 = employeeService.addEmployee(emp1);
	 
	 EmployeeDTO emp01 = new EmployeeDTO();
		emp01.setId(1);
		emp01.setName("kk");
		emp01.setEmail("kk@gmail.com");
		emp01.setPhone(846757585);
		emp01.setCreated_by("krishna");
		 EmployeeDTO emp3 = employeeconverter.entityToDto(emp1);
	 
		 ResponseEntity<?> expected = new ResponseEntity<>(HttpStatus.CREATED);

	
	 Mockito.when(employeeService.addEmployee(emp2)).thenReturn(emp1);
	 Mockito.when(employeeconverter.entityToDto(emp2)).thenReturn(emp01);
	 ResponseEntity<?> res=empcontroller.addEmployee(emp3);
     assertEquals(res,expected);
    	
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
		 
		ResponseEntity<?> expected = new ResponseEntity<>(HttpStatus.OK);
		 
		 ResponseEntity<?> res=empcontroller.deleteEmployee(emp1.getId());
		 assertEquals(res,expected);
	       	
	}
	
	@Test
	 void testDeleteEmployeeException()throws Exception 
	{
	
		Employee emp1 = new Employee();

		 
		ResponseEntity<?> expected = new ResponseEntity<>(HttpStatus.OK);
		 
		 ResponseEntity<?> res=empcontroller.deleteEmployee(emp1.getId());
		 assertEquals(res,expected);
	       	
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
	 Employee emp2 = employeeService.updateEmployee(emp1);
	 
	 EmployeeDTO emp01 = new EmployeeDTO();
		emp01.setId(1);
		emp01.setName("kk");
		emp01.setEmail("kk@gmail.com");
		emp01.setPhone(846757585);
		emp01.setCreated_by("krishna");
		 EmployeeDTO emp3 = employeeconverter.entityToDto(emp1);
	 
		 ResponseEntity<?> expected = new ResponseEntity<>(HttpStatus.OK);

	
	 Mockito.when(employeeService.updateEmployee(emp2)).thenReturn(emp1);
	 Mockito.when(employeeconverter.entityToDto(emp2)).thenReturn(emp01);
	 ResponseEntity<?> res=empcontroller.updateEmployee(emp3);
    assertEquals(res,expected);
   	
	}



}
