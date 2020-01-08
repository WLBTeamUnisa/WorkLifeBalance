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
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * Servlet implementation class CheckManagerServlet
 */
@WebServlet(name="CheckManagerServlet", urlPatterns="/CheckManager")
@Interceptors({LoggerSingleton.class})
public class CheckManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	IEmployeeDAO employeeDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		Employee employee;
		JSONObject obj = new JSONObject();
		if( (email!=null) && (email.matches("^[a-z]{1}\\.[a-z]+[0-9]*\\@wlb.it$"))) {
			try {
				employee = employeeDao.retrieveByEmail(email);
				obj.put("status", employee.getStatus());
			} catch (Exception exception) {
				
			}
			
			response.setContentType("application/json");
			response.getWriter().append(obj.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
