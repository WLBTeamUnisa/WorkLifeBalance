package it.unisa.wlb.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.unisa.wlb.controller.HomeServlet;
import it.unisa.wlb.model.bean.Employee;

/**
 * 
 * This class tests methods of HomeServlet
 * 
 * @author Vincenzo Fabiano
 *
 */
class HomeServletTest {
	
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	HttpSession session;
	@Mock
	RequestDispatcher dispatcher;
	HomeServlet servlet;
	
	private Employee employee;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		servlet = new HomeServlet();
		employee = new Employee();
	}
	/**
	 * Case of success. The servlet will redirect to Homepage.jsp
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	void homeServletSuccess() throws ServletException, IOException {
		String path = "WEB-INF/Homepage.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
		
	}
	
	/**
	 * Case of failure. The servlet will redirect to Index.jsp
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	void homeServletFail() throws ServletException, IOException {
		String path = "WEB-INF/Index.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
		
	}
}
