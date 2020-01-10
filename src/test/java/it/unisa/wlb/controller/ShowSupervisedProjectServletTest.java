package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.ShowSupervisedProjectServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDAO;

public class ShowSupervisedProjectServletTest {
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	@Mock
	private IProjectDAO projectDao;
	@Mock
	private HttpSession session;
	@Mock
	private RequestDispatcher dispatcher;
	
	private ShowSupervisedProjectServlet servlet;
	private Project project;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		servlet = new ShowSupervisedProjectServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		String commonName = "WLB13PO";
		String managerEmail = "m.rossi1@wlb.it";
		Employee manager = new Employee();
		manager.setEmail(managerEmail);
		manager.setStatus(1);
		Date dateS = new Date(2019,11,02);
		Date dateE = new Date(2019,12,02);
		String scope="SmartWorking";
		String description="Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative";
		
		project = new Project();
		project.setName(commonName);
		project.setEmployee(manager);
		project.setEndDate(dateE);
		project.setStartDate(dateS);
		project.setScope(scope);
		project.setDescription(description);
		request.addParameter("projectName", "WLB13PO");
		
	}
	
	@Test
	void test() throws ServletException, IOException {
		when(projectDao.retrieveByName(project.getName())).thenReturn(project);
		servlet.setProjectDao(projectDao);
		servlet.doPost(request, response);
		Project p = (Project) request.getAttribute("project");
		assertEquals(p.getName(), "WLB13PO");

		
	}
	
	@Test
	void projectNull() throws ServletException, IOException {
		when(projectDao.retrieveByName(project.getName())).thenReturn(null);
		servlet.setProjectDao(projectDao);
		servlet.doPost(request, response);
		String result =(String) request.getAttribute("result");
		assertEquals(result, "error");
		
	}
}
