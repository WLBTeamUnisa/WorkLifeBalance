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
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IProjectDAO;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * Servlet implementation class ShowEmployeePage
 */
@WebServlet(name="ShowEmployeePageServlet", urlPatterns="/ShowEmployeePage")
@Interceptors({LoggerSingleton.class})
public class ShowEmployeePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@EJB
	private IEmployeeDAO employeeDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowEmployeePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void setEmployeeDao(IEmployeeDAO employeeDao) {
    	this.employeeDao = employeeDao;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		if(session.getAttribute("userRole").equals("Admin")) {
				
			String email=request.getParameter("email");
			if(email!=null && email!="") {
				try { 			
					Employee employee=employeeDao.retrieveByEmail(email);
					session.setAttribute("employee", employee);
					request.setAttribute("result", "ok");
					request.getRequestDispatcher("WEB-INF/Employee.jsp").forward(request, response);
				} catch(Exception ex) {
					request.setAttribute("result", "error");
					request.getRequestDispatcher("/EmployeeListPageServlet").forward(request, response);
				} 
			} else {//if email is null or email is empty
				request.setAttribute("result", "error");
				request.getRequestDispatcher("/EmployeeListPageServlet").forward(request, response);
			}
			
		} else {
			request.setAttribute("result", "error");
			request.getRequestDispatcher(".").forward(request, response);
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
