package it.unisa.wlb.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.CheckEmployeeServlet;
import it.unisa.wlb.controller.ShowWorkstationPrenotationPageServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IRoomDao;



public class CheckEmployeeServletTest {
	
	private MockHttpServletRequest request;
	
	private MockHttpServletResponse response;
	

	@Mock
	private HttpSession session;
	
	@Mock
	private RequestDispatcher dispatcher;
	
	@Mock
	private IEmployeeDAO employeeDao;
	
	private  CheckEmployeeServlet servlet;
	private Employee employee;
	private String name;
	private String surname;
	private String email;
	private int status;
	private String password;
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		servlet = new CheckEmployeeServlet();
		employee = new Employee();
		
		name = "Marco";
		surname = "Rossi";
		email = "m.rossi1@wlb.it";
		status = 0;
		password = "Ciao1234.";
		
		employee.setName(name);
		employee.setSurname(surname);
		employee.setEmail(email);
		employee.setStatus(status);
		employee.setPassword(password);
		
		request = new MockHttpServletRequest();
		response= new MockHttpServletResponse();
	}
	
	
	@Test
	void noExistEmployee() throws ServletException, IOException {
		
		request.setParameter("email", email);
		when(employeeDao.retrieveByEmail(email)).thenReturn(employee);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertEquals("<no/>",response.getContentAsString());
	}
	
	
	@Test
	void yesExistEmployee() throws ServletException, IOException {
		
		request.setParameter("email", email);
		when(employeeDao.retrieveByEmail(email)).thenThrow(Exception.class);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertEquals("<ok/>",response.getContentAsString());
	}
	
	@Test
	void emailNullEmployee() throws ServletException, IOException {
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertEquals("<no/>",response.getContentAsString());
	}
	
}
