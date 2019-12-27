package it.unisa.wlb.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.inject.Inject;
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
 * Servlet implementation class SmartWorkingDaysPrenotationServlet
 */
@WebServlet("/SmartWorkingDaysPrenotationServlet")
public class SmartWorkingDaysPrenotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ISmartWorkingPrenotationDAO SmartWorkingDao;
	
	@EJB
	private IPrenotationDateDAO PrenotationDateDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SmartWorkingDaysPrenotationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Employee employee=(Employee) session.getAttribute("user");
		Calendar Localcalendar = Calendar.getInstance();
		TimeZone tz = Localcalendar.getTimeZone();
		ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
		LocalDate localDate = LocalDateTime.ofInstant(Localcalendar.toInstant(), zid).toLocalDate();
		Localcalendar.setTime(Date.from(localDate.atStartOfDay().atZone(zid).toInstant()));
		int currentCalendarWeek=Localcalendar.get(Calendar.WEEK_OF_YEAR);
		int lastWeekOfYear=Localcalendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
		if(employee!=null)
		{
			String[] ArrayDate=request.getParameterValues("dates");
			LocalDate Localdate;
			List<LocalDate> dateList=new ArrayList<LocalDate>();
			Calendar bookingCalendar;
			
			for(int i=0; i<ArrayDate.length; i++)
			{
				if(ArrayDate[i]!=null && !ArrayDate[i].equals(""))
				{
					Localdate=LocalDate.parse(ArrayDate[i]);
					bookingCalendar=GregorianCalendar.from(Localdate.atStartOfDay(ZoneId.systemDefault()));
					int bookingCalendarWeek=bookingCalendar.get(Calendar.WEEK_OF_YEAR);
					if((currentCalendarWeek == lastWeekOfYear) && (bookingCalendarWeek==1))
					{
						dateList.add(Localdate);
					}
					
					else if(bookingCalendarWeek-1 == currentCalendarWeek && Localcalendar.get(Calendar.YEAR)==bookingCalendar.get(Calendar.YEAR))
					{
						dateList.add(Localdate);
					}
				} 
			}
		
			SmartWorkingPrenotation sw=new SmartWorkingPrenotation();
			
			if(lastWeekOfYear==currentCalendarWeek)
			{
				sw.setCalendarWeek(1);
				sw.setYear(Localcalendar.get(Calendar.YEAR)+1);
			}
			
			else
			{
				sw.setCalendarWeek(currentCalendarWeek+1);
				sw.setYear(Localcalendar.get(Calendar.YEAR));
			}
			
			
			sw.setEmployee(employee);
			SmartWorkingPrenotationPK pk=new SmartWorkingPrenotationPK();
			pk.setEmployeeEmail(employee.getEmail());
			sw.setId(pk);
			SmartWorkingDao.create(sw);
			
			List<PrenotationDate> PrenotationDateList=new ArrayList<PrenotationDate>();
			
			for(int i=0; i<dateList.size(); i++)
			{
				PrenotationDate prenotationDate=new PrenotationDate();
				PrenotationDatePK prenotationDatePK=new PrenotationDatePK();
				prenotationDatePK.setDate(Date.from(dateList.get(i).atStartOfDay()
					      .atZone(ZoneId.systemDefault())
					      .toInstant()));
				prenotationDatePK.setEmployeeEmail(employee.getEmail());
				prenotationDatePK.setIdPrenotationSw(SmartWorkingDao.retrieveByWeeklyPlanning(sw.getCalendarWeek(), sw.getYear(), sw.getEmployee().getEmail()).getId().getId());
				prenotationDate.setId(prenotationDatePK);
				PrenotationDateList.add(prenotationDate);
				PrenotationDateDao.create(prenotationDate);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Homepage.jsp");
        	dispatcher.forward(request, response);
		}
		
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Index.jsp");
        	dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

