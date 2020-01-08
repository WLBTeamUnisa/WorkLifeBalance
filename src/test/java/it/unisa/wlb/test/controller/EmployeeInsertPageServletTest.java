package it.unisa.wlb.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import it.unisa.wlb.controller.EmployeeInsertPageServlet;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.dao.IAdminDAO;
import static org.mockito.Mockito.verify;

/**
 * The aim of this class is testing EmployeeInsertPageServlet.java
 * 
 * @author Vincenzo Fabiano
 *
 */
public class EmployeeInsertPageServletTest {

	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpServletResponse response;
	
	private EmployeeInsertPageServlet servlet;
	
	@Mock
	private IAdminDAO adminDao;
	@Mock
	private HttpSession session;
	@Mock
	private RequestDispatcher dispatcher;
	
	private Admin admin;
	private String name;
	private String surname;
	private String email;
	private String password;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		servlet = new EmployeeInsertPageServlet();
		admin = new Admin();
		
		name = "Marco";
		surname = "Rossi";
		email = "m.rossi1@wlbadmin.it";
		password = "Ciao1234.";
		
		admin.setName(name);
		admin.setSurname(surname);
		admin.setEmail(email);
		admin.setPassword(password);
		
	
	}
	
	
	@Test
	void userRoleAdmin() throws ServletException, IOException {
		String path = "WEB-INF/EmployeeRegistration.jsp";
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userRole")).thenReturn("Admin");
		when(session.getAttribute("user")).thenReturn(admin);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		servlet.doPost(request, response);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	@Test
	void userRoleNotAdmin() throws ServletException, IOException {
		String path = "WEB-INF/EmployeeRegistration.jsp";
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userRole")).thenReturn("Employee");
		when(session.getAttribute("user")).thenReturn(admin);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		servlet.doPost(request, response);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	@Test
	void userNull() throws ServletException, IOException {
		String path = "WEB-INF/EmployeeRegistration.jsp";
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userRole")).thenReturn("Employee");
		when(session.getAttribute("user")).thenReturn(null);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		servlet.doPost(request, response);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
}
