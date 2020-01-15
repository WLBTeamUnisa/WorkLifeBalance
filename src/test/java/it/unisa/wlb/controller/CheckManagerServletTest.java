package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;

/**
 * The aim of this servlet is testing CheckManagerServlet.java
 * 
 * @author Sabato Nocera
 *
 */
class CheckManagerServletTest {

	@Mock
	private IEmployeeDao employeeDao;
	
	private CheckManagerServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private String email;
	private Employee employee;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		servlet = new CheckManagerServlet();
		email = "m.rossi1@wlb.it";
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		
	}

	@Test
	void checkManagerSuccess() throws ServletException, IOException {
		String json = "{\"status\":0}";
		request.setParameter("email", email);
		employee = new Employee();

		when(employeeDao.retrieveByEmail(email)).thenReturn(employee);
		employee.setEmail(email);
		employee.setStatus(0);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertEquals(json, response.getContentAsString());
		
	}
	
	@Test
	void voidEmailEmployee() throws ServletException, IOException {
		String email ="";
		servlet.setEmployeeDao(employeeDao);
		request.setParameter("email", email);
		servlet.doPost(request, response);
		assertTrue(!response.getContentAsString().contains("status"));
	}
	
	@Test
	void nullEmailEmployee() throws ServletException, IOException {
		String email = null;
		servlet.setEmployeeDao(employeeDao);
		request.setParameter("email", email);
		servlet.doPost(request, response);
		assertTrue(!response.getContentAsString().contains("status"));
	}
	
	@Test
	void wrongEmailEmployee() throws ServletException, IOException {
		String email = "g.verdana11@wlb.it";
		servlet.setEmployeeDao(employeeDao);
		request.setParameter("email", email);
		servlet.doPost(request, response);
		assertTrue(!response.getContentAsString().contains("status"));
	}
	
	@Test
	void wrongEmailEmployee2() throws ServletException, IOException {
		String email = "g.verdana11verdana11verdana11@wlb.it";
		servlet.setEmployeeDao(employeeDao);
		request.setParameter("email", email);
		servlet.doPost(request, response);
		assertTrue(!response.getContentAsString().contains("status"));
	}

}
