package it.unisa.wlb.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;

/**
 * Servlet implementation class ShowSmartWorkingPrenotationServlet
 */
@WebServlet("/ShowSmartWorkingPrenotation")
public class ShowSmartWorkingPrenotationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Calendar CALENDAR = Calendar.getInstance();
	private SmartWorkingPrenotation smartWorkingPrenotation;
	private Employee employee;
	
	@EJB
	private ISmartWorkingPrenotationDAO smartWorkingDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")==null) {
			request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request, response);
		}
		else {
			CALENDAR = Calendar.getInstance();
			SmartWorkingPrenotation smartWorkingPrenotation;
			Employee employee;
			employee = (Employee) request.getSession().getAttribute("user");
			TimeZone tz = CALENDAR.getTimeZone();
			ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
			LocalDate today = LocalDateTime.ofInstant(CALENDAR.toInstant(), zid).toLocalDate();
			
			LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
			System.out.println(nextMonday);
			LocalDate newDate;
			newDate= nextMonday.plusDays(7);
			System.out.println(newDate);
			CALENDAR.setTime(Date.from(newDate.atStartOfDay().atZone(zid).toInstant()));
			int currentCalendarWeek = CALENDAR.get(Calendar.WEEK_OF_YEAR);
			System.out.println(currentCalendarWeek);
			int currentYear = CALENDAR.get(Calendar.YEAR);
			System.out.println(currentYear);
				try {
					smartWorkingPrenotation = smartWorkingDao.retrieveByWeeklyPlanning(currentCalendarWeek, currentYear, employee.getEmail());
					request.setAttribute("booking", "no");
					request.getRequestDispatcher("WEB-INF/SmartWorkingPrenotation.jsp").forward(request, response);
					
				} catch(EJBException e) {
					request.setAttribute("booking", "yes");
					request.getRequestDispatcher("WEB-INF/SmartWorkingPrenotation.jsp").forward(request, response);
				} catch(Exception e) {
					
				}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}