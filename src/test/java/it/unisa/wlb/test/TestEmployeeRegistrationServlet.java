package it.unisa.wlb.test;

import java.io.IOException;
import java.util.*;
import it.unisa.wlb.controller.EmployeeRegistrationServlet;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.jpa.EmployeeJpa;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import javax.servlet.ServletException;

public class TestEmployeeRegistrationServlet extends Mockito {
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private EmployeeRegistrationServlet servlet;

	

	@Before
	public void setUp() {
		servlet = new EmployeeRegistrationServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	
	// campo nome non rispetta la lunghezza specificata
	@Test 
	public void TC_1_1_1() throws ServletException, IOException {
		request.addParameter("name", "M");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo nome supera la lunghezza stabilita
	@Test 
	public void TC_1_1_2() throws ServletException, IOException {
		request.addParameter("name", "Marcooooooooooooooo");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo nome non rispetta il formato stabilito
	@Test 
	public void TC_1_1_3() throws ServletException, IOException {
		request.addParameter("name", "Marco90");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo cognome non rispetta la lunghezza specificata
	@Test 
	public void TC_1_1_4() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "R");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo cognome supera la lunghezza stabilita
	@Test 
	public void TC_1_1_5() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossiiiiiiiiiiiiiiiiiiii");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo cognome non rispetta il formato stabilito
	@Test 
	public void TC_1_1_6() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi90");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo E-mail non rispetta la lunghezza stabilita
	@Test 
	public void TC_1_1_7() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.ross1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo E-mail supera la lunghezza specificata
	@Test 
	public void TC_1_1_8() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossiiiiiiiiiiiiiiiii1@wlb.it");
		request.addParameter("password", "MarcoRossi1");
		request.addParameter("verifyPassword","MarcoRossi1");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo E-mail non rispetta il formato stabilito
	@Test 
	public void TC_1_1_9() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@email.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo E-mail già esistente nel database

	@Test 
	public void TC_1_1_10() throws ServletException, IOException {
		Employee em = new Employee();
		em.setName("Marco");
		em.setSurname("Rossi");
		em.setEmail("m.rossi1@wlb.it");
		em.setPassword("MarcoRossi1.");
		em.setStatus(1);
		
		EmployeeJpa jpa = new EmployeeJpa();
		jpa.create(em);
		
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.ross1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo password non rispetta la lunghezza specificata
	@Test 
	public void TC_1_1_11() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "Mr1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo password supera la lunghezza specificata
	@Test 
	public void TC_1_1_12() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossimarcomarco1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo password non rispetta il formato spcificato
	@Test 
	public void TC_1_1_13() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "marco");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo verifica password non rispetta la lunghezza specificata
	@Test 
	public void TC_1_1_14() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","Mr1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo verifica password supera la lunghezza specificata
	@Test 
	public void TC_1_1_15() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossimarcomarco1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo verifica password  non rispetta il formato stabilito
	@Test 
	public void TC_1_1_16() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","marco");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// i campi password e verifica password non corrispondono
	@Test 
	public void TC_1_1_17() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1234.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// campo status non è stato inserito
	@Test 
	public void TC_1_1_18() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	// la registrazione dell'utente termina con successo
	@Test 
	public void TC_1_1_19() throws ServletException, IOException {
		
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		servlet.doGet(request, response);
		assertEquals("json", response.getContentType());
		
	}

}