package it.unisa.wlb.test;

import java.io.IOException;
import java.util.*;
import it.unisa.wlb.controller.AddProjectServlet;
import it.unisa.wlb.controller.EmployeeRegistrationServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IProjectDAO;
import it.unisa.wlb.model.jpa.ProjectJpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;

public class AddProjectServletTest extends Mockito {


	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private AddProjectServlet servlet;
	private IProjectDAO pDao;



	@BeforeEach
	public void setUp() {
		servlet = new AddProjectServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		pDao = mock(IProjectDAO.class);
	}


	// name field not inserted - TC_2.1_1
	@Test
	public void TC_2_1_1() throws ServletException, IOException {
		request.addParameter("name", "");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("manager","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// name field inserted doesn't respect the specified lenght  - TC_2.2_2
	@Test
	public void TC_2_2_2() throws ServletException, IOException {
		request.addParameter("name", "WLBCHG341DMAW29QWE");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("manager","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}
	
	// name field doesn't respect the specified format  -  TC_2.2_3
	@Test
	public void TC_2_2_3() throws ServletException, IOException {
		request.addParameter("name", "WLBè");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("manager","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// name field already exists  -  TC_2.2_4
	@Test
	public void TC_2_2_4() throws ServletException, IOException {
		
		String commonName = "WLB13PO";
		Project pr = new Project();
		Date dateS = new Date(2019,11,02);
		Date dateE = new Date(2019,12,02);
		Employee em = new Employee();
		List<Employee> li = new ArrayList<Employee>();
		li.add(em);
		
		pr.setName("WLB13PO");
	    pr.setScope("SmartWorking");
	    pr.setStartDate(dateS);
	    pr.setEndDate(dateE);
	    pr.setDescription("Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
	    pr.setEmployees(li);
	    pr.setEmployee(em);
		
		IProjectDAO projectDao = mock(IProjectDAO.class);
		when(projectDao.retrieveByName(commonName)).thenReturn(pr);		
		
		AddProjectServlet tmp = new AddProjectServlet(projectDao);
	   
	    
		request.addParameter("name", commonName);
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		
		assertThrows(IllegalArgumentException.class, () -> {
			tmp.doPost(request, response);
		});

	}

	// scope field not inserted  -  TC_2.2_5
	@Test
	public void TC_2_2_5() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// scope field doesn't respect the specified lenght -  TC_2.2_6
	@Test
	public void TC_2_2_6() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "VBFHBVHDVBDFHJBVDFVFVHFVH FHFBVHDJBFDK");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// scope field doesn't respect the specified format  -  TC_2.2_7
	@Test
	public void TC_2_2_7() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking11.");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// start date field doesn't respect the specified format  -  TC_2.2_8
	@Test
	public void TC_2_2_8() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2-29-200");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// end date field doesn't respect the specified format   -  TC_2.2_9
	@Test
	public void TC_2_2_9() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2-29-2000");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// Manager E-mail doesn't exist into database -  TC_2.2_10
	@Test
	public void TC_2_2_10() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossivasdf1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// inserted email doesn't correspond to any manager -  TC_2.2_11
	@Test
	public void TC_2_2_11() throws ServletException, IOException {
		request.addParameter("name", "");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// description field doesn't respect the specified lenght -  TC_2.2_12
	@Test
	public void TC_2_2_12() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// description field doesn't respect the specified lenght  -  TC_2.2_13
	@Test
	public void TC_2_2_13() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "L’azione di piano intende aiutare e sostenere quelle famiglie e quei genitori che non riescono a conciliare i tempi del lavoro con quelli della famiglia, "
				+ "soprattutto nella fase della chiusura estiva delle scuole. Aumentare la capacità del sistema pubblico di cure a lungo termine di supportare le famiglie.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// Employees list field is empty -  TC_2.2_14
	@Test
	public void TC_2_2_14() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "0");
		request.addParameter("employee", "");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	// Employee's email doens't exist into database  -  TC_2.2_15
	@Test
	public void TC_2_2_15() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchiii100@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}
	
	// Inserted email doesn't correspond to any manager  -  TC_2.2_16
	@Test
	public void TC_2_2_16() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.verdi2@wlb.it");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}
	
	// Project inserted with succcess  -  TC_2.2_17
	@Test
	public void TC_2_2_17() throws ServletException, IOException {
		
		String commonName = "WLB13PO";
		Project pr = new Project();
		Date dateS = new Date(2019,11,02);
		Date dateE = new Date(2019,12,02);
		Employee em = new Employee();
		List<Employee> li = new ArrayList<Employee>();
		li.add(em);
		
		IProjectDAO projectDao = mock(IProjectDAO.class);
		when(projectDao.retrieveByName(commonName)).thenReturn(pr);		
		
		AddProjectServlet tmp = new AddProjectServlet(projectDao);
		
		request.addParameter("name", commonName);
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail","m.rossi1@wlb.it");
		request.addParameter("description", "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("employeesList", "1");
		request.addParameter("employee", "m.bianchi1@wlb.it");
		tmp.doPost(request, response);
		assertEquals("success", request.getAttribute("result"));
	}
	
	
}