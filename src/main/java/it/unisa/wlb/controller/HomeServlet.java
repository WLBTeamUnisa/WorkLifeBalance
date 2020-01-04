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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ISmartWorkingPrenotationDAO smartWorkingDao;
	
	@EJB
	private IWorkstationPrenotationDao workstationPrenotationDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("user")==null) {
			request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request, response);			
		} else {
			JSONArray jsonList = new JSONArray();
			if(request.getSession().getAttribute("userRole")==null) {
				
				Employee employee = (Employee) request.getSession().getAttribute("user");
				
				/**
				 * Get information about the next calendar week (number and year)
				 */
				Calendar calendar = Calendar.getInstance();
				TimeZone timeZone = calendar.getTimeZone();
				ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
				LocalDate today = LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
				LocalDate monday = today.with(DayOfWeek.MONDAY);
				LocalDate friday = monday.plusDays(4);
				calendar.setTime(Date.from(friday.atStartOfDay().atZone(zoneId).toInstant()));
				int calendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
				int year = calendar.get(Calendar.YEAR);
				System.out.println("Venerdì: " + friday.toString());
				System.out.println("CalendarWeek: " + calendarWeek);
				System.out.println("Year: " + year);
				
				List<LocalDate> listDates = new ArrayList<>();
				
				/**
				 * Get SmartWorkingPrenotation list
				 */
				SmartWorkingPrenotation smartWorkingPrenotation=null;
				try {
					smartWorkingPrenotation = smartWorkingDao.retrieveByWeeklyPlanning(calendarWeek, year, employee.getEmail());
				} catch(Exception exception) {
					/*
						request.setAttribute("error", "Prima devi prenotare i giorni di Smart Working!");
						request.getRequestDispatcher("/ShowSmartWorkingPrenotation").forward(request, response);
					*/
				}
				
				List<PrenotationDate> smartWorkingPrenotationDateList=null;
				try {
					smartWorkingPrenotationDateList = smartWorkingPrenotation.getPrenotationDates();
				} catch(Exception exception) {}			 
				
				if(smartWorkingPrenotationDateList!=null) {
					for(int i=0; i<smartWorkingPrenotationDateList.size(); i++) {
						Date tempDate = (Date) smartWorkingPrenotationDateList.get(i).getId().getDate();
						LocalDate tempDateConverted = new Date(tempDate.getTime()).toLocalDate();
						JSONObject obj = new JSONObject();
						obj.put("date", tempDateConverted);
						obj.put("type", "smartWorking");
						jsonList.put(obj);
						//listDates.add(tempDateConverted);
					}				
				}
				
				/**
				 * Get WorkstationPrenotationList
				 */
				List<WorkstationPrenotation> workstationPrenotations = null;
				try {
					workstationPrenotations = workstationPrenotationDao.retrieveByWeeklyPlanning(calendarWeek, year, employee.getEmail());
				} catch(Exception exception) {}
				
				if(workstationPrenotations!=null) {
					for(int i=0; i<workstationPrenotations.size(); i++) {
						Date tempDate = (Date) workstationPrenotations.get(i).getId().getPrenotationDate();
						LocalDate tempDateConverted = new Date(tempDate.getTime()).toLocalDate();
						JSONObject obj = new JSONObject();
						obj.put("date", tempDateConverted);
						obj.put("floor", workstationPrenotations.get(i).getWorkstation().getRoom().getFloor().getNumFloor());
						obj.put("room", workstationPrenotations.get(i).getWorkstation().getRoom().getId().getNumRoom());
						obj.put("workstation", workstationPrenotations.get(i).getWorkstation().getId().getWorkstation());
						obj.put("type", "workstation");
						jsonList.put(obj);
						//listDates.add(tempDateConverted);
					}
				}
				
				/*
				obj = {["data":"2019-12-12", "piano":1, "stanza":1, ,"postazione":1],
						["data":"2019-12-12", "piano":1, "stanza":1, ,"postazione":1],
						["data":"2019-12-12", "piano":, "stanza":, ,"postazione":]}
				*/
				
				for(LocalDate x : listDates) {
					System.out.println(x.toString());

				}
			}
			
			request.setAttribute("jsonList", jsonList);
			request.getRequestDispatcher("WEB-INF/Homepage.jsp").forward(request, response);			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
