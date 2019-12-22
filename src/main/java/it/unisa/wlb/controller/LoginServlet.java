package it.unisa.wlb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
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
import it.unisa.wlb.utils.Utils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
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
			if(email != null && password != null && checkPasswordLogin(password)) {
				try {
					String generatedPwd = Utils.generatePwd(password);
					if(email.endsWith("@wlb.it")) {
						Employee e = employeeDao.retrieveByEmailPassword(email, generatedPwd);
						if(e != null) {
							session.setAttribute("user", e);
							response.sendRedirect("Homepage.jsp");
						}
					} else if(email.endsWith("@wlbadmin.it")) {
						Admin a = adminDao.retrieveByEmailPassword(email, password);
						if(a != null) {
							session.setAttribute("userRole", "Admin");
							session.setAttribute("user", a);
							response.sendRedirect("Homepage.jsp");
						}
					} else {
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						response.getWriter().write("Email e/o password non validi");
						response.getWriter().flush();
					}
				}catch(Exception e) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getWriter().write("Email e/o password non validi");
					response.getWriter().flush();
				}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Email e/o password non validi");
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
		if(password.length() >= 8 && password.length() <= 20 && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\.!@#\\$%\\^&\\*]).{8,20}$")) {
			return true;
		}
		return false;
	}

}
