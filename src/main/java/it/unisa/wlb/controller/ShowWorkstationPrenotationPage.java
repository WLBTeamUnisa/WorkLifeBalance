package it.unisa.wlb.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;

/**
 * Servlet implementation class ShowWorkstationPrenotationPage
 */
@WebServlet("/ShowWorkstationPrenotationPage")
public class ShowWorkstationPrenotationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private ISmartWorkingPrenotationDAO smartWorkingDao;
	
    public ShowWorkstationPrenotationPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")==null) {
			request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request, response);
		}
		else {
			/**
			 * Get information about the next calendar week (number and year)
			 */
			Calendar CALENDAR = Calendar.getInstance();
			SmartWorkingPrenotation smartWorkingPrenotation;
			Employee employee;
			employee = (Employee) request.getSession().getAttribute("user");
			TimeZone tz = CALENDAR.getTimeZone();
			ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
			LocalDate today = LocalDateTime.ofInstant(CALENDAR.toInstant(), zid).toLocalDate();
			
			LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
			LocalDate newDate;
			newDate= nextMonday.plusDays(7);
			CALENDAR.setTime(Date.from(newDate.atStartOfDay().atZone(zid).toInstant()));
			int nextCalendarWeek = CALENDAR.get(Calendar.WEEK_OF_YEAR);
			int year = CALENDAR.get(Calendar.YEAR);
				try {
					/**
					 * If the user has made a reservation for the next week he will not be able to make a new reservation
					 */
					smartWorkingPrenotation = smartWorkingDao.retrieveByWeeklyPlanning(nextCalendarWeek, year, employee.getEmail());
					request.setAttribute("booking", "no");
					request.getRequestDispatcher("WEB-INF/SmartWorkingPrenotation.jsp").forward(request, response);
					
				} catch(Exception e) {
					request.setAttribute("booking", "yes");
					request.getRequestDispatcher("WEB-INF/SmartWorkingPrenotation.jsp").forward(request, response);
				} 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
