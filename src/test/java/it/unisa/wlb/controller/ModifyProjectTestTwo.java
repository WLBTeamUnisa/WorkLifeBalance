package it.unisa.wlb.controller;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import it.unisa.wlb.controller.ModifyProjectServlet;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.model.dao.IProjectDao;
/**
 * This test class follows the specification of the section "3.2.3 TC_2.3 Modifica progetto" of the document "Test Case Specification"
 * 
 * @author Aniello Romano
 *
 */
public class ModifyProjectTestTwo extends Mockito {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private ModifyProjectServlet servlet;
	private IEmployeeDao employeeDao;
	private Employee manager;
	private Project oldProject;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {

		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ModifyProjectServlet();
		request.getSession().setAttribute("userRole", "Admin");
		request.getSession().setAttribute("user", new Admin());
		manager = new Employee();
		manager.setName("Luca");
		manager.setSurname("Rossi");
		manager.setEmail("l.rossi1@wlb.it");
		manager.setStatus(1);
		manager.setPassword("Ciao123.");
		oldProject = new Project();
		oldProject.setName("WLB13PO");
		oldProject.setScope("SmartWorking");
		oldProject.setStartDate(new Date(2019, 11, 02));
		oldProject.setEndDate(new Date(2019, 12, 02));
		oldProject.setDescription(
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		oldProject.setEmployee(manager);
		request.getSession().setAttribute("oldProject", oldProject);
	}
	
	@Test
	void wrongUserRoleTest() throws ServletException, IOException {

		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		request.getSession().setAttribute("userRole", "");
		request.addParameter("name", "WLB13BA");
		request.addParameter("scope", "SmartWorkingasd");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void nameTest() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		request.addParameter("name", "W");
		request.addParameter("scope", "SmartWorkingasd");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void name2Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aName = null;
		request.addParameter("name", aName);
		request.addParameter("scope", "SmartWorkingasd");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);

		assertEquals("", response.getContentAsString());
	}
	
	@Test
	void name3Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aName = "";
		request.addParameter("name", aName);
		request.addParameter("scope", "SmartWorkingasd");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void name4Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		request.addParameter("name", aName);
		request.addParameter("scope", "SmartWorkingasd");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void name5Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aName = "cioè+";
		request.addParameter("name", aName);
		request.addParameter("scope", "SmartWorkingasd");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void scope1Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aScope = null;
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", aScope);
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void scope2Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aScope = "";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", aScope);
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void scope3Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aScope = "èè";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", aScope);
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void scope4Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aScope = "a";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", aScope);
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void scope5Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aScope = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", aScope);
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void startDate1Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aDate = null;
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", aDate);
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void startDate2Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aDate = "";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", aDate);
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void startDate3Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aDate = "202020-15-24";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", aDate);
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void endDate1Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aDate = null;
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", aDate);
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void endDate2Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aDate = "";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", aDate);
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void endDate3Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aDate = "202020-15-24";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate","2019-10-02");
		request.addParameter("endDate", aDate);
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void description1Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aDescription = "";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description", aDescription);
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void description2Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aDescription = "a";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-10-2");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description", aDescription);
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void description3Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String aDescription = "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description", aDescription);
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void manager2Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String anEmail = "";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", anEmail);

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
		
	@Test
	void manager1Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		manager.setStatus(0);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void manager3Test() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		String anEmail = "m.rossi1@gmail.com";
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", anEmail);

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void sameManagerTest() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-10-02");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void afterDateTest() throws ServletException, IOException {
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-10-02");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("error", request.getAttribute("result"));
	}
	
	@Test
	void differentManagerTest() throws ServletException, IOException {
		List<Employee> employeesList = new ArrayList<>();
		employeesList.add(new Employee());	
		request.getSession().setAttribute("employeeList", employeesList);
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("success", request.getAttribute("result"));
	}
	
	@Test
	void employeeListEmptyTest() throws ServletException, IOException {
		List<Employee> employeesList = new ArrayList<>();
		request.getSession().setAttribute("employeeList", employeesList);
		IProjectDao projectDao = mock(IProjectDao.class);
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("success", request.getAttribute("result"));
	}
	
}