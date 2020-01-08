package it.unisa.wlb.test.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Project;

/**
 * The aim of this class is testing Admin.java
 * 
 * @author Sabato Nocera
 *
 */
class AdminTest {
	
	private String email;
	private String name;
	private String surname;
	private String password;
	private Admin admin;
	private List<Floor> floors;
	private List<Project> projects;

	@BeforeEach
	void setUp() throws Exception {
		email = "m.rossi1@wlbadmin.it";	
		name = "Mario";
		surname = "Rossi";
		password = "MarcoRossi1.";
		
		admin = new Admin();
		admin.setEmail(email);
		admin.setName(name);
		admin.setSurname(surname);
		admin.setPassword(password);
		
		floors = new ArrayList<Floor>();
		projects = new ArrayList<Project>();
		
		Floor floor1 = new Floor();
		
		floor1.setNumFloor(1);
		floor1.setAdmin(admin);
		
		Floor floor2 = new Floor();
		floor2.setAdmin(admin);
		floor2.setNumFloor(2);
		
		floors.add(floor1);
		floors.add(floor2);
		
		Project project1 = new Project();
		project1.setAdmin(admin);
		project1.setDescription("abc");
		project1.setEmployee(new Employee());
		project1.setEndDate(new Date());
		project1.setId(1);
		project1.setName("abc");
		project1.setScope("abc");
		project1.setStartDate(new Date());
		
		Project project2 = new Project();
		project2.setAdmin(admin);
		project2.setDescription("def");
		project2.setEmployee(new Employee());
		project2.setEndDate(new Date());
		project2.setId(2);
		project2.setName("def");
		project2.setScope("def");
		project2.setStartDate(new Date());
		
		projects.add(project1);
		projects.add(project2);
				
		admin.setFloors(floors);
		admin.setProjects(projects);
	}
	
	@Test
	public void getEmailTest() {
		assertEquals(email,admin.getEmail());
	}

	@Test
	public void setEmailTest() {
		admin.setEmail("m.rossi2@wlbadmin.it");
		assertEquals("m.rossi2@wlbadmin.it",admin.getEmail());
	}

	@Test
	public void getNameTest() {
		assertEquals(name,admin.getName());
	}

	@Test
	public void setNameTest() {
		admin.setName("Luca");
		assertEquals("Luca",admin.getName());
	}

	@Test
	public void getPasswordTest() {
		assertEquals(password,admin.getPassword());
	}

	@Test
	public void setPasswordTest() {
		admin.setPassword("MarioRossi2..");
		assertEquals("MarioRossi2..",admin.getPassword());
	}

	@Test
	public void getSurnameTest() {
		assertEquals(surname,admin.getSurname());
	}

	@Test
	public void setSurnameTest() {
		admin.setSurname("Verdi");
		assertEquals("Verdi",admin.getSurname());
	}

	@Test
	public void getFloors() {
		List<Floor> list = admin.getFloors();
		if(list.size()!=floors.size())
			assertTrue(false);
		for(Floor floor:list)
			if(!floors.contains(floor)) {
				assertTrue(false);
				return ;
			}
		assertTrue(true);
	}

	@Test
	public void setFloorsTest() {	
		List<Floor> floorsList = new ArrayList<Floor>();
		
		Floor floor3 = new Floor();
		floor3.setAdmin(admin);
		floor3.setNumFloor(3);
		
		floorsList.add(floor3);
		
		Floor floor4 = new Floor();
		floor4.setAdmin(admin);
		floor4.setNumFloor(4);
		
		floorsList.add(floor4);
		
		admin.setFloors(floorsList);
		
		if(!admin.getFloors().contains(floor4) || !admin.getFloors().contains(floor3)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}

	@Test
	public void addFloorTest() {
		Floor floor3 = new Floor();
		floor3.setAdmin(admin);
		floor3.setNumFloor(3);
		
		admin.addFloor(floor3);
		
		List<Floor> list = admin.getFloors();
		
		if(!list.contains(floor3)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}

	@Test
	public void removeFloorTest() {
		Floor floor3 = new Floor();
		floor3.setAdmin(admin);
		floor3.setNumFloor(3);
		
		admin.addFloor(floor3);
		
		List<Floor> list = admin.getFloors();
		
		if(list.contains(floor3)) {				
			admin.removeFloor(floor3);
			if(!list.contains(floor3)) {
				assertTrue(true);
				return ;
			}
		}
		assertTrue(false);
	}

	@Test
	public void getProjectsTest() {
		List<Project> list = admin.getProjects();
		if(list.size()!=projects.size())
			assertTrue(false);
		for(Project project:list)
			if(!projects.contains(project)) {
				assertTrue(false);
				return ;
			}
		assertTrue(true);
	}

	@Test
	public void setProjectsTest() {
		List<Project> projectsList = new ArrayList<Project>();

		Project project3 = new Project();
		project3.setAdmin(admin);
		project3.setDescription("abc");
		project3.setEmployee(new Employee());
		project3.setEndDate(new Date());
		project3.setId(3);
		project3.setName("abc");
		project3.setScope("abc");
		project3.setStartDate(new Date());
		
		projectsList.add(project3);
		
		Project project4 = new Project();
		project4.setAdmin(admin);
		project4.setDescription("def");
		project4.setEmployee(new Employee());
		project4.setEndDate(new Date());
		project4.setId(4);
		project4.setName("def");
		project4.setScope("def");
		project4.setStartDate(new Date());
		
		projectsList.add(project4);
		
		admin.setProjects(projectsList);
		
		List<Project> list = admin.getProjects();
		
		if(!list.contains(project3) || !list.contains(project4)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}

	@Test
	public void addProjectTest() {
		Project project3 = new Project();
		project3.setAdmin(admin);
		project3.setDescription("abc");
		project3.setEmployee(new Employee());
		project3.setEndDate(new Date());
		project3.setId(3);
		project3.setName("abc");
		project3.setScope("abc");
		project3.setStartDate(new Date());
		
		admin.addProject(project3);
		
		List<Project> list = admin.getProjects();
		
		if(!list.contains(project3)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	
	@Test
	public void removeProjectTest() {
		Project project3 = new Project();
		project3.setAdmin(admin);
		project3.setDescription("abc");
		project3.setEmployee(new Employee());
		project3.setEndDate(new Date());
		project3.setId(3);
		project3.setName("abc");
		project3.setScope("abc");
		project3.setStartDate(new Date());
		
		projects.add(project3);
		
		List<Project> list = admin.getProjects();
		
		if(list.contains(project3)) {				
			admin.removeProject(project3);
			if(!list.contains(project3)) {
				assertTrue(true);
				return ;
			}
		}
		assertTrue(false);
	}

	@Test
	public void equalsTest1() {
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertEquals(admin, admin2);
	}
	
	public void equalsTest2() {
		Admin admin2 = new Admin();
		admin2.setEmail("");
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest3() {
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName("");
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest4() {
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname("");
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest5() {
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword("");
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest6() {
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(new ArrayList<Floor>());
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest7() {
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(new ArrayList<Project>());
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest8() {
		Admin admin2 = new Admin();
		admin2.setEmail(null);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest9() {
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(null);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest10() {
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(null);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest11() {
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(null);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest12() {
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(null);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest13() {
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(null);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest14() {
		admin.setEmail(null);
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest15() {
		admin.setName(null);
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest16() {
		admin.setSurname(null);
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest17() {
		admin.setPassword(null);
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest18() {
		admin.setFloors(null);
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest19() {
		admin.setProjects(null);
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	
	@Test
	public void equalsTest20() {
		String error = "";
		assertNotEquals(admin, error);
	}
	@Test
	public void equalsTest21() {
		admin=null;
		Admin admin2 = new Admin();
		admin2.setEmail(email);
		admin2.setName(name);
		admin2.setSurname(surname);
		admin2.setPassword(password);
		admin2.setFloors(floors);
		admin2.setProjects(projects);
		assertNotEquals(admin, admin2);
	}
	@Test
	public void equalsTest22() {
		Admin admin2 = null;
		assertNotEquals(admin, admin2);
	}
	@Test
	public void equalsTest23() {
		Admin admin2 = admin;
		assertEquals(admin2, admin);
	}

	@Test
	public void toStringTest() {
		String string = "Admin [email=" + email + ", name=" + name + ", password=" + password + ", surname=" + surname
				+ "]";
		assertEquals(string, admin.toString());
	}

}
