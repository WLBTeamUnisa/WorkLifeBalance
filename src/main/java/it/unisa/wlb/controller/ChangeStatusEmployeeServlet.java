package it.unisa.wlb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.dao.IEmployeeDAO;

/**
 * Servlet implementation class ChangeStatusEmployeeServlet
 */
@WebServlet("/ChangeStatusEmployeeServlet")
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String email=request.getParameter("email");
		 String status=request.getParameter("status");
		 int statusInt;
		 if(status.equals("Employee") || status.equals("Manager") ) {
				if(status.equals("Employee"))
				statusInt=0;
				if(status.equals("Manager")) {
					statusInt=1;
				}
			}
		 
		 else
		 {
			 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			 response.getWriter().write("Parametro status non valido");			
			 response.getWriter().flush();
			 throw new IllegalArgumentException("Parametro status non valido");
		 }
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
