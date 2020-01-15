package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.unisa.wlb.controller.ProjectInsertPageServlet;

/**
 * The aim of this class is testing ProjectInsertPageServlet.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class ProjectInsertPageServletTest {

    private ProjectInsertPageServlet servlet;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new ProjectInsertPageServlet();
    }

    @Test
    void dispatchOk() throws ServletException, IOException {
        String path = "WEB-INF/ProjectInsertion.jsp";
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        servlet.doPost(request, response);
        verify(request).getRequestDispatcher(captor.capture());
        assertEquals(path, captor.getValue());
    }

}
