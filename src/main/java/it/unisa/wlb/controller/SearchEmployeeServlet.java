package it.unisa.wlb.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;

/**
 * The aim of this Servlet is checking the presence of an Employee by searching him using his email. 
 * 
 * @author Sabato
 *
 */
@WebServlet("/SearchEmployeeServlet")
public class SearchEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IEmployeeDao employeeDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployeeServlet() {
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
		
		Employee employee;
		
		if( (email!=null) && (email.length()>=6) && email.matches("/^[0-9a-zA-Z]+$/") ){
			employee = employeeDao.retrieveByEmail(email);
			if(employee.getEmail()==null)
				response.getWriter().append("<ok/>");
			else
				response.getWriter().append("<no/>");
						
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
