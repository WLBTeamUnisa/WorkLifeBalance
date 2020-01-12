package it.unisa.wlb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this Servlet is checking the presence of an Employee by searching him using his email. 
 * 
 * @author Sabato Nocera
 *
 */
@WebServlet(name="CheckEmployeeServlet", urlPatterns="/CheckEmployeeServlet")
@Interceptors({LoggerSingleton.class})
public class CheckEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IEmployeeDao employeeDao;
       
    public CheckEmployeeServlet() {
        super();
    }
   
    /**
     * This set method is used during testing in order to simulate the behaviour of the dao class
     * 
     * @param employeeDao
     */
    public void setEmployeeDao(IEmployeeDao employeeDao) {
    	this.employeeDao=employeeDao;
    }

	/**
	 * If the Employee already exists, the response (written in XML) will be <no/>, otherwise it will be <ok/>, meaning that the email is not yet associated with any Employee.
	 * 
	 * @param request Object that identifies an HTTP request
	 * @param response Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @pre request.getParameter("email") != null
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		response.setContentType("text/xml");
		
		if( (email!=null) && (email.length()>=6) && email.matches("^[a-z]{1}\\.[a-z]+[0-9]+\\@wlb.it$") ){
			try {
				employeeDao.retrieveByEmail(email);
				response.getWriter().write("<no/>");
			} catch (Exception e) {
				response.getWriter().write("<ok/>");
			}		
		} else {
			response.getWriter().write("<no/>");
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

}
