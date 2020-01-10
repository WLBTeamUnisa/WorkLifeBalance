package it.unisa.wlb.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
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

import it.unisa.wlb.controller.ShowCalendarHistoryPageServlet;
import it.unisa.wlb.controller.ShowCalendarHistoryServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IPrenotationDateDAO;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;
/**
 * The aim of this class is testing ShowCalendarHistoryPageServlet.java
 * 
 * @author Simranjit Singh
 *
 */
public class ShowCalendarHistoryPageServletTest {

	@Mock
	private IEmployeeDAO employeeDAO;
	@Mock
	private ISmartWorkingPrenotationDAO smartWorkingPrenotationDAO;
	@Mock
	private IPrenotationDateDAO prenotationDateDAO;
	@Mock
	private IWorkstationPrenotationDao workstationPrenotationDao;
	
	private MockHttpServletRequest request;
	
	private MockHttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private RequestDispatcher dispatcher;
	
	private ShowCalendarHistoryPageServlet servlet;
	
	private Employee employee;
	private Employee manager;
	private String name;
	private String surname;
	private String email;
	private String password;
	private int status;
	
	private String nameManager;
	private String surnameManager;
	private String emailManager;
	private String passwordManager;
	private int statusManager;
	private Project project;
	private Project project2;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		servlet = new ShowCalendarHistoryPageServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		employee = new Employee();
		manager= new Employee();
		project= new Project();
		project2= new Project();
		
		name="Marco";
		surname="Rossi";
		email="m.rossi1@wlb.it";
		password="Ciao1234.";
		status=0;
		
		nameManager="Vincenzo";
		surnameManager="Bianchi";
		emailManager="v.bianchi@wlb.it";
		passwordManager="Ciao1234.";
		statusManager=1;
		
		
		
}
	
	@Test
	void testFlag1() throws ServletException, IOException {

		employee.setEmail(email);
		employee.setName(name);
		employee.setSurname(surname);
		employee.setPassword(password);
		employee.setStatus(status);
		
		manager.setEmail(emailManager);
		manager.setName(nameManager);
		manager.setSurname(surnameManager);
		manager.setPassword(passwordManager);
		manager.setStatus(statusManager);
		
		ArrayList<Employee> projectEmployee= new ArrayList<>();
		projectEmployee.add(employee);
		project.setName("WLB13");
		project.setEmployee(manager);
		project.setEmployees(projectEmployee);
		
		ArrayList<Project> projectList= new ArrayList<>();
		projectList.add(project);
		manager.setProjects1(projectList);
		employee.setProjects2(projectList);
		
		request.getSession().setAttribute("user", manager);
		request.setParameter("employeeEmail", email);
		when(employeeDAO.retrieveByEmail(email)).thenReturn(employee);
		//servlet.setEmployeeDAO(employeeDAO);
		
		servlet.setEmployeeDAO(employeeDAO);;
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(attribute, "success");
		
	}
	
	@Test
	void testFlag0() throws ServletException, IOException {

		employee.setEmail(email);
		employee.setName(name);
		employee.setSurname(surname);
		employee.setPassword(password);
		employee.setStatus(status);
		
		manager.setEmail(emailManager);
		manager.setName(nameManager);
		manager.setSurname(surnameManager);
		manager.setPassword(passwordManager);
		manager.setStatus(statusManager);
		
		ArrayList<Employee> projectEmployee= new ArrayList<>();
		projectEmployee.add(employee);
		project.setName("WLB13");
		project.setEmployee(manager);
		
		ArrayList<Employee> projectEmployee2= new ArrayList<>();
		projectEmployee.add(employee);
		project2.setName("WLB13");
		project2.setEmployees(projectEmployee);
		
		ArrayList<Project> projectList= new ArrayList<>();
		projectList.add(project);
		ArrayList<Project> projectList2= new ArrayList<>();
		projectList.add(project2);
		manager.setProjects1(projectList);
		employee.setProjects2(projectList2);
		
		request.getSession().setAttribute("user", manager);
		request.setParameter("employeeEmail", email);
		when(employeeDAO.retrieveByEmail(email)).thenReturn(employee);
		//servlet.setEmployeeDAO(employeeDAO);
		
		servlet.setEmployeeDAO(employeeDAO);;
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(attribute, "error");
		
		
	}
	
	@Test
	void getProjectManagerFail() throws ServletException, IOException {

		employee.setEmail(email);
		employee.setName(name);
		employee.setSurname(surname);
		employee.setPassword(password);
		employee.setStatus(status);
		
		manager.setEmail(emailManager);
		manager.setName(nameManager);
		manager.setSurname(surnameManager);
		manager.setPassword(passwordManager);
		manager.setStatus(statusManager);
		
		ArrayList<Employee> projectEmployee= new ArrayList<>();
		projectEmployee.add(employee);
		project.setName("WLB13");
		project.setEmployee(manager);
		
		ArrayList<Employee> projectEmployee2= new ArrayList<>();
		projectEmployee.add(employee);
		project2.setName("WLB13");
		project2.setEmployees(projectEmployee);
		
		ArrayList<Project> projectList= new ArrayList<>();
		projectList.add(project);
		ArrayList<Project> projectList2= new ArrayList<>();
		projectList.add(project2);
		//manager.setProjects1(projectList);
		employee.setProjects2(projectList2);
		
		request.getSession().setAttribute("user", manager);
		request.setParameter("employeeEmail", email);
		when(employeeDAO.retrieveByEmail(email)).thenReturn(employee);
		
		servlet.setEmployeeDAO(employeeDAO);;
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(attribute, "error");
		
		
	}
	
	@Test
	void sessionEmployeeNull() throws ServletException, IOException {

		
		request.getSession().setAttribute("user", manager);
		servlet.setEmployeeDAO(employeeDAO);;
		when(employeeDAO.retrieveByEmail(email)).thenReturn(null);
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(attribute, "success");
		
		
	}

	
	@Test
	void emailEmployeeNull() throws ServletException, IOException {

		
		request.getSession().setAttribute("user", null);
		servlet.setEmployeeDAO(employeeDAO);;
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(attribute, "error");
		
		
	}
	@Test
	void managerStatus0() throws ServletException, IOException {

		employee.setEmail(email);
		employee.setName(name);
		employee.setSurname(surname);
		employee.setPassword(password);
		employee.setStatus(status);
		
		manager.setEmail(emailManager);
		manager.setName(nameManager);
		manager.setSurname(surnameManager);
		manager.setPassword(passwordManager);
		manager.setStatus(0);
		
		ArrayList<Employee> projectEmployee= new ArrayList<>();
		projectEmployee.add(employee);
		project.setName("WLB13");
		project.setEmployee(manager);
		project.setEmployees(projectEmployee);
		
		ArrayList<Project> projectList= new ArrayList<>();
		projectList.add(project);
		manager.setProjects1(projectList);
		employee.setProjects2(projectList);
		
		request.getSession().setAttribute("user", manager);
		request.setParameter("employeeEmail", email);
		when(employeeDAO.retrieveByEmail(email)).thenReturn(employee);
		
		servlet.setEmployeeDAO(employeeDAO);;
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(attribute, "error");
		
	}
	
}
