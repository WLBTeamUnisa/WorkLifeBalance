package it.unisa.wlb.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.wlb.model.bean.*;

/**
 * The aim of this class is testing Project.java
 * 
 * @author Sabato Nocera
 *
 */
class ProjectTest {
	
	private Project project;
	private Admin admin;
	private String description;
	private Employee employee;
	private List<Employee> employees;
	private Date endDate;
	private int id;
	private List<Message> messages;
	private String name;
	private String scope;
	private Date startDate;

	@BeforeEach
	void setUp() throws Exception {
		project = new Project();
		
		description="Qwertyuiopasdfghjklzxcvbnm";
		name="ProjectName";
		scope="ProjectScope";
		id=1;
		admin = new Admin();
		employee = new Employee();
		employees = new ArrayList<Employee>();
		startDate = new Date();
		endDate = new Date();
		endDate.setTime(startDate.getTime()+1500);
		messages = new ArrayList<Message>();
		
		/**
		 * End Date
		 */
		project.setEndDate(endDate);
		
		/**
		 * Id
		 */
		project.setId(id);
		
		/**
		 * Description
		 */
		project.setDescription(description);
		
		/**
		 * Name
		 */
		project.setName(name);
		
		/**
		 * Scope
		 */
		project.setScope(scope);
		
		/**
		 * Start Date
		 */
		project.setStartDate(startDate);
		
		/**
		 * Admin
		 */
		admin.setEmail("a.dmin15@wlbadmin.it");
		admin.setFloors(new ArrayList<Floor>());
		admin.setName("Admin");
		admin.setSurname("Administrator");
		List<Project> projects = new ArrayList<Project>();
		projects.add(project);
		admin.setProjects(projects);
		admin.setPassword("Admin1234.");
		project.setAdmin(admin);
		
		/**
		 * Employee
		 */
		employee.setEmail("m.anager10@wlb.it");
		employee.setMessages(new ArrayList<Message>());
		employee.setName("ManagerName");
		employee.setPassword("Manager1234.");
		employee.setProjects1(projects);
		employee.setProjects2(new ArrayList<Project>());
		employee.setSmartWorkingPrenotations(new ArrayList<SmartWorkingPrenotation>());
		employee.setStatus(1);
		employee.setSurname("ManagerSurname");
		employee.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
		project.setEmployee(employee);
		
		/**
		 * Employees
		 */
		Employee workingEmployee = new Employee();
		workingEmployee.setEmail("e.mployee10@wlb.it");
		workingEmployee.setMessages(new ArrayList<Message>());
		workingEmployee.setName("EmployeeName");
		workingEmployee.setPassword("Employee1234.");
		workingEmployee.setProjects1(new ArrayList<Project>());
		workingEmployee.setProjects2(projects);
		workingEmployee.setSmartWorkingPrenotations(new ArrayList<SmartWorkingPrenotation>());
		workingEmployee.setStatus(0);
		workingEmployee.setSurname("EmployeeSurname");
		workingEmployee.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
		employees.add(workingEmployee);
		project.setEmployees(employees);
		
		/**
		 * Messages
		 */
		Message message = new Message();
		message.setDate(new Date());
		message.setEmployee(new Employee());
		message.setId(1);
		message.setProject(project);
		message.setText("123456789abcdefgh");
		messages.add(message);
		project.setMessages(messages);
	}


	@Test
	final void testGetId() {
		assertEquals(id,project.getId());
	}

	@Test
	final void testSetId() {
		int newId = 2;
		project.setId(newId);
		assertEquals(newId,project.getId());
	}

	@Test
	final void testGetDescription() {
		assertEquals(description,project.getDescription());
	}

	@Test
	final void testSetDescription() {
		String newDescription ="Qazwsxedcrfvtgbyhnujmikolp";
		project.setDescription(newDescription);
		assertEquals(newDescription,project.getDescription());
	}

	@Test
	final void testGetEndDate() {
		assertEquals(endDate,project.getEndDate());
	}

	@Test
	final void testSetEndDate() {
		Date newDate = new Date();
		newDate.setTime(endDate.getTime()+1000);
		project.setEndDate(newDate);	
		assertEquals(newDate,project.getEndDate());			
	}

	@Test
	final void testGetName() {
		assertEquals(name,project.getName());
	}

	@Test
	final void testSetName() {
		String newName ="NewProjectName";
		project.setName(newName);
		assertEquals(newName,project.getName());
	}

	@Test
	final void testGetScope() {
		assertEquals(scope,project.getScope());
	}

	@Test
	final void testSetScope() {
		String newScope ="NewProjectScope";
		project.setScope(newScope);
		assertEquals(newScope,project.getScope());
	}

	@Test
	final void testGetStartDate() {
		assertEquals(startDate,project.getStartDate());
	}

	@Test
	final void testSetStartDate() {
		Date newDate = new Date();
		newDate.setTime(startDate.getTime()+1000);
		project.setStartDate(newDate);	
		assertEquals(newDate,project.getStartDate());
	}

	@Test
	final void testGetMessages() {
		List<Message> list = project.getMessages();
		if(list.size()!=messages.size())
			assertTrue(false);
		for(Message message:list)
			if(!messages.contains(message)) {
				assertTrue(false);
				return ;
			}
		assertTrue(true);
	}
	
	@Test
	final void testGetEmployees() {
		List<Employee> list = project.getEmployees();
		if(list.size()!=employees.size())
			assertTrue(false);
		for(Employee employee:list)
			if(!employees.contains(employee)) {
				assertTrue(false);
				return ;
			}
		assertTrue(true);
	}

	@Test
	final void testSetEmployees() {	
		List<Employee> employeesList = new ArrayList<Employee>();
		
		Employee newEmployee = new Employee();
		newEmployee.setEmail("n.ewemployee1@wlb.it");
		newEmployee.setMessages(employee.getMessages());
		newEmployee.setName("New"+employee.getEmail());
		newEmployee.setPassword(employee.getPassword());
		newEmployee.setProjects1(employee.getProjects1());
		newEmployee.setProjects2(employee.getProjects2());
		newEmployee.setSmartWorkingPrenotations(employee.getSmartWorkingPrenotations());
		newEmployee.setStatus(1);
		newEmployee.setSurname("New"+employee.getSurname());
		newEmployee.setWorkstationPrenotations(employee.getWorkstationPrenotations());
		
		employeesList.add(newEmployee);
		
		project.setEmployees(employeesList);
		
		if(!project.getEmployees().contains(newEmployee)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}

	@Test
	final void testSetMessages() {	
		List<Message> messagesList = new ArrayList<Message>();
		
		Message message = new Message();
		message.setDate(new Date());
		message.setEmployee(new Employee());
		message.setId(3);
		message.setProject(project);
		message.setText("753196842qwertyui");
		
		messagesList.add(message);
		
		project.setMessages(messagesList);
		
		if(!project.getMessages().contains(message)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}

	@Test
	final void testAddMessage() {
		Message message = new Message();
		message.setDate(new Date());
		message.setEmployee(new Employee());
		message.setId(3);
		message.setProject(project);
		message.setText("753196842qwertyui");
		
		project.addMessage(message);
		
		List<Message> list = project.getMessages();
		
		if(!list.contains(message)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}

	@Test
	final void testRemoveMessage() {
		Message message = new Message();
		message.setDate(new Date());
		message.setEmployee(new Employee());
		message.setId(3);
		message.setProject(project);
		message.setText("753196842qwertyui");
		
		project.addMessage(message);
		
		List<Message> list = project.getMessages();
		
		if(list.contains(message)) {				
			project.removeMessage(message);
			if(!list.contains(message)) {
				assertTrue(true);
				return ;
			}
		}
		assertTrue(false);
	}

	@Test
	final void testGetAdmin() {
		assertEquals(admin,project.getAdmin());
	}

	@Test
	final void testSetAdmin() {
		Admin newAdmin = new Admin();
		admin.setEmail("n.ewadmin11@wlbadmin.it");
		admin.setFloors(new ArrayList<Floor>());
		admin.setName("New"+admin.getName());
		admin.setPassword(admin.getPassword());
		admin.setProjects(admin.getProjects());
		admin.setSurname("New"+admin.getSurname());
		project.setAdmin(newAdmin);
		assertEquals(newAdmin,project.getAdmin());
	}

	@Test
	final void testGetEmployee() {
		assertEquals(employee,project.getEmployee());
	}

	@Test
	final void testSetEmployee() {
		Employee newEmployee = new Employee();
		newEmployee.setEmail("n.ewemployee1@wlb.it");
		newEmployee.setMessages(employee.getMessages());
		newEmployee.setName("New"+employee.getEmail());
		newEmployee.setPassword(employee.getPassword());
		newEmployee.setProjects1(employee.getProjects1());
		newEmployee.setProjects2(employee.getProjects2());
		newEmployee.setSmartWorkingPrenotations(employee.getSmartWorkingPrenotations());
		newEmployee.setStatus(1);
		newEmployee.setSurname("New"+employee.getSurname());
		newEmployee.setWorkstationPrenotations(employee.getWorkstationPrenotations());
		project.setEmployee(newEmployee);
		assertEquals(newEmployee,project.getEmployee());
	}

	@Test
	final void testToString() {
		String string = "Project [id=" + id + ", description=" + description + ", endDate=" + endDate + ", name=" + name
				+ ", scope=" + scope + ", startDate=" + startDate + ", admin=" + admin
				+ ", employee=" + employee + "]";
		assertEquals(string, project.toString());
	}

	@Test
	final void testEqualsObject1() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertEquals(project, newProject);
	}
	@Test
	final void testEqualsObject2() {
		Project newProject = new Project();
		newProject.setAdmin(new Admin());
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject3() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription("");
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject4() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(new Employee());
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject5() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(new ArrayList<Employee>());
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject6() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(startDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject7() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id+2);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject8() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(new ArrayList<Message>());
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject9() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name+"name");
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject10() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope+"scope");
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject11() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(endDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject12() {
		Project newProject = new Project();
		newProject.setAdmin(null);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject13() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(null);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject14() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(null);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject15() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(null);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject16() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(null);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject17() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(null);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject18() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(null);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject19() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(null);
		newProject.setStartDate(startDate);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject20() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(null);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject21() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		project.setDescription(null);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject22() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		project.setEmployee(null);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject23() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		project.setEmployees(null);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject24() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		project.setEndDate(null);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject25() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		project.setMessages(null);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject26() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		project.setName(null);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject27() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		project.setScope(null);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject28() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		project.setStartDate(null);
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject29() {
		Project newProject = null;
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject30() {
		String newProject = "";
		assertNotEquals(project, newProject);
	}
	@Test
	final void testEqualsObject31() {
		Project newProject = project;
		assertEquals(project, newProject);
	}
	@Test
	final void testEqualsObject32() {
		Project newProject = new Project();
		newProject.setAdmin(admin);
		newProject.setDescription(description);
		newProject.setEmployee(employee);
		newProject.setEmployees(employees);
		newProject.setEndDate(endDate);
		newProject.setId(id);
		newProject.setMessages(messages);
		newProject.setName(name);
		newProject.setScope(scope);
		newProject.setStartDate(startDate);
		project.setAdmin(null);
		assertNotEquals(project, newProject);
	}

}
