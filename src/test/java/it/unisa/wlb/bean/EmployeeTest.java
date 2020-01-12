package it.unisa.wlb.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Message;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.SmartWorkingPrenotationPK;
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotationPK;

/**
 * The aim of this class is testing Employee.java
 * 
 * @author Sabato Nocera
 *
 */
class EmployeeTest {
	
	private Employee employee;
	private String email;
	private List<Message> messages;
	private String name;
	private String password;
	private List<Project> projects1;
	private List<Project> projects2;
	private List<SmartWorkingPrenotation> smartWorkingPrenotations;
	private int status;
	private String surname;
	private List<WorkstationPrenotation> workstationPrenotations;
	

	@BeforeEach
	void setUp() throws Exception {
		employee = new Employee();
		email = "g.verdana@wlb.it";
		messages = new ArrayList<Message>();
		name = "Giuseppe";
		password = "Giuseppe1234.";
		projects1 = new ArrayList<Project>();
		projects2 = new ArrayList<Project>();
		smartWorkingPrenotations = new ArrayList<SmartWorkingPrenotation>();
		status = 1;
		surname = "Verdana";
		workstationPrenotations = new ArrayList<WorkstationPrenotation>();		
		
		/**
		 * Email
		 */
		employee.setEmail(email);
		
		/**
		 * Name
		 */
		employee.setName(name);
		
		/**
		 * Password
		 */
		employee.setPassword(password);
		
		/**
		 * Status
		 */
		employee.setStatus(status);
		
		/**
		 * Surname
		 */
		employee.setSurname(surname);
		
		/**
		 * Messages
		 */
		Message message = new Message();
		Date date = new Date();
		message.setDate(date);
		message.setEmployee(employee);
		message.setId(0);
		Project project = new Project();
		Admin admin = new Admin();
		admin.setEmail("a.dministrator1@wlbadmin.it");
		admin.setFloors(new ArrayList<Floor>());
		admin.setName("Admin");
		admin.setPassword("Admin1234.");
		List<Project> projects = new ArrayList<Project>();
		projects.add(project);
		admin.setProjects(projects);
		admin.setSurname("Administrator");
		project.setAdmin(admin);
		project.setDescription("123456789Abcdefgh");
		project.setEmployee(employee);
		List<Employee> employees = new ArrayList<Employee>();
		project.setEmployees(employees);
		project.setEndDate(new Date());
		project.setId(1);
		List<Message> messages2 = new ArrayList<Message>();
		messages2.add(message);
		project.setMessages(messages2);
		project.setName("ProjectName");
		project.setScope("ProjectScope");
		project.setStartDate(new Date());
		message.setProject(project);
		message.setText("Qwertyuiopasdfghjklzxcvbnm");
		messages.add(message);
		employee.setMessages(messages);
				
		/**
		 * Projects1 (supervised)
		 */
		projects1.add(project);
		employee.setProjects1(projects1);
		
		/**
		 * Projects2 (working)
		 */
		projects2.add(project);
		employee.setProjects2(projects2);
		
		/**
		 * SmartWorkingPrenotations
		 */
		SmartWorkingPrenotation smartWorkingPrenotation = new SmartWorkingPrenotation();
		Calendar localCalendar = Calendar.getInstance();
		TimeZone timeZone = localCalendar.getTimeZone();
		ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
		LocalDate today = LocalDateTime.ofInstant(localCalendar.toInstant(), zoneId).toLocalDate();
		localCalendar.setTime(Date.from(today.atStartOfDay().atZone(zoneId).toInstant()));
		int calendarWeek = localCalendar.get(Calendar.WEEK_OF_YEAR);
		int year = localCalendar.get(Calendar.YEAR);
		SmartWorkingPrenotationPK smartWorkingPrenotationPK = new SmartWorkingPrenotationPK();
		smartWorkingPrenotationPK.setEmployeeEmail(email);
		smartWorkingPrenotationPK.setId(1);	
		List<PrenotationDate> prenotationDates = new ArrayList<PrenotationDate>();
		smartWorkingPrenotation.setCalendarWeek(calendarWeek);
		smartWorkingPrenotation.setEmployee(employee);
		smartWorkingPrenotation.setId(smartWorkingPrenotationPK);
		smartWorkingPrenotation.setPrenotationDates(prenotationDates);
		smartWorkingPrenotation.setYear(year);
		smartWorkingPrenotations.add(smartWorkingPrenotation);
		employee.setSmartWorkingPrenotations(smartWorkingPrenotations);
		
		/**
		 * WorkstationPrenotations
		 */
		WorkstationPrenotation workstationPrenotation = new WorkstationPrenotation();
		workstationPrenotation.setCalendarWeek(calendarWeek);
		workstationPrenotation.setEmployee(employee);
		WorkstationPrenotationPK workstationPrenotationPk = new WorkstationPrenotationPK();
		workstationPrenotationPk.setEmailEmployee(email);
		workstationPrenotationPk.setPrenotationDate(new Date());
		workstationPrenotation.setId(workstationPrenotationPk);
		workstationPrenotation.setWorkstation(new Workstation());
		workstationPrenotation.setYear(year);
		workstationPrenotations.add(workstationPrenotation);
		employee.setWorkstationPrenotations(workstationPrenotations);
	}
	
	@Test
	public void getEmailTest() {
		assertEquals(employee.getEmail(),email);
	}
	@Test
	public void setEmailTest() {
		String newEmail="l.longobardi@wlb.it";
		employee.setEmail(newEmail);
		assertEquals(employee.getEmail(),newEmail);
	}
	@Test
	public void getNameTest() {
		assertEquals(employee.getName(),name);
	}
	@Test
	public void setNameTest() {
		String newName="Luca";
		employee.setName(newName);
		assertEquals(employee.getName(),newName);
	}
	@Test
	public void getPasswordTest() {
		assertEquals(employee.getPassword(),password);
	}
	@Test
	public void setPasswordTest() {
		String newPassword = "Longobardi1234.";
		employee.setPassword(newPassword);
		assertEquals(employee.getPassword(),newPassword);
	}
	@Test
	public void getStatusTest() {
		assertEquals(employee.getStatus(), status);
	}
	@Test
	public void setStatusTest() {
		int newStatus = 0;
		employee.setStatus(newStatus);
		assertEquals(employee.getStatus(), newStatus);
	}
	@Test
	public void getSurnameTest() {
		assertEquals(employee.getSurname(), surname);
	}
	@Test
	public void setSurnameTest() {
		String newSurname = "Longobardi";
		employee.setSurname(newSurname);
		assertEquals(employee.getSurname(),newSurname);
	}
	@Test
	public void getMessagesTest() {
		List<Message> list = employee.getMessages();
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
	public void setMessagesTest() {
		Message message = new Message();
		message.setDate(new Date());
		message.setEmployee(employee);
		message.setId(2);
		message.setProject(new Project());
		message.setText("qwertyuiopasdfghjklzxcvbnm");
		
		List<Message> messagesList = new ArrayList<Message>();
		messagesList.add(message);
		
		employee.setMessages(messagesList);
		
		if(!employee.getMessages().contains(message)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	@Test
	public void addMessageTest() {
		Message message = new Message();
		message.setDate(new Date());
		message.setEmployee(employee);
		message.setId(2);
		message.setProject(new Project());
		message.setText("qwertyuiopasdfghjklzxcvbnm");
		
		employee.addMessage(message);
		
		if(!employee.getMessages().contains(message)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	@Test
	public void removeMessageTest() {
		Message message = new Message();
		message.setDate(new Date());
		message.setEmployee(employee);
		message.setId(2);
		message.setProject(new Project());
		message.setText("qwertyuiopasdfghjklzxcvbnm");
		
		employee.addMessage(message);
		
		List<Message> list = employee.getMessages();
		
		if(list.contains(message)) {				
			employee.removeMessage(message);
			if(!list.contains(message)) {
				assertTrue(true);
				return ;
			}
		}
		assertTrue(false);
	}
	@Test
	public void getProjects1Test() {
		List<Project> list = employee.getProjects1();
		if(list.size()!=projects1.size())
			assertTrue(false);
		for(Project project:list)
			if(!projects1.contains(project)) {
				assertTrue(false);
				return ;
			}
		assertTrue(true);
	}
	@Test
	public void setProjects1Test() {
		Project project = new Project();
		project.setAdmin(projects1.get(0).getAdmin());
		project.setDescription(projects1.get(0).getDescription());
		project.setEmployee(projects1.get(0).getEmployee());
		project.setEmployees(projects1.get(0).getEmployees());
		project.setEndDate(projects1.get(0).getEndDate());
		project.setId(3);
		project.setMessages(projects1.get(0).getMessages());
		project.setName(projects1.get(0).getName()+"abcdef");
		project.setScope(projects1.get(0).getScope());
		project.setStartDate(projects1.get(0).getStartDate());
		
		List<Project> projectsList = new ArrayList<Project>();
		projectsList.add(project);
		
		employee.setProjects1(projectsList);
		
		if(!employee.getProjects1().contains(project)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	@Test
	public void addProjects1Test() {
		Project project = new Project();
		project.setAdmin(projects1.get(0).getAdmin());
		project.setDescription(projects1.get(0).getDescription());
		project.setEmployee(projects1.get(0).getEmployee());
		project.setEmployees(projects1.get(0).getEmployees());
		project.setEndDate(projects1.get(0).getEndDate());
		project.setId(3);
		project.setMessages(projects1.get(0).getMessages());
		project.setName(projects1.get(0).getName()+"abcdef");
		project.setScope(projects1.get(0).getScope());
		project.setStartDate(projects1.get(0).getStartDate());
		
		employee.addProjects1(project);
		
		List<Project> list = employee.getProjects1();
		
		if(!list.contains(project)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	@Test
	public void removeProjects1Test() {
		Project project = new Project();
		project.setAdmin(projects1.get(0).getAdmin());
		project.setDescription(projects1.get(0).getDescription());
		project.setEmployee(projects1.get(0).getEmployee());
		project.setEmployees(projects1.get(0).getEmployees());
		project.setEndDate(projects1.get(0).getEndDate());
		project.setId(3);
		project.setMessages(projects1.get(0).getMessages());
		project.setName(projects1.get(0).getName()+"abcdef");
		project.setScope(projects1.get(0).getScope());
		project.setStartDate(projects1.get(0).getStartDate());
		
		projects1.add(project);
		
		List<Project> list = employee.getProjects1();
		
		if(list.contains(project)) {				
			employee.removeProjects1(project);
			if(!list.contains(project)) {
				assertTrue(true);
				return ;
			}
		}
		assertTrue(false);
	}
	@Test
	public void getProjects2Test() {
		List<Project> list = employee.getProjects2();
		if(list.size()!=projects2.size())
			assertTrue(false);
		for(Project project:list)
			if(!projects2.contains(project)) {
				assertTrue(false);
				return ;
			}
		assertTrue(true);
	}
	@Test
	public void setProjects2Test() {
		Project project = new Project();
		project.setAdmin(projects1.get(0).getAdmin());
		project.setDescription(projects1.get(0).getDescription());
		project.setEmployee(projects1.get(0).getEmployee());
		project.setEmployees(projects1.get(0).getEmployees());
		project.setEndDate(projects1.get(0).getEndDate());
		project.setId(3);
		project.setMessages(projects1.get(0).getMessages());
		project.setName(projects1.get(0).getName()+"abcdef");
		project.setScope(projects1.get(0).getScope());
		project.setStartDate(projects1.get(0).getStartDate());
		
		List<Project> projectsList = new ArrayList<Project>();
		projectsList.add(project);
		
		employee.setProjects2(projectsList);
		
		if(!employee.getProjects2().contains(project)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	@Test
	public void getSmartWorkingPrenotationsTest() {
		List<SmartWorkingPrenotation> list = employee.getSmartWorkingPrenotations();
		if(list.size()!=smartWorkingPrenotations.size())
			assertTrue(false);
		for(SmartWorkingPrenotation smartWorkingPrenotation:list)
			if(!smartWorkingPrenotations.contains(smartWorkingPrenotation)) {
				assertTrue(false);
				return ;
			}
		assertTrue(true);
	}
	@Test
	public void setSmartWorkingPrenotationsTest() {
		SmartWorkingPrenotation smartWorkingPrenotation = new SmartWorkingPrenotation();
		smartWorkingPrenotation.setCalendarWeek(smartWorkingPrenotations.get(0).getCalendarWeek());
		smartWorkingPrenotation.setEmployee(smartWorkingPrenotations.get(0).getEmployee());
		SmartWorkingPrenotationPK smartWorkingPrenotationPK = new SmartWorkingPrenotationPK();
		smartWorkingPrenotationPK.setEmployeeEmail("n.newww15wlb.it");
		smartWorkingPrenotationPK.setId(3);
		smartWorkingPrenotation.setId(smartWorkingPrenotationPK);
		smartWorkingPrenotation.setPrenotationDates(smartWorkingPrenotations.get(0).getPrenotationDates());
		smartWorkingPrenotation.setYear(smartWorkingPrenotations.get(0).getYear());
		
		List<SmartWorkingPrenotation> smartWorkingPrenotationsList = new ArrayList<SmartWorkingPrenotation>();
		smartWorkingPrenotationsList.add(smartWorkingPrenotation);
		
		employee.setSmartWorkingPrenotations(smartWorkingPrenotationsList);
		
		if(!employee.getSmartWorkingPrenotations().contains(smartWorkingPrenotation)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	@Test
	public void addSmartWorkingPrenotationTest() {
		SmartWorkingPrenotation smartWorkingPrenotation = new SmartWorkingPrenotation();
		smartWorkingPrenotation.setCalendarWeek(smartWorkingPrenotations.get(0).getCalendarWeek());
		smartWorkingPrenotation.setEmployee(smartWorkingPrenotations.get(0).getEmployee());
		SmartWorkingPrenotationPK smartWorkingPrenotationPK = new SmartWorkingPrenotationPK();
		smartWorkingPrenotationPK.setEmployeeEmail("n.newww15wlb.it");
		smartWorkingPrenotationPK.setId(3);
		smartWorkingPrenotation.setId(smartWorkingPrenotationPK);
		smartWorkingPrenotation.setPrenotationDates(smartWorkingPrenotations.get(0).getPrenotationDates());
		smartWorkingPrenotation.setYear(smartWorkingPrenotations.get(0).getYear());
		
		employee.addSmartWorkingPrenotation(smartWorkingPrenotation);
		
		List<SmartWorkingPrenotation> list = employee.getSmartWorkingPrenotations();
		
		if(!list.contains(smartWorkingPrenotation)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	@Test
	public void removeSmartWorkingPrenotationTest() {
		SmartWorkingPrenotation smartWorkingPrenotation = new SmartWorkingPrenotation();
		smartWorkingPrenotation.setCalendarWeek(smartWorkingPrenotations.get(0).getCalendarWeek());
		smartWorkingPrenotation.setEmployee(smartWorkingPrenotations.get(0).getEmployee());
		SmartWorkingPrenotationPK smartWorkingPrenotationPK = new SmartWorkingPrenotationPK();
		smartWorkingPrenotationPK.setEmployeeEmail("n.newww15wlb.it");
		smartWorkingPrenotationPK.setId(3);
		smartWorkingPrenotation.setId(smartWorkingPrenotationPK);
		smartWorkingPrenotation.setPrenotationDates(smartWorkingPrenotations.get(0).getPrenotationDates());
		smartWorkingPrenotation.setYear(smartWorkingPrenotations.get(0).getYear());
		
		employee.addSmartWorkingPrenotation(smartWorkingPrenotation);
		
		List<SmartWorkingPrenotation> list = employee.getSmartWorkingPrenotations();
		
		if(list.contains(smartWorkingPrenotation)) {				
			employee.removeSmartWorkingPrenotation(smartWorkingPrenotation);
			if(!list.contains(smartWorkingPrenotation)) {
				assertTrue(true);
				return ;
			}
		}
		assertTrue(false);
	}
	@Test
	public void getWorkstationPrenotationsTest() {		
		List<WorkstationPrenotation> list = employee.getWorkstationPrenotations();
		if(list.size()!=workstationPrenotations.size())
			assertTrue(false);
		for(WorkstationPrenotation workstationPrenotation:list)
			if(!workstationPrenotations.contains(workstationPrenotation)) {
				assertTrue(false);
				return ;
			}
		assertTrue(true);
	}
	@Test
	public void setWorkstationPrenotationsTest() {
		WorkstationPrenotation workstationPrenotation = new WorkstationPrenotation();
		workstationPrenotation.setCalendarWeek(workstationPrenotations.get(0).getCalendarWeek());
		workstationPrenotation.setEmployee(workstationPrenotations.get(0).getEmployee());
		WorkstationPrenotationPK workstationPrenotationPk = new WorkstationPrenotationPK();
		workstationPrenotationPk.setEmailEmployee("n.newww15wlb.it");
		workstationPrenotationPk.setPrenotationDate(new Date());
		workstationPrenotation.setId(workstationPrenotationPk);
		workstationPrenotation.setWorkstation(workstationPrenotations.get(0).getWorkstation());
		workstationPrenotation.setYear(workstationPrenotations.get(0).getYear());
		
		List<WorkstationPrenotation> workstationPrenotationsList = new ArrayList<WorkstationPrenotation>();
		workstationPrenotationsList.add(workstationPrenotation);
		
		employee.setWorkstationPrenotations(workstationPrenotationsList);
		
		if(!employee.getWorkstationPrenotations().contains(workstationPrenotation)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	@Test
	public void addWorkstationPrenotationTest() {
		WorkstationPrenotation workstationPrenotation = new WorkstationPrenotation();
		workstationPrenotation.setCalendarWeek(workstationPrenotations.get(0).getCalendarWeek());
		workstationPrenotation.setEmployee(workstationPrenotations.get(0).getEmployee());
		WorkstationPrenotationPK workstationPrenotationPk = new WorkstationPrenotationPK();
		workstationPrenotationPk.setEmailEmployee("n.newww15wlb.it");
		workstationPrenotationPk.setPrenotationDate(new Date());
		workstationPrenotation.setId(workstationPrenotationPk);
		workstationPrenotation.setWorkstation(workstationPrenotations.get(0).getWorkstation());
		workstationPrenotation.setYear(workstationPrenotations.get(0).getYear());
		
		employee.addWorkstationPrenotation(workstationPrenotation);
		
		List<WorkstationPrenotation> list = employee.getWorkstationPrenotations();
		
		if(!list.contains(workstationPrenotation)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	@Test
	public void removeWorkstationPrenotationTest() {
		WorkstationPrenotation workstationPrenotation = new WorkstationPrenotation();
		workstationPrenotation.setCalendarWeek(workstationPrenotations.get(0).getCalendarWeek());
		workstationPrenotation.setEmployee(workstationPrenotations.get(0).getEmployee());
		WorkstationPrenotationPK workstationPrenotationPk = new WorkstationPrenotationPK();
		workstationPrenotationPk.setEmailEmployee("n.newww15wlb.it");
		workstationPrenotationPk.setPrenotationDate(new Date());
		workstationPrenotation.setId(workstationPrenotationPk);
		workstationPrenotation.setWorkstation(workstationPrenotations.get(0).getWorkstation());
		workstationPrenotation.setYear(workstationPrenotations.get(0).getYear());
		
		employee.addWorkstationPrenotation(workstationPrenotation);
		
		List<WorkstationPrenotation> list = employee.getWorkstationPrenotations();
		
		if(list.contains(workstationPrenotation)) {				
			employee.removeWorkstationPrenotation(workstationPrenotation);
			if(!list.contains(workstationPrenotation)) {
				assertTrue(true);
				return ;
			}
		}
		assertTrue(false);
	}

	@Test
	public void toStringTest() {				
		String string = "Employee [email=" + email + ", name=" + name + ", password=" + password + ", status=" + status
				+ ", surname=" + surname + "]";
		assertEquals(string, employee.toString());
	}
	
	@Test
	public void equalsTest1() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertEquals(employee, employee2);
	}

	@Test
	public void equalsTest2() {
		Employee employee2 = new Employee();
		employee2.setEmail("");
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}

	@Test
	public void equalsTest3() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(new ArrayList<Message>());
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest4() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName("");
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest5() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword("");
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest6() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(new ArrayList<Project>());
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest7() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(new ArrayList<Project>());
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest8() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(new ArrayList<SmartWorkingPrenotation>());
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest9() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(3);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest10() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname("");
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest11() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest12() {
		Employee employee2 = new Employee();
		employee2.setEmail(null);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest13() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(null);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest14() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(null);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest15() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(null);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest16() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(null);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest17() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(null);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest18() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(null);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest19() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(null);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest20() {
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(null);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest21() {
		employee.setEmail(null);
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest22() {
		employee.setMessages(null);
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest23() {
		employee.setName(null);
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest24() {
		employee.setPassword(null);
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest25() {
		employee.setProjects1(null);
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest26() {
		employee.setProjects2(null);
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest27() {
		employee.setSmartWorkingPrenotations(null);
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest28() {
		employee.setSurname(null);
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest29() {
		employee.setWorkstationPrenotations(null);
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest30() {
		employee = null;
		Employee employee2 = new Employee();
		employee2.setEmail(email);
		employee2.setMessages(messages);
		employee2.setName(name);
		employee2.setPassword(password);
		employee2.setProjects1(projects1);
		employee2.setProjects2(projects2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations);
		employee2.setStatus(status);
		employee2.setSurname(surname);
		employee2.setWorkstationPrenotations(workstationPrenotations);
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest31() {
		Employee employee2 = null;
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest32() {
		String employee2 = "";
		assertNotEquals(employee, employee2);
	}
	@Test
	public void equalsTest33() {
		Employee employee2 = employee;
		assertEquals(employee, employee2);
	}
}
