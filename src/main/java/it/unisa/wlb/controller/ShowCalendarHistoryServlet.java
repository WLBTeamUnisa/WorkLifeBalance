package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
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

/**
 * Servlet implementation class ShowCalendarHistoryServlet
 */
@WebServlet(name="ShowCalendarHistoryServlet", urlPatterns="/ShowCalendarHistory")
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
		String emailEmployee = request.getParameter("employeeEmail");
		Employee sessionEmployee = (Employee) session.getAttribute("user");
		
		if(sessionEmployee!=null)
		{
			/**
			 *	If the parameter of request is null, or empty, it means that the system
			 *	must show the calendar history of the employee's session
			 * 
			 */
			if(emailEmployee==null || emailEmployee=="")
			{
				List<SmartWorkingPrenotation> smartWorkingPrenotationList = sessionEmployee.getSmartWorkingPrenotations();
				List<WorkstationPrenotation> workstationPrenotationList = sessionEmployee.getWorkstationPrenotations();
				JSONArray jsonArray=ShowCalendarHistoryServlet.buildJsonArray(smartWorkingPrenotationList, workstationPrenotationList);
				request.setAttribute("prenotationList",jsonArray.toString());
				request.getRequestDispatcher("WEB-INF/CalendarHistory.jsp").forward(request, response);
			}
		
			else
			{
				Employee employee=employeeDao.retrieveByEmail(emailEmployee);
				if(employee==null)
				{
					request.setAttribute("result", "error");
					request.getRequestDispatcher(".").forward(request, response);	
				}
				
				else
				{
					List<SmartWorkingPrenotation> smartWorkingPrenotationList = employee.getSmartWorkingPrenotations();
					List<WorkstationPrenotation> workstationPrenotationList = employee.getWorkstationPrenotations();
					JSONArray jsonArray=ShowCalendarHistoryServlet.buildJsonArray(smartWorkingPrenotationList, workstationPrenotationList);
					request.setAttribute("prenotationList",jsonArray.toString());
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
	
	
	public static JSONArray buildJsonArray(List<SmartWorkingPrenotation> smartWorkingPrenotationList, List<WorkstationPrenotation> workstationPrenotationList)
	{
			JSONArray jsonArray = new JSONArray();
			if(smartWorkingPrenotationList!=null)
			{
				for(int i=0; i<smartWorkingPrenotationList.size(); i++)
				{
					List<PrenotationDate> prenotationDateList = smartWorkingPrenotationList.get(i).getPrenotationDates();
					if(prenotationDateList!=null)
					{
						for (int j=0; j<prenotationDateList.size(); j++)
						{
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("date", prenotationDateList.get(j).getId().getDate());
							jsonObject.put("type", "SmartWorking");
							jsonArray.put(jsonObject);
						}
					}
				}
			}
			
			if(workstationPrenotationList!=null)
			{
				for(int i=0; i<workstationPrenotationList.size(); i++)
				{
					WorkstationPK workstationLocal=workstationPrenotationList.get(i).getWorkstation().getId();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("date", workstationPrenotationList.get(i).getId().getPrenotationDate());
					jsonObject.put("type", "Workstation");
					jsonObject.put("floor", workstationLocal.getFloor());
					jsonObject.put("room", workstationLocal.getRoom());
					jsonObject.put("workstation", workstationLocal.getWorkstation());
					jsonArray.put(jsonObject);
				}
			}
			return jsonArray;
	}

}
