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
 * The aim of this servlet is to manage system access for users
 * 
 * @author Vincenzo Fabiano
 * 
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	IAdminDAO adminDao;		
	@EJB
	IEmployeeDAO employeeDao;

	public LoginServlet() {
		super();
	}

	public void setEmployeeDao(IEmployeeDAO empDao) {
		this.employeeDao = empDao;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((request.getParameter("email")!=null) && (request.getParameter("password")!=null)) {
			HttpSession session = request.getSession();
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if(email != null && password != null && checkPasswordLogin(password)) {
				try {
				    String generatedPwd = Utils.generatePwd(password);
					/**
					 * Checking if email respects employee email format
					 */
				    if(email.endsWith("@wlb.it") && checkEmailEmployee(email)) {
				    	Employee e = employeeDao.retrieveByEmailPassword(email, generatedPwd);
						if(e != null) {
							session.setAttribute("user", e);
							request.setAttribute("result", "success");
							request.getRequestDispatcher("WEB-INF/Homepage.jsp").forward(request, response);
						}
					/**
					 * Checking if email respects admin email format
					 */
					} else if(email.endsWith("@wlbadmin.it") && checkEmailAdmin(email)) {
						Admin a = adminDao.retrieveByEmailPassword(email, generatedPwd);
						if(a != null) {
							session.setAttribute("userRole", "Admin");
							session.setAttribute("user", a);
							request.getRequestDispatcher("WEB-INF/Homepage.jsp").forward(request, response);
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
					return ;
				}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Email e/o password non validi");
				response.getWriter().flush();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	public static boolean checkPasswordLogin(String password) {
		if(password.length() >= 8 && password.length() <= 20 && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\.!@#\\$%\\^&\\*]).{8,20}$")) {
			return true;
		}
		return false;
	}
	
	public static boolean checkEmailEmployee(String email) {
		if(email==null || !email.matches("^[a-z]{1}\\.[a-z]+[0-9]*\\@wlb.it$") || email.equals("") || (email.length()-7)<5 || (email.length()-7)>30) {
			return false;
		}
		else
			return true;
	}
	
	public static boolean checkEmailAdmin(String email) {
		if(email==null || !email.matches("^[a-z]{1}\\.[a-z]+[0-9]*\\@wlbadmin.it$") || email.equals("") || (email.length()-12)<5 || (email.length()-12)>30) {
			return false;
		}
		else
			return true;
	}

}
