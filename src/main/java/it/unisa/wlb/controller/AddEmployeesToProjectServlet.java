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
 * The aim of this Servlet is to create effectively the project and to add
 * employees that belong to it
 * 
 * @author Luigi Cerrone
 *
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

	public AddEmployeesToProjectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session = request.getSession();
		  String userRole=(String) session.getAttribute("userRole");
		  
		  /**
		   * Check about admin role
		   * 
		   * */
		  if(userRole.equalsIgnoreCase("Admin"))
		  {
		      /**
		       * Taking the project setted thanks to request's attribute
		       * */
		      Project project=(Project) request.getAttribute("Project");
		      if(project==null)
		      {
		        String url= response.encodeURL("/AddEmployeesToProjectServlet");
		        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		        dispatcher.forward(request, response);
		      }
		    
		      else
		      {
		        
		        List<Employee> lista_dipendenti=(List<Employee>) request.getSession().getAttribute("lista_dipendenti");
		        
		        if(lista_dipendenti!=null && lista_dipendenti.size()>=1 )
		        {
		        	/**
		        	 * Insertion of employees into works table
		        	 * 
		        	 * */
		        	project.setEmployees(lista_dipendenti);
		        
		        	/**
		        	 * Creation of the new project
		        	 */
		        	projectDao.create(project);
		        
		        	Employee manager=(Employee) request.getAttribute("manager");
		        	manager.addProjects1(project);
		        
		        	/**
		        	 * Updating the relationship between manager and project
		        	 */
		        	employeeDao.update(manager);
		        	session.removeAttribute("lista_dipendenti");
					request.setAttribute("result", "success");
					
		        	String url= response.encodeURL("/ProjectsListPage");
		        	RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		        	dispatcher.forward(request, response);
		        }
		        
		        else
		        {
		        	request.setAttribute("result", "error");
		        	session.removeAttribute("lista_dipendenti");
		        	request.getRequestDispatcher("/ProjectsListPage").forward(request, response);
		        }
		      
		   }
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
