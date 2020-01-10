package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;

import it.unisa.wlb.controller.ShowEmployeePage;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.utils.Utils;


/**
 * The aim of this class is testing ShowEmployeePage.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class ShowEmployeePageTest {

	@Mock
	private IEmployeeDAO employeeDao;
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private ShowEmployeePage servlet;
	
	private Admin admin;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String generatedPassword;
	private Employee employee;
	private String nameEmployee;
	private int statusEmployee;
	private String surnameEmployee;
	private String emailEmployee;
	private String passwordEmployee;
	private String generatedPasswordEmployee;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ShowEmployeePage();
		
		name = "Marco";
		surname = "Rossi";
		email = "m.rossi1@wlbadmin.it";
		password = "MarcoRossi1.";
		generatedPassword = Utils.generatePwd(password);
		
		admin = new Admin();
		admin.setEmail(email);
		admin.setName(name);
		admin.setSurname(surname);
		admin.setPassword(generatedPassword);
		
		nameEmployee = "Vincenzo";
		surnameEmployee = "Verdi";
		emailEmployee = "v.verdi1@wlb,it";
		passwordEmployee = "VerdiVinc1.";
		statusEmployee = 0;
		generatedPasswordEmployee = Utils.generatePwd(passwordEmployee);
		
		employee = new Employee();
		employee.setName(nameEmployee);
		employee.setSurname(surnameEmployee);
		employee.setEmail(emailEmployee);
		employee.setPassword(generatedPasswordEmployee);
		employee.setStatus(statusEmployee);
		
		
	}

	@Test
	void employeeExists() throws ServletException, IOException {
		String message = "ok";
		request.getSession().setAttribute("user", admin);
		request.getSession().setAttribute("userRole", "Admin");
		request.setParameter("email", emailEmployee);
		when(employeeDao.retrieveByEmail(emailEmployee)).thenReturn(employee);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(message, attribute);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void employeeNotExists() throws ServletException, IOException {
		String message = "error";
		request.getSession().setAttribute("user", admin);
		request.getSession().setAttribute("userRole", "Admin");
		request.setParameter("email", emailEmployee);
		when(employeeDao.retrieveByEmail(emailEmployee)).thenThrow(Exception.class);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(message, attribute);
	}
	
	@Test
	void emailNull() throws ServletException, IOException {
		String message = "error";
		request.getSession().setAttribute("user", admin);
		request.getSession().setAttribute("userRole", "Admin");
		request.setParameter("email", "");
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(message, attribute);
	}
	
	@Test
	void userIsNotAdmin() throws ServletException, IOException {
		String message = "error";
		request.getSession().setAttribute("userRole", "");
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(message, attribute);
	}
	
	

}
