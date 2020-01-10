package it.unisa.wlb.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

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
	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;
	
	@BeforeEach
	void setUp() throws Exception {
		employeeJpa = new EmployeeJpa();
		employee = new Employee();
		employee.setEmail("m.rossi1@wlb.it");
		employee.setName("Mario");
		employee.setSurname("Rossi");
		employee.setStatus(0);
		employee.setPassword("Ciao1234.");
		
		employee2 = new Employee();
		employee2.setEmail("m.rossini1@wlb.it");
		employee2.setName("Marco");
		employee2.setSurname("Rossini");
		employee2.setStatus(0);
		employee2.setPassword("Ciao1234.");
		
		manager = new Employee();
		manager.setEmail("v.verdi1@wlb.it");
		manager.setPassword("Ciao1234.");
		manager.setName("Vincenzo");
		manager.setSurname("Verdi");
		manager.setStatus(1);
		
		manager2 = new Employee();
		manager2.setEmail("v.verdini1@wlb.it");
		manager2.setPassword("Ciao1234.");
		manager2.setName("Vito");
		manager2.setSurname("Verdini");
		manager2.setStatus(1);
		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(employee);
			entityManager.persist(employee2);
			entityManager.persist(manager);
			entityManager.persist(manager2);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@AfterEach
	void after() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(employee));
			entityManager.remove(entityManager.merge(employee2));
			entityManager.remove(entityManager.merge(manager));
			entityManager.remove(entityManager.merge(manager2));
			entityManager.getTransaction().commit();
		}

		finally {
			entityManager.close();
		}
	}
	
	/**
	 * Tests the creation of an Employee
	 */
		
	@Test
	void createTest() {
		Employee anEmployee = new Employee();
		anEmployee.setEmail("g.gialli1@wlb.it");
		anEmployee.setPassword("Ciao1234.");
		anEmployee.setName("Giovanni");
		anEmployee.setSurname("Gialli");
		anEmployee.setStatus(0);
		
		Employee createdEmployee = employeeJpa.create(anEmployee);
		assertEquals("g.gialli1@wlb.it", createdEmployee.getEmail());
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(anEmployee));
			entityManager.getTransaction().commit();
		}

		finally {
			entityManager.close();
		}
	}
	
	/**
	 * Tests an update done to an Employee
	 */
	
	@Test
	void updateTest() {
		employee.setStatus(1);
		Employee aManager = employeeJpa.update(employee);
		assertEquals(1, aManager.getStatus());
	}
	
	/**
	 * Tests the removal of an Employee
	 */
	
	@Test
	void removeTest() {
		Employee anEmployee = new Employee();
		anEmployee.setEmail("g.gialli1@wlb.it");
		anEmployee.setPassword("Ciao1234.");
		anEmployee.setName("Giovanni");
		anEmployee.setSurname("Gialli");
		anEmployee.setStatus(0);
		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(anEmployee);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		employeeJpa.remove(anEmployee);
		assertThrows(NoResultException.class, ()->{
			employeeJpa.retrieveByEmail("g.gialli1@wlb.it");
		});
	}
	
	/**
	 * Tests the retriving of all employees
	 */
	
	@Test
	void retrieveAllTest() {
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		boolean check4 = false;
		List<Employee> employeesList = employeeJpa.retrieveAll();
		for(Employee anEmployee : employeesList) {
			String email = anEmployee.getEmail();
			if(email.equals("m.rossi1@wlb.it")) check1 = true;
			if(email.equals("m.rossini1@wlb.it")) check2 = true;
			if(email.equals("v.verdi1@wlb.it")) check3 = true;
			if(email.equals("v.verdi1@wlb.it")) check4 = true;
		}
		assertTrue(check1);
		assertTrue(check2);
		assertTrue(check3);
		assertTrue(check4);
	}
	
	/**
	 * Tests the retrieve of an employee by his Email
	 */
	
	@Test
	void retrieveByEmailTest() {
		Employee anEmployee = employeeJpa.retrieveByEmail("m.rossi1@wlb.it");
		assertEquals("m.rossi1@wlb.it", anEmployee.getEmail());
	}
	
	/**
	 * Tests the retrieve of an employee by his email and password 
	 */
	
	@Test
	void retrieveByEmailPasswordTest() {
		Employee anEmployee = employeeJpa.retrieveByEmailPassword("m.rossi1@wlb.it", "Ciao1234.");
		assertEquals("m.rossi1@wlb.it", anEmployee.getEmail());
	}
	
	/**
	 * Tests the suggestions retrieved using an employee or manager email
	 */
	
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
				return;
			}
		}
		assertTrue(check);
	}
	
	/**
	 * Tests the suggestions retrieved using an employee email
	 */
	
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
	
	/**
	 * Tests the suggestions retrieved using a manager email
	 */
	
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
