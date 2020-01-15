package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.AddEmployeesToProjectServlet;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.model.dao.IProjectDao;
/**
 * The aim of this class is testing AddEmployeesToProjectServlet.java
 *  
 * @author Sabato Nocera
 *
 */
class AddEmployeesToProjectServletTestTwo extends Mockito {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private AddEmployeesToProjectServlet servlet;


	@BeforeEach
	void setUp() throws Exception {		
		servlet = new AddEmployeesToProjectServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		
		request.getSession().setAttribute("userRole", "Admin");
		request.getSession().setAttribute("user", new Admin());
		request.setAttribute("status", "creating");
	}
	
	@Test
	void notAdmin() throws ServletException, IOException {		
		request.getSession().removeAttribute("userRole");
		request.getSession().setAttribute("userRole", "notAdmin");
		
		servlet.doPost(request, response);
		
		if(request.getAttribute("result")==null)
			assertTrue(true);
		else
			assertTrue(false);
	}
	
	@Test
	void projectNull() throws ServletException, IOException {	
		Project project = null;
		request.setAttribute("Project", project);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}
	
	@Test
	void employeesListNull() throws ServletException, IOException {
	
		Project project = new Project();
		
		project.setName("WorkLifeBalance");
		project.setScope("SmartWorking");
		project.setStartDate(new Date(2019-11-02));
		project.setEndDate(new Date(2019-12-02));
		
		Employee manager = new Employee();
		manager.setEmail("l.rossi1@wlb.it");
		manager.setName("Luca");
		manager.setSurname("Rossi");
		manager.setPassword("Ciao123.");
		manager.setStatus(1);
		ArrayList<Project> list = new ArrayList<Project>();
		List<Project> projectList = list;
		manager.setProjects1(projectList);
		
		request.setAttribute("manager", manager);
		
		Employee employee = new Employee();
		employee.setEmail("l.bianchi1@wlb.it");
		employee.setName("Luca");
		employee.setSurname("Bianchi");
		employee.setPassword("Ciao123.");
		employee.setStatus(0);
		
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		IProjectDao projectDao = mock(IProjectDao.class);
		
		when(projectDao.create(project)).thenReturn(project);
		when(employeeDao.update(manager)).thenReturn(manager);
		
		request.setAttribute("Project", project);
		
		servlet.setEmployeeDao(employeeDao);
		servlet.setProjectDao(projectDao);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(employee);
		
		request.getSession().setAttribute("employeeList", null);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}
	
	@Test
	void employeesListZeroSize() throws ServletException, IOException {
	
		Project project = new Project();
		
		project.setName("WorkLifeBalance");
		project.setScope("SmartWorking");
		project.setStartDate(new Date(2019-11-02));
		project.setEndDate(new Date(2019-12-02));
		
		Employee manager = new Employee();
		manager.setEmail("l.rossi1@wlb.it");
		manager.setName("Luca");
		manager.setSurname("Rossi");
		manager.setPassword("Ciao123.");
		manager.setStatus(1);
		ArrayList<Project> list = new ArrayList<Project>();
		List<Project> projectList = list;
		manager.setProjects1(projectList);
		
		request.setAttribute("manager", manager);
		
		Employee employee = new Employee();
		employee.setEmail("l.bianchi1@wlb.it");
		employee.setName("Luca");
		employee.setSurname("Bianchi");
		employee.setPassword("Ciao123.");
		employee.setStatus(0);
		
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		IProjectDao projectDao = mock(IProjectDao.class);
		
		when(projectDao.create(project)).thenReturn(project);
		when(employeeDao.update(manager)).thenReturn(manager);
		
		request.setAttribute("Project", project);
		
		servlet.setEmployeeDao(employeeDao);
		servlet.setProjectDao(projectDao);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		
		request.getSession().setAttribute("employeeList", employeeList);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}
	
	@Test
	void statusModifying() throws ServletException, IOException {
		request.removeAttribute("status");
		request.setAttribute("status", "modifying");
	
		Project project = new Project();
		
		project.setName("WorkLifeBalance");
		project.setScope("SmartWorking");
		project.setStartDate(new Date(2019-11-02));
		project.setEndDate(new Date(2019-12-02));
		project.setEmployee(new Employee());
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee());
		project.setEmployees(employeeList);
		
		Employee manager = new Employee();
		manager.setEmail("l.rossi1@wlb.it");
		manager.setName("Luca");
		manager.setSurname("Rossi");
		manager.setPassword("Ciao123.");
		manager.setStatus(1);
		ArrayList<Project> list = new ArrayList<Project>();
		List<Project> projectList = list;
		manager.setProjects1(projectList);
		
		request.setAttribute("manager", manager);
		
		Employee employee = new Employee();
		employee.setEmail("l.bianchi1@wlb.it");
		employee.setName("Luca");
		employee.setSurname("Bianchi");
		employee.setPassword("Ciao123.");
		employee.setStatus(0);
		
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		IProjectDao projectDao = mock(IProjectDao.class);
		
		when(projectDao.create(project)).thenReturn(project);
		when(employeeDao.update(manager)).thenReturn(manager);
		
		request.setAttribute("Project", project);
		
		servlet.setEmployeeDao(employeeDao);
		servlet.setProjectDao(projectDao);
		
		List<Employee> employeeListTwo = new ArrayList<Employee>();
		employeeListTwo.add(employee);
		
		request.getSession().setAttribute("employeeList", employeeListTwo);
		
		servlet.doPost(request, response);
		assertEquals("success", request.getAttribute("result"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void projectDaoThrowsExceptionUpdating() throws ServletException, IOException {
		request.removeAttribute("status");
		request.setAttribute("status", "modifying");
	
		Project project = new Project();
		
		project.setName("WorkLifeBalance");
		project.setScope("SmartWorking");
		project.setStartDate(new Date(2019-11-02));
		project.setEndDate(new Date(2019-12-02));
		project.setEmployee(new Employee());
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee());
		project.setEmployees(employeeList);
		
		Employee manager = new Employee();
		manager.setEmail("l.rossi1@wlb.it");
		manager.setName("Luca");
		manager.setSurname("Rossi");
		manager.setPassword("Ciao123.");
		manager.setStatus(1);
		ArrayList<Project> list = new ArrayList<Project>();
		List<Project> projectList = list;
		manager.setProjects1(projectList);
		
		request.setAttribute("manager", manager);
		
		Employee employee = new Employee();
		employee.setEmail("l.bianchi1@wlb.it");
		employee.setName("Luca");
		employee.setSurname("Bianchi");
		employee.setPassword("Ciao123.");
		employee.setStatus(0);
		
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		IProjectDao projectDao = mock(IProjectDao.class);
		
		when(projectDao.create(project)).thenReturn(project);
		when(projectDao.update(project)).thenThrow(Exception.class);
		when(employeeDao.update(manager)).thenReturn(manager);
		
		request.setAttribute("Project", project);
		
		servlet.setEmployeeDao(employeeDao);
		servlet.setProjectDao(projectDao);
		
		List<Employee> employeeListTwo = new ArrayList<Employee>();
		employeeListTwo.add(employee);
		
		request.getSession().setAttribute("employeeList", employeeListTwo);
		
		servlet.doPost(request, response);
		if(response.getContentAsString().toString().contains("Errore nell'aggiornamento del progetto"))
			assertTrue(true);
		else
			assertTrue(false);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void projectDaoThrowsExceptionCreating() throws ServletException, IOException {
	
		Project project = new Project();
		
		project.setName("WorkLifeBalance");
		project.setScope("SmartWorking");
		project.setStartDate(new Date(2019-11-02));
		project.setEndDate(new Date(2019-12-02));
		
		Employee manager = new Employee();
		manager.setEmail("l.rossi1@wlb.it");
		manager.setName("Luca");
		manager.setSurname("Rossi");
		manager.setPassword("Ciao123.");
		manager.setStatus(1);
		ArrayList<Project> list = new ArrayList<Project>();
		List<Project> projectList = list;
		manager.setProjects1(projectList);
		
		request.setAttribute("manager", manager);
		
		Employee employee = new Employee();
		employee.setEmail("l.bianchi1@wlb.it");
		employee.setName("Luca");
		employee.setSurname("Bianchi");
		employee.setPassword("Ciao123.");
		employee.setStatus(0);
		
		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		IProjectDao projectDao = mock(IProjectDao.class);
		
		when(projectDao.create(project)).thenThrow(Exception.class);
		when(employeeDao.update(manager)).thenReturn(manager);
		
		request.setAttribute("Project", project);
		
		servlet.setEmployeeDao(employeeDao);
		servlet.setProjectDao(projectDao);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(employee);
		
		request.getSession().setAttribute("employeeList", employeeList);
		
		servlet.doPost(request, response);
		if(response.getContentAsString().toString().contains("Errore nella creazione del progetto"))
			assertTrue(true);
		else
			assertTrue(false);
	}
}
