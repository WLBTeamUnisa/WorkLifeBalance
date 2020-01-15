package it.unisa.wlb.controller;

import java.io.IOException;
import it.unisa.wlb.controller.EmployeeRegistrationServlet;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;

/**
 * The aim of this class is testing EmployeeRegistrationServlet.java
 * 
 * @author Sabato Nocera
 *
 */
public class EmployeeRegistrationServletTestTwo extends Mockito {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private EmployeeRegistrationServlet servlet;

	@BeforeEach
	public void setUp() {
		servlet = new EmployeeRegistrationServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	
	@Test 
	public void voidName() throws ServletException, IOException {
		request.addParameter("name", "");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void voidSurname() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void voidEmail() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void tooLongEmail() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "g.verdanaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void voidPassword() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void voidVerifyPassword() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void voidStatus() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void nullName() throws ServletException, IOException {
		String tmp = null;
		request.addParameter("name", tmp);
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void nulSurname() throws ServletException, IOException {
		String tmp = null;
		request.addParameter("name", "Marco");
		request.addParameter("surname", tmp);
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void nullEmail() throws ServletException, IOException {
		String tmp = null;
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", tmp);
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void nullPassword() throws ServletException, IOException {
		String tmp = null;
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", tmp);
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void nullVerifyPassword() throws ServletException, IOException {
		String tmp = null;
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword", tmp);
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	@Test 
	public void employeeSuccess() throws ServletException, IOException {	
		
		Employee employee = new Employee();
		employee.setName("Marco");
		employee.setSurname("Rossi");
		employee.setEmail("m.rossi1@wlb.it");
		employee.setPassword("MarcoRossi1.");
		employee.setStatus(0);
		
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		when(employeeDao.create(employee)).thenReturn(employee);		
		
		EmployeeRegistrationServlet tmp = new EmployeeRegistrationServlet(employeeDao);

		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Employee");
		tmp.doGet(request, response);
		assertEquals("success", request.getAttribute("result"));

	}
	
	@SuppressWarnings("unchecked")
	@Test 
	public void exceptionThrown() throws ServletException, IOException {	
		
		Employee employee = new Employee();
		employee.setName("Marco");
		employee.setSurname("Rossi");
		employee.setEmail("m.rossi1@wlb.it");
		employee.setPassword("MarcoRossi1.");
		employee.setStatus(1);
		
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		when(employeeDao.create(any(Employee.class))).thenThrow(Exception.class);		
		
		EmployeeRegistrationServlet tmp = new EmployeeRegistrationServlet(employeeDao);

		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		tmp.doPost(request, response);
		assertEquals("failure", request.getAttribute("result"));
	}

}