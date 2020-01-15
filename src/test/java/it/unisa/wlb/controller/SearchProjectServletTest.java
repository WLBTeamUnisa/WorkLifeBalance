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
import static org.mockito.Mockito.when;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.SearchProjectServlet;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDao;
/**
 * This test class follows the specification of the section "TC_2.2 Ricerca progetto" Test Case Specification"
 * 
 * @author Vincenzo Fabiano
 *
 */
class SearchProjectServletTest {
	
	@Mock
	private IProjectDao projectDao;
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private SearchProjectServlet servlet;
	private Project project;
	private String name;
	private List<Project> projectList;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		
	}

	/**
	 * TC_2.2_1: name doesn't respect the length - FAIL
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	void TC_2_2_1() throws ServletException, IOException{
		String errorMessage = "Il parametro non rispetta la lunghezza";
		request.setParameter("name", "Progettoooooooo1");
		servlet = new SearchProjectServlet();
		try {
			servlet.doPost(request, response);
		} catch (Exception e) {
			;
		} finally { 
			assertEquals(errorMessage, response.getContentAsString());
		}
	}
	
	/**
	 * TC_2.2_2: Retrieve project based on suggestions - SUCCESS
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	void TC_2_2_2() throws ServletException, IOException{
		name = "Progetto1";
		String json = "[{\"name\":\"Progetto1\"}]";
		request.setParameter("name", name);
		project = new Project();
		project.setName(name);
		projectList = new ArrayList<>();
		projectList.add(project);
		when(projectDao.searchByName(name)).thenReturn(projectList);
		
		servlet = new SearchProjectServlet(projectDao);
		servlet.doPost(request, response);
		assertEquals(json, response.getContentAsString().toString());
		
	}
		
	@Test
	void voidName() throws ServletException, IOException {
		name = "";
		request.setParameter("name", name);
		project = new Project();
		project.setName(name);
		projectList = new ArrayList<>();
		projectList.add(project);
		when(projectDao.searchByName(name)).thenReturn(projectList);
		
		servlet = new SearchProjectServlet(projectDao);
		try {
			servlet.doPost(request, response);
		} catch(Exception exception) {
			;
		} finally {
			assertEquals("[]", response.getContentAsString().toString());
		}
			
	}

}
