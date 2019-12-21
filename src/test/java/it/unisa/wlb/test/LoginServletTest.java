package it.unisa.wlb.test;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.LoginServlet;
import it.unisa.wlb.model.bean.Employee;

import org.junit.jupiter.api.Test;



	public class LoginServletTest {

		MockHttpServletRequest request;
		MockHttpServletResponse response;
		LoginServlet servlet;
		
		@BeforeEach
		 public void setUp() {
	        servlet= new LoginServlet();
	        request = new MockHttpServletRequest();
	        response = new MockHttpServletResponse();
	    }
		
		@Test
		public void TC_4_1_1() throws Exception {
			final String message = "Email e/o password non validi";
			request.setParameter("email", "");
			request.setParameter("password", "MarcoRossi1.");
			servlet.doPost(request, response);
			assertEquals(message, response.getContentAsString());	
		}
		
		
		
		@Test
		public void TC_4_1_2() throws Exception {
			final String message = "Email e/o password non validi";
			request.setParameter("email", "m.rossiiiiiiiiiiiiiiiiiiiiiiiii@wlb.it");
			request.setParameter("password", "MarcoRossi1.");
			servlet.doPost(request, response);
			assertEquals(message, response.getContentAsString());
		}
		
		@Test
		public void TC_4_1_3() throws Exception {
			final String message = "Email e/o password non validi";
			request.setParameter("email", "m.rossi1@email.it");
			request.setParameter("password", "MarcoRossi1.");
			servlet.doPost(request, response);
			assertEquals(message, response.getContentAsString());
		}

		@Test
		public void TC_4_1_4() throws Exception {
			final String message = "Email e/o password non validi";
			request.setParameter("email", "m.rossi1@wlb.it");
			request.setParameter("password", "MarcoRossi1.");
			servlet.doPost(request, response);
			assertEquals(message, response.getContentAsString());
		}
		
		@Test
		public void TC_4_1_5() throws Exception {
			final String message = "Email e/o password non validi";
			request.setParameter("email", "m.rossi1@wlb.it");
			request.setParameter("password", "MarcoRos");
			servlet.doPost(request, response);
			assertEquals(message, response.getContentAsString());
		}
		
		@Test
		public void TC_4_1_6() throws Exception {
			final String message = "Email e/o password non validi";
			request.setParameter("email", "m.rossi1@wlb.it");
			request.setParameter("password", "MarcoRossi2.");
			servlet.doPost(request, response);
			assertEquals(message, response.getContentAsString());
		}
		
		@Test
		public void TC_4_1_7() throws Exception {
			final String message = "Email e/o password non validi";
			request.setParameter("email", "m.rossi1@wlb.it");
			request.setParameter("password", "MarcoRossi2.");
			servlet.doPost(request, response);
			assertEquals(message, response.getContentAsString());
		}
		
		@Test
		public void TC_4_1_8() throws Exception {
			request.setParameter("email", "v.fabiano1@wlb.it");
			request.setParameter("password", "Vincenzo98.");
			servlet.doPost(request, response);
			assertTrue(response.SC_ACCEPTED==202);
		}

}