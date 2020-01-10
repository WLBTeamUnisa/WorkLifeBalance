package it.unisa.wlb.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
import it.unisa.wlb.model.bean.Message;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.PrenotationDatePK;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.SmartWorkingPrenotationPK;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.jpa.PrenotationDateJpa;

/**
 * The aim of this class is testing PrenotationDate.java
 * 
 * @author Sabato Nocera
 *
 */
class PrenotationDateJpaTest {
	
	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalanceTest");
	private EntityManager entityManager;
	private PrenotationDate prenotationDate;
	private PrenotationDate prenotationDate2;
	private PrenotationDateJpa prenotationDateJpa;

	@BeforeEach
	void setUp() throws Exception {
		prenotationDateJpa = new PrenotationDateJpa();
		prenotationDate = new PrenotationDate();
		prenotationDate2 = new PrenotationDate();
		
		Calendar localCalendar = Calendar.getInstance();
		TimeZone timeZone = localCalendar.getTimeZone();
		ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
		LocalDate today = LocalDateTime.ofInstant(localCalendar.toInstant(), zoneId).toLocalDate();
		localCalendar.setTime(Date.from(today.atStartOfDay().atZone(zoneId).toInstant()));
		
		/**
		 * prenotationDate
		 */
		Date date = new Date();
		Date realDate = new Date(date.getYear(), date.getMonth(), date.getDay());
		String employeeEmail = "g.verdana12@wlb.it";
		int idPrenotationSmartWorking = 1;
		PrenotationDatePK id = new PrenotationDatePK();
		id.setDate(realDate);
		id.setEmployeeEmail(employeeEmail);
		id.setIdPrenotationSw(idPrenotationSmartWorking);
		prenotationDate.setId(id);
		
		SmartWorkingPrenotation smartWorkingPrenotation = new SmartWorkingPrenotation();
		int calendarWeek = localCalendar.get(Calendar.WEEK_OF_YEAR);
		int year=localCalendar.get(Calendar.YEAR);
		smartWorkingPrenotation.setCalendarWeek(calendarWeek);
		Employee employee = new Employee();
		employee.setEmail(employeeEmail);
		employee.setMessages(new ArrayList<Message>());
		String name="Giuseppe";
		employee.setName(name);
		String password = "Giuseppe1234.";
		employee.setPassword(password);
		employee.setProjects1(new ArrayList<Project>());
		employee.setProjects2(new ArrayList<Project>());
		List<SmartWorkingPrenotation> smartWorkingPrenotations = new ArrayList<SmartWorkingPrenotation>();
		smartWorkingPrenotations.add(smartWorkingPrenotation);
		employee.setSmartWorkingPrenotations(smartWorkingPrenotations);
		int status = 0;
		employee.setStatus(status);
		String surname = "Verdana";
		employee.setSurname(surname);
		employee.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
		smartWorkingPrenotation.setEmployee(employee);
		SmartWorkingPrenotationPK smartWorkingPrenotationPK = new SmartWorkingPrenotationPK();
		smartWorkingPrenotationPK.setEmployeeEmail(employeeEmail);
		smartWorkingPrenotationPK.setId(1);
		smartWorkingPrenotation.setId(smartWorkingPrenotationPK);
		List<PrenotationDate> prenotationDates = new ArrayList<PrenotationDate>();
		prenotationDates.add(prenotationDate);
		smartWorkingPrenotation.setPrenotationDates(prenotationDates);
		smartWorkingPrenotation.setYear(year);
		prenotationDate.setSmartWorkingPrenotation(smartWorkingPrenotation);
		
		/**
		 * prenotationDate2
		 */
		Date realDate2 = new Date(date.getYear(), date.getMonth(), date.getDay());
		String employeeEmail2 = "g.verdana72@wlb.it";
		int idPrenotationSmartWorking2 = 2;
		PrenotationDatePK id2 = new PrenotationDatePK();
		id2.setDate(realDate2);
		id2.setEmployeeEmail(employeeEmail2);
		id2.setIdPrenotationSw(idPrenotationSmartWorking2);
		prenotationDate2.setId(id2);
		
		SmartWorkingPrenotation smartWorkingPrenotation2 = new SmartWorkingPrenotation();
		int calendarWeek2 = localCalendar.get(Calendar.WEEK_OF_YEAR)+1;
		int year2=localCalendar.get(Calendar.YEAR)+1;
		smartWorkingPrenotation2.setCalendarWeek(calendarWeek2);
		Employee employee2 = new Employee();
		employee2.setEmail(employeeEmail2);
		employee2.setMessages(new ArrayList<Message>());
		String name2="Gianmarco";
		employee2.setName(name2);
		String password2 = "Gianmarco1234.";
		employee2.setPassword(password2);
		employee2.setProjects1(new ArrayList<Project>());
		employee2.setProjects2(new ArrayList<Project>());
		List<SmartWorkingPrenotation> smartWorkingPrenotations2 = new ArrayList<SmartWorkingPrenotation>();
		smartWorkingPrenotations2.add(smartWorkingPrenotation2);
		employee2.setSmartWorkingPrenotations(smartWorkingPrenotations2);
		int status2 = 0;
		employee2.setStatus(status2);
		String surname2 = "Verdana";
		employee2.setSurname(surname2);
		employee2.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
		smartWorkingPrenotation2.setEmployee(employee2);
		SmartWorkingPrenotationPK smartWorkingPrenotationPK2 = new SmartWorkingPrenotationPK();
		smartWorkingPrenotationPK2.setEmployeeEmail(employeeEmail2);
		smartWorkingPrenotationPK2.setId(2);
		smartWorkingPrenotation2.setId(smartWorkingPrenotationPK2);
		List<PrenotationDate> prenotationDates2 = new ArrayList<PrenotationDate>();
		prenotationDates2.add(prenotationDate2);
		smartWorkingPrenotation2.setPrenotationDates(prenotationDates2);
		smartWorkingPrenotation2.setYear(year2);
		prenotationDate2.setSmartWorkingPrenotation(smartWorkingPrenotation2);
				
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(employee);
			entityManager.persist(smartWorkingPrenotation);
			entityManager.persist(prenotationDate);
			entityManager.persist(employee2);
			entityManager.persist(smartWorkingPrenotation2);
			entityManager.persist(prenotationDate2);
			entityManager.getTransaction().commit();
		}
		finally {
			entityManager.close();
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		SmartWorkingPrenotation smartWorkingPrenotation2 = prenotationDate2.getSmartWorkingPrenotation();
		SmartWorkingPrenotation smartWorkingPrenotation = prenotationDate.getSmartWorkingPrenotation();
		Employee employee2 = prenotationDate2.getSmartWorkingPrenotation().getEmployee();
		Employee employee = prenotationDate.getSmartWorkingPrenotation().getEmployee();
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(prenotationDate2));
			entityManager.remove(entityManager.merge(prenotationDate));
			entityManager.remove(entityManager.merge(smartWorkingPrenotation2));
			entityManager.remove(entityManager.merge(smartWorkingPrenotation));
			entityManager.remove(entityManager.merge(employee2));
			entityManager.remove(entityManager.merge(employee));
			entityManager.getTransaction().commit();
		}
		finally {
			entityManager.close();
		}
	}

	@Test
	final void testCreate() {		
		prenotationDateJpa.remove(prenotationDate);
		
		PrenotationDate aPrenotationDate = prenotationDateJpa.create(prenotationDate);
		assertEquals(prenotationDate, aPrenotationDate);

	}
	
	@Test
	final void testRetrieveAll() {
		boolean check1 = false;
		boolean check2 = false;
		List<PrenotationDate> list = prenotationDateJpa.retrieveAll();	
		for(PrenotationDate p : list) {
			if(p.getId().getDate().getTime()==prenotationDate.getId().getDate().getTime()
					&& p.getId().getEmployeeEmail().equals(prenotationDate.getId().getEmployeeEmail())
					&& p.getId().getIdPrenotationSw()==prenotationDate.getId().getIdPrenotationSw())
				check1 = true;
			
			if(p.getId().getDate().getTime()==prenotationDate2.getId().getDate().getTime()
					&& p.getId().getEmployeeEmail().equals(prenotationDate2.getId().getEmployeeEmail())
					&& p.getId().getIdPrenotationSw()==prenotationDate2.getId().getIdPrenotationSw())
				check2 = true;
		}
		assertTrue(check1 && check2);
	}

	@Test
	final void testRemove() {
		boolean check1 = false;
		List<PrenotationDate> list = prenotationDateJpa.retrieveAll();	
		for(PrenotationDate p : list) {
			if(p.getId().getDate().getTime()==prenotationDate.getId().getDate().getTime()
					&& p.getId().getEmployeeEmail().equals(prenotationDate.getId().getEmployeeEmail())
					&& p.getId().getIdPrenotationSw()==prenotationDate.getId().getIdPrenotationSw())
				check1 = true;
			
		}
		if(!check1) {
			assertTrue(false);
			return ;
		}
		
		prenotationDateJpa.remove(prenotationDate);
				
		check1 = false;
		list = prenotationDateJpa.retrieveAll();	
		for(PrenotationDate p : list) {
			System.err.println(p);
			if(p.getId().getDate().getTime()==prenotationDate.getId().getDate().getTime()
					&& p.getId().getEmployeeEmail().equals(prenotationDate.getId().getEmployeeEmail())
					&& p.getId().getIdPrenotationSw()==prenotationDate.getId().getIdPrenotationSw())
				check1 = true;
			
		}
		
		if(check1)
			assertTrue(false);
		else
			assertTrue(true);		
	}

	@Test
	final void testUpdate() {
		prenotationDate.getId().setEmployeeEmail("g.verdana72@wlb.it");
		PrenotationDate modifyied = prenotationDateJpa.update(prenotationDate);
		assertEquals(prenotationDate.getId().getEmployeeEmail(), modifyied.getId().getEmployeeEmail());
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.createNativeQuery("delete from prenotation_date where DATE=? and EMPLOYEE_EMAIL=? and ID_PRENOTATION_SW=?").setParameter(1, modifyied.getId().getDate()).setParameter(2, modifyied.getId().getEmployeeEmail()).setParameter(3, modifyied.getId().getIdPrenotationSw()).executeUpdate();
			entityManager.getTransaction().commit();
		}
		finally {
			entityManager.close();
		}
		prenotationDate.getId().setEmployeeEmail("g.verdana12@wlb.it");
	}

	@Test
	final void testRetrieveBySmartWorking() {
		boolean check1 = false;
		boolean check2 = false;
		List<PrenotationDate> list = prenotationDateJpa.retrieveBySmartWorking(prenotationDate.getId().getIdPrenotationSw(), prenotationDate.getId().getEmployeeEmail());
		
		for(PrenotationDate p : list) {
			if(p.getId().getDate().getTime()==prenotationDate.getId().getDate().getTime()
					&& p.getId().getEmployeeEmail().equals(prenotationDate.getId().getEmployeeEmail())
					&& p.getId().getIdPrenotationSw()==prenotationDate.getId().getIdPrenotationSw())
				check1 = true;
			
			if(!(p.getId().getDate().getTime()==prenotationDate2.getId().getDate().getTime()
					&& p.getId().getEmployeeEmail().equals(prenotationDate2.getId().getEmployeeEmail())
					&& p.getId().getIdPrenotationSw()==prenotationDate2.getId().getIdPrenotationSw()))
				check2 = true;
		}
		assertTrue(check1 && check2);
	}

}
