package it.unisa.wlb.controller;

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

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Room;

import it.unisa.wlb.model.dao.IPrenotationDateDAO;
import it.unisa.wlb.model.dao.IRoomDao;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * Servlet implementation class ShowPlanimetryPageServlet
 */
@WebServlet(name="ShowPlanimetryPageServlet", urlPatterns="/ShowPlanimetryPage")
@Interceptors({LoggerSingleton.class})
public class ShowPlanimetryPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static String FLOOR = "floor";
	private final static String ROOM = "room";
	private final static String PLANIMETRY = "insertedPlanimetry";
	
	@EJB
	private IRoomDao roomDao;
	
	@EJB
	private ISmartWorkingPrenotationDAO smartWorkingDao;
	
	@EJB
	private IPrenotationDateDAO prenotationDateDao;
	
	@EJB
	private IWorkstationPrenotationDao workstationPrenotationDao;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPlanimetryPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void setRoomDao(IRoomDao roomDao) {
    	this.roomDao = roomDao;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = (Employee) request.getSession().getAttribute("user");
		
		if(employee==null) {
			request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request, response);
		} else {
			/**
			 * Get information about the calendar week (number and year)
			 */
			Calendar calendar = Calendar.getInstance();
			TimeZone timeZone = calendar.getTimeZone();
			ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
			LocalDate today = LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
			LocalDate monday = today.with(DayOfWeek.MONDAY);
			calendar.setTime(Date.from(monday.atStartOfDay().atZone(zoneId).toInstant()));
			
			List<LocalDate> datesList = new ArrayList<>();
			
			datesList.add(monday);
			datesList.add(monday.plusDays(1));
			datesList.add(monday.plusDays(2));
			datesList.add(monday.plusDays(3));
			datesList.add(monday.plusDays(4));
			
			
			try {
				List<Room> rooms = roomDao.retrieveAll();
				if(rooms!=null && rooms.size()>0) {
					JSONArray jsonArray = new JSONArray();
					for(Room room :  rooms) {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put(FLOOR, room.getId().getNumFloor());
						jsonObject.put(ROOM, room.getId().getNumRoom());						
						jsonArray.put(jsonObject);				
					}
					request.setAttribute(PLANIMETRY, jsonArray.toString());				
				}			
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Planimetria assente nel database");	
				response.getWriter().flush();
			}
			
			request.setAttribute("availableDates", datesList);
			request.getRequestDispatcher("WEB-INF/Planimetry.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
