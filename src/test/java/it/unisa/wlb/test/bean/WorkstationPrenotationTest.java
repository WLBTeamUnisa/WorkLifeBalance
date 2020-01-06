package it.unisa.wlb.test.bean;

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
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPK;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotationPK;

/**
 * The aim of this class is testing WorkstationPrenotation.java and WorkstationPrenotationPK.java
 * 
 * @author Sabato Nocera
 *
 */
class WorkstationPrenotationTest {
	
	private WorkstationPrenotation workstationPrenotation;
	private int calendarWeek;
	private WorkstationPrenotationPK id;
	private Workstation workstation;
	private int year;
	private Employee employee;

	@BeforeEach
	void setUp() throws Exception {
		int numFloor=1;
		int numRoom=1;
		int numWorkstation=1;	
		
		String email = "m.rossi1@wlbadmin.it";	
		String name = "Mario";
		String surname = "Rossi";
		String password = "MarcoRossi1.";		
		
		Calendar localCalendar = Calendar.getInstance();
		TimeZone timeZone = localCalendar.getTimeZone();
		ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
		LocalDate today = LocalDateTime.ofInstant(localCalendar.toInstant(), zoneId).toLocalDate();
		localCalendar.setTime(Date.from(today.atStartOfDay().atZone(zoneId).toInstant()));
		/**
		 * Calendar Week
		 */
		calendarWeek = localCalendar.get(Calendar.WEEK_OF_YEAR);
		
		workstationPrenotation = new WorkstationPrenotation();
		workstationPrenotation.setCalendarWeek(calendarWeek);
		
		employee = new Employee();
		employee.setEmail("m.rossi1@wlb.it");
		employee.setName(surname);
		employee.setPassword(password);
		employee.setStatus(0);
		employee.setSurname(surname);
		/**
		 * Employee
		 */
		workstationPrenotation.setEmployee(employee);
		id = new WorkstationPrenotationPK();
		id.setEmailEmployee("m.rossi1@wlb.it");
		id.setPrenotationDate(new Date());
		/**
		 * WorkstationPrenotationPK
		 */
		workstationPrenotation.setId(id);	
		/**
		 * Year
		 */
		year=localCalendar.get(Calendar.YEAR);
		workstationPrenotation.setYear(year);
		workstationPrenotation.setWorkstation(workstation);
		
		/**
		 * Workstation
		 */
		Room room = new Room();
		Floor floor = new Floor();
		workstation = new Workstation();
		
		List<Floor> floors = new ArrayList<Floor>();
		
		Admin admin = new Admin();
		admin.setEmail(email);
		admin.setName(name);
		admin.setSurname(surname);
		admin.setPassword(password);
		floors.add(floor);
		admin.setFloors(floors);
		admin.setProjects(new ArrayList<Project>());
		
		floor.setNumFloor(numFloor);
		floor.setAdmin(admin);
		List<Room> rooms = new ArrayList<Room>();
		rooms.add(room);
		floor.setRooms(rooms);
		room.setFloor(floor);	
		
		RoomPK idRoom = new RoomPK();
		idRoom.setNumFloor(numFloor);
		idRoom.setNumRoom(numRoom);
		room.setId(idRoom);	
		
		List<Workstation> workstations = new ArrayList<Workstation>();
		workstations.add(workstation);
		room.setWorkstations(workstations);
		
		workstation.setRoom(room);
		WorkstationPK workstationPK = new WorkstationPK();
		workstationPK.setFloor(numFloor);
		workstationPK.setRoom(numRoom);
		workstationPK.setWorkstation(numWorkstation);
		workstation.setId(workstationPK);		
		
		workstationPrenotation.setWorkstation(workstation);
	}
	
	@Test
	public void getIdTest() {
		assertEquals(workstationPrenotation.getId(),id);
	}
	@Test
	public void setIdTest() {
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee(id.getEmailEmployee());
		workstationPrenotationPK.setPrenotationDate(id.getPrenotationDate());
		workstationPrenotation.setId(workstationPrenotationPK);
		assertEquals(workstationPrenotation.getId(),workstationPrenotationPK);
	}
	@Test
	public void getCalendarWeekTest() {
		assertEquals(workstationPrenotation.getCalendarWeek(),calendarWeek);
	}
	@Test
	public void setCalendarWeekTest() {
		int calendarWeek2 = 5;
		workstationPrenotation.setCalendarWeek(calendarWeek2);
		assertEquals(workstationPrenotation.getCalendarWeek(),calendarWeek2);
	}
	@Test
	public void getYearTest() {
		assertEquals(workstationPrenotation.getYear(),year);
	}
	@Test
	public void setYearTest() {
		int year2 = 5;
		workstationPrenotation.setYear(year2);
		assertEquals(workstationPrenotation.getYear(),year2);
	}
	@Test
	public void getEmployeeTest() {
		assertEquals(workstationPrenotation.getEmployee(),employee);
	}
	@Test
	public void setEmployeeTest() {
		Employee employee2 = new Employee();
		employee2.setEmail("g.verdana2@wbl.it");
		employee2.setName("Giuseppe");
		employee2.setPassword("Giuseppe1234.");
		employee2.setStatus(1);
		employee2.setSurname("Verdana");
		workstationPrenotation.setEmployee(employee2);
		assertEquals(workstationPrenotation.getEmployee(),employee2);
	}
	@Test
	public void getWorkstationTest() {
		assertEquals(workstationPrenotation.getWorkstation(),workstation);
	}
	@Test
	public void setWorkstationTest() {
		Workstation workstation2 = new Workstation();
		workstation2.setId(new WorkstationPK());
		workstation2.setRoom(new Room());
		List<WorkstationPrenotation> list = new ArrayList<WorkstationPrenotation>();
		list.add(new WorkstationPrenotation());
		workstation2.setWorkstationPrenotations(list);
		workstationPrenotation.setWorkstation(workstation2);
		assertEquals(workstationPrenotation.getWorkstation(),workstation2);
	}
	@Test
	public void toStringTest() {
		String string = "WorkstationPrenotation [id=" + id + ", calendarWeek=" + calendarWeek + ", year=" + year + ", employee="
				+ employee + ", workstation=" + workstation + "]";
		assertEquals(string, workstationPrenotation.toString());
	}
	@Test
	public void equalsTest1() {
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(id);
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(employee);
		workstationPrenotation2.setWorkstation(workstation);
		workstationPrenotation2.setYear(year);
		assertEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest2() {
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(new WorkstationPrenotationPK());
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(employee);
		workstationPrenotation2.setWorkstation(workstation);
		workstationPrenotation2.setYear(year);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest3() {
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(id);
		workstationPrenotation2.setCalendarWeek(0);
		workstationPrenotation2.setEmployee(employee);
		workstationPrenotation2.setWorkstation(workstation);
		workstationPrenotation2.setYear(year);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest4() {
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(id);
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(new Employee());
		workstationPrenotation2.setWorkstation(workstation);
		workstationPrenotation2.setYear(year);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest5() {
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(id);
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(employee);
		workstationPrenotation2.setWorkstation(new Workstation());
		workstationPrenotation2.setYear(year);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest6() {
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(id);
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(employee);
		workstationPrenotation2.setWorkstation(workstation);
		workstationPrenotation2.setYear(-1);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest7() {
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(null);
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(employee);
		workstationPrenotation2.setWorkstation(workstation);
		workstationPrenotation2.setYear(year);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest8() {
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(id);
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(null);
		workstationPrenotation2.setWorkstation(workstation);
		workstationPrenotation2.setYear(year);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest9() {
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(id);
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(employee);
		workstationPrenotation2.setWorkstation(null);
		workstationPrenotation2.setYear(year);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest10() {
		workstationPrenotation.setEmployee(null);
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(id);
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(employee);
		workstationPrenotation2.setWorkstation(workstation);
		workstationPrenotation2.setYear(year);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest11() {
		workstationPrenotation.setId(null);
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(id);
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(employee);
		workstationPrenotation2.setWorkstation(workstation);
		workstationPrenotation2.setYear(year);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest12() {
		workstationPrenotation.setWorkstation(null);
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(id);
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(employee);
		workstationPrenotation2.setWorkstation(workstation);
		workstationPrenotation2.setYear(year);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest13() {
		String workstationPrenotation2 = "";
		assertNotEquals(workstationPrenotation, workstationPrenotation2);
	}
	@Test
	public void equalsTest14() {
		WorkstationPrenotation workstationPrenotation2 = null;
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest15() {
		workstationPrenotation = null;
		WorkstationPrenotation workstationPrenotation2 = new WorkstationPrenotation();
		workstationPrenotation2.setId(id);
		workstationPrenotation2.setCalendarWeek(calendarWeek);
		workstationPrenotation2.setEmployee(employee);
		workstationPrenotation2.setWorkstation(workstation);
		workstationPrenotation2.setYear(year);
		assertNotEquals(workstationPrenotation2, workstationPrenotation);
	}
	@Test
	public void equalsTest16() {
		WorkstationPrenotation workstationPrenotation2 = workstationPrenotation;
		assertEquals(workstationPrenotation2, workstationPrenotation);
	}
	
	@Test
	public void equalsTest17() {
		String idPk = "";
		assertNotEquals(id, idPk);
	}
	
	@Test
	public void equalsTest18() {
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee(id.getEmailEmployee());
		workstationPrenotationPK.setPrenotationDate(id.getPrenotationDate());
		assertEquals(id, workstationPrenotationPK);
	}
	
	@Test
	public void equalsTest19() {
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee(id.getEmailEmployee());
		workstationPrenotationPK.setPrenotationDate(id.getPrenotationDate());
		assertEquals(id, workstationPrenotationPK);
	}
	
	@Test
	public void equalsTest20() {
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee("g.verdana@wlb.it");
		workstationPrenotationPK.setPrenotationDate(id.getPrenotationDate());
		assertNotEquals(id, workstationPrenotationPK);
	}
	
	@Test
	public void equalsTest21() {
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee(id.getEmailEmployee());
		Date date = new Date();
		date.setTime(id.getPrenotationDate().getTime()+15);
		workstationPrenotationPK.setPrenotationDate(date);
		assertNotEquals(id, workstationPrenotationPK);
	}
	
	@Test
	public void equalsTest23() {		
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee(id.getEmailEmployee());
		workstationPrenotationPK.setPrenotationDate(null);
		assertNotEquals(id, workstationPrenotationPK);
	}
	
	@Test
	public void equalsTest24() {		
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee(id.getEmailEmployee());
		workstationPrenotationPK.setPrenotationDate(id.getPrenotationDate());
		id.setPrenotationDate(null);
		assertNotEquals(id, workstationPrenotationPK);
	}
	
	@Test
	public void equalsTest25() {		
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee(null);
		workstationPrenotationPK.setPrenotationDate(id.getPrenotationDate());
		assertNotEquals(id, workstationPrenotationPK);
	}
	
	@Test
	public void equalsTest26() {		
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee(id.getEmailEmployee());
		workstationPrenotationPK.setPrenotationDate(id.getPrenotationDate());
		id.setEmailEmployee(null);
		assertNotEquals(id, workstationPrenotationPK);
	}
	
	@Test
	public void equalsTest27() {		
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee(id.getEmailEmployee());
		workstationPrenotationPK.setPrenotationDate(id.getPrenotationDate());
		workstationPrenotationPK.setId(id.getId()+1);
		assertNotEquals(id, workstationPrenotationPK);
	}
}
