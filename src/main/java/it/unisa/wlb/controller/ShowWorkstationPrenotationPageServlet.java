package it.unisa.wlb.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.dao.IPrenotationDateDAO;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;

/**
 * 
 * 
 * @author Vincenzo Fabiano, Luigi Cerrone, Sabato Nocera
 *
 */
@WebServlet("/ShowWorkstationPrenotationPage")
public class ShowWorkstationPrenotationPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
				;
			}

			List<PrenotationDate> smartWorkingPrenotationDateList =  smartWorkingPrenotation.getPrenotationDates();
			if(smartWorkingPrenotationDateList!=null) {
				for(int i=0; i<smartWorkingPrenotationDateList.size(); i++) {
					Date tempDate = smartWorkingPrenotationDateList.get(i).getId().getDate();
					LocalDate tempDateConverted = tempDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					listDates.remove(tempDateConverted);
				}				
			} 
			
			List<WorkstationPrenotation> workstationPrenotations = workstationPrenotationDao.retrieveByWeeklyPlanning(nextCalendarWeek, year, employee.getEmail());
			if(workstationPrenotations!=null) {
				for(int i=0; i<workstationPrenotations.size(); i++) {
					Date tempDate = workstationPrenotations.get(i).getId().getPrenotationDate();
					LocalDate tempDateConverted = tempDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					listDates.remove(tempDateConverted);
				}
			}
			
			request.setAttribute("availableDates", listDates);
			request.getRequestDispatcher("WEB-INF/WorkstationPrenotation.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
