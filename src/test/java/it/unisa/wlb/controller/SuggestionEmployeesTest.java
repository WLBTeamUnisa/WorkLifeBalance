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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import it.unisa.wlb.controller.SuggestionEmployeesServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

/**
 * The aim of this class is testing SuggestionEmployees.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class SuggestionEmployeesTest {

	@Mock 
	private IEmployeeDAO employeeDao;
	
	private SuggestionEmployeesServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private String email;
	private Employee employee;
	private List<Employee> employeeList;
	private String json;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new SuggestionEmployeesServlet();
		email = "m.rossi1@wlb.it";
		employee = new Employee();
		employee.setEmail(email);
		employeeList = new ArrayList<>();
		json = "[{\"email\":\"m.rossi1@wlb.it\"}]";
	}

	@Test
	void employeeSuggestionsSuccess() throws ServletException, IOException {
		request.setParameter("email", email);
		request.setParameter("flag", "0");
		employee.setStatus(0);
		employeeList.add(employee);
		when(employeeDao.suggestByEmail(email)).thenReturn(employeeList);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertEquals(json, response.getContentAsString());
	}

	@Test
	void emanagerSuggestionsSuccess() throws ServletException, IOException {
		request.setParameter("email", email);
		request.setParameter("flag", "1");
		employee.setStatus(1);
		employeeList.add(employee);
		when(employeeDao.retrieveSuggestsManagerByEmail(email)).thenReturn(employeeList);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertEquals(json, response.getContentAsString());
	}
}
