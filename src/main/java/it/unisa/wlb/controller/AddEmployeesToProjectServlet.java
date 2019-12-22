package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IAdminDAO;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IProjectDAO;

/**
 * Servlet implementation class AddEmployeesToProjectServlet
 */
@WebServlet("/AddEmployeesToProjectServlet")
public class AddEmployeesToProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	    @EJB
	    private IProjectDAO projectDao;
	   
	    @EJB
	    private IEmployeeDAO employeeDao;
	 
	    private static final String EMAIL_EMPLOYEE = "email"; 
	    
    public AddEmployeesToProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  HttpSession session = request.getSession();
	  int userRole=(int) session.getAttribute("userRole");
	  
	  if(userRole==2)
	  {
	      //acquisisco il progetto tramite un attributo
	      Project project=(Project) request.getAttribute("Project");
	      if(project==null)
	      {
	        String url= response.encodeURL("AddEmployeesToProjectServlet.java");
	        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	        dispatcher.forward(request, response);
	      }
	    
	      else
	      {
	        
	        List<Employee> lista_dipendenti=(List<Employee>) request.getAttribute("lista_dipendenti");
	        
	        //inserisco tutti i dipendenti che fanno parte della lista
	        for(int i=0; i<lista_dipendenti.size(); i++)
	        {
	          projectDao.insertEmployeeToProject(EMAIL_EMPLOYEE, project.getId());
	        }
	        
	        String url= response.encodeURL("localhost:8080/WorkLifeBalance/ProjectList.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
	      
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
