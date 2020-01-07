package it.unisa.wlb.controller;

import java.io.IOException;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;
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
 * @author Simranjit
 *
 */
@WebServlet(name = "ChangeStatusEmployeeServlet", urlPatterns = "/ChangeStatusEmployee")
@Interceptors({LoggerSingleton.class})
public class ChangeStatusEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IEmployeeDAO employeeDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeStatusEmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setEmployeeDao(IEmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String status = request.getParameter("status"); // get
		int statusInt = 0;
		Employee employee;

		// Check status and if is correct, convert status String to integer
		if (status.equals("Employee") || status.equals("Manager")) {
			if (status.equals("Employee"))
				statusInt = 0;
			else
				statusInt = 1;

			try {
				employee = employeeDao.retrieveByEmail(email);

				// this attribute is used in EmployeeList.jsp for the sweetAlert
				request.setAttribute("statusResult", "success");
			} catch (Exception e) {
				request.getRequestDispatcher("EmployeesListPage").forward(request, response);
				request.setAttribute("statusResult", "failure");
				throw new IllegalArgumentException();
			}
			employee.setStatus(statusInt);
			employeeDao.update(employee);
			// Dispatcher to EmployeesListServlet

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