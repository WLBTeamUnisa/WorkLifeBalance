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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.dao.IPrenotationDateDAO;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;

/**
 * Servlet implementation class ShowWorkstationPrenotationPage
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
		if(request.getSession().getAttribute("user")==null) {
			//request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request, response);
		}
		else {
			/**
			 * Get information about the next calendar week (number and year)
			 */
			Calendar CALENDAR = Calendar.getInstance();
			SmartWorkingPrenotation smartWorkingPrenotation=null;
			List<PrenotationDate> smartWorkingPrenotationDateList=null;
			Employee employee;
			employee = (Employee) request.getSession().getAttribute("user");
			TimeZone tz = CALENDAR.getTimeZone();
			ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
			LocalDate today = LocalDateTime.ofInstant(CALENDAR.toInstant(), zid).toLocalDate();
			
			LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
			LocalDate newDate;
			newDate = nextMonday.plusDays(7);
			CALENDAR.setTime(Date.from(newDate.atStartOfDay().atZone(zid).toInstant()));
			int nextCalendarWeek = CALENDAR.get(Calendar.WEEK_OF_YEAR);
			int year = CALENDAR.get(Calendar.YEAR);
			List<LocalDate> listDates = new ArrayList<>();
			listDates.add(newDate);
			listDates.add(newDate.plusDays(1));
			listDates.add(newDate.plusDays(2));
			listDates.add(newDate.plusDays(3));
			listDates.add(newDate.plusDays(4));
			try {
				smartWorkingPrenotation = smartWorkingDao.retrieveByWeeklyPlanning(nextCalendarWeek, year, employee.getEmail());
			} catch(Exception exception) {
				;
			}
			
			smartWorkingPrenotationDateList = smartWorkingPrenotation.getPrenotationDates();
			if(smartWorkingPrenotationDateList!=null)
			{
				for(int i=0; i<smartWorkingPrenotationDateList.size(); i++)
				{
					Date tempDate = (Date) smartWorkingPrenotationDateList.get(i).getId().getDate();
					LocalDate tempDateConverted = new Date(tempDate.getTime()).toLocalDate();
					tempDateConverted=tempDateConverted.plusDays(1);
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
