package it.unisa.wlb.controller;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;

/**
 * Servlet implementation class SmartWorkingDaysPrenotationServlet
 */
@WebServlet("/SmartWorkingDaysPrenotationServlet")
public class SmartWorkingDaysPrenotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Calendar flagCalendar = new GregorianCalendar();    
	private static final int CurrentCalendarWeek=flagCalendar.get(Calendar.WEEK_OF_YEAR);
	private static final int LastWeekOfYear=flagCalendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
	private static final int CurrentYear=flagCalendar.YEAR;
	
	@EJB
	private ISmartWorkingPrenotationDAO SmartWorkingDao;
	
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
		
		if(employee!=null)
		{
			String dateString1=request.getParameter("firstDate");
			String dateString2=request.getParameter("secondDate");
			String dateString3=request.getParameter("thirdDate");
			Date date1=null;
			Date date2=null;
			Date date3=null;
		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			List<Date> dateList=new ArrayList<Date>();
			Calendar Bookingcalendar = new GregorianCalendar();
			
			if(dateString1!=null)
			{
				try 
				{
					date1=formatter.parse(dateString1);
					Bookingcalendar.setTime(date1);
					int BookingCalendarWeek=Bookingcalendar.get(Calendar.WEEK_OF_YEAR);
					
					if(CurrentCalendarWeek == LastWeekOfYear && 
							(BookingCalendarWeek==1 && CurrentYear+1==Bookingcalendar.YEAR))
					{
						dateList.add(date1);
					}
					
					else if(BookingCalendarWeek-1 == CurrentCalendarWeek && CurrentYear==Bookingcalendar.YEAR)
					{
						dateList.add(date1);
					}
				} 
			
				catch (ParseException e) 
				{
					request.setAttribute("result", "error");
				}
			}
		
			if(dateString2!=null)
			{
				try 
				{
					date2=formatter.parse(dateString2);  
					Bookingcalendar.setTime(date2);
					int BookingCalendarWeek=Bookingcalendar.get(Calendar.WEEK_OF_YEAR);
					
					if(CurrentCalendarWeek == LastWeekOfYear && 
							(BookingCalendarWeek==1 && CurrentYear+1==Bookingcalendar.YEAR))
					{
						dateList.add(date2);
					}
					
					else if(BookingCalendarWeek-1 == CurrentCalendarWeek && CurrentYear==Bookingcalendar.YEAR)
					{
						dateList.add(date2);
					}
				} 
			
				catch (ParseException e) 
				{
					request.setAttribute("result", "error");
				}
			}
		
			if(dateString3!=null)
			{
				try 
				{
					date3=formatter.parse(dateString3);
					Bookingcalendar.setTime(date3);
					int BookingCalendarWeek=Bookingcalendar.get(Calendar.WEEK_OF_YEAR);
					
					if(CurrentCalendarWeek == LastWeekOfYear && 
							(BookingCalendarWeek==1 && CurrentYear+1==Bookingcalendar.YEAR))
					{
						dateList.add(date3);
					}
					
					else if(BookingCalendarWeek-1 == CurrentCalendarWeek && CurrentYear==Bookingcalendar.YEAR)
					{
						dateList.add(date3);
					}
				} 
			
				catch (ParseException e) 
				{
					request.setAttribute("result", "error");
				}
			}
			
			SmartWorkingPrenotation sw=new SmartWorkingPrenotation();
			
			boolean LastWeekflag=true;
			
			if(LastWeekOfYear==CurrentCalendarWeek)
			{
				sw.setCalendarWeek(1);
				sw.setYear(CurrentYear+1);
			}
			
			else
			{
				sw.setCalendarWeek(CurrentCalendarWeek+1);
				sw.setYear(CurrentYear);
				LastWeekflag=false;
			}
			
			SmartWorkingPrenotationPK pk=new SmartWorkingPrenotationPK();
			pk.setEmployeeEmail(employee.getEmail());
			sw.setId(pk);
			SmartWorkingDao.create(sw);
			
			List<PrenotationDate> PrenotationDateList=new ArrayList<PrenotationDate>();
			
			for(int i=0; i<dateList.size(); i++)
			{
				PrenotationDate prenotationDate=new PrenotationDate();
				PrenotationDatePK prenotationDatePK=new PrenotationDatePK();
				prenotationDatePK.setDate(dateList.get(i));
				prenotationDatePK.setEmployeeEmail(employee.getEmail());
				
				if(LastWeekflag)
				{
					prenotationDatePK.setIdPrenotationSw(SmartWorkingDao.retrieveByWeeklyPlanning(1, CurrentYear+1, employee.getEmail()).getId().getId());
				}
				
				else
				{
					prenotationDatePK.setIdPrenotationSw(SmartWorkingDao.retrieveByWeeklyPlanning(CurrentCalendarWeek+1, CurrentYear, employee.getEmail()).getId().getId());
				}
				
				prenotationDate.setId(prenotationDatePK);
				PrenotationDateList.add(prenotationDate);
			}
			
			sw.setPrenotationDates(PrenotationDateList);
			SmartWorkingDao.update(sw);
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
	}

}
