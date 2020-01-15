package it.unisa.wlb.controller;

import java.io.IOException;

import java.util.*;
import it.unisa.wlb.controller.AddProjectServlet;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.model.dao.IProjectDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;

/**
 * This test class follows the specification of the section "3.2.1 TC_2.1 Inserisci progetto" of the document "Test Case
 * Specification"
 * 
 * @author Aniello Romano
 *
 */
public class AddProjectServletTest extends Mockito {

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

    /**
     * TC_2.1_1: Name field inserted doesn't respect the minimum length
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_1_1() throws ServletException, IOException {
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

    /**
     * TC_2.2_2: Name field inserted doesn't respect the maximum length
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_2() throws ServletException, IOException {
        request.addParameter("name", "WLBCHG341DMAW29QWE");
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

    /**
     * TC_2.2_3: Name field doesn't respect the specified format
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_3() throws ServletException, IOException {
        request.addParameter("name", "WLBè");
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

    /**
     * TC_2.2_4: Name field already exists
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    @Test
    public void TC_2_2_4() throws ServletException, IOException {

        String commonName = "WLB13PO";
        Project pr = new Project();
        Date dateS = new Date(2019, 11, 02);
        Date dateE = new Date(2019, 12, 02);
        Employee em = new Employee();
        List<Employee> li = new ArrayList<Employee>();
        li.add(em);

        pr.setName("WLB13PO");
        pr.setScope("SmartWorking");
        pr.setStartDate(dateS);
        pr.setEndDate(dateE);
        pr.setDescription(
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        pr.setEmployees(li);
        pr.setEmployee(em);

        IProjectDao projectDao = mock(IProjectDao.class);
        IEmployeeDao employeeDao = mock(IEmployeeDao.class);
        when(projectDao.retrieveByName(commonName)).thenReturn(pr);
        AddProjectServlet tmp = new AddProjectServlet(projectDao, employeeDao);

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
            tmp.doPost(request, response);
        });

    }

    /**
     * TC_2.2_5: Scope field not inserted
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_5() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "");
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

    /**
     * TC_2.2_6: Scope field doesn't respect the specified length
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_6() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "VBFHBVHDVBDFHJBVDFVFVHFVH FHFBVHDJBFDK");
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

    /**
     * TC_2.2_7: Scope field doesn't respect the specified format
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_7() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "SmartWorking11.");
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

    /**
     * TC_2.2_8: Start date field doesn't respect the specified format
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_8() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2-29-200");
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

    /**
     * TC_2.2_9: End date field doesn't respect the specified format
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_9() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2-29-2000");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");
        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_2.2_10: Manager E-mail doesn't exist into database
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_10() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossivasdf1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");
        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_2.2_11: Email inserted doesn't correspond to any manager
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_11() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");
        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_2.2_12: Description field doesn't respect the specified length
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_12() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description", "Il progetto");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");
        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_2.2_13: Description field doesn't respect the specified length
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_13() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "L’azione di piano intende aiutare e sostenere quelle famiglie e quei genitori che non riescono a conciliare i tempi del lavoro con quelli della famiglia, "
                        + "soprattutto nella fase della chiusura estiva delle scuole. Aumentare la capacità del sistema pubblico di cure a lungo termine di supportare le famiglie.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");
        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_2.2_14: Employees list field is empty
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_14() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "0");
        request.addParameter("employee", "");
        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_2.2_15: Employee's email doens't exist into database
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_15() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchiii100@wlb.it");
        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_2.2_16: Inserted email doesn't correspond to any employee
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_2_2_16() throws ServletException, IOException {
        request.addParameter("name", "WLB13PO");
        request.addParameter("scope", "SmartWorking");
        request.addParameter("startDate", "2019-11-02");
        request.addParameter("endDate", "2019-12-02");
        request.addParameter("managerEmail", "m.rossi1@wlb.it");
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.verdi2@wlb.it");
        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_2.2_17: Project inserted with succcess
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings({ "deprecation", "unused" })
    @Test
    public void TC_2_2_17() throws ServletException, IOException {

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
        request.addParameter("description",
                "Il progetto si occuperà della realizzazione di una piattaforma che consentirà ai dipendenti di organizzare le proprie giornate lavorative.");
        request.addParameter("employeesList", "1");
        request.addParameter("employee", "m.bianchi1@wlb.it");

        temp.doPost(request, response);
        assertEquals("success", request.getAttribute("result"));
    }

}