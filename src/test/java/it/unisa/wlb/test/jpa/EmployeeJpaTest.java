package it.unisa.wlb.test.jpa;

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
	private Employee employee2;
	private Employee manager;
	private Employee manager2;
	
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
		
		employee2 = new Employee();
		employee2.setEmail("m.rossini1@wlb.it");
		employee2.setName("Marco");
		employee2.setSurname("Rossini");
		employee2.setStatus(0);
		employee2.setPassword("Ciao1234.");
		employeeJpa.create(employee2);
		
		manager = new Employee();
		manager.setEmail("v.verdi1@wlb.it");
		manager.setPassword("Ciao1234.");
		manager.setName("Vincenzo");
		manager.setSurname("Verdi");
		manager.setStatus(1);
		employeeJpa.create(manager);
		
		manager2 = new Employee();
		manager2.setEmail("v.verdini1@wlb.it");
		manager2.setPassword("Ciao1234.");
		manager2.setName("Vito");
		manager2.setSurname("Verdini");
		manager2.setStatus(1);
		employeeJpa.create(manager2);
	}

	@AfterEach
	void after() {
		employeeJpa.remove(employee);
		employeeJpa.remove(employee2);
		employeeJpa.remove(manager);
		employeeJpa.remove(manager2);
	}
		
	@Test
	void retrieveByEmailTest() {
		Employee anEmployee = employeeJpa.retrieveByEmail("m.rossi1@wlb.it");
		assertEquals("m.rossi1@wlb.it", anEmployee.getEmail());
	}
	
	@Test
	void retrieveByEmailPasswordTest() {
		Employee anEmployee = employeeJpa.retrieveByEmailPassword("m.rossi1@wlb.it", "Ciao1234.");
		assertEquals("m.rossi1@wlb.it", anEmployee.getEmail());
	}
	
	@Test
	void suggestByEmailTest() {
		List<Employee> employeesList = employeeJpa.suggestByEmail("m.ross");
		boolean check = false;
		for(Employee anEmployee : employeesList) {
			if(anEmployee.getEmail().contains("m.ross")) {
				check = true;
			} else {
				check = false;
				assertTrue(check);
			}
		}
		assertTrue(check);
	}
	
	@Test
	void retrieveSuggestsEmployeeByEmailTest() {
		List<Employee> employeesList = employeeJpa.retrieveSuggestsEmployeeByEmail("m.ross");
		boolean check = false;
		for(Employee anEmployee : employeesList) {
			if(anEmployee.getEmail().contains("m.ross") && anEmployee.getStatus() == 0) {
				check = true;
			} else {
				check = false;
				assertTrue(check);
			}
		}
		assertTrue(check);
	}
	
	@Test
	void retrieveSuggestsManagerByEmailTest() {
		List<Employee> employeesList = employeeJpa.retrieveSuggestsManagerByEmail("v.verd");
		boolean check = false;
		for(Employee anEmployee : employeesList) {
			if(anEmployee.getEmail().contains("v.verd") && anEmployee.getStatus() == 1) {
				check = true;
			} else {
				check = false;
				assertTrue(check);
			}
		}
		assertTrue(check);
	}
}
