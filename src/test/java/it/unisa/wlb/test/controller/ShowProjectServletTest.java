package it.unisa.wlb.test.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.unisa.wlb.controller.ShowProjectServlet;
import it.unisa.wlb.model.bean.Project;

class ShowProjectServletTest {
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpServletResponse response;

	private ShowProjectServlet servlet;
	
	private String name;
	private Date endDate;
	private Date startDate;
	private String scope;
	private String description;
	
	private Project project;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		project = new Project();
		project.setName(name);
		project.setDescription(description);
		project.setScope(scope);
		project.setEndDate(endDate);
		project.setStartDate(startDate);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
