package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import it.unisa.wlb.controller.EmployeesListPageServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.utils.Utils;

/**
 * This class tests EmployeesListPageServlet.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class EmployeesListPageServletTest {

	@Mock
	private IEmployeeDAO employeeDao;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private RequestDispatcher dispatcher;
	
	private EmployeesListPageServlet servlet;
	private Employee employee1;
	private Employee employee2;
	private List<Employee> employeeList;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		employee1 = new Employee();
		employee1.setName("Marco");
		employee1.setSurname("Rossi");
		employee1.setEmail("m.rossi1@wlb.it");
		employee1.setPassword(Utils.generatePwd("Ciao1234."));
		employee1.setStatus(1);
		
		employee2 = new Employee();
		employee2.setName("Vincenzo");
		employee2.setSurname("Verdi");
		employee2.setEmail("v.verdi1@wlb.it");
		employee2.setPassword(Utils.generatePwd("Ciao123."));
		employee2.setStatus(0);
		
		employeeList = new ArrayList<>();
		employeeList.add(employee1);
		employeeList.add(employee2);
		servlet = new EmployeesListPageServlet();
		
	}

	/**
	 * Retrieve all returns a list of employees
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	void test() throws ServletException, IOException {
		String path = "WEB-INF/EmployeesList.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userRole")).thenReturn("Admin");
		when(session.getAttribute("user")).thenReturn(null);
		when(employeeDao.retrieveAll()).thenReturn(employeeList);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}

	/**
	 * Retrieve All throws an Exception
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@Test
	void test1() throws ServletException, IOException {
		String path = ".";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userRole")).thenReturn("Admin");
		when(session.getAttribute("user")).thenReturn(null);
		when(employeeDao.retrieveAll()).thenThrow(Exception.class);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		servlet.setEmployeeDao(employeeDao);
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
}
