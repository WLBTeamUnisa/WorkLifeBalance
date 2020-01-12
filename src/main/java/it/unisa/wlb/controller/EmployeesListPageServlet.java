package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this Servlet is redirecting to EmployeesList.jsp
 * 
 * @author Emmanuel Tesauro
 * 
 */
@WebServlet(name="EmployeesListPageServlet", urlPatterns="/EmployeesListPage")
@Interceptors({LoggerSingleton.class})
public class EmployeesListPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	IEmployeeDao employeeDao;

    public EmployeesListPageServlet() {
        super();
    }
    
    public void setEmployeeDao(IEmployeeDao employeeDao) {
    	this.employeeDao = employeeDao;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Employee> list = null;
			list = employeeDao.retrieveAll();
			request.setAttribute("employeeList", list);
			request.getRequestDispatcher("WEB-INF/EmployeesList.jsp").forward(request, response);
		}catch(Exception e) {
			request.getRequestDispatcher(".").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

