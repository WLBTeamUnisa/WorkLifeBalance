package it.unisa.wlb.test.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.wlb.controller.ShowSmartWorkingPrenotationServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.SmartWorkingPrenotationPK;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;
import it.unisa.wlb.utils.Utils;

/**
 * This class tests ShowSmartWorkingPrenotationServlet
 * 
 * @author Vincenzo Fabiano 
 *
 */
class ShowSmartWorkingPrenotationServletTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private RequestDispatcher dispatcher;
	private ShowSmartWorkingPrenotationServlet servlet;
	
	@Mock
	private ISmartWorkingPrenotationDAO smartWorkingDao;
	
	private SmartWorkingPrenotation smartWorkingPrenotation;
	private SmartWorkingPrenotationPK smartWorkingPrenotationPk;
	
	private int id;
	private int calendarWeek;
	private int year;
	private Employee employee;
	private String name;
	private String surname;
	private String email;
	private int status;
	private String password;
	private String generatedPassword;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		SmartWorkingPrenotation smartWorkingPrenotation = new SmartWorkingPrenotation();
		SmartWorkingPrenotationPK smartWorkingPrenotationPk = new SmartWorkingPrenotationPK();
		employee = new Employee();
		servlet = new ShowSmartWorkingPrenotationServlet();
		
		name = "Marco";
		surname = "Rossi";
		email = "m.rossi1@wlb.it";
		status = 0;
		password = "Ciao1234.";
		generatedPassword = Utils.generatePwd(password);
		
		employee.setName(name);
		employee.setSurname(surname);
		employee.setEmail(email);
		employee.setStatus(status);
		employee.setPassword(generatedPassword);
		
		id = 1;
		Calendar calendar = Calendar.getInstance();
		TimeZone tz = calendar.getTimeZone();
		ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
		LocalDate today = LocalDateTime.ofInstant(calendar.toInstant(), zid).toLocalDate();
	
		LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
		LocalDate newDate;
		newDate = nextMonday.plusDays(7);
		calendar.setTime(Date.from(newDate.atStartOfDay().atZone(zid).toInstant()));
		calendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		year = calendar.get(Calendar.YEAR);
		
		smartWorkingPrenotationPk.setId(id);
		smartWorkingPrenotationPk.setEmployeeEmail(email);
		smartWorkingPrenotation.setCalendarWeek(calendarWeek);
		smartWorkingPrenotation.setEmployee(employee);
		smartWorkingPrenotation.setId(smartWorkingPrenotationPk);
		smartWorkingPrenotation.setYear(year);
	}

	/**
	 * If exists a smart working prenotation for the next week
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	void noBookingTest() throws ServletException, IOException {
		String path = "WEB-INF/SmartWorkingPrenotation.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		when(request.getAttribute("booking")).thenReturn("no");
		when(smartWorkingDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenReturn(smartWorkingPrenotation);
		servlet.setSmartWorkingDao(smartWorkingDao);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	/**
	 * If doesn't exist a smart working prenotation for the next week
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@Test
	void yesBookingTest() throws ServletException, IOException {
		String path = "WEB-INF/SmartWorkingPrenotation.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		when(request.getAttribute("booking")).thenReturn("yes");
		when(smartWorkingDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenThrow(Exception.class);
		servlet.setSmartWorkingDao(smartWorkingDao);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	/**
	 * if user is null
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	void userNull() throws ServletException, IOException {
		String path = "WEB-INF/Index.jsp";
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}

}
