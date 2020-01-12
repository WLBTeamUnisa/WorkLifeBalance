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
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * This servlet aims to redirect to the booking page for Smart Working days
 * 
 * @author Vincenzo Fabiano, Luigi Cerrone
 */
@WebServlet(name="ShowSmartWorkingPrenotationServlet", urlPatterns="/ShowSmartWorkingPrenotation")
@Interceptors({LoggerSingleton.class})
public class ShowSmartWorkingPrenotationServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ISmartWorkingPrenotationDao smartWorkingDao;

	/**
	 * This set method is used during testing in order to simulate the behaviour of the dao class
	 * 
	 * @param smartWorkingDao
	 */
	public void setSmartWorkingDao(ISmartWorkingPrenotationDao smartWorkingDao) {
		this.smartWorkingDao = smartWorkingDao;
	}
	
	/**
	 * @param request Object that identifies an HTTP request
	 * @param response Object that identifies an HTTP response
	 * @pre request != null
	 * @pre response != null
	 * @pre request.getSession().getAttribute("user") != null
	 * @pre request.getParameter(FLAG) != null
	 * @post request.getAttribute("booking") !=null
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")==null) {
			request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request, response);
		} else {
			/**
			 * Get information about the next calendar week (number and year)
			 */
			Calendar calendar = Calendar.getInstance();
			@SuppressWarnings("unused")
			SmartWorkingPrenotation smartWorkingPrenotation;
			Employee employee;
			employee = (Employee) request.getSession().getAttribute("user");
			TimeZone tz = calendar.getTimeZone();
			ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
			LocalDate today = LocalDateTime.ofInstant(calendar.toInstant(), zid).toLocalDate();
			LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
			LocalDate newDate;
			newDate = nextMonday.plusDays(7);
			calendar.setTime(Date.from(newDate.atStartOfDay().atZone(zid).toInstant()));
			int nextCalendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
			int year = calendar.get(Calendar.YEAR);
			
			if((nextMonday.getYear() == newDate.getYear()) && nextCalendarWeek==1) {
				year = year + 1;
			}
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

	/**
	 * @param request Object that identifies an HTTP request
	 * @param response Object that identifies an HTTP response
	 * @pre request != null
	 * @pre response != null
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
