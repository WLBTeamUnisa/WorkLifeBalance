package it.unisa.wlb.test;

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
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IProjectDAO;
/**
 * This test class follows the specification of the section "3.2.4 TC_2.4 Inserisci dipendente ad un progetto" of the document "Test Case Specification"
 * 
 * @author nello
 *
 */
class AddEmployeesToProjectServletTest extends Mockito {

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
		request.setAttribute("manager", new Employee());
		request.setAttribute("status", "creating");
	}

	
	/**
	 * EmployeeList is empty
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	void TC_2_4_1() throws ServletException, IOException {
		
		Project project = new Project();
		
		project.setName("WorkLifeBalance");
		project.setScope("SmartWorking");
		project.setStartDate(new Date(2019-11-02));
		project.setEndDate(new Date(2019-12-02));
		IProjectDAO projectDao = mock(IProjectDAO.class);
		
		when(projectDao.retrieveByName("WorkLifeBalance")).thenReturn(project);
		
		request.setAttribute("Project", project);
		
		List<Employee> employeeList = null;
		
		request.setAttribute("lista_dipendenti", employeeList);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}
	
	/**
	 * Employee doesn't exists into the database
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	void TC_2_4_2() throws ServletException, IOException {
		
		Project project = new Project();
		
		project.setName("WorkLifeBalance");
		project.setScope("SmartWorking");
		project.setStartDate(new Date(2019-11-02));
		project.setEndDate(new Date(2019-12-02));
		IProjectDAO projectDao = mock(IProjectDAO.class);
		
		when(projectDao.retrieveByName("WorkLifeBalance")).thenReturn(project);
		
		request.setAttribute("Project", project);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		
		request.setAttribute("lista_dipendenti", employeeList);
		request.setAttribute("employee","m.rossi4@wlb.it");
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}
	
	/**
	 * Employee doesn't exists into the database
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	void TC_2_4_3() throws ServletException, IOException {
		
		Project project = new Project();
		
		project.setName("WorkLifeBalance");
		project.setScope("SmartWorking");
		project.setStartDate(new Date(2019-11-02));
		project.setEndDate(new Date(2019-12-02));
		IProjectDAO projectDao = mock(IProjectDAO.class);
		
		Employee employee = new Employee();
		employee.setEmail("l.rossi1@wlb.it");
		employee.setName("Luca");
		employee.setSurname("Rossi");
		employee.setPassword("Ciao123.");
		IEmployeeDAO employeeDao = mock(IEmployeeDAO.class);
		
		when(projectDao.retrieveByName("WorkLifeBalance")).thenReturn(project);
		when(employeeDao.retrieveByEmail("l.rossi1@wlb.it")).thenReturn(employee);
		
		request.setAttribute("Project", project);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(employee);
		
		request.setAttribute("lista_dipendenti", employeeList);
		request.setAttribute("employee","m.bianchi4@wlb.it");
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}
	
	/**
	 * Employee doesn't exists into the database
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	
	@Test
	void TC_2_4_4() throws ServletException, IOException {
	
		Project project = new Project();
		
		project.setName("WorkLifeBalance");
		project.setScope("SmartWorking");
		project.setStartDate(new Date(2019-11-02));
		project.setEndDate(new Date(2019-12-02));
		
		Employee employee = new Employee();
		employee.setEmail("l.rossi1@wlb.it");
		employee.setName("Luca");
		employee.setSurname("Rossi");
		employee.setPassword("Ciao123.");
		
		IEmployeeDAO employeeDao = mock(IEmployeeDAO.class);
		IProjectDAO projectDao = mock(IProjectDAO.class);
		when(projectDao.retrieveByName("WorkLifeBalance")).thenReturn(project);	
		when(employeeDao.retrieveByEmail("l.rossi1@wlb.it")).thenReturn(employee);
		
		request.setAttribute("Project", project);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(employee);
		employeeList.add(employee);
		
		request.setAttribute("lista_dipendenti", employeeList);
		request.setAttribute("employee", "l.rossi1@wlb.it");
		
		servlet.doPost(request, response);
		assertEquals("success", request.getAttribute("result"));
	}

}
