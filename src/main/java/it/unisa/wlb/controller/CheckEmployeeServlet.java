package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

/**
 * The aim of this Servlet is checking the presence of an Employee by searching him using his email. 
 * 
 * @author Sabato
 *
 */
@WebServlet("/CheckEmployeeServlet")
public class CheckEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IEmployeeDAO employeeDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckEmployeeServlet() {
        super();
    }

	/**
	 * If the Employee already exists, the response (written in XML) will be <no/>, otherwise it will be <ok/>, meaning that the email is not yet associated with any Employee.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		response.setContentType("text/xml");
		
		if( (email!=null) && (email.length()>=6) && email.matches("^[a-z]{1}\\.[a-z]+[1-9]*\\@wlb.it$") ){
			Employee employee = employeeDao.retrieveByEmail(email);
			if(employee==null) {
				response.getWriter().append("<ok/>");
			} else {
				response.getWriter().append("<no/>");
			}		
		} else {
			response.getWriter().append("<no/>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
