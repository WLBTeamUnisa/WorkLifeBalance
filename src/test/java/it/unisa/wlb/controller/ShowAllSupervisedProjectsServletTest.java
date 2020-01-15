package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.ShowAllSupervisedProjectsServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;

/**
 * The aim of this class is testing ShowAllSupervisedProjectsServlet.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class ShowAllSupervisedProjectsServletTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	private ShowAllSupervisedProjectsServlet servlet;
	private Project project;
	private List<Project> listProject;
	private Employee employee;
	private String email;
	private int status;
	private String projectName;
	private int idProject;
	private String json;
	
	@BeforeEach
	void setUp() throws Exception {
		email = "m.rossi1@wlb.it";
		status = 1;
		employee = new Employee();
		employee.setEmail(email);
		employee.setStatus(status);
		
		projectName = "WLBG13";
		idProject = 1;
		project = new Project();
		project.setName(projectName);
		project.setEmployee(employee);
		project.setId(idProject);
		listProject = new ArrayList<Project>();
		listProject.add(project);
		employee.setProjects1(listProject);
		
		servlet = new ShowAllSupervisedProjectsServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		json = "[{\"projectName\":\"WLBG13\"}]";
	}

	@Test
	void testFailure() throws ServletException, IOException {
		employee.setStatus(0);
		request.getSession().setAttribute("user", employee);
		servlet.doPost(request, response);
		assertEquals(response.getContentAsString(), "");
	}
	
	@Test 
	void nullTest() throws ServletException, IOException {
		employee = null;
		request.getSession().setAttribute("user", employee);
		servlet.doPost(request, response);
		assertEquals(response.getContentAsString(), "");
	}
	
	@Test
	void testSuccess() throws ServletException, IOException {
		 request.getSession().setAttribute("user", employee);
		 servlet.doPost(request, response);
		 assertEquals(json, response.getContentAsString()); 
	}

}
