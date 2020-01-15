package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.ChangeStatusEmployeeServlet;

import it.unisa.wlb.model.bean.Employee;

import it.unisa.wlb.model.dao.IEmployeeDao;

/**
 * The aim of this class is testing ChangeStatusEmployeeServlet.java
 * 
 * @author Sabato Nocera
 *
 */
public class ChangeStatusEmployeeServletTestTwo extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private ChangeStatusEmployeeServlet servlet;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new ChangeStatusEmployeeServlet();
    }

    @Test
    public void employeeChanging() throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setName("Marco");
        employee.setSurname("Rossi");
        employee.setEmail("m.rossi1@wlb.it");
        employee.setPassword("MarcoRossi1.");
        employee.setStatus(0);

        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.create(employee)).thenReturn(employee);
        when(employeeDao.update(employee)).thenReturn(employee);
        when(employeeDao.retrieveByEmail(employee.getEmail())).thenReturn(employee);

        servlet.setEmployeeDao(employeeDao);

        request.addParameter("email", "m.rossi1@wlb.it");
        request.addParameter("status", "Employee");

        servlet.doPost(request, response);
        assertEquals("success", request.getAttribute("statusResult"));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void exceptionThrown() throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setName("Marco");
        employee.setSurname("Rossi");
        employee.setEmail("m.rossi1@wlb.it");
        employee.setPassword("MarcoRossi1.");
        employee.setStatus(0);

        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.create(employee)).thenReturn(employee);
        when(employeeDao.update(employee)).thenReturn(employee);
        when(employeeDao.retrieveByEmail(employee.getEmail())).thenThrow(Exception.class);

        servlet.setEmployeeDao(employeeDao);

        request.addParameter("email", "m.rossi1@wlb.it");
        request.addParameter("status", "Manager");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }
}
