package it.unisa.wlb.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPK;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotationPK;
import it.unisa.wlb.model.jpa.EmployeeJpa;
import it.unisa.wlb.model.jpa.WorkstationPrenotationJpa;

class WorkstationPrenotationJpaTest {

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;
	private Floor firstFloor;
	private Room firstRoom;
	private RoomPK roomPk;
	private Workstation workstation;
	private WorkstationPK stationPk;
	private WorkstationPrenotationJpa prenotationJpa; 
	private WorkstationPrenotation prenotation;
	private WorkstationPrenotationPK prenotationPk;
	private Admin admin;
	private Employee employee;
	private EmployeeJpa employeeJpa;
	private int calendarWeek;
	private int year;
	private Date workstationPrenotationDate;
	private WorkstationPK aStationPk;
	private Workstation aWorkstation;
	
	@BeforeEach
	void setUp() throws Exception {
		prenotationJpa = new WorkstationPrenotationJpa();
		employeeJpa = new EmployeeJpa();
		prenotation = new WorkstationPrenotation();
		prenotationPk = new WorkstationPrenotationPK();
		firstFloor = new Floor();
		firstRoom = new Room();
		roomPk = new RoomPK();
		workstation = new Workstation();
		stationPk = new WorkstationPK();
		aWorkstation = new Workstation();
		aStationPk = new WorkstationPK();
		admin = new Admin();
		employee = new Employee();
		
		admin.setEmail("m.rossi1@wlbadmin.it");
		admin.setName("Mario");
		admin.setSurname("Rossi");
		admin.setPassword("Ciao1234.");
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(admin);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		/**
		 * Creating a floor
		 */
		firstFloor.setAdmin(admin);
		firstFloor.setNumFloor(1);
		/**
		 * Creating a room
		 */
		roomPk.setNumFloor(1);
		roomPk.setNumRoom(1);
		firstRoom.setFloor(firstFloor);
		firstRoom.setId(roomPk);
		/**
		 * Creating a workstation
		 */
		stationPk.setFloor(1);
		stationPk.setRoom(1);
		stationPk.setWorkstation(1);
		workstation.setId(stationPk);
		workstation.setRoom(firstRoom);
		
		employee.setEmail("v.verdi1@wlb.it");
		employee.setName("Vincenzo");
		employee.setSurname("Verdi");
		employee.setPassword("Ciao1234.");
		employee.setStatus(0);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(workstation);
			entityManager.persist(employee);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		LocalDate date = LocalDate.now();
		date = LocalDate.parse(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		date = date.plusDays(7);
		Calendar calendar = Calendar.getInstance();
		TimeZone timeZone = calendar.getTimeZone();
		ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
		workstationPrenotationDate = Date.from(date.atStartOfDay().atZone(zoneId).toInstant());
		calendar.setTime(workstationPrenotationDate);
		
		calendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		year = calendar.get(Calendar.YEAR);
		
		prenotationPk.setPrenotationDate(workstationPrenotationDate);
		prenotationPk.setEmailEmployee(employee.getEmail());
		prenotationPk.setId(1);
		prenotation.setCalendarWeek(calendarWeek);
		prenotation.setYear(year);
		prenotation.setEmployee(employee);
		prenotation.setWorkstation(workstation);
		prenotation.setId(prenotationPk);
	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(workstation));
			entityManager.remove(entityManager.merge(firstRoom));
			entityManager.remove(entityManager.merge(firstFloor));
			entityManager.remove(entityManager.merge(employee));
			entityManager.remove(entityManager.merge(admin));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Test
	void createTest() {
		WorkstationPrenotation created;		
		created = prenotationJpa.create(prenotation);
		assertEquals(created.getId().getId(), prenotation.getId().getId());
		prenotation.getId().setId(1);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(prenotation));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
/*	
	@Test
	void updateTest() {
		WorkstationPrenotation updated;
		aWorkstation.setRoom(firstRoom);
		aStationPk.setFloor(firstFloor.getNumFloor());
		aStationPk.setRoom(firstRoom.getId().getNumRoom());
		aStationPk.setWorkstation(2);
		aWorkstation.setId(aStationPk);
		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(prenotation);
			entityManager.persist(aWorkstation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		prenotation.setWorkstation(aWorkstation);
		prenotation.getId().setId(1);
		updated = prenotationJpa.update(prenotation);
		assertEquals(updated.getId().getId(), prenotation.getId().getId());
		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(prenotation));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}	
*/	
	@Test
	void removeTest() {		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(prenotation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		prenotationJpa.remove(prenotation);
		employee = employeeJpa.retrieveByEmail(employee.getEmail());
		assertTrue(employee.getWorkstationPrenotations().isEmpty());
	}	
	
	@Test
	void retrieveByEmployeeTest() {
		List<WorkstationPrenotation> list;
		boolean check = true;
		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(prenotation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		list = prenotationJpa.retrieveByEmployee(employee.getEmail());
		for(WorkstationPrenotation aPrenotation : list) {
			if(!aPrenotation.getEmployee().getEmail().equals(employee.getEmail())) check = false;
		}
		assertTrue(check);
		
		prenotation.getId().setId(1);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(prenotation));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
	
	@Test
	void retrieveByWeeklyPlanningTest() {
		List<WorkstationPrenotation> list;
		boolean check = true;
		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(prenotation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		list = prenotationJpa.retrieveByWeeklyPlanning(calendarWeek, year, employee.getEmail());
		list = prenotationJpa.retrieveByEmployee(employee.getEmail());
		for(WorkstationPrenotation aPrenotation : list) {
			if(!aPrenotation.getEmployee().getEmail().equals(employee.getEmail()) || !(aPrenotation.getCalendarWeek() == calendarWeek)) check = false;
		}
		assertTrue(check);
		
		prenotation.getId().setId(1);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(prenotation));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Test
	void retrieveByWorkstationDateTest() {
		List<WorkstationPrenotation> list;
		boolean check = true;
		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(prenotation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		list = prenotationJpa.retrieveByWorkstationDate(workstationPrenotationDate, firstFloor.getNumFloor(), firstRoom.getId().getNumRoom());
		for(WorkstationPrenotation aPrenotation : list) {
			if(!(aPrenotation.getId().getPrenotationDate().compareTo(workstationPrenotationDate) == 0) || 
					!(aPrenotation.getWorkstation().getRoom().getId().getNumRoom() == firstRoom.getId().getNumRoom()) || 
						!(aPrenotation.getWorkstation().getRoom().getFloor().getNumFloor() == firstFloor.getNumFloor())) check = false;
		}
		assertTrue(check);
		
		prenotation.getId().setId(1);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(prenotation));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
	
	@Test
	void retrieveAllTest() {
		List<WorkstationPrenotation> list;
		boolean check = false;
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(prenotation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		list = prenotationJpa.retrieveAll();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().getId() == prenotation.getId().getId()) check = true;
		}
		assertTrue(check);
		
		prenotation.getId().setId(1);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(prenotation));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
}
