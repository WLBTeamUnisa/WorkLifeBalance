package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.WorkstationPK;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this servlet is to return an array JSON to CalendarHistory.jsp
 * 
 * @author Luigi Cerrone
 * 
 */
@WebServlet(name="ShowCalendarHistoryServlet", urlPatterns="/ShowCalendarHistory")
@Interceptors({LoggerSingleton.class})
public class ShowCalendarHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
    private IEmployeeDAO employeeDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCalendarHistoryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeString = request.getParameter("employeeEmail");
		String monthString = request.getParameter("month");
		Integer month = Integer.parseInt(monthString);
		String yearString = request.getParameter("year");
		Integer year = Integer.parseInt(yearString);
		Employee sessionEmployee = (Employee) session.getAttribute("user");
		
		if(sessionEmployee!=null)
		{
			/**
			 *	If the parameter employeeEmail is null, or empty, it means that the system
			 *	has to retrieve the calendar history of the employee's session
			 * 
			 */
			if(employeeString==null || employeeString.equals(""))
			{
				Employee employee = employeeDao.retrieveByEmail(sessionEmployee.getEmail());
				List<SmartWorkingPrenotation> smartWorkingPrenotationList = employee.getSmartWorkingPrenotations();
				List<WorkstationPrenotation> workstationPrenotationList = employee.getWorkstationPrenotations();
				JSONArray jsonArray=ShowCalendarHistoryServlet.buildJsonArray(smartWorkingPrenotationList, workstationPrenotationList, month, year);
				response.setContentType("application/json");
				response.getWriter().append(jsonArray.toString());
			}
			
			/**
			 *	If the parameter employeeEmail isn't null, or empty, it means that the system
			 *	has to retrieve the calendar history of the employee's request
			 *
			 */
			else
			{
				try
				{
					Employee employee = employeeDao.retrieveByEmail(employeeString);
					List<SmartWorkingPrenotation> smartWorkingPrenotationList = employee.getSmartWorkingPrenotations();
					List<WorkstationPrenotation> workstationPrenotationList = employee.getWorkstationPrenotations();
					JSONArray jsonArray = ShowCalendarHistoryServlet.buildJsonArray(smartWorkingPrenotationList, workstationPrenotationList, month, year);
					response.setContentType("application/json");
					response.getWriter().append(jsonArray.toString());
				}
				
				catch(Exception exception)
				{
					request.setAttribute("result", "error");
					request.getRequestDispatcher("WEB-INF/CalendarHistory.jsp").forward(request, response);
				}
			}
		}
		
		else
		{
			request.setAttribute("result", "error");
			request.getRequestDispatcher(".").forward(request, response);	
		}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * This method build a JsonArray that will contain a list of JSON object with these attributes: date,type,floor,room and workstation
	 * 
	 * Example:
	 * [{"date"="2019-02-01","type"="Workstation",floor"=1,"room"=1,"workstation"=50},
		 {"date"="2019-02-02","type"="Smartworking",floor"=,"room"=,"workstation"=},
		 ...]
	 * 
	 * @param smartWorkingPrenotationList
	 * @param workstationPrenotationList
	 * @param month
	 * @param year
	 * @return JSONArray
	 */
	public static JSONArray buildJsonArray(List<SmartWorkingPrenotation> smartWorkingPrenotationList, List<WorkstationPrenotation> workstationPrenotationList, Integer month, Integer year)
	{
			JSONArray jsonArray = new JSONArray();
			if(smartWorkingPrenotationList!=null)
			{
				for(int i=0; i<smartWorkingPrenotationList.size(); i++)
				{
					List<PrenotationDate> prenotationDateList = smartWorkingPrenotationList.get(i).getPrenotationDates();
					if(prenotationDateList!=null)
					{
						for(int j=0; j<prenotationDateList.size(); j++)
						{
							Calendar calendar=Calendar.getInstance();
							calendar.setTime(prenotationDateList.get(j).getId().getDate());
							if(calendar.get(Calendar.MONTH)+1==month && calendar.get(Calendar.YEAR)==year)
							{
								JSONObject jsonObject = new JSONObject();
								jsonObject.put("date", prenotationDateList.get(j).getId().getDate());
								jsonObject.put("type", "Smartworking");
								jsonArray.put(jsonObject);
							}
						}
					}
				}
			}
			
			if(workstationPrenotationList!=null)
			{
				for(int i=0; i<workstationPrenotationList.size(); i++)
				{
					WorkstationPK workstationLocal=workstationPrenotationList.get(i).getWorkstation().getId();
					Calendar calendar=Calendar.getInstance();
					calendar.setTime(workstationPrenotationList.get(i).getId().getPrenotationDate());
					if(calendar.get(Calendar.MONTH)+1==month && calendar.get(Calendar.YEAR)==year)
					{
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("date", workstationPrenotationList.get(i).getId().getPrenotationDate());
						jsonObject.put("type", "Workstation");
						jsonObject.put("floor", workstationLocal.getFloor());
						jsonObject.put("room", workstationLocal.getRoom());
						jsonObject.put("workstation", workstationLocal.getWorkstation());
						jsonArray.put(jsonObject);
					}
				}
			}
			return jsonArray;
	}

}
