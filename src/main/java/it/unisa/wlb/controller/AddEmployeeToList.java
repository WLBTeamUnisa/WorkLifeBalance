package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

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
	    Employee employee=employeeDao.retrieveByEmail(email_employee);
	    if(employee==null)
	    {
	        response.getWriter().append("Non hai inserito un dipendente valido");
	    }
	    
	    else
	    {
	    	JSONObject obj = new JSONObject();
	    	obj.put("emailEmployee", employee.getEmail());
	        
	        /**
	         * Se il dipendente esiste, lo inserisco nella lista dei dipendenti da inserire nel progetto
	         * 
	         * */
	    	HttpSession session=request.getSession();
	        ArrayList<Employee> lista=(ArrayList<Employee>) session.getAttribute("lista_dipendenti");
	        
	        if(lista==null)
	        {
	        	lista=new ArrayList<Employee>();
	        	lista.add(employee);
	        	session.setAttribute("lista_dipendenti", lista);
		        response.getWriter().append(obj.toString());
		        response.setContentType("application/json");
	        }
	        
	        
	        /**
	         * Controllo che il dipendente non faccia già parte della lista
	         * 
	         * */
	        else
	        {
	          int flag=0;
	          for(int i=0; i<lista.size() && flag==0; i++)
	          {
	        	  if(lista.get(i).getEmail()==employee.getEmail())
	        	  {
	        		  flag=1;
	        	  }
	          }
	          
	          if(flag==0)
	          {
	        	  lista.add(employee);
        		  session.setAttribute("lista_dipendenti", lista);
        		  response.getWriter().append(obj.toString());
        		  response.setContentType("application/json");
	          }
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
