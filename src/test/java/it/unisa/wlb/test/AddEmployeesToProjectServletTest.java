package it.unisa.wlb.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
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

	@Test
	void test() throws ServletException, IOException {
		Project project = new Project();
		project.setName("WorkLifeBalance");
		request.setAttribute("Project", project);
		List<Employee> employeeList = null;
		request.setAttribute("lista_dipendenti", employeeList);
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

}
