package it.unisa.wlb.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.LoginServlet;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.dao.IAdminDAO;
import it.unisa.wlb.utils.Utils;
/**
 * The aims of this class it testing admin access
 * 
 * @author Vincenzo Fabiano
 *
 */
class LoginServletTestAdmin {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private LoginServlet servlet;
	
	private Admin admin;
	private String name;
	private String email;
	private String surname;
	private String password;
	private String generatedPassword;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		
		servlet = new LoginServlet();
		name = "Marco";
		surname = "Rossi";
		password = "MarcoRossi1.";
		generatedPassword = Utils.generatePwd(password);
		email = "m.rossi1@wlbadmin.it";
		admin = new Admin();
		admin.setEmail(email);
		admin.setName(name);
		admin.setSurname(surname);
		admin.setPassword(generatedPassword);
	}

	/**
	 * Admin logs in correctly
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	void adminLogin() throws ServletException,IOException {
		String message = "success";
		

		IAdminDAO adminDao = mock(IAdminDAO.class);
		Mockito.when(adminDao.retrieveByEmailPassword(admin.getEmail(), admin.getPassword())).thenReturn(admin);

		servlet = new LoginServlet();
		servlet.setAdminDao(adminDao);

		request.setParameter("email", email);
		request.setParameter("password", password);
		
		servlet.doPost(request, response);

		assertEquals(message, (String) request.getAttribute("login"));
	}

}
