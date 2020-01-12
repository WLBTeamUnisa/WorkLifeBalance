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

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IAdminDao;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.utils.LoggerSingleton;
import it.unisa.wlb.utils.Utils;

/**
 * The aim of this servlet is to manage system access for users
 * 
 * @author Vincenzo Fabiano
 * 
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
@Interceptors({ LoggerSingleton.class })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	IAdminDao adminDao;
	
	@EJB
	IEmployeeDao employeeDao;
	

	public LoginServlet() {
		super();
	}

	/**
	 * This set method is used during testing in order to simulate the behaviour of the dao class
	 * 
	 * @param employeeDao
	 */
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * This set method is used during testing in order to simulate the behaviour of the dao class
	 * 
	 * @param adminDao
	 */
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	/**
	 * @param request Object that identifies an HTTP request
	 * @param response Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @pre request.getParameter("email") != null
     * @pre request.getParameter("password") != null
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			request.getRequestDispatcher(".").forward(request, response);
		}

		else {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email != null && password != null && checkPasswordLogin(password)) {
				try {
					String generatedPassword = Utils.generatePwd(password);
					/**
					 * Checking if email respects employee email format
					 */
					if (email.endsWith("@wlb.it") && checkEmailEmployee(email)) {
						Employee employee = employeeDao.retrieveByEmailPassword(email, generatedPassword);
						if (employee != null) {
							session.setAttribute("user", employee);
							request.setAttribute("login", "success");
							request.getRequestDispatcher(".").forward(request, response);
						}
					/**
					 * Checking if email respects admin email format
					 */
					} else if (email.endsWith("@wlbadmin.it") && checkEmailAdmin(email)) {
						Admin admin = adminDao.retrieveByEmailPassword(email, generatedPassword);
						if (admin != null) {
							session.setAttribute("userRole", "Admin");
							session.setAttribute("user", admin);
							request.setAttribute("login", "success");
							request.getRequestDispatcher(".").forward(request, response);
						}
					} else {
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						response.getWriter().write("Email e/o password non validi");
						response.getWriter().flush();
					}
				} catch (Exception exception) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getWriter().write("Email e/o password non validi");
					response.getWriter().flush();
					return;
				}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Email e/o password non validi");
				response.getWriter().flush();
			}
		}
	}

	/**
	 * @param request Object that identifies an HTTP request
	 * @param response Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/** 
	 * @param password
	 * @pre password != null
	 * 
	 * @return a boolean, true if password is ok and false if it is not ok
	 */
	public static boolean checkPasswordLogin(String password) {
		if (password.length() >= 8 && password.length() <= 20
				&& password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\.!@#\\$%\\^&\\*]).{8,20}$")) {
			return true;
		}
		return false;
	}

	/**
	 * @param email
	 * @pre email != null
	 * 
	 * @return a boolean value, true if employee's email is ok and false if it is not ok
	 */
	public static boolean checkEmailEmployee(String email) {
		if (email == null || !email.matches("^[a-z]{1}\\.[a-z]+[0-9]+\\@wlb.it$") || email.equals("")
				|| (email.length() - 7) < 5 || (email.length() - 7) > 30) {
			return false;
		} else
			return true;
	}

	/**
	 * @param email
	 * @pre email != null
	 * 
	 * @return a boolean value, true if admin's email is ok and false if it is not ok
	 */
	public static boolean checkEmailAdmin(String email) {
		if (email == null || !email.matches("^[a-z]{1}\\.[a-z]+[0-9]+\\@wlbadmin.it$") || email.equals("")
				|| (email.length() - 12) < 5 || (email.length() - 12) > 30) {
			return false;
		} else
			return true;
	}

}
