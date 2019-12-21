import static org.junit.jupiter.api.Assertions.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.jupiter.api.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.LoginServlet;

import org.junit.jupiter.api.Test;

	public class LoginServletTest extends Mockito {

		@Mock
		private static HttpServletRequest request;
		@Mock
		private static HttpServletResponse response;
		LoginServlet servlet;
		
		@Before
		 public void setUp() {
	        servlet= new LoginServlet();
	        request = new MockHttpServletRequest();
	        response = new MockHttpServletResponse();
	    }
		
		//Campo email minore rispetto alla lunghezza prestabilita
		@Test
		public void TC_4_1_1() throws Exception {
			
			when(request.getParameter("email")).thenReturn(" ");
			when(request.getParameter("password")).thenReturn("MarcoRossi1.");
			final String message = "Email e password non validi";
			IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

				servlet.doPost(request, response);
			});
			assertEquals(message, exceptionThrown.getMessage());
		}
		
		
		//Campo email maggiore rispetto alla lunghezza prestabilita
		@Test
		public void TC_4_1_2() throws Exception {
			
			when(request.getParameter("email")).thenReturn("m.rossiiiiiiiiiiiiiiiiiiiiiiiii@wlb.it");
			when(request.getParameter("password")).thenReturn("MarcoRossi1.");
			final String message = "Email e password non validi";
			IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

				servlet.doPost(request, response);
			});
			assertEquals(message, exceptionThrown.getMessage());
		}
		
		//Campo email non rispetta il formato
		@Test
		public void TC_4_1_3() throws Exception {
			
			when(request.getParameter("email")).thenReturn("m.rossi1@email.it ");
			when(request.getParameter("password")).thenReturn("MarcoRossi1.");
			final String message = "Email e password non validi";
			IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

				servlet.doPost(request, response);
			});
			assertEquals(message, exceptionThrown.getMessage());
		}
		
		//Campo password non rispetta il formato
		@Test
		public void TC_4_1_5() throws Exception {
			
			when(request.getParameter("email")).thenReturn("m.rossi1@wlb.it");
			when(request.getParameter("password")).thenReturn("MarcoRos");
			final String message = "Email e password non validi";
			IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

				servlet.doPost(request, response);
			});
			assertEquals(message, exceptionThrown.getMessage());
		}

}

