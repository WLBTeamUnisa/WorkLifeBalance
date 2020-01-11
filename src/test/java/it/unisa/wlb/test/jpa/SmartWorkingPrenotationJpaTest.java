package it.unisa.wlb.test.jpa;

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

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.SmartWorkingPrenotationPK;
import it.unisa.wlb.model.jpa.SmartWorkingPrenotationJpa;

class SmartWorkingPrenotationJpaTest {

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;
	private SmartWorkingPrenotationJpa prenotationJpa;
	private SmartWorkingPrenotation prenotation;
	private SmartWorkingPrenotationPK prenotationPk;
	private Employee employee;
	private Date prenotationDate;
	private int calendarWeek;
	private int year;
	
	@BeforeEach
	void setUp() throws Exception {
		prenotationJpa = new SmartWorkingPrenotationJpa();
		prenotation = new SmartWorkingPrenotation();
		prenotationPk = new SmartWorkingPrenotationPK();
		employee = new Employee();
		
		employee.setEmail("m.rossi1@wlb.it");
		employee.setName("Mario");
		employee.setSurname("Rossi");
		employee.setPassword("Ciao1234.");
		employee.setStatus(0);
		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(employee);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		prenotationPk.setId(1);
		prenotationPk.setEmployeeEmail(employee.getEmail());
		prenotation.setEmployee(employee);
		
		LocalDate date = LocalDate.now();
		date = LocalDate.parse(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		date = date.plusDays(7);
		Calendar calendar = Calendar.getInstance();
		TimeZone timeZone = calendar.getTimeZone();
		ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
		prenotationDate = Date.from(date.atStartOfDay().atZone(zoneId).toInstant());
		calendar.setTime(prenotationDate);
		calendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		year = calendar.get(Calendar.YEAR);
		
		prenotation.setCalendarWeek(calendarWeek);
		prenotation.setId(prenotationPk);
		prenotation.setYear(year);
	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(prenotation));
			entityManager.remove(entityManager.merge(employee));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Test
	void createTest() {
		SmartWorkingPrenotation created;
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
	
	@Test
	void removeTest() {
		List<SmartWorkingPrenotation> list;
		boolean check = true;
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(prenotation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		prenotation.getId().setId(1);
		prenotationJpa.remove(prenotation);
		list = prenotationJpa.retrieveAll();
		if(!list.isEmpty() || list != null) {
			for(SmartWorkingPrenotation aPrenotation : list) {
				if(aPrenotation.getId().getId() == 1) check = false;
			}
		}
		assertTrue(check);
	}
	
	@Test
	void retrieveByWeeklyPlanningTest() {
		SmartWorkingPrenotation retrieved;
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(prenotation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		retrieved = prenotationJpa.retrieveByWeeklyPlanning(prenotation.getCalendarWeek(), prenotation.getYear(), employee.getEmail());
		assertEquals(retrieved.getCalendarWeek(), prenotation.getCalendarWeek());
		
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
	void retrieveByEmployeeTest() {
		List<SmartWorkingPrenotation> list;
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
		for(SmartWorkingPrenotation aPrenotation : list) {
			if(!aPrenotation.getEmployee().getEmail().equals(employee.getEmail())) check = false;
		}
		
		assertTrue(check);
		
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
		List<SmartWorkingPrenotation> list;
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
		for(SmartWorkingPrenotation aPrenotation : list) {
			if(aPrenotation.getId().getId() == prenotation.getId().getId()) check = true;
		}
		assertTrue(check);
		
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
