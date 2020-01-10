package it.unisa.wlb.test.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import it.unisa.wlb.utils.DenyManager;
import it.unisa.wlb.utils.Utils;
/**
 * The aim of this class is testing DenyManager.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class DenyManagerTest {


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
	
	private Employee employee;
	
	private DenyManager denyManager;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		denyManager = new DenyManager();
		employee = new Employee();
		employee.setEmail("m.rossi1@wlbadmin.it");
		employee.setName("Marco");
		employee.setSurname("Rossi");
		employee.setPassword(Utils.generatePwd("MarcoRossi1."));
		employee.setStatus(1);
		
	}

	@Test
	void accessDeniedTest() throws IOException, ServletException {
		String path = "WEB-INF/DenyAccess.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		denyManager.doFilter(request, response, chain);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}

}
