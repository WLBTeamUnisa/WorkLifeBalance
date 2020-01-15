package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.mockito.Mockito.when;
import it.unisa.wlb.controller.AddEmployeeToListServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;

/**
 * The aim of this class is testing AddEmployeeToListTest.java
 * 
 * @author Vincenzo Fabiano, Sabato Nocera
 *
 */
class AddEmployeeToListTest {

	@Mock
	private IEmployeeDao employeeDao;
	
	private AddEmployeeToListServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private String email;
	private Employee employee1;
	private Employee employee2;
	private List<Employee> employeeList;
	
	@BeforeEach
	void setUp() throws Exception {
		servlet = new AddEmployeeToListServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		email = "m.rossi1@wlb.it";
		
	}

	@Test
	void listNotEmpty() throws ServletException, IOException {
		MockitoAnnotations.initMocks(this);
		request.setParameter("email", email);
		String json = "{\"emailEmployee\":\"m.rossi1@wlb.it\"}";
		employee1 = new Employee();
		employee1.setEmail(email);
		employee2 = new Employee();
		String email2 = "v.verdi1@wlb.it";
		employee2.setEmail(email2);
		employeeList = new ArrayList<>();
		employeeList.add(employee2);
		request.getSession().setAttribute("employeeList", employeeList);
		when(employeeDao.retrieveByEmail(email)).thenReturn(employee1);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertEquals(json,response.getContentAsString());
		
	}

	@Test
	void listEmpty() throws ServletException, IOException {
		MockitoAnnotations.initMocks(this);
		request.setParameter("email", email);
		employee1 = new Employee();
		employee1.setEmail(email);
		request.getSession().setAttribute("employeeList", null);
		String json = "{\"emailEmployee\":\"m.rossi1@wlb.it\"}";
		when(employeeDao.retrieveByEmail(email)).thenReturn(employee1);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertEquals(json,response.getContentAsString());	
	}
	
	@Test
	void emailEmployeeNull() throws ServletException, IOException {	
		MockitoAnnotations.initMocks(this);	
		email = null;
		request.setParameter("email", email);
		
		servlet.doPost(request, response);
		
		if(request.getAttribute("employeeList")==null)
			assertTrue(true);
		else
			assertTrue(false);
	}
	
	@Test
	void emailManagerNotNullEqualsManager() throws ServletException, IOException {
		MockitoAnnotations.initMocks(this);
		request.setParameter("email", email);
		request.setParameter("emailManager", email);
		employee1 = new Employee();
		employee1.setEmail(email);
		request.getSession().setAttribute("employeeList", null);
		String json = "{\"emailEmployee\":\"m.rossi1@wlb.it\"}";
		when(employeeDao.retrieveByEmail(email)).thenReturn(employee1);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertNotEquals(json,response.getContentAsString());	
	}
	
	@Test
	void emailManagerNotNull() throws ServletException, IOException {
		MockitoAnnotations.initMocks(this);
		request.setParameter("email", email);
		request.setParameter("emailManager", "g.verdana15@wlb.it");
		employee1 = new Employee();
		employee1.setEmail(email);
		request.getSession().setAttribute("employeeList", null);
		String json = "{\"emailEmployee\":\"m.rossi1@wlb.it\"}";
		when(employeeDao.retrieveByEmail(email)).thenReturn(employee1);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertEquals(json,response.getContentAsString());	
	}
	
	@Test
	void listNotEmptyManagerNotNull() throws ServletException, IOException {
		MockitoAnnotations.initMocks(this);
		request.setParameter("email", email);
		request.setParameter("emailManager", "g.verdana15@wlb.it");
		String json = "{\"emailEmployee\":\"m.rossi1@wlb.it\"}";
		employee1 = new Employee();
		employee1.setEmail(email);
		employee2 = new Employee();
		String email2 = email;
		employee2.setEmail(email2);
		employeeList = new ArrayList<>();
		employeeList.add(employee2);
		request.getSession().setAttribute("employeeList", employeeList);
		when(employeeDao.retrieveByEmail(email)).thenReturn(employee1);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertNotEquals(json,response.getContentAsString());		
	}
	
	@Test
	void listNotEmptyManagerNotNullSizeZero() throws ServletException, IOException {
		MockitoAnnotations.initMocks(this);
		request.setParameter("email", email);
		request.setParameter("emailManager", "g.verdana15@wlb.it");
		String json = "{\"emailEmployee\":\"m.rossi1@wlb.it\"}";
		employee1 = new Employee();
		employee1.setEmail(email);
		employee2 = new Employee();
		String email2 = email;
		employee2.setEmail(email2);
		employeeList = new ArrayList<>();
		employeeList.add(employee2);
		request.getSession().setAttribute("employeeList", new ArrayList<Employee>());
		when(employeeDao.retrieveByEmail(email)).thenReturn(employee1);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertEquals(json,response.getContentAsString());		
	}
	
	@Test
	void emailManagerNotNullEmployeeNull() throws ServletException, IOException {
		MockitoAnnotations.initMocks(this);
		request.setParameter("email", email);
		request.setParameter("emailManager", "g.verdana15@wlb.it");
		employee1 = new Employee();
		employee1.setEmail(email);
		request.getSession().setAttribute("employeeList", null);
		when(employeeDao.retrieveByEmail(email)).thenReturn(null);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertTrue(response.getContentAsString().toString().contains("The employee is not valid"));	
	}
	
	@Test
	void emailManagerNotNullEmployeeNotNullEquals() throws ServletException, IOException {
		MockitoAnnotations.initMocks(this);
		request.setParameter("email", email);
		request.setParameter("emailManager", "g.verdana15@wlb.it");
		employee1 = new Employee();
		employee1.setEmail(email);
		request.getSession().setAttribute("employeeList", null);
		List<Employee> list = new ArrayList<Employee>();
		list.add(employee1);
		request.getSession().setAttribute("currentEmployees", list);
		String json = "{\"emailEmployee\":\"m.rossi1@wlb.it\"}";
		when(employeeDao.retrieveByEmail(email)).thenReturn(employee1);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertNotEquals(json,response.getContentAsString());	
	}
	
	@Test
	void emailManagerNotNullEmployeeNotNull() throws ServletException, IOException {
		MockitoAnnotations.initMocks(this);
		request.setParameter("email", email);
		request.setParameter("emailManager", "g.verdana15@wlb.it");
		employee1 = new Employee();
		employee1.setEmail(email);
		request.getSession().setAttribute("employeeList", null);
		List<Employee> list = new ArrayList<Employee>();
		Employee employee2 = new Employee();
		employee2.setEmail("g.verdana188@wlb.it");
		list.add(employee2);
		request.getSession().setAttribute("currentEmployees", list);
		String json = "{\"emailEmployee\":\"m.rossi1@wlb.it\"}";
		when(employeeDao.retrieveByEmail(email)).thenReturn(employee1);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		assertEquals(json,response.getContentAsString());	
	}
	
}
