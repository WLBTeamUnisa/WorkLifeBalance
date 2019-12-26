package it.unisa.wlb.test;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.LoginServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

import org.junit.jupiter.api.Test;

/*Unit Test Black Box for TC_4.1 (RF_GA_17 - Login)*/
public class LoginServletTest {

	MockHttpServletRequest request;
	MockHttpServletResponse response;
	LoginServlet servlet;
	IEmployeeDAO eDao;
	
	@BeforeEach
	 public void setUp() {
		MockitoAnnotations.initMocks(this);
        servlet= new LoginServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        eDao = mock(IEmployeeDAO.class);
    }
	
	/*TC_4.1_1: email.length() < 5. 
	 * It should be email.length >= 5 and email.length <=30 - FAIL*/
	@Test
	public void TC_4_1_1() throws Exception {
		final String message = "Email e/o password non validi";
		request.setParameter("email", "");
		request.setParameter("password", "MarcoRossi1.");
		servlet.doPost(request, response);
		assertEquals(message, response.getContentAsString());	
	}
	
	/*TC_4.1_2: email.length() > 30. 
	 * It should be email.length >= 5 and email.length <=30 - FAIL*/
	@Test
	public void TC_4_1_2() throws Exception {
		final String message = "Email e/o password non validi";
		request.setParameter("email", "m.rossiiiiiiiiiiiiiiiiiiiiiiiii@wlb.it");
		request.setParameter("password", "MarcoRossi1.");
		servlet.doPost(request, response);
		assertEquals(message, response.getContentAsString());
	}
	
	/* TC_4.1_3: email doesn't respect format. 
	 * The formats that should be respected are:
	 * 	- [a-z]{1}\.[a-z]+[1-9]*\@wlb.it 
	 * 	- [a-z]{1}\.[a-z]+[1-9]*\@wlbadmin.it) - FAIL*/
	@Test
	public void TC_4_1_3() throws Exception {
		final String message = "Email e/o password non validi";
		request.setParameter("email", "m.rossi1@email.it");
		request.setParameter("password", "MarcoRossi1.");
		servlet.doPost(request, response);
		assertEquals(message, response.getContentAsString());
	}

	/* TC_4.1_4: email doesn't exist in database. - FAIL*/
	@Test
	public void TC_4_1_4() throws Exception {
		final String message = "Email e/o password non validi";
		request.setParameter("email", "m.rossi1@wlb.it");
		request.setParameter("password", "MarcoRossi1.");
		servlet.doPost(request, response);
		assertEquals(message, response.getContentAsString());
	}
	
	/* TC_4.1_5: password doesn't respect the format. 
	 * The format that should be respected is:
	 * (?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\.!@#\$%\^&\*])(?=.{8,20}) - FAIL*/
	@Test
	public void TC_4_1_5() throws Exception {
		final String message = "Email e/o password non validi";
		request.setParameter("email", "m.rossi1@wlb.it");
		request.setParameter("password", "MarcoRos");
		servlet.doPost(request, response);
		assertEquals(message, response.getContentAsString());
	}
	
	/* TC_4.1_6: password doesn't exist in database. - FAIL */
	@Test
	public void TC_4_1_6() throws Exception {
		final String message = "Email e/o password non validi";
		request.setParameter("email", "m.rossi1@wlb.it");
		request.setParameter("password", "MarcoRossi2.");
		servlet.doPost(request, response);
		assertEquals(message, response.getContentAsString());
	}
	
	/* TC_4.1_7: password doesn't correspond to the email entered. - FAIL */
	@Test
	public void TC_4_1_7() throws Exception {
		final String message = "Email e/o password non validi";
		request.setParameter("email", "m.rossi1@wlb.it");
		request.setParameter("password", "MarcoRossi2.");
		servlet.doPost(request, response);
		assertEquals(message, response.getContentAsString());
	}
	
	/* TC_4.1_8: SUCCESS. */
	@Test
	public void TC_4_1_8() throws Exception {
		Employee e = new Employee();
		e.setEmail("m.rossi1@wlb.it");
		e.setPassword("MarcoRossi1.");
		e.setName("Marco");
		e.setPassword("Rossi");
		e.setStatus(0);
		Mockito.when(eDao.create(e)).thenReturn(e);
		request.setParameter("email", "m.rossi1@wlb.it");
		request.setParameter("password", "MarcoRossi1.");
		servlet.doPost(request, response);
		assertTrue(response.SC_ACCEPTED==202);
	}

}