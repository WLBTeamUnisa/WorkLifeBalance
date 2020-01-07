package it.unisa.wlb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * 	The aim of this servlet is to dispatch correctly the employee on CalendarHistory.jsp
 * 
 *	@author Luigi Cerrone
 *
 */
@WebServlet(name="ShowCalendarHistoryPageSevlet", urlPatterns="/ShowCalendarHistoryPage")
@Interceptors({LoggerSingleton.class})
public class ShowCalendarHistoryPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private IEmployeeDAO employeeDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCalendarHistoryPageServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			if(emailEmployee!=null)
			{
				/**
				 * if the parameter employeeEmail  isn't null, it means that the manager 
				 * want to show an employee's calendar history, so it needs to verify that the sessionEmployee is a manager 
				 */
				if(sessionEmployee.getStatus()==1)
				{
					try 
					{
						Employee employee=employeeDao.retrieveByEmail(emailEmployee);
						request.setAttribute("employeeSupervised", employee);
						request.setAttribute("result", "success");
						request.getRequestDispatcher("WEB-INF/CalendarHistory.jsp").forward(request, response);
					}	
					
					catch(Exception exception) 
					{
						request.setAttribute("result", "error");
						request.getRequestDispatcher(".").forward(request, response);
					}
				}
				
				else
				{
					request.setAttribute("result", "error");
					request.getRequestDispatcher(".").forward(request, response);
				}
			}
			
			else
			{
				/**
				 * if the parameter employeeEmail, it means that an employee tries to show his CalendarHistory
				 */
				request.setAttribute("result", "success");
				request.getRequestDispatcher("WEB-INF/CalendarHistory.jsp").forward(request, response);
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

}
