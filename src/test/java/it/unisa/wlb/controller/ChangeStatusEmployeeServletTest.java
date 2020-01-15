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
 * This test class follows the specification of the section "3.1.3 TC_1.3 Modifica status dipendente" of the document
 * "Test Case Specification"
 * 
 * @author Simranjith Singh
 *
 */
public class ChangeStatusEmployeeServletTest extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private ChangeStatusEmployeeServlet servlet;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        servlet = new ChangeStatusEmployeeServlet();
    }

    /**
     * TC_1.3_1: Status parameter is not correct
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_1_3_1() throws ServletException, IOException {
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
        request.addParameter("status", "");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_1.3_2: Case of success
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_1_3_2() throws ServletException, IOException {
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
        request.addParameter("status", "Manager");

        servlet.doPost(request, response);
        assertEquals("success", request.getAttribute("statusResult"));
    }
}
