package it.unisa.wlb.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.jpa.EmployeeJpa;

class EmployeeJpaTest {

	private EmployeeJpa employeeJpa;
	private Employee employee;
	
	@BeforeEach
	void setUp() throws Exception {
		employeeJpa = new EmployeeJpa();
		employee = new Employee();
		employee.setEmail("m.rossi1@wlb.it");
		employee.setName("Mario");
		employee.setSurname("Rossi");
		employee.setStatus(0);
		employee.setPassword("Ciao1234.");
		employeeJpa.create(employee);
	}

	@AfterEach
	void after() {
		employeeJpa.remove(employee);
	}
	
	@Test
	void retrieveByEmailtest() {
		Employee anEmployee = employeeJpa.retrieveByEmail("m.rossi1@wlb.it");
		assertEquals("m.rossi1@wlb.it", anEmployee.getEmail());
	}
	
	@Test
	void retrieveByEmailPassword() {
		Employee anEmployee = employeeJpa.retrieveByEmailPassword("m.rossi1@wlb.it", "Ciao1234.");
		assertEquals("m.rossi1@wlb.it", anEmployee.getEmail());
	}

}
