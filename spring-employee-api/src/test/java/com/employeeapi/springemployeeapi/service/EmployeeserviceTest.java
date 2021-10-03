package com.employeeapi.springemployeeapi.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employeeapi.springemployeeapi.model.Employee;
import com.employeeapi.springemployeeapi.repository.EmployeeRepository;

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
		String uri="/employee";
	}

	@Test
	void testGetEmployee() {
		fail("Not yet implemented");
	}

	@Test
	void testAddEmployee() {
		Employee empreq =new Employee();
		empreq.setName("kk");
		empreq.getId();
		empreq.setEmail("kk@gmail.com");
		empreq.setPhone(787676788);
		
		
	}

	@Test
	void testUpdateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteEmployee() {
		fail("Not yet implemented");
	}

}
