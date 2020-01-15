package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.LoginServlet;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IAdminDao;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.utils.Utils;

/**
 * The aims of this class it testing admin access
 * 
 * @author Vincenzo Fabiano, Sabato Nocera
 *
 */
class LoginServletTestTwo {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private LoginServlet servlet;

    private Admin admin;
    private String name;
    private String email;
    private String surname;
    private String password;
    private String generatedPassword;

    @BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        servlet = new LoginServlet();
        name = "Marco";
        surname = "Rossi";
        password = "MarcoRossi1.";
        generatedPassword = Utils.generatePwd(password);
        email = "m.rossi1@wlbadmin.it";
        admin = new Admin();
        admin.setEmail(email);
        admin.setName(name);
        admin.setSurname(surname);
        admin.setPassword(generatedPassword);
    }

    /**
     * Admin logs in correctly
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    void adminLogin() throws ServletException, IOException {
        String message = "success";

        IAdminDao adminDao = mock(IAdminDao.class);
        Mockito.when(adminDao.retrieveByEmailPassword(admin.getEmail(), admin.getPassword())).thenReturn(admin);

        servlet = new LoginServlet();
        servlet.setAdminDao(adminDao);

        request.setParameter("email", email);
        request.setParameter("password", password);

        servlet.doPost(request, response);

        assertEquals(message, (String) request.getAttribute("login"));
    }

    @Test
    public void passwordNotOk1() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.rossi1@wlb.it");
        request.setParameter("password", "");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void passwordNotOk2() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.rossi1@wlb.it");
        request.setParameter("password", "");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void passwordNotOk3() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.rossi1@wlb.it");
        request.setParameter("password", "marcorossi1*_à§");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void passwordNotOk4() throws ServletException, IOException {
        String tmp = null;
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.rossi1@wlb.it");
        request.setParameter("password", tmp);
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void passwordNotOk5() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.rossi1@wlb.it");
        request.setParameter("password", "A");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void passwordNotOk6() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.rossi1@wlb.it");
        request.setParameter("password",
                "MarcoRomanoMarcoRomanoMarcoRomanoMarcoRomanoMarcoRomanoMarcoRomanoMarcoRomano.");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void emailNotOk1() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.r1@wlbadmin.it");
        request.setParameter("password", "MarcoRossi1.");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void emailNotOk8() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.r1@wlb.it");
        request.setParameter("password", "MarcoRossi1.");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void emailNotOk2() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.rossirossirossirossirossirossirossirossirossirossirossirossi1@wlbadmin.it");
        request.setParameter("password", "MarcoRossi1.");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void emailNotOk7() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.rossirossirossirossirossirossirossirossirossirossirossirossi1@wlb.it");
        request.setParameter("password", "MarcoRossi1.");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void emailNotOk3() throws ServletException, IOException {
        String tmp = null;
        final String message = "Email e/o password non validi";
        request.setParameter("email", tmp);
        request.setParameter("password", "MarcoRossi1.");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void emailNotOk4() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.*@wlbadmin.it");
        request.setParameter("password", "MarcoRossi1.");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void emailNotOk6() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "m.*@wlb.it");
        request.setParameter("password", "MarcoRossi1.");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void emailNotOk5() throws ServletException, IOException {
        final String message = "Email e/o password non validi";
        request.setParameter("email", "");
        request.setParameter("password", "MarcoRossi1.");
        servlet.doPost(request, response);
        String attribute = (String) request.getAttribute("result");
        assertEquals(message, attribute);
    }

    @Test
    public void nullEmployee() throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setEmail("m.rossi1@wlb.it");
        employee.setPassword(Utils.generatePwd("MarcoRossi1."));
        employee.setName("Marco");
        employee.setSurname("Rossi");
        employee.setStatus(0);

        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        Mockito.when(employeeDao.retrieveByEmailPassword(Mockito.any(String.class), Mockito.any(String.class)))
                .thenReturn(null);

        servlet.setEmployeeDao(employeeDao);

        request.setParameter("email", "m.rossi1@wlb.it");
        request.setParameter("password", "MarcoRossi1.");

        servlet.doPost(request, response);

        assertEquals(null, (String) request.getAttribute("login"));
    }

    @Test
    void nullAdmin() throws ServletException, IOException {
        IAdminDao adminDao = mock(IAdminDao.class);
        Mockito.when(adminDao.retrieveByEmailPassword(Mockito.any(String.class), Mockito.any(String.class)))
                .thenReturn(null);

        servlet = new LoginServlet();
        servlet.setAdminDao(adminDao);

        request.setParameter("email", email);
        request.setParameter("password", password);

        servlet.doPost(request, response);

        assertEquals(null, (String) request.getAttribute("login"));
    }

    @Test
    void userNotNull() throws ServletException, IOException {
        request.getSession().setAttribute("user", "notNull");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }
}
