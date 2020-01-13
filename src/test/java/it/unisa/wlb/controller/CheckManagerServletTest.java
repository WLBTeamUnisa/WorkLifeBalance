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
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;

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

}
