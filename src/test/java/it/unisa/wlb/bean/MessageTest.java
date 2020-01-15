package it.unisa.wlb.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Message;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotation;

/**
 * The aim of this class is testing Message.java
 * 
 * @author Sabato Nocera
 *
 */
class MessageTest {

    private Message message;
    private Date date;
    private Employee employee;
    private int id;
    private Project project;
    private String text;

    @BeforeEach
    void setUp() throws Exception {
        message = new Message();

        employee = new Employee();
        project = new Project();
        date = new Date();
        id = 1;
        text = "Qwertyuiopasdfghjklzxcvbnm1234567890";

        /**
         * Date
         */
        message.setDate(date);

        /**
         * Text
         */
        message.setText(text);

        /**
         * Id
         */
        message.setId(id);

        /**
         * Employee
         */
        employee.setEmail("g.verdana1@wlb.it");
        List<Message> messages = new ArrayList<Message>();
        messages.add(message);
        employee.setMessages(messages);
        employee.setName("Giuseppe");
        employee.setPassword("Giuseppe1234.");
        List<Project> projects2 = new ArrayList<Project>();
        projects2.add(project);
        employee.setProjects2(projects2);
        employee.setStatus(0);
        employee.setSmartWorkingPrenotations(new ArrayList<SmartWorkingPrenotation>());
        employee.setSurname("Verdana");
        employee.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
        message.setEmployee(employee);

        /**
         * Project
         */
        Admin admin = new Admin();
        admin.setEmail("m.fiorenzo@wlbadmin.it");
        admin.setFloors(new ArrayList<Floor>());
        admin.setName("Mariano");
        admin.setPassword("Mariano1234.");
        List<Project> projects = new ArrayList<Project>();
        projects.add(project);
        admin.setProjects(projects);
        admin.setSurname("Fiorenzo");
        project.setAdmin(admin);
        project.setDescription(text + text);
        Employee manager = new Employee();
        manager.setEmail("m.verdana1@wlb.it");
        manager.setMessages(new ArrayList<Message>());
        manager.setName("Marco");
        manager.setPassword("Marcooo1234.");
        List<Project> projects1 = new ArrayList<Project>();
        projects1.add(project);
        manager.setProjects1(projects1);
        manager.setStatus(1);
        manager.setSmartWorkingPrenotations(new ArrayList<SmartWorkingPrenotation>());
        manager.setSurname("Verdana");
        manager.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
        project.setEmployee(manager);
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee);
        project.setEmployees(employees);
        Date endDate = new Date();
        endDate.setTime((new Date()).getTime() + 100);
        project.setEndDate(endDate);
        project.setId(id);
        project.setMessages(messages);
        project.setName("ProjectName");
        project.setScope("ProjectScope");
        project.setStartDate(new Date());
        message.setProject(project);
    }

    @Test
    public void getIdTest() {
        assertEquals(id, message.getId());
    }

    @Test
    public void setIdTest() {
        int newId = 3;
        message.setId(newId);
        assertEquals(newId, message.getId());
    }

    @Test
    public void getDateTest() {
        assertEquals(date, message.getDate());
    }

    @Test
    public void setDateTest() {
        Date newDate = new Date();
        newDate.setTime((new Date()).getTime() + 23);
        message.setDate(newDate);
        assertEquals(newDate, message.getDate());
    }

    @Test
    public void getTextTest() {
        assertEquals(text, message.getText());
    }

    @Test
    public void setTextTest() {
        String newText = "Abcdefg123456789hilmnopqrstuvz";
        message.setText(newText);
        assertEquals(newText, message.getText());
    }

    @Test
    public void getEmployeeTest() {
        assertEquals(employee, message.getEmployee());
    }

    @Test
    public void setEmployeeTest() {
        Employee newEmployee = new Employee();
        newEmployee.setEmail("l.longobardi@wlb.it");
        newEmployee.setMessages(employee.getMessages());
        newEmployee.setName("Luca");
        newEmployee.setPassword("Longobardi12334.");
        newEmployee.setProjects1(employee.getProjects1());
        newEmployee.setProjects2(employee.getProjects2());
        newEmployee.setSmartWorkingPrenotations(new ArrayList<SmartWorkingPrenotation>());
        newEmployee.setStatus(1);
        newEmployee.setSurname("Longobardi");
        newEmployee.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
        message.setEmployee(newEmployee);
        assertEquals(newEmployee, message.getEmployee());
    }

    @Test
    public void getProjectTest() {
        assertEquals(project, message.getProject());
    }

    @Test
    public void setProjectTest() {
        Project newProejct = new Project();
        newProejct.setAdmin(project.getAdmin());
        newProejct.setDescription(project.getDescription() + project.getDescription());
        newProejct.setEmployee(employee);
        newProejct.setEmployees(project.getEmployees());
        newProejct.setEndDate(new Date());
        newProejct.setId(project.getId() * 2);
        newProejct.setMessages(project.getMessages());
        newProejct.setName(project.getName() + project.getName());
        newProejct.setScope(project.getScope() + project.getScope());
        newProejct.setStartDate(new Date());
        message.setProject(newProejct);
        assertEquals(newProejct, message.getProject());
    }

    @Test
    public void toStringTest() {
        String string = "Message [id=" + id + ", date=" + date + ", text=" + text + ", employee=" + employee
                + ", project=" + project + "]";
        assertEquals(string, message.toString());
    }

    @Test
    public void equalsTest1() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(message.getText());
        assertEquals(message, message2);
    }

    @Test
    public void equalsTest2() {
        Message message2 = new Message();
        Date date = new Date();
        date.setTime(message.getDate().getTime() + 100);
        message2.setDate(date);
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(message.getText());
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest3() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(new Employee());
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(message.getText());
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest4() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId() + 5);
        message2.setProject(message.getProject());
        message2.setText(message.getText());
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest5() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(new Project());
        message2.setText(message.getText());
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest6() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(null);
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest7() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(message.getText() + message.getText());
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest8() {
        Message message2 = new Message();
        message2.setDate(null);
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(message.getText());
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest9() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(null);
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(message.getText());
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest10() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(null);
        message2.setText(message.getText());
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest11() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(message.getText());
        message.setDate(null);
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest12() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(message.getText());
        message.setEmployee(null);
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest13() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(message.getText());
        message.setProject(null);
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest14() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(message.getText());
        message = null;
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest15() {
        Message message2 = null;
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest16() {
        String message2 = "";
        assertNotEquals(message, message2);
    }

    @Test
    public void equalsTest17() {
        Message message2 = message;
        assertEquals(message, message2);
    }

    @Test
    public void equalsTest18() {
        Message message2 = new Message();
        message2.setDate(message.getDate());
        message2.setEmployee(message.getEmployee());
        message2.setId(message.getId());
        message2.setProject(message.getProject());
        message2.setText(message.getText());
        message.setText(null);
        assertNotEquals(message, message2);
    }
}
