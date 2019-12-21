package it.unisa.wlb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IAdminDAO;
import it.unisa.wlb.model.dao.IEmployeeDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	IAdminDAO adminDao;		
	@EJB
	IEmployeeDAO employeeDao;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((request.getParameter("email")!=null) && (request.getParameter("password")!=null)) {
			HttpSession session = request.getSession();
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if(checkPasswordLogin(password) && checkEmailLogin(email)) {
				if(email.endsWith("@wlb.it")) {
					Employee e = employeeDao.retrieveByEmailPassword(email, password);
					if(e != null) {
						session.setAttribute("userRole", e.getStatus());
						session.setAttribute("user", e);
						response.sendRedirect("index.jsp");
					}
				} else if(email.endsWith("@wlbadmin.it")) {
					Admin a = adminDao.retrieveByEmailPassword(email, password);
					if(a != null) {
						//userRole = 2 means who access to the platform is an Admin
						session.setAttribute("userRole", 2);
						session.setAttribute("user", a);
						response.sendRedirect("index.jsp");
					}
				} else {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getWriter().write("Email e Password non validi");
					response.getWriter().flush();
				}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Email e Password non validi");
				response.getWriter().flush();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	public static boolean checkPasswordLogin(String password) {
		if(password.length() >= 8 && password.length() <= 20 && password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\.!@#\\$%\\^&\\*])(?=.{8,20})")) {
			return true;
		}
		return false;
	}
	
	public static boolean checkEmailLogin(String email) {
		if(email.matches("[a-z]{1}\\.[a-z]+[1-9]*\\@wlb.it") || email.matches("[a-z]{1}\\.[a-z]+[1-9]*\\@wlbadmin.it")) {
			return true;
		}
		return false;
	}

}
