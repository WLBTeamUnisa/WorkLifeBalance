package it.unisa.wlb.controller;

import java.io.IOException;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.utils.LoggerSingleton;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The aim of this Servlet is change the Employee's status into the system.
 * 
 * @author Simranjit Singh
 *
 */
@WebServlet(name = "ChangeStatusEmployeeServlet", urlPatterns = "/ChangeStatusEmployee")
@Interceptors({LoggerSingleton.class})
public class ChangeStatusEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IEmployeeDao employeeDao;

	public ChangeStatusEmployeeServlet() {
		super();
	}

	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String status = request.getParameter("status"); 
		int statusInt = 0;
		Employee employee;
		
		if (status.equals("Employee") || status.equals("Manager")) {
			if (status.equals("Employee"))
				statusInt = 0;
			else
				statusInt = 1;
			try {
				employee = employeeDao.retrieveByEmail(email);
				request.setAttribute("statusResult", "success");
			} catch (Exception e) {
				request.getRequestDispatcher("EmployeesListPage").forward(request, response);
				request.setAttribute("statusResult", "failure");
				throw new IllegalArgumentException();
			}
			employee.setStatus(statusInt);
			employeeDao.update(employee);
			request.getRequestDispatcher("EmployeesListPage").forward(request, response);

		} else {
			response.getWriter().write("Parametro status non valido");
			response.getWriter().flush();
			throw new IllegalArgumentException("Parametro status non valido");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}