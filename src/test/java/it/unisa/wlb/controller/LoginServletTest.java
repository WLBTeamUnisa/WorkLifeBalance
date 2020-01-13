//package it.unisa.wlb.controller;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import javax.persistence.NoResultException;
//import javax.servlet.ServletException;
//
//import static org.mockito.Mockito.mock;
//
//import java.io.IOException;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//
//import it.unisa.wlb.controller.LoginServlet;
//import it.unisa.wlb.model.bean.Employee;
//import it.unisa.wlb.model.dao.IEmployeeDao;
//import it.unisa.wlb.utils.Utils;
//
///**
// * This test class follows the specification of the section "3.4.1 TC_4.1 Login" Test Case Specification"
// * 
// * @author Vincenzo Fabiano
// * 
// */
//public class LoginServletTest {
//
//	MockHttpServletRequest request;
//	MockHttpServletResponse response;
//	LoginServlet servlet;
//
//	@BeforeEach
//	public void setUp() {
//	
//		servlet= new LoginServlet();
//		request = new MockHttpServletRequest();
//		response = new MockHttpServletResponse();
//	}
//
//	/**
//	 * TC_4.1_1: email.length() < 5. 
//	 * It should be email.length >= 5 and email.length <=30 - FAIL
//	 * 
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@Test
//	public void TC_4_1_1() throws ServletException,IOException {
//		final String message = "Email e/o password non validi";
//		request.setParameter("email", "");
//		request.setParameter("password", "MarcoRossi1.");
//		servlet.doPost(request, response);
//		assertEquals(message, response.getContentAsString());	
//	}
//
//	/**
//	 * TC_4.1_2: email.length() > 30. 
//	 * It should be email.length >= 5 and email.length <=30 - FAIL
//	 * 
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@Test
//	public void TC_4_1_2() throws ServletException,IOException {
//		final String message = "Email e/o password non validi";
//		request.setParameter("email", "m.rossiiiiiiiiiiiiiiiiiiiiiiiii@wlb.it");
//		request.setParameter("password", "MarcoRossi1.");
//		servlet.doPost(request, response);
//		assertEquals(message, response.getContentAsString());
//	}
//
//	/**
//	 * TC_4.1_3: email doesn't respect format. 
//	 * The formats that should be respected are:
//	 * 	- [a-z]{1}\.[a-z]+[0-9]*\@wlb.it 
//	 * 	- [a-z]{1}\.[a-z]+[0-9]*\@wlbadmin.it) - FAIL
//	 * 
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@Test
//	public void TC_4_1_3() throws ServletException,IOException {
//		final String message = "Email e/o password non validi";
//		request.setParameter("email", "m.rossi1@email.it");
//		request.setParameter("password", "MarcoRossi1.");
//		servlet.doPost(request, response);
//		assertEquals(message, response.getContentAsString());
//	}
//
//	/**
//	 * TC_4.1_4: email doesn't exist in database. - FAIL
//	 * 
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@Test
//	public void TC_4_1_4() throws ServletException,IOException {
//		final String message = "Email e/o password non validi";		
//		String email = "m.rossi1@wlb.it";
//		String password = "MarcoRossi1.";		
//		request.setParameter("email", email);
//		request.setParameter("password", password);		
//		IEmployeeDao eDao = mock(IEmployeeDao.class);
//		Mockito.when(eDao.retrieveByEmailPassword(email, Utils.generatePwd(password))).thenThrow(new NoResultException());		
//		servlet.setEmployeeDao(eDao);
//		servlet.doPost(request, response);		
//		assertEquals(message, response.getContentAsString());
//	}
//
//	/**
//	 * TC_4.1_5: password doesn't respect the format. 
//	 * The format that should be respected is:
//	 * (?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\.!@#\$%\^&\*])(?=.{8,20}) - FAIL
//	 * 
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@Test
//	public void TC_4_1_5() throws ServletException,IOException {
//		final String message = "Email e/o password non validi";
//		request.setParameter("email", "m.rossi1@wlb.it");
//		request.setParameter("password", "MarcoRos");
//		servlet.doPost(request, response);
//		assertEquals(message, response.getContentAsString());
//	}
//
//	/**
//	 * TC_4.1_6: password doesn't exist in database. - FAIL 
//	 * 
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@Test
//	public void TC_4_1_6() throws ServletException,IOException {
//		final String message = "Email e/o password non validi";		
//		String email = "m.rossi1@wlb.it";
//		String password = "MarcoRossi2.";		
//		request.setParameter("email", email);
//		request.setParameter("password", password);		
//		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
//		Mockito.when(employeeDao.retrieveByEmailPassword(email, Utils.generatePwd(password))).thenThrow(new NoResultException());		
//		servlet.setEmployeeDao(employeeDao);
//		servlet.doPost(request, response);		
//		assertEquals(message, response.getContentAsString());
//	}
//
//	/**
//	 * TC_4.1_7: password doesn't correspond to the email inserted. - FAIL 
//	 * 
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@Test
//	public void TC_4_1_7() throws ServletException,IOException{
//		final String message = "Email e/o password non validi";		
//		String email = "m.rossi1@wlb.it";
//		String password = "MarcoRossi2.";		
//		request.setParameter("email", email);
//		request.setParameter("password", password);		
//		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
//		Mockito.when(employeeDao.retrieveByEmailPassword(email, Utils.generatePwd(password))).thenThrow(new NoResultException());		
//		servlet.setEmployeeDao(employeeDao);
//		servlet.doPost(request, response);
//		assertEquals(message, response.getContentAsString());
//	}
//
//	/**
//	 * TC_4.1_8: SUCCESS.
//	 * 
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@Test
//	public void TC_4_1_8() throws ServletException,IOException {
//		String message = "success";
//		Employee employee = new Employee();
//		employee.setEmail("m.rossi1@wlb.it");
//		employee.setPassword(Utils.generatePwd("MarcoRossi1."));
//		employee.setName("Marco");
//		employee.setSurname("Rossi");
//		employee.setStatus(0);
//
//		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
//		Mockito.when(employeeDao.retrieveByEmailPassword(employee.getEmail(), employee.getPassword())).thenReturn(employee);
//
//		servlet.setEmployeeDao(employeeDao);
//
//		request.setParameter("email", "m.rossi1@wlb.it");
//		request.setParameter("password", "MarcoRossi1.");
//
//		servlet.doPost(request, response);
//
//		assertEquals(message, (String) request.getAttribute("login"));
//	}
//	
//}
//
