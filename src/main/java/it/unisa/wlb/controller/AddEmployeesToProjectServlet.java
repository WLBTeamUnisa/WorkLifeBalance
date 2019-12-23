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
		   * Ruolo dell'admin
		   * 
		   * */
		  if(userRole.equalsIgnoreCase("Admin"))
		  {
		      /**
		       * Acquisisco il progetto tramite un attributo
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
		        
		        if(lista_dipendenti.size()>0)
		        {
		        	/**
		        	 * Inserisco tutti i dipendenti che fanno parte della lista
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
		        	 * Updating manager with the insertion of the project
		        	 */
		        	employeeDao.update(manager);
		        	session.removeAttribute("lista_dipendenti");
		        	String url= response.encodeURL("WEB-INF/ProjectList.jsp");
		        	RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		        	dispatcher.forward(request, response);
		        	
		        }
		        
		        else
		        {
		        	response.getWriter().write("Progetto non inserito correttamente");
		        	session.removeAttribute("lista_dipendenti");
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
