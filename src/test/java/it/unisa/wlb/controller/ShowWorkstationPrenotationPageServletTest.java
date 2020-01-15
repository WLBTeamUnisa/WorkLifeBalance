package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Date;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.unisa.wlb.controller.ShowWorkstationPrenotationPageServlet;
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
import it.unisa.wlb.model.dao.IPrenotationDateDao;
import it.unisa.wlb.model.dao.IRoomDao;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDao;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;
import it.unisa.wlb.utils.Utils;

/**
 * The aim of this class is testing ShowWorkstationPrenotationPageServlet.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class ShowWorkstationPrenotationPageServletTest {

	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpServletResponse response;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private RequestDispatcher dispatcher;
	
	@Mock
	private IRoomDao roomDao;
	
	@Mock
	private ISmartWorkingPrenotationDao smartWorkingDao;

	@Mock
	private IPrenotationDateDao prenotationDateDao;

	@Mock
	private IWorkstationPrenotationDao workstationPrenotationDao;
	private MockHttpServletResponse responseMock;
	private ShowWorkstationPrenotationPageServlet servlet;

	private Employee employee;
	private String name;
	private String surname;
	private String email;
	private int status;
	private String password;
	private String generatedPassword;
	private List<LocalDate> listDates;
	private int nextCalendarWeek;
	private int year;
	private int id;
	private SmartWorkingPrenotation smartWorkingPrenotation;
	private SmartWorkingPrenotationPK smartWorkingPrenotationPk;
	private PrenotationDate prenotationDate1;
	private PrenotationDate prenotationDate2;
	private PrenotationDatePK prenotationDatePk1;
	private PrenotationDatePK prenotationDatePk2;
	private List<PrenotationDate> prenotationDates;
	private Floor floor;
	private Room room;
	private Workstation workstation;
	private RoomPK roomPk;
	private WorkstationPK workstationPk;
	private List<WorkstationPrenotation> workstationPrenotationList;
	private WorkstationPrenotation workstationPrenotation;
	private WorkstationPrenotationPK workstationPrenotationPk;
	private List<Workstation> workstationList;
	private List<Room> roomList;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		responseMock = new MockHttpServletResponse();
		servlet = new ShowWorkstationPrenotationPageServlet();
		smartWorkingPrenotation = new SmartWorkingPrenotation();
		smartWorkingPrenotationPk = new SmartWorkingPrenotationPK();
		prenotationDates = new ArrayList<PrenotationDate>();
		prenotationDate1 = new PrenotationDate();
		prenotationDate2 = new PrenotationDate();
		prenotationDatePk1 = new PrenotationDatePK();
		prenotationDatePk2 = new PrenotationDatePK();
		employee = new Employee();
		name = "Marco";
		surname = "Rossi";
		email = "m.rossi1@wlb.it";
		status = 0;
		password = "Ciao1234.";
		generatedPassword = Utils.generatePwd(password);
		id = 1;
		
		employee.setName(name);
		employee.setSurname(surname);
		employee.setEmail(email);
		employee.setStatus(status);
		employee.setPassword(generatedPassword);
		
		Calendar calendar = Calendar.getInstance();
		TimeZone timeZone = calendar.getTimeZone();
		ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
		LocalDate today = LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
		LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
		LocalDate newDate = nextMonday.plusDays(7);
		calendar.setTime(Date.from(newDate.atStartOfDay().atZone(zoneId).toInstant()));
		nextCalendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		year = calendar.get(Calendar.YEAR);
		listDates = new ArrayList<>();
		listDates.add(newDate);
		listDates.add(newDate.plusDays(1));
		listDates.add(newDate.plusDays(2));
		listDates.add(newDate.plusDays(3));
		listDates.add(newDate.plusDays(4));
		
		smartWorkingPrenotationPk.setId(id);
		smartWorkingPrenotationPk.setEmployeeEmail(email);
		smartWorkingPrenotation.setCalendarWeek(nextCalendarWeek);
		smartWorkingPrenotation.setEmployee(employee);
		smartWorkingPrenotation.setId(smartWorkingPrenotationPk);
		smartWorkingPrenotation.setYear(year);
		
		Date date1 = (Date) Date.from(listDates.get(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date date2 = (Date) Date.from(listDates.get(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		prenotationDatePk1.setDate(date1);
		prenotationDatePk1.setEmployeeEmail(email);
		prenotationDatePk1.setIdPrenotationSw(id);
		
		prenotationDatePk2.setDate(date2);
		prenotationDatePk2.setEmployeeEmail(email);
		prenotationDatePk2.setIdPrenotationSw(id);
		
		prenotationDate1.setId(prenotationDatePk1);
		prenotationDate2.setId(prenotationDatePk2);
		
		prenotationDate1.setSmartWorkingPrenotation(smartWorkingPrenotation);
		prenotationDate2.setSmartWorkingPrenotation(smartWorkingPrenotation);
		
		prenotationDates.add(prenotationDate1);
		prenotationDates.add(prenotationDate2);
		smartWorkingPrenotation.setPrenotationDates(prenotationDates);
		
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
		
		workstationList = new ArrayList<>();
		workstationList.add(workstation);
		room.setWorkstations(workstationList);
		
		roomList = new ArrayList<>();
		roomList.add(room);
		
		workstationPrenotationPk = new WorkstationPrenotationPK();
		workstationPrenotationPk.setEmailEmployee(email);
		workstationPrenotationPk.setPrenotationDate(Date.from(nextMonday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		
		workstationPrenotation = new WorkstationPrenotation();
		workstationPrenotation.setEmployee(employee);
		workstationPrenotation.setId(workstationPrenotationPk);
		workstationPrenotation.setCalendarWeek(nextCalendarWeek);
		workstationPrenotation.setYear(year);
		workstationPrenotation.setWorkstation(workstation);
		
		workstationPrenotationList = new ArrayList<>();
		workstationPrenotationList.add(workstationPrenotation);
		
		
	}
	
	@Test
	void yesSmartWorkingPrenotationAndWorkstationPrenotation() throws ServletException, IOException {
		String path = "WEB-INF/WorkstationPrenotation.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		when(smartWorkingDao.retrieveByWeeklyPlanning(nextCalendarWeek, year, email)).thenReturn(smartWorkingPrenotation);
		when(workstationPrenotationDao.retrieveByWeeklyPlanning(nextCalendarWeek, year, email)).thenReturn(workstationPrenotationList);
		when(roomDao.retrieveAll()).thenReturn(roomList);
		servlet.setSmartWorkingDao(smartWorkingDao);
		servlet.setRoomDao(roomDao);
		servlet.setWorkstationPrenotationDao(workstationPrenotationDao);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	@Test
	void userNull() throws ServletException, IOException {
		String path = "WEB-INF/Index.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void noSmartWorkingPrenotation() throws ServletException, IOException {
		String path = "/ShowSmartWorkingPrenotation";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		when(smartWorkingDao.retrieveByWeeklyPlanning(nextCalendarWeek, year, email)).thenThrow(Exception.class);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		servlet.setSmartWorkingDao(smartWorkingDao);
		try {
			servlet.doPost(request, response);
		} catch(Exception e) {
			;
		} finally {
			verify(request).getRequestDispatcher(captor.capture());
			assertEquals(path, captor.getValue());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void noPlanimetry() throws ServletException, IOException {
		String errorMessage = "Planimetria assente nel database";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(employee);
		when(request.getRequestDispatcher(errorMessage)).thenReturn(dispatcher);
		when(smartWorkingDao.retrieveByWeeklyPlanning(nextCalendarWeek, year, email)).thenReturn(smartWorkingPrenotation);
		when(workstationPrenotationDao.retrieveByWeeklyPlanning(nextCalendarWeek, year, email)).thenReturn(workstationPrenotationList);
		when(roomDao.retrieveAll()).thenThrow(Exception.class);
		servlet.setSmartWorkingDao(smartWorkingDao);
		servlet.setRoomDao(roomDao);
		servlet.setWorkstationPrenotationDao(workstationPrenotationDao);
		servlet.doPost(request, responseMock);
		assertEquals(errorMessage, responseMock.getContentAsString());
	}
}
