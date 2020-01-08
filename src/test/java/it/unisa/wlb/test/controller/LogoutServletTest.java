package it.unisa.wlb.test.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;

import it.unisa.wlb.controller.LogoutServlet;

/**
 * This class tests logout functionality
 * 
 * @author Vincenzo Fabiano
 *
 */
class LogoutServletTest extends Mockito {
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	
	LogoutServlet servlet;
	MockHttpSession session;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		servlet = new LogoutServlet();
		session = new MockHttpSession();
	}

	/**
	 * This method tests a success logout
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	void logoutTest() throws IOException, ServletException {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		when(request.getSession()).thenReturn(session);
		servlet.doPost(request, response);
		verify(response).sendRedirect(captor.capture());
		assertEquals(".", captor.getValue());
	}


}
