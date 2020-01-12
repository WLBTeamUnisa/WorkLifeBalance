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
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this servlet is redirecting to Employee.jsp
 * 
 * @author Simranjit Singh
 */
@WebServlet(name="ShowEmployeePageServlet", urlPatterns="/ShowEmployeePage")
@Interceptors({LoggerSingleton.class})
public class ShowEmployeePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@EJB
	private IEmployeeDao employeeDao;
	
    public ShowEmployeePageServlet() {
        super();
    }
    
    public void setEmployeeDao(IEmployeeDao employeeDao) {
    	this.employeeDao = employeeDao;
    }

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
			} else {
				request.setAttribute("result", "error");
				request.getRequestDispatcher("/EmployeeListPageServlet").forward(request, response);
			}
			
		} else {
			request.setAttribute("result", "error");
			request.getRequestDispatcher(".").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
