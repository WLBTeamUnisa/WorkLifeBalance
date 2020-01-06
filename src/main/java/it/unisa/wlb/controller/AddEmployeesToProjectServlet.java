package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.interceptor.Interceptors;
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
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this Servlet is to create effectively the project and to add
 * employees that belong to it
 * 
 * @author Luigi Cerrone
 *
 */

@WebServlet(name="AddEmployeesToProjectServlet", urlPatterns="/AddEmployeesToProjectServlet")
@Interceptors({LoggerSingleton.class})
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
	
	public void setEmployeeDao(IEmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void setProjectDao(IProjectDAO projectDao) {
		this.projectDao = projectDao;
	}

	/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session = request.getSession();
		  Employee manager;
		  String userRole=(String) session.getAttribute("userRole");
		  Project project=(Project) request.getAttribute("Project");
		  List<Employee> employeesList=(List<Employee>) request.getSession().getAttribute("employeeList");
		  List<Employee> currentEmployees;
		  String status;
		  status = (String)request.getAttribute("status");
		  /**
		   * Check about admin role
		   * 
		   * */
		 
		  if(userRole.equalsIgnoreCase("Admin")) {			  
		      /**
		       * Taking the project setted thanks to request's attribute
		       * */
		      
		      if(project==null) {
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/AddEmployeesToProjectServlet");
		        dispatcher.forward(request, response);
		        throw new IllegalArgumentException();
		      } else {
		    	  
		        if(employeesList!=null && employeesList.size()>=1) {
		        	if(status.equals("modifying")) {
						  currentEmployees = project.getEmployees();
						  for(Employee anEmployee : employeesList) {
							  System.out.println("Aggiungo dipendente: " + anEmployee.getEmail());
							  currentEmployees.add(anEmployee);
						  }
						  project.setEmployees(currentEmployees);
						  projectDao.update(project);
						  
					  } else {
						  /**
				        	 * Insertion of employees into works table
				        	 * 
				        	 * */
				        	project.setEmployees(employeesList);
				        
				        	/**
				        	 * Creation of the new project
				        	 */
				        	projectDao.create(project);
				        
				        	manager=(Employee) request.getAttribute("manager");
				        	manager.addProjects1(project);
				        
				        	/**
				        	 * Updating the relationship between manager and project
				        	 */
				        	employeeDao.update(manager);
					  }
		        
		        	session.removeAttribute("employeeList");
					request.setAttribute("result", "success");
					request.removeAttribute("Project");
				
		        	RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectsListPage");
		        	dispatcher.forward(request, response);
		        } else {
		        	request.setAttribute("result", "error");
		        	session.removeAttribute("employeeList");
		        	request.getRequestDispatcher("/ProjectsListPage").forward(request, response);
		        	throw new IllegalArgumentException();
		          }
		   }
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}