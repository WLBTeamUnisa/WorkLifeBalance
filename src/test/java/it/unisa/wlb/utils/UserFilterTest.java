package it.unisa.wlb.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeast;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.utils.UserFilter;
import it.unisa.wlb.utils.Utils;

/**
 * The aims of this class is testing UserFilter.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class UserFilterTest {

	@Mock
	private HttpServletRequest request;
	
	@Mock
	private ServletResponse response;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private RequestDispatcher dispatcher;
	
	@Mock
	private FilterChain chain;
	
	private UserFilter userFilter;
	private Employee employee;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		userFilter = new UserFilter();
		employee = new Employee();
		employee.setEmail("m.rossi1@wlbadmin.it");
		employee.setName("Marco");
		employee.setSurname("Rossi");
		employee.setPassword(Utils.generatePwd("MarcoRossi1."));
		employee.setStatus(0);
	}

	@Test
	void accessSuccessTest() throws IOException, ServletException {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		userFilter.doFilter(request, response, chain);
		verify(chain, atLeast(1)).doFilter(request, response);
	}

	@Test
	void userNotLoggedTest() throws IOException, ServletException {
		String path = "/WorkLifeBalance/";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		when(request.getRequestURI()).thenReturn(path);
		userFilter.doFilter(request, response, chain);
		verify(chain, atLeast(1)).doFilter(request, response);
	}
	
	@Test
	void pageAccessDeniedTest() throws IOException, ServletException {
		String path = "/WorkLife/";
		String pathDispatcher = "WEB-INF/DenyAccess.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);
		when(request.getRequestDispatcher(pathDispatcher)).thenReturn(dispatcher);
		when(request.getRequestURI()).thenReturn(path);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		userFilter.doFilter(request, response, chain);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(pathDispatcher, captor.getValue());
	}
}
