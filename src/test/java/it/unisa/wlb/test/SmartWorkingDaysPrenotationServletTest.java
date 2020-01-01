package it.unisa.wlb.test;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.SmartWorkingDaysPrenotationServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.PrenotationDatePK;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.SmartWorkingPrenotationPK;
import it.unisa.wlb.model.dao.IPrenotationDateDAO;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;
import it.unisa.wlb.utils.Utils;

/**
 * This test class follows the specification of the section "3.5.2 TC_5.2 Prenota giorni di Smart Working" of the document "Test Case Specification"
 * 
 * @author Vincenzo Fabiano
 *
 */
public class SmartWorkingDaysPrenotationServletTest extends Mockito{

	private SmartWorkingDaysPrenotationServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private Employee employee;
	
	@BeforeEach
	void setUp() throws Exception {
		servlet = new SmartWorkingDaysPrenotationServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		employee = new Employee();
		employee.setEmail("m.rossi1@wlb.it");
		employee.setName("Marco");
		employee.setSurname("Rossi");
		employee.setStatus(0);
		employee.setPassword(Utils.generatePwd("MarcoRossi1."));
		request.getSession().setAttribute("user", employee);
	}

	/**
	 * TC_5.2_1: The number of dates inserted is greater than three - FAIL
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void TC_5_2_1() throws ServletException, IOException {
		final String message = "Non puoi prenotare più di 3 date";
		String[] dates = {"2019-11-25", "2019-11-26", "2019-11-27", "2019-11-28"};
		request.setParameter("dates", dates);
		try {
			servlet.doGet(request, response);
		} catch (Exception employee) {			
			
		} finally {
			assertTrue(response.getStatus()==HttpServletResponse.SC_BAD_REQUEST && response.getContentAsString().toString().contains(message));
		}	
	}
	
	/**
	 * TC_5.2_2: Dates don't respect the format - FAIL
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void TC_5_2_2() throws ServletException, IOException{
		final String message = "formatError";
		String[] dates = {"22-11-2019"};
		request.setParameter("dates", dates);
		try {
			servlet.doGet(request, response);
		} catch (Exception employee) {
			
		} finally {
			String attribute = (String) request.getAttribute("result");
			assertEquals(message, attribute);
		}
	}
	
	/**
	 * Smart Working Prenotation insertion ended with success
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void TC_5_2_3() throws ServletException, IOException {
		Calendar CALENDAR = Calendar.getInstance();
		TimeZone timeZone = CALENDAR.getTimeZone();
		ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
		LocalDate today = LocalDateTime.ofInstant(CALENDAR.toInstant(), zoneId).toLocalDate();
		LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
		LocalDate newDate;
		newDate= nextMonday.plusDays(7);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = newDate.format(formatter);
		CALENDAR.setTime(Date.from(newDate.atStartOfDay().atZone(zoneId).toInstant()));
		int nextCalendarWeek = CALENDAR.get(Calendar.WEEK_OF_YEAR);
		int year = CALENDAR.get(Calendar.YEAR);
		String[] dates = {date};
		request.setParameter("dates", dates);
		int idSmartWorking = 1;
		ISmartWorkingPrenotationDAO smartWorkingDao = mock(ISmartWorkingPrenotationDAO.class);
		IPrenotationDateDAO prenotationDateDao = mock(IPrenotationDateDAO.class);
		SmartWorkingPrenotation smartWorking = new SmartWorkingPrenotation();
		SmartWorkingPrenotationPK smartWorkingPk = new SmartWorkingPrenotationPK();
		smartWorkingPk.setEmployeeEmail(employee.getEmail());
		smartWorking.setCalendarWeek(nextCalendarWeek);
		smartWorkingPk.setId(idSmartWorking);
		smartWorking.setYear(year);
		smartWorking.setEmployee(employee);
		smartWorking.setId(smartWorkingPk);
		when(smartWorkingDao.create(smartWorking)).thenReturn(smartWorking);
		when(smartWorkingDao.retrieveByWeeklyPlanning(nextCalendarWeek, year, smartWorking.getEmployee().getEmail())).thenReturn(smartWorking);
		when(smartWorkingDao.update(smartWorking)).thenReturn(smartWorking);
		servlet.setSmartWorkingPrenotationDao(smartWorkingDao);
		PrenotationDate prenotationDate = new PrenotationDate();
		PrenotationDatePK prenotationDatePk = new PrenotationDatePK();
		prenotationDate.setSmartWorkingPrenotation(smartWorking);
		LocalDate localDate = LocalDate.parse("2020-01-01");
		prenotationDatePk.setDate(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		prenotationDatePk.setEmployeeEmail(employee.getEmail());
		prenotationDatePk.setIdPrenotationSw(smartWorking.getId().getId());
		when(prenotationDateDao.create(any(PrenotationDate.class))).thenReturn(prenotationDate);
		servlet.setPrenotationDateDao(prenotationDateDao);
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals("ok",attribute);
	
	}

}


