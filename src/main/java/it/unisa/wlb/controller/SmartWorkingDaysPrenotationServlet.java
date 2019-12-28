package it.unisa.wlb.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.PrenotationDatePK;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.SmartWorkingPrenotationPK;
import it.unisa.wlb.model.dao.IPrenotationDateDAO;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;

/**
 * Implementation of SmartWorkingDaysPrenotationServlet
 * 
 * @author Luigi Cerrone, Vincenzo Fabiano
 */
@WebServlet("/SmartWorkingDaysPrenotationServlet")
public class SmartWorkingDaysPrenotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ISmartWorkingPrenotationDAO smartWorkingDao;
	
	@EJB
	private IPrenotationDateDAO prenotationDateDao;
	
   
    public SmartWorkingDaysPrenotationServlet() {
        super();
    }
    
    public void setSmartWorkingPrenotationDao(ISmartWorkingPrenotationDAO swDao) {
    	this.smartWorkingDao = swDao;
    }
   
    public void setPrenotationDateDao(IPrenotationDateDAO pdDao) {
    	this.prenotationDateDao = pdDao;
    }


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("user");
		/**
		 * Set calendar parameters to take information about last week of year and current calendar week
		 */
		Calendar localCalendar = Calendar.getInstance();
		TimeZone tz = localCalendar.getTimeZone();
		ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
		LocalDate today = LocalDateTime.ofInstant(localCalendar.toInstant(), zid).toLocalDate();
		localCalendar.setTime(Date.from(today.atStartOfDay().atZone(zid).toInstant()));
		
		int currentCalendarWeek = localCalendar.get(Calendar.WEEK_OF_YEAR);
		int lastWeekOfYear = localCalendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
		/**
		 * Checking if employee is null
		 */
		if(employee != null) {
			
			String[] arrayDates = request.getParameterValues("dates");
			
			/**
			 * Checking size of dateList
			 */
			if(arrayDates.length > 3) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Non puoi prenotare più di 3 date");
				response.getWriter().flush();
			}
			
			LocalDate localDate;
			List<LocalDate> dateList=new ArrayList<LocalDate>();
			Calendar bookingCalendar;
			/**
			 * Checking format of dates and add them to the array dateList
			 */
			for(int i=0; i < arrayDates.length; i++) {
				
				if(arrayDates[i]!=null && !arrayDates[i].equals("")) {
					try {
						localDate = LocalDate.parse(arrayDates[i]);
						bookingCalendar = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
						int bookingCalendarWeek = bookingCalendar.get(Calendar.WEEK_OF_YEAR);
						if((currentCalendarWeek == lastWeekOfYear) && (bookingCalendarWeek == 1)){
						
							dateList.add(localDate);
						
						} else if(bookingCalendarWeek-1 == currentCalendarWeek && localCalendar.get(Calendar.YEAR) == bookingCalendar.get(Calendar.YEAR)) {
						
							dateList.add(localDate);
					
						}
					}
					
					catch(Exception e)
					{
						request.setAttribute("result", "formatError");
						RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/SmartWorkingPrenotation.jsp");
			        	dispatcher.forward(request, response);
					}
				} 
			}
		
			/**
			 * Creating Smart Working Prenotation
			 */
			SmartWorkingPrenotation smartWorkBooking = new SmartWorkingPrenotation();
			 
			if(lastWeekOfYear==currentCalendarWeek){
					
				smartWorkBooking.setCalendarWeek(1);
				smartWorkBooking.setYear(localCalendar.get(Calendar.YEAR)+1);
				
			} else {
				
				smartWorkBooking.setCalendarWeek(currentCalendarWeek+1);
				smartWorkBooking.setYear(localCalendar.get(Calendar.YEAR));
				
			}
			
			smartWorkBooking.setEmployee(employee);
			SmartWorkingPrenotationPK pk=new SmartWorkingPrenotationPK();
			pk.setEmployeeEmail(employee.getEmail());

			smartWorkBooking.setId(pk);
			smartWorkingDao.create(smartWorkBooking);
			int idSmartWorking = smartWorkingDao.retrieveByWeeklyPlanning(smartWorkBooking.getCalendarWeek(), smartWorkBooking.getYear(), smartWorkBooking.getEmployee().getEmail()).getId().getId();
			
			pk.setId(idSmartWorking);
			smartWorkBooking.setId(pk);

			/**
			 * Add Prenotation Dates for inserted Smart Working Prenotation 
			 */
			List<PrenotationDate> PrenotationDateList=new ArrayList<PrenotationDate>();
			for(int i = 0; i < dateList.size(); i++) {
				
				PrenotationDate prenotationDate=new PrenotationDate();
				PrenotationDatePK prenotationDatePK=new PrenotationDatePK();
				prenotationDatePK.setDate(Date.from(dateList.get(i).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				prenotationDatePK.setEmployeeEmail(employee.getEmail());
				prenotationDatePK.setIdPrenotationSw(idSmartWorking);
				prenotationDate.setId(prenotationDatePK);
				PrenotationDateList.add(prenotationDate);
				prenotationDateDao.create(prenotationDate);
			}
			

			smartWorkBooking.setPrenotationDates(PrenotationDateList);
			smartWorkingDao.update(smartWorkBooking);

			request.setAttribute("result", "ok");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Homepage.jsp");
        	dispatcher.forward(request, response);
        	
		} else {
			/**
			 * Checking if user is not logged
			 */
			request.setAttribute("result", "NotLogged");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Index.jsp");
        	dispatcher.forward(request, response);
        	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}



