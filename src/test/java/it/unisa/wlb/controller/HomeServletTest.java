package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.unisa.wlb.controller.HomeServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.PrenotationDatePK;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.SmartWorkingPrenotationPK;
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPK;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotationPK;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDao;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;
import it.unisa.wlb.utils.Utils;

/**
 * This class tests HomeServlet.java
 * 
 * @author Vincenzo Fabiano, Sabato Nocera
 *
 */
class HomeServletTest {
	
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private RequestDispatcher dispatcher;
	@Mock
	private ISmartWorkingPrenotationDao smartWorkingDao;
	@Mock
	private IWorkstationPrenotationDao workstationPrenotationDao;
	
	private HomeServlet servlet;
	private Employee employee;
	private String email;
	private String name;
	private String surname;
	private int status;
	private String password;
	private String generatedPassword;
	private SmartWorkingPrenotation smartWorkingPrenotation;
	private SmartWorkingPrenotationPK smartWorkingPrenotationPk;
	private WorkstationPrenotation workstationPrenotation;
	private WorkstationPrenotationPK workstationPrenotationPk;
	private int calendarWeek;
	private int year;
	private int idSmartWorking;
	private List<PrenotationDate> prenotationDateList;
	private PrenotationDate date1;
	private PrenotationDate date2;
	private PrenotationDatePK datePk1;
	private PrenotationDatePK datePk2;
	private Floor floor;
	private Room room;
	private Workstation workstation;
	private RoomPK roomPk;
	private WorkstationPK workstationPk;
	private List<WorkstationPrenotation> workstationPrenotationList;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		email = "m.rossi1@wlb.it";
		password = "Ciao1234.";
		name = "Vincenzo";
		surname = "Fabiano";
		status = 0;
		generatedPassword = Utils.generatePwd(password);
		employee = new Employee();
		employee.setEmail(email);
		employee.setName(name);
		employee.setSurname(surname);
		employee.setPassword(generatedPassword);
		employee.setStatus(status);
		
		Calendar calendar = Calendar.getInstance();
		TimeZone timeZone = calendar.getTimeZone();
		ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
		LocalDate today = LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
		LocalDate monday = today.with(DayOfWeek.MONDAY);
		LocalDate friday = monday.plusDays(4);
		calendar.setTime(Date.from(friday.atStartOfDay().atZone(zoneId).toInstant()));
		calendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		year = calendar.get(Calendar.YEAR);
		
		smartWorkingPrenotationPk = new SmartWorkingPrenotationPK();
		smartWorkingPrenotationPk.setEmployeeEmail(email);
		idSmartWorking = 1;
		smartWorkingPrenotationPk.setId(idSmartWorking);
		smartWorkingPrenotation = new SmartWorkingPrenotation();
		smartWorkingPrenotation.setCalendarWeek(calendarWeek);
		smartWorkingPrenotation.setYear(year);
		smartWorkingPrenotation.setEmployee(employee);
		smartWorkingPrenotation.setId(smartWorkingPrenotationPk);
		
		datePk1 = new PrenotationDatePK();
		datePk1.setEmployeeEmail(email);
		datePk1.setIdPrenotationSw(idSmartWorking);
		datePk1.setDate(Date.from(monday.plusDays(2).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		
		datePk2 = new PrenotationDatePK();
		datePk2.setEmployeeEmail(email);
		datePk2.setIdPrenotationSw(idSmartWorking);
		datePk2.setDate(Date.from(monday.plusDays(3).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		
		date1 = new PrenotationDate();
		date1.setId(datePk1);
		date1.setSmartWorkingPrenotation(smartWorkingPrenotation);
		
		date2 = new PrenotationDate();
		date2.setId(datePk2);
		date2.setSmartWorkingPrenotation(smartWorkingPrenotation);
		
		prenotationDateList = new ArrayList<PrenotationDate>();
		prenotationDateList.add(date1);
		prenotationDateList.add(date2);
		
		smartWorkingPrenotation.setPrenotationDates(prenotationDateList);
		
		int floorNumber = 2;
		floor = new Floor();
		floor.setNumFloor(floorNumber);
		
		int roomNumber = 3;
		roomPk = new RoomPK();
		roomPk.setNumFloor(floorNumber);
		roomPk.setNumRoom(roomNumber);
		
		room = new Room();
		room.setFloor(floor);
		room.setId(roomPk);
		
		int workstationNumber = 7;
		workstationPk = new WorkstationPK();
		workstationPk.setFloor(floorNumber);
		workstationPk.setRoom(roomNumber);
		workstationPk.setWorkstation(workstationNumber);
		
		workstation = new Workstation();
		workstation.setId(workstationPk);
		workstation.setRoom(room);
		
		workstationPrenotationPk = new WorkstationPrenotationPK();
		workstationPrenotationPk.setEmailEmployee(email);
		workstationPrenotationPk.setPrenotationDate(Date.from(monday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		
		workstationPrenotation = new WorkstationPrenotation();
		workstationPrenotation.setEmployee(employee);
		workstationPrenotation.setId(workstationPrenotationPk);
		workstationPrenotation.setCalendarWeek(calendarWeek);
		workstationPrenotation.setYear(year);
		workstationPrenotation.setWorkstation(workstation);
		
		workstationPrenotationList = new ArrayList<>();
		workstationPrenotationList.add(workstationPrenotation);
		
		servlet = new HomeServlet();
		
	}
	
	@Test
	void homeServletFail() throws ServletException, IOException {
		String path = "WEB-INF/Index.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	@Test
	void homeServletSuccess() throws ServletException, IOException {
		String path = "WEB-INF/Homepage.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(session.getAttribute("userRole")).thenReturn(null);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		when(smartWorkingDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenReturn(smartWorkingPrenotation);
		when(workstationPrenotationDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenReturn(workstationPrenotationList);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		servlet.setSmartWorkingDao(smartWorkingDao);
		servlet.setWorkstationPrenotationDao(workstationPrenotationDao);
		
		
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	@Test
	void notNullUserRole() throws ServletException, IOException {
		String path = "WEB-INF/Homepage.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(session.getAttribute("userRole")).thenReturn("Manager");
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		when(smartWorkingDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenReturn(smartWorkingPrenotation);
		when(workstationPrenotationDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenReturn(workstationPrenotationList);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		servlet.setSmartWorkingDao(smartWorkingDao);
		servlet.setWorkstationPrenotationDao(workstationPrenotationDao);
		
		
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	@Test
	void nullPrenotationDates() throws ServletException, IOException {
		smartWorkingPrenotation.setPrenotationDates(null);
		String path = "WEB-INF/Homepage.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(session.getAttribute("userRole")).thenReturn(null);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		when(smartWorkingDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenReturn(smartWorkingPrenotation);
		when(workstationPrenotationDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenReturn(workstationPrenotationList);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		servlet.setSmartWorkingDao(smartWorkingDao);
		servlet.setWorkstationPrenotationDao(workstationPrenotationDao);
		
		
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	@Test
	void notNullPrenotationDates() throws ServletException, IOException {
		String path = "WEB-INF/Homepage.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(session.getAttribute("userRole")).thenReturn(null);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		when(smartWorkingDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenReturn(smartWorkingPrenotation);
		when(workstationPrenotationDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenReturn(workstationPrenotationList);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		servlet.setSmartWorkingDao(smartWorkingDao);
		servlet.setWorkstationPrenotationDao(workstationPrenotationDao);
		
		
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	@Test
	void nullWorkstationPrenotations() throws ServletException, IOException {
		String path = "WEB-INF/Homepage.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(session.getAttribute("userRole")).thenReturn(null);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		when(smartWorkingDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenReturn(smartWorkingPrenotation);
		when(workstationPrenotationDao.retrieveByWeeklyPlanning(calendarWeek, year, email)).thenReturn(null);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		servlet.setSmartWorkingDao(smartWorkingDao);
		servlet.setWorkstationPrenotationDao(workstationPrenotationDao);
		
		
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	
}
