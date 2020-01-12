package it.unisa.wlb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this servlet is checking manager's email
 * 
 * @author Emmanuel Tesauro
 */
@WebServlet(name="CheckManagerServlet", urlPatterns="/CheckManager")
@Interceptors({LoggerSingleton.class})
public class CheckManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	IEmployeeDao employeeDao;
       
    public CheckManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		Employee employee;
		JSONObject object = new JSONObject();
		if( (email!=null) && (email.matches("^[a-z]{1}\\.[a-z]+[0-9]+\\@wlb.it$"))) {
			try {
				employee = employeeDao.retrieveByEmail(email);
				object.put("status", employee.getStatus());
			} catch (Exception exception) {
				
			}
			
			response.setContentType("application/json");
			response.getWriter().append(object.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
