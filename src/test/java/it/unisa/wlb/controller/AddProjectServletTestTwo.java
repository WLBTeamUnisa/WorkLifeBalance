package it.unisa.wlb.controller;

import java.io.IOException;

import java.util.*;
import it.unisa.wlb.controller.AddProjectServlet;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.model.dao.IProjectDao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;

/**
 * The aim of this class is testing AddProjectServlet.java
 * 
 * @author Sabato Nocera
 *
 */
public class AddProjectServletTestTwo extends Mockito {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AddProjectServlet servlet;

    @BeforeEach
    public void setUp() {
        servlet = new AddProjectServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.getSession().setAttribute("userRole", "Admin");
        request.getSession().setAttribute("user", new Admin());
    }

    @Test
    public void roleNotOk() throws ServletException, IOException {
        request.getSession().setAttribute("userRole", "notAdmin");
        request.addParameter("name", "");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("manager", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");
        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void nullName() throws ServletException, IOException {
        String tmp = null;

        request.addParameter("name", tmp);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void nullScope() throws ServletException, IOException {
        String tmp = null;

        request.addParameter("name", "MyName");
        request.addParameter("scope", tmp);
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void nullStartDate() throws ServletException, IOException {
        String tmp = null;

        request.addParameter("name", "MyName");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", tmp);
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void nullEndDate() throws ServletException, IOException {
        String tmp = null;

        request.addParameter("name", "MyName");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", tmp);
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void nullEmployeesList() throws ServletException, IOException {
        String tmp = null;

        request.addParameter("name", "MyName");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", tmp);
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void nullEmployee() throws ServletException, IOException {
        String tmp = null;

        request.addParameter("name", "MyName");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", tmp);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void voidName() throws ServletException, IOException {
        String tmp = "";

        request.addParameter("name", tmp);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void voidScope() throws ServletException, IOException {
        String tmp = "";

        request.addParameter("name", "MyName");
        request.addParameter("scope", tmp);
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void voidStartDate() throws ServletException, IOException {
        String tmp = "";

        request.addParameter("name", "MyName");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", tmp);
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void voidEndDate() throws ServletException, IOException {
        String tmp = "";

        request.addParameter("name", "MyName");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", tmp);
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void voidEmployeesList() throws ServletException, IOException {
        String tmp = "";

        request.addParameter("name", "MyName");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", tmp);
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void voidEmployee() throws ServletException, IOException {
        String tmp = null;

        request.addParameter("name", "MyName");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", tmp);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void voidDescription() throws ServletException, IOException {
        String tmp = "";

        request.addParameter("name", "MyName");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description", tmp);
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void nameTooShort() throws ServletException, IOException {
        request.addParameter("name", "M");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @Test
    public void scopeTooShort() throws ServletException, IOException {
        request.addParameter("name", "MyName");
        request.addParameter("scope", "S");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void managerNull() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(any(String.class))).thenReturn(null);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void voidManagerEmail() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(any(String.class))).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        String tmp = "";
        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", tmp);
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void wrongManagerEmail() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(any(String.class))).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@w");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void wrongStatusManager() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(0);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(any(String.class))).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void nullEmailManager() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = null;
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(any(String.class))).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        String tmp = null;
        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", tmp);
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "unused", "deprecation" })
    @Test
    public void endDateAfterStartDate() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(managerEmail)).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("endDate", "2019-11-02");
        request.addParameter("startDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void dateNotOk1() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(managerEmail)).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void dateNotOk2() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(managerEmail)).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019");
        request.addParameter("endDate", "2019");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void dateNotOk3() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(managerEmail)).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019");
        request.addParameter("endDate", "2019-11-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void fieldNotOk1() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(managerEmail)).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", "");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void fieldNotOk2() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(managerEmail)).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void fieldNotOk3() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(managerEmail)).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void fieldNotOk4() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(managerEmail)).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void fieldNotOk5() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(managerEmail)).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void fieldNotOk6() throws ServletException, IOException {

        String commonName = "WLB13PO";

        Project project = new Project();
        String managerEmail = "m.rossi1@wlb.it";
        Employee manager = new Employee();
        manager.setEmail(managerEmail);
        manager.setStatus(1);
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);

        Employee employee = new Employee();
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);

        IProjectDao projectDao = mock(IProjectDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(project);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(employeeDao.retrieveByEmail(managerEmail)).thenReturn(manager);
        AddProjectServlet temp = new AddProjectServlet(projectDao, employeeDao);

        request.addParameter("name", commonName);
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description", "");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.doPost(request, response);
        });
    }

}