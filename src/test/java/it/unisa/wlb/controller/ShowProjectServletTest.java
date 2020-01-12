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
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import it.unisa.wlb.controller.ShowProjectServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDao;

/**
 * The aim of this class is testing ShowProjectServlet.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class ShowProjectServletTest {

	@Mock
	private IProjectDao projectDao;
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private ShowProjectServlet servlet;
	private Project project;
	private Employee employee1;
	private Employee employee2;
	private String name;
	private String description;
	private String scope;
	private Date startDate;
	private Date endDate;
	private List<Employee> employeeList;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ShowProjectServlet();
		employee1 = new Employee();
		employee2 = new Employee();
		
		name = "WLBG13";
		startDate = new Date();
		endDate = new Date();
		scope = "smart working";
		description = "flessibilità sul lavoro";
		project = new Project();
		project.setEndDate(endDate);
		project.setStartDate(startDate);
		project.setDescription(description);
		project.setScope(scope);
		employeeList = new ArrayList<>();
		employeeList.add(employee1);
		employeeList.add(employee2);
		project.setEmployees(employeeList);
		
	}

	@Test
	void projectExists() throws ServletException, IOException {
		String message = "ok";
		request.setParameter("name", name);
		request.getSession().setAttribute("userRole", "Admin");
		when(projectDao.retrieveByName(name)).thenReturn(project);
		servlet.setProjectDao(projectDao);
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(message, attribute);		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void projectNotExists() throws ServletException, IOException {
		String message = "error";
		request.setParameter("name", name);
		request.getSession().setAttribute("userRole", "Admin");
		when(projectDao.retrieveByName(name)).thenThrow(Exception.class);
		servlet.setProjectDao(projectDao);
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(message, attribute);		
	}
	
	@Test
	void projecNameEmpty() throws ServletException, IOException {
		String message = "error";
		request.setParameter("name", "");
		request.getSession().setAttribute("userRole", "Admin");
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
