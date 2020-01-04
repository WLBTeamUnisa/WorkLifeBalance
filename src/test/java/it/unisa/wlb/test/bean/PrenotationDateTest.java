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

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.PrenotationDatePK;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.SmartWorkingPrenotationPK;

/**
 * The aim of this class is testing WorkstationPrenotation.java and WorkstationPrenotationPK.java
 * 
 * @author Sabato Nocera
 *
 */
class PrenotationDateTest {
	
	private PrenotationDate prenotationDate;
	private PrenotationDatePK id;
	private SmartWorkingPrenotation smartWorkingPrenotation;

	@BeforeEach
	void setUp() throws Exception {
		prenotationDate = new PrenotationDate();
		
		String name = "Mario";
		String surname = "Rossi";
		String password = "MarcoRossi1.";	
		
		/**
		 * PrenotationDatePK
		 */
		id = new PrenotationDatePK();
		id.setDate(new Date());
		id.setEmployeeEmail("m.rossi1@wlb.it");
		id.setIdPrenotationSw(1);		
		prenotationDate.setId(id);
		
		/**
		 * SmartWorkingPrenotation
		 */
		smartWorkingPrenotation = new SmartWorkingPrenotation();
		
		Calendar localCalendar = Calendar.getInstance();
		TimeZone timeZone = localCalendar.getTimeZone();
		ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
		LocalDate today = LocalDateTime.ofInstant(localCalendar.toInstant(), zoneId).toLocalDate();
		localCalendar.setTime(Date.from(today.atStartOfDay().atZone(zoneId).toInstant()));
		int calendarWeek = localCalendar.get(Calendar.WEEK_OF_YEAR);
		int year=localCalendar.get(Calendar.YEAR);
		
		Employee employee = new Employee();
		employee.setEmail("m.rossi1@wlb.it");
		employee.setName(name);
		employee.setPassword(password);
		employee.setStatus(0);
		employee.setSurname(surname);
		
		smartWorkingPrenotation.setCalendarWeek(calendarWeek);
		smartWorkingPrenotation.setEmployee(employee);
		SmartWorkingPrenotationPK smartWorkingPrenotationPK = new SmartWorkingPrenotationPK();
		smartWorkingPrenotationPK.setEmployeeEmail(employee.getEmail());
		smartWorkingPrenotationPK.setId(1);
		smartWorkingPrenotation.setId(smartWorkingPrenotationPK);
		List<PrenotationDate> list = new ArrayList<PrenotationDate>();
		list.add(prenotationDate);
		smartWorkingPrenotation.setPrenotationDates(list);
		smartWorkingPrenotation.setYear(year);
		
		prenotationDate.setSmartWorkingPrenotation(smartWorkingPrenotation);
	}

	
	@Test
	public void getIdTest() {
		assertEquals(prenotationDate.getId(),id);
	}
	@Test
	public void setIdTest() {
		PrenotationDatePK prenotationDatePk = new PrenotationDatePK();
		prenotationDatePk.setDate(id.getDate());
		prenotationDatePk.setEmployeeEmail(id.getEmployeeEmail());
		prenotationDatePk.setIdPrenotationSw(id.getIdPrenotationSw()+1);
		prenotationDate.setId(prenotationDatePk);
		assertEquals(prenotationDate.getId(),prenotationDatePk);
	}
	@Test
	public void getSmartWorkingPrenotationTest() {
		assertEquals(prenotationDate.getSmartWorkingPrenotation(),smartWorkingPrenotation);
	}
	@Test
	public void setSmartWorkingPrenotationTest() {
		SmartWorkingPrenotation smartWorkingPrenotation2 = new SmartWorkingPrenotation();
		smartWorkingPrenotation2.setCalendarWeek(smartWorkingPrenotation.getCalendarWeek()+1);
		smartWorkingPrenotation2.setEmployee(smartWorkingPrenotation.getEmployee());
		smartWorkingPrenotation2.setId(new SmartWorkingPrenotationPK());
		smartWorkingPrenotation2.setPrenotationDates(smartWorkingPrenotation.getPrenotationDates());
		smartWorkingPrenotation2.setYear(smartWorkingPrenotation.getYear()+1);
		prenotationDate.setSmartWorkingPrenotation(smartWorkingPrenotation2);
		assertEquals(prenotationDate.getSmartWorkingPrenotation(),smartWorkingPrenotation2);
	}	

	@Test
	public void toStringTest() {
		String string = "PrenotationDate [id=" + id + ", smartWorkingPrenotation=" + smartWorkingPrenotation + "]";
		assertEquals(string, prenotationDate.toString());
	}

	@Test
	public void equalsTest1() {
		PrenotationDate prenotationDate2 = new PrenotationDate();
		prenotationDate2.setId(prenotationDate.getId());
		prenotationDate2.setSmartWorkingPrenotation(prenotationDate.getSmartWorkingPrenotation());
		assertEquals(prenotationDate, prenotationDate2);
	}
	
	@Test
	public void equalsTest2() {
		PrenotationDate prenotationDate2 = new PrenotationDate();
		prenotationDate2.setId(new PrenotationDatePK());
		prenotationDate2.setSmartWorkingPrenotation(prenotationDate.getSmartWorkingPrenotation());
		assertNotEquals(prenotationDate, prenotationDate2);
	}
	
	@Test
	public void equalsTest3() {
		PrenotationDate prenotationDate2 = new PrenotationDate();
		prenotationDate2.setId(prenotationDate.getId());
		prenotationDate2.setSmartWorkingPrenotation(new SmartWorkingPrenotation());
		assertNotEquals(prenotationDate, prenotationDate2);
	}
	
	@Test
	public void equalsTest4() {
		PrenotationDate prenotationDate2 = new PrenotationDate();
		prenotationDate2.setId(null);
		prenotationDate2.setSmartWorkingPrenotation(prenotationDate.getSmartWorkingPrenotation());
		assertNotEquals(prenotationDate, prenotationDate2);
	}
	
	@Test
	public void equalsTest5() {
		PrenotationDate prenotationDate2 = new PrenotationDate();
		prenotationDate2.setId(prenotationDate.getId());
		prenotationDate2.setSmartWorkingPrenotation(null);
		assertNotEquals(prenotationDate, prenotationDate2);
	}
	
	@Test
	public void equalsTest6() {
		PrenotationDate prenotationDate2 = new PrenotationDate();
		prenotationDate2.setId(prenotationDate.getId());
		prenotationDate2.setSmartWorkingPrenotation(prenotationDate.getSmartWorkingPrenotation());
		prenotationDate.setId(null);
		assertNotEquals(prenotationDate, prenotationDate2);
	}
	
	@Test
	public void equalsTest7() {
		PrenotationDate prenotationDate2 = new PrenotationDate();
		prenotationDate2.setId(prenotationDate.getId());
		prenotationDate2.setSmartWorkingPrenotation(prenotationDate.getSmartWorkingPrenotation());
		prenotationDate.setSmartWorkingPrenotation(null);
		assertNotEquals(prenotationDate, prenotationDate2);
	}
	
	@Test
	public void equalsTest8() {
		String prenotationDate2 = "";
		assertNotEquals(prenotationDate, prenotationDate2);
	}
	
	@Test
	public void equalsTest9() {
		PrenotationDate prenotationDate2 = null;
		assertNotEquals(prenotationDate, prenotationDate2);
	}
	@Test
	public void equalsTest10() {
		PrenotationDate prenotationDate2 = new PrenotationDate();
		prenotationDate2.setId(prenotationDate.getId());
		prenotationDate2.setSmartWorkingPrenotation(prenotationDate.getSmartWorkingPrenotation());
		prenotationDate=null;
		assertNotEquals(prenotationDate, prenotationDate2);
	}
	
	@Test
	public void equalsTest11() {
		PrenotationDate prenotationDate2 = prenotationDate;
		assertEquals(prenotationDate, prenotationDate2);
	}
	@Test
	public void equalsTest16() {
		String idPk = "";
		assertNotEquals(id, idPk);
	}
	@Test
	public void equalsTest17() {
		PrenotationDatePK prenotationDatePK = new PrenotationDatePK();
		Date date2= new Date();
		date2.setTime(id.getDate().getTime()+10);
		prenotationDatePK.setDate(date2);
		prenotationDatePK.setEmployeeEmail(id.getEmployeeEmail());
		prenotationDatePK.setIdPrenotationSw(id.getIdPrenotationSw());
		assertNotEquals(id, prenotationDatePK);
	}
	@Test
	public void equalsTest18() {
		PrenotationDatePK prenotationDatePK = new PrenotationDatePK();
		prenotationDatePK.setDate(id.getDate());
		prenotationDatePK.setEmployeeEmail("l.esposito15@wlb.it");
		prenotationDatePK.setIdPrenotationSw(id.getIdPrenotationSw());
		assertNotEquals(id, prenotationDatePK);
	}
	@Test
	public void equalsTest19() {
		PrenotationDatePK prenotationDatePK = new PrenotationDatePK();
		prenotationDatePK.setDate(id.getDate());
		prenotationDatePK.setEmployeeEmail(id.getEmployeeEmail());
		prenotationDatePK.setIdPrenotationSw(id.getIdPrenotationSw()+1);
		assertNotEquals(id, prenotationDatePK);
	}
	@Test
	public void equalsTest20() {
		PrenotationDatePK prenotationDatePK = new PrenotationDatePK();
		prenotationDatePK.setDate(id.getDate());
		prenotationDatePK.setEmployeeEmail(id.getEmployeeEmail());
		prenotationDatePK.setIdPrenotationSw(id.getIdPrenotationSw());
		assertEquals(id, prenotationDatePK);
	}

}
