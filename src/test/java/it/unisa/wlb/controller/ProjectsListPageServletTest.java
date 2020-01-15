package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.unisa.wlb.controller.ProjectsListPageServlet;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDao;

/**
 * The aim of this class is testing ProjectsListPageServletTest.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class ProjectsListPageServletTest {

    @Mock
    private IProjectDao projectDao;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;

    private ProjectsListPageServlet servlet;
    private Project project1;
    private Project project2;
    private List<Project> projectList;
    private String name1;
    private String name2;
    private int id1;
    private int id2;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new ProjectsListPageServlet();
        id1 = 1;
        name1 = "WLBG13";
        project1 = new Project();
        project1.setName(name1);
        project1.setId(id1);

        id2 = 2;
        name2 = "WLBG14";
        project2 = new Project();
        project2.setName(name2);
        project2.setId(id2);

        projectList = new ArrayList<>();
        projectList.add(project1);
        projectList.add(project2);

    }

    @Test
    void dispatcherSuccess() throws ServletException, IOException {
        String path = "WEB-INF/ProjectList.jsp";
        when(projectDao.retrieveAll()).thenReturn(projectList);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        servlet.setProjectDao(projectDao);
        servlet.doPost(request, response);
        verify(request).getRequestDispatcher(captor.capture());
        assertEquals(path, captor.getValue());

    }

    @SuppressWarnings("unchecked")
    @Test
    void dispatcherFails() throws ServletException, IOException {
        String path = ".";
        when(projectDao.retrieveAll()).thenThrow(Exception.class);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        servlet.setProjectDao(projectDao);
        servlet.doPost(request, response);
        verify(request).getRequestDispatcher(captor.capture());
        assertEquals(path, captor.getValue());

    }
}
