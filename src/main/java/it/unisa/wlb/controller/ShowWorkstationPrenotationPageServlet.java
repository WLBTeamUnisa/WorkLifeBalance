package it.unisa.wlb.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
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
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.dao.IPrenotationDateDAO;
import it.unisa.wlb.model.dao.IRoomDao;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * 
 * 
 * @author Vincenzo Fabiano, Luigi Cerrone, Sabato Nocera
 *
 */
@WebServlet(name="ShowWorkstationPrenotationPageServlet", urlPatterns="/ShowWorkstationPrenotationPage")
@Interceptors({LoggerSingleton.class})
public class ShowWorkstationPrenotationPageServlet extends HttpServlet {
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
	
    public ShowWorkstationPrenotationPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = (Employee) request.getSession().getAttribute("user");
		
		if(request.getSession().getAttribute("user")==null) {
			//request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request, response);
		}
		else {
			/**
			 * Get information about the next calendar week (number and year)
			 */
			Calendar calendar = Calendar.getInstance();
			TimeZone timeZone = calendar.getTimeZone();
			ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
			LocalDate today = LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
			LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
			LocalDate newDate = nextMonday.plusDays(7);
			calendar.setTime(Date.from(newDate.atStartOfDay().atZone(zoneId).toInstant()));
			int nextCalendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
			int year = calendar.get(Calendar.YEAR);
			List<LocalDate> listDates = new ArrayList<>();
			listDates.add(newDate);
			listDates.add(newDate.plusDays(1));
			listDates.add(newDate.plusDays(2));
			listDates.add(newDate.plusDays(3));
			listDates.add(newDate.plusDays(4));
			
			SmartWorkingPrenotation smartWorkingPrenotation=null;
			try {
				smartWorkingPrenotation = smartWorkingDao.retrieveByWeeklyPlanning(nextCalendarWeek, year, employee.getEmail());
			} catch(Exception exception) {
				request.setAttribute("error", "Prima devi prenotare i giorni di Smart Working!");
				request.getRequestDispatcher("/ShowSmartWorkingPrenotation").forward(request, response);;
			}

			List<PrenotationDate> smartWorkingPrenotationDateList=null;
			try {
				smartWorkingPrenotationDateList = smartWorkingPrenotation.getPrenotationDates();
			} catch(Exception exception) {
				
			}			 
			if(smartWorkingPrenotationDateList!=null) {
				for(int i=0; i<smartWorkingPrenotationDateList.size(); i++) {
					Date tempDate = (Date) smartWorkingPrenotationDateList.get(i).getId().getDate();
					LocalDate tempDateConverted = new Date(tempDate.getTime()).toLocalDate();
					listDates.remove(tempDateConverted);
				}				
			} 
			
			List<WorkstationPrenotation> workstationPrenotations = null;
			try {
				workstationPrenotations = workstationPrenotationDao.retrieveByWeeklyPlanning(nextCalendarWeek, year, employee.getEmail());
			} catch(Exception exception) {
				;
			}			 
			if(workstationPrenotations!=null) {
				for(int i=0; i<workstationPrenotations.size(); i++) {
					Date tempDate = (Date) workstationPrenotations.get(i).getId().getPrenotationDate();
					LocalDate tempDateConverted = new Date(tempDate.getTime()).toLocalDate();
					listDates.remove(tempDateConverted);
				}
			}
			
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
						
			request.setAttribute("availableDates", listDates);
			request.getRequestDispatcher("WEB-INF/WorkstationPrenotation.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
