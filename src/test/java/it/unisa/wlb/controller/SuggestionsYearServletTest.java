package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.SuggestionsYearServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;

public class SuggestionsYearServletTest {

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private IEmployeeDao employeeDao;

    private SuggestionsYearServlet servlet;
    private Employee employee;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int status;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new SuggestionsYearServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        employee = new Employee();
        name = "Marco";
        surname = "Rossi";
        email = "m.rossi1@wlb.it";
        password = "Ciao1234.";
        status = 0;

        employee.setEmail(email);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPassword(password);
        employee.setStatus(status);

    }

    @Test
    void setYearsTest() throws ServletException, IOException {
        String string = "[{\"year\":2020},{\"year\":2019}]";
        request.getSession().setAttribute("user", employee);
        when(employeeDao.retrieveByEmail(email)).thenReturn(employee);
        servlet.setEmployeeDao(employeeDao);
        servlet.doPost(request, response);
        assertEquals(string, response.getContentAsString());
    }

    @Test
    void emailNull() throws ServletException, IOException {
        employee = null;
        request.getSession().setAttribute("user", employee);
        servlet.doPost(request, response);
        assertEquals("", response.getContentAsString());
    }

}
