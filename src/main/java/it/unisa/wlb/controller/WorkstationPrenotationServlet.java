package it.unisa.wlb.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotationPK;
import it.unisa.wlb.model.dao.IFloorDao;
import it.unisa.wlb.model.dao.IRoomDao;
import it.unisa.wlb.model.dao.IWorkstationDao;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;

/**
 * The aim of this Servlet is to insert a workstation prenotation into the database
 * 
 * @author Vincenzo Fabiano, Luigi Cerrone, Sabato Nocera
 *
 */
@WebServlet("/WorkstationPrenotationServlet")
public class WorkstationPrenotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final static String JSON_STRING = "jsonObject";
	private final static String FLOOR = "floor";
	private final static String ROOM = "room";
	private final static String WORKSTATION = "workstation";
	private final static String DATE = "date";
	private final static int MIN = 1;
	
	private int maxFloor;
	private int maxRoom;
	private int maxWorkstation;
	
	@EJB
	private IFloorDao floorDao;
	
	@EJB
	private IRoomDao roomDao;
	
	@EJB
	private IWorkstationDao workstationDao;
	
	@EJB
	private IWorkstationPrenotationDao workstationPrenotationDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkstationPrenotationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = (Employee) request.getSession().getAttribute("user");
		String jsonString = request.getParameter(JSON_STRING);
		JSONObject jsonObject =  new JSONObject(jsonString);
		
		Workstation workstation = null;
		WorkstationPrenotation workstationPrenotation = null;
		
		/**
		 * Expected JSONObject: {"date":"2019-12-30", "workstation":4, "room":1, "floor":1}
		 */		
		int floorNumber = 0;
		int roomNumber = 0;
		int workstationNumber = 0;
		String datePrenotation = null;
		
		try {
			floorNumber = jsonObject.getInt(FLOOR);
			roomNumber = jsonObject.getInt(ROOM);
			workstationNumber = jsonObject.getInt(WORKSTATION);	
			datePrenotation = jsonObject.getString(DATE);			
		} catch (JSONException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("\nErrore nel recupero della prenotazione");			
			response.getWriter().flush();
		}
		
		try {			
			maxFloor = floorDao.countMax();
			maxRoom = roomDao.countMaxByFloor(floorNumber);
			maxWorkstation = workstationDao.countMaxByFloorAndRoom(floorNumber, roomNumber);			
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("\nErrore nel recupero delle informazioni relative al piano massimo, alla stanza massima e alla postazione massima");			
			response.getWriter().flush();	
		}		
		
		if(floorNumber<MIN || floorNumber>maxFloor || roomNumber<MIN || roomNumber>maxRoom || workstationNumber<MIN || workstationNumber>maxWorkstation || (datePrenotation==null) || !datePrenotation.matches("^(19|20)\\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$") || datePrenotation.equals("")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("\nI parametri inseriti non rispettano il formato/lunghezza");			
			response.getWriter().flush();
			throw new IllegalArgumentException("I parametri inseriti non rispettano il formato/lugnhezza");					
		}
		
		LocalDate date = LocalDate.parse(datePrenotation, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Calendar calendar = Calendar.getInstance();
		TimeZone timeZone = calendar.getTimeZone();
		ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
		Date workstationPrenotationDate = Date.from(date.atStartOfDay().atZone(zoneId).toInstant());
		calendar.setTime(workstationPrenotationDate);
		
		int calendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		int year = calendar.get(Calendar.YEAR);
		
		try {
			workstation = workstationDao.retrieveById(floorNumber, roomNumber, workstationNumber);			
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("\nErrore nel recupero della postazione di lavoro dal database");			
			response.getWriter().flush();
		}
			
		WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
		workstationPrenotationPK.setEmailEmployee(employee.getEmail());
		workstationPrenotationPK.setPrenotationDate(workstationPrenotationDate);
		
		workstationPrenotation = new WorkstationPrenotation();
		
		workstationPrenotation.setCalendarWeek(calendarWeek);
		workstationPrenotation.setEmployee(employee);
		workstationPrenotation.setId(workstationPrenotationPK);
		workstationPrenotation.setWorkstation(workstation);
		workstationPrenotation.setYear(year);
		
		try {
			workstationPrenotation = workstationPrenotationDao.create(workstationPrenotation);
		}catch(Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("\nErrore nella prenotazione della postazione: "+workstationPrenotation.toString());			
			response.getWriter().flush();
		}
		
		request.setAttribute("result", "success");		
		request.getRequestDispatcher("/ShowWorkstationPrenotationPage").forward(request, response);			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
