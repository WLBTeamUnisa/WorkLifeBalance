package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IProjectDAO;

/**
 * Servlet implementation class AddEmployeeToList
 */
@WebServlet("/AddEmployeeToList")
public class AddEmployeeToList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
    private IProjectDAO projectDao;
   
    @EJB
    private IEmployeeDAO employeeDao; 
    
    private static final String EMAIL_EMPLOYEE = "email";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeToList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String email_employee=request.getParameter(EMAIL_EMPLOYEE);
	    
	    /**
	     * Controllo che il dipendente effettivamente esista
	     * 
	     * */
	    Employee employee=employeeDao.retrieveByEmail(EMAIL_EMPLOYEE);
	    if(employee==null)
	    {
	        response.getWriter().append("Non hai inserito un dipendente valido");
	    }
	    
	    else
	    {
	        /**
	         * Se il dipendente esiste, lo inserisco nella lista dei dipendenti da inserire nel progetto
	         * 
	         * */
	        List<Employee> lista=(List<Employee>) request.getAttribute("lista_dipendenti");
	        
	        /**
	         * Controllo che il dipendente non faccia già parte della lista
	         * 
	         * */
	        if(lista.contains(employee))
	        {
	          response.getWriter().append("Hai inserito un dipendente che fa già parte del progetto");
	        }
	        
	        else
	        {
	          lista.add(employee);
	          request.setAttribute("lista_dipendenti", lista);
	        }
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
