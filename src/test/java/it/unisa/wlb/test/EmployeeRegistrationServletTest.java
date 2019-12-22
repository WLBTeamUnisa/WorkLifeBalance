package it.unisa.wlb.test;

import java.io.IOException;
import it.unisa.wlb.controller.EmployeeRegistrationServlet;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.ejb.EJB;
import javax.servlet.ServletException;

/**
 * This test class follows the specification of the section "3.1.1 TC_1.1 Registra dipendente" of the document "Test Case Specification"
 * 
 * @author Aniello, Sabato
 *
 */
public class EmployeeRegistrationServletTest extends Mockito {
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private EmployeeRegistrationServlet servlet;

	@EJB
	private IEmployeeDAO employeeDao;
	
	@BeforeEach
	public void setUp() {
		servlet = new EmployeeRegistrationServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	
	
	/**
	 * "name" field doesn't respect the specified lenght
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * "name" field doesn't respect the specified lenght
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test 
	public void TC_1_1_2() throws ServletException, IOException {
		request.addParameter("name", "Marcooooooooooooooooo");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	/**
	 * "name" field doesn't respect the specified format
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * "surname" field doesn't respect the specified lenght
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * "surname" field doesn't respect the specified lenght
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	 
	/**
	 * "surname" field doesn't respect the specified format
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	
	/**
	 * "email" field doesn't respect the specified lenght
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test 
	public void TC_1_1_7() throws ServletException, IOException {
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.r1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
	}
	
	/**
	 * "email" field doesn't respect the specified lenght
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	
	/**
	 * "email" field doesn't respect the specified format
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * "email" field doesn't exist into database
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test 
	public void TC_1_1_10() throws ServletException, IOException {
		
		Employee employee = new Employee();
		employee.setName("Marco");
		employee.setSurname("Rossi");
		employee.setEmail("m.rossi1@wlb.it");
		employee.setPassword("MarcoRossi1.");
		employee.setStatus(1);
		
		employee = employeeDao.create(employee);
		
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.ross1@wlb.it");
		request.addParameter("password", "MarcoRossi1.");
		request.addParameter("verifyPassword","MarcoRossi1.");
		request.addParameter("status", "Manager");
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
		});
		
		employeeDao.remove(employee);
	}
	
	/**
	 * "password" field doesn't respect the specified lenght
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * "password" field doesn't respect the specified lenght
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * "password" field doesn't respect the specified lenght
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * "password" field doesn't respect the specified format
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * "verify password" field doesn't respect the specified lenght
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * "verify password" field doesn't respect the specified format
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * "password" field and verify password field doesn't correspond
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * "status" field not inserted
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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
	
	/**
	 * Employee/Manager registration ended with success
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
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