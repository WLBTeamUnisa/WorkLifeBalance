package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import it.unisa.wlb.controller.SmartWorkingDaysPrenotationServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.SmartWorkingPrenotationPK;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;
import it.unisa.wlb.utils.Utils;
/**
 * The aim of this class is testing the case with zero prenotation dates
 * 
 * @author Vincenzo Fabiano
 *
 */
class SmartWorkingPrenotationZeroDaysTest {

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

	@Test
	void test() throws ServletException, IOException {
		Calendar CALENDAR = Calendar.getInstance();
		TimeZone timeZone = CALENDAR.getTimeZone();
		ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
		LocalDate today = LocalDateTime.ofInstant(CALENDAR.toInstant(), zoneId).toLocalDate();
		LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
		LocalDate newDate;
		newDate= nextMonday.plusDays(7);
		CALENDAR.setTime(Date.from(newDate.atStartOfDay().atZone(zoneId).toInstant()));
		int nextCalendarWeek = CALENDAR.get(Calendar.WEEK_OF_YEAR);
		int year = CALENDAR.get(Calendar.YEAR);
		int idSmartWorking = 1;
		ISmartWorkingPrenotationDAO smartWorkingDao = mock(ISmartWorkingPrenotationDAO.class);
		SmartWorkingPrenotation smartWorking = new SmartWorkingPrenotation();
		SmartWorkingPrenotationPK smartWorkingPk = new SmartWorkingPrenotationPK();
		smartWorkingPk.setEmployeeEmail(employee.getEmail());
		smartWorking.setCalendarWeek(nextCalendarWeek);
		smartWorkingPk.setId(idSmartWorking);
		smartWorking.setYear(year);
		smartWorking.setEmployee(employee);
		smartWorking.setId(smartWorkingPk);
		when(smartWorkingDao.create(smartWorking)).thenReturn(smartWorking);
		servlet.setSmartWorkingPrenotationDao(smartWorkingDao);
		try {
			servlet.doPost(request, response);
		} catch (Exception e) {
			;
		} finally {
			String attribute = (String) request.getAttribute("result");
			assertEquals("success",attribute);
		}
	}

}
