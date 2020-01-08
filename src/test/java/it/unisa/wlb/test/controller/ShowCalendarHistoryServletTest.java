package it.unisa.wlb.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import antlr.collections.List;
import it.unisa.wlb.controller.ShowCalendarHistoryServlet;
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
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IPrenotationDateDAO;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;


public class ShowCalendarHistoryServletTest {
	
	@Mock
	private IEmployeeDAO employeeDAO;
	@Mock
	private ISmartWorkingPrenotationDAO smartWorkingPrenotationDAO;
	@Mock
	private IPrenotationDateDAO prenotationDateDAO;
	@Mock
	private IWorkstationPrenotationDao workstationPrenotationDao;
	
	private MockHttpServletRequest request;
	
	private MockHttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private RequestDispatcher dispatcher;
	
	private ShowCalendarHistoryServlet servlet;
	
	private Employee employee;
	private String name;
	private String surname;
	private String email;
	private String password;
	private int status;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		servlet = new ShowCalendarHistoryServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		employee = new Employee();
		
		name="Marco";
		surname="Rossi";
		email="m.rossi1@wlb.it";
		password="Ciao1234.";
		status=0;
		
		employee.setEmail(email);
		employee.setName(name);
		employee.setSurname(surname);
		employee.setPassword(password);
		employee.setStatus(status);
		
		//add SmartWorkingPrenotation to employee
		SmartWorkingPrenotation smartWorking = new SmartWorkingPrenotation();
		SmartWorkingPrenotationPK smartWorkingPk = new SmartWorkingPrenotationPK();
		smartWorkingPk.setEmployeeEmail(employee.getEmail());
		smartWorking.setCalendarWeek(1);
		smartWorkingPk.setId(1);
		smartWorking.setYear(2020);
		smartWorking.setEmployee(employee);
		smartWorking.setId(smartWorkingPk);
		ArrayList<SmartWorkingPrenotation> smartWorkingPrenotations= new ArrayList<>();
		smartWorkingPrenotations.add(smartWorking);
		
		PrenotationDate prenotationDate = new PrenotationDate();
		PrenotationDatePK prenotationDatePk = new PrenotationDatePK();
		prenotationDate.setSmartWorkingPrenotation(smartWorking);
		
		LocalDate localDate = LocalDate.parse("2020-01-01");
		prenotationDatePk.setDate(new Date());
		
		prenotationDatePk.setEmployeeEmail(employee.getEmail());
		prenotationDatePk.setIdPrenotationSw(smartWorking.getId().getId());
		prenotationDate.setId(prenotationDatePk);
		ArrayList<PrenotationDate> prenotationDates= new ArrayList<>();
		prenotationDates.add(prenotationDate);
		smartWorking.setPrenotationDates(prenotationDates);
		//System.out.println(smartWorkingPrenotations.get(0).getPrenotationDates().get(0).getId().getDate());
		employee.setSmartWorkingPrenotations(smartWorkingPrenotations);
		
		
		//add workstationPrenotation to employee
	
		int existingFloor = 6;
		int existingRoom = 8;
		int existingWorkstation = 27;
		String datePrenotation = "2019-11-25";		

		Floor floor = new Floor();
		floor.setNumFloor(existingFloor);	

		Room room = new Room();
		room.setFloor(floor);
		RoomPK roomPk = new RoomPK();
		roomPk.setNumFloor(floor.getNumFloor());
		roomPk.setNumRoom(existingRoom);
		room.setId(roomPk);

		Workstation workstation = new Workstation();
		WorkstationPK workstationPK = new WorkstationPK();
		workstationPK.setFloor(room.getId().getNumFloor());
		workstationPK.setRoom(room.getId().getNumRoom());
		workstationPK.setWorkstation(existingWorkstation);
		workstation.setId(workstationPK);
		workstation.setRoom(room);

	
		Date workstationPrenotationDate = new Date();
		int calendarWeek = 10;
		
		int year = 2020;
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee(employee.getEmail());
		workstationPrenotationPK.setPrenotationDate(workstationPrenotationDate);

		WorkstationPrenotation workstationPrenotation = new WorkstationPrenotation();

		workstationPrenotation.setCalendarWeek(calendarWeek);
		workstationPrenotation.setEmployee(employee);
		workstationPrenotation.setId(workstationPrenotationPK);
		workstationPrenotation.setWorkstation(workstation);
		workstationPrenotation.setYear(year);
		
		
		ArrayList<WorkstationPrenotation> workstationPrenotationList= new ArrayList<>();
		workstationPrenotationList.add(workstationPrenotation);
		
		employee.setWorkstationPrenotations(workstationPrenotationList);
		
		
		//employee.setWorkstationPrenotations(null);
	}
	
	@Test
	void test() throws ServletException, IOException {
		Date actualDate= new Date();
		String string="[{\"date\":\""+actualDate+"\",\"type\":\"Smartworking\"},{\"date\":\""+actualDate+"\",\"workstation\":27,\"type\":\"Workstation\",\"floor\":6,\"room\":8}]";
		request.getSession().setAttribute("user", employee);
		//when(request.getSession()).thenReturn(session);
		request.setParameter("employeeEmail", email);
		request.setParameter("month","1");
		request.setParameter("year", "2020");
		request.setParameter("year", "2020");
	
		when(employeeDAO.retrieveByEmail(email)).thenReturn(employee);
		servlet.setEmployeeDAO(employeeDAO);
		servlet.doPost(request, response);
		assertEquals(string, response.getContentAsString());
		
	}
	
	//funziona
	@Test
	void retriveFail() throws ServletException, IOException {
		request.getSession().setAttribute("user", employee);
		//when(employeeDAO.retrieveByEmail(email)).thenReturn(employee);
		request.setParameter("employeeEmail", email);
		request.setParameter("month","12");
		request.setParameter("year", "2020");
		request.setParameter("year", "2020");
	
		//when(employeeDAO.retrieveByEmail(email)).thenReturn(f);
		servlet.setEmployeeDAO(employeeDAO);
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(attribute, "error");
		
	}
	//funziona
	@Test
	void employeeSessionNull() throws ServletException, IOException {
		//request.getSession().setAttribute("user", employee);
		//when(request.getSession()).thenReturn(session);
	//	request.setParameter("employeeEmail", "ciao");
	
		request.setParameter("month","12");
		request.setParameter("year", "2020");
		request.setParameter("year", "2020");
		
		servlet.setEmployeeDAO(employeeDAO);
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("result");
		assertEquals(attribute, "error");
		
	}
	
	
	//funziona
	@Test
	void employeeStringNull() throws ServletException, IOException {
		
		
		Date actualDate= new Date();
		String string="[{\"date\":\""+actualDate+"\",\"type\":\"Smartworking\"},{\"date\":\""+actualDate+"\",\"workstation\":27,\"type\":\"Workstation\",\"floor\":6,\"room\":8}]";
		request.getSession().setAttribute("user", employee);
		
		//request.setParameter("employeeEmail", "ciao");
		request.setParameter("month","1");
		request.setParameter("year", "2020");
		when(employeeDAO.retrieveByEmail(email)).thenReturn(employee);
		
		servlet.setEmployeeDAO(employeeDAO);
		servlet.doPost(request, response);


		assertEquals(string, response.getContentAsString());
		
	
	}

}
