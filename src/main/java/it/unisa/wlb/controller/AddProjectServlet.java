package it.unisa.wlb.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IAdminDAO;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IProjectDAO;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.jpa.AdminJpa;
import it.unisa.wlb.model.jpa.EmployeeJpa;
import it.unisa.wlb.model.jpa.ProjectJpa;





/**
 * Servlet implementation class AddProjectServlet
 */
@WebServlet(name = "AddProjectServlet", urlPatterns = "/AddProjectServlet")
public class AddProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IProjectDAO projectDao;
	@EJB
	private IEmployeeDAO employeeDao;
	@EJB
	private IAdminDAO adminDao;
	
	private static final String PROJECT_NAME = "name"; 
	private static final String PROJECT_SCOPE = "scope";
	private static final String PROJECT_START_DATE = "startDate"; 
	private static final String PROJECT_END_DATE = "endDate";
	private static final String PROJECT_DESCRIPTION = "description";
	private static final String PROJECT_MANAGER = "managerEmail"; 
	private static final String USER = "user";
	private static final String EMPLOYEES_LIST = "employeesList";
	private static final String USER_ROLE = "userRole";
	
    public AddProjectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Project Parameters
		 */
		Project project;
		Employee manager;
		Admin admin;
		String name;
		String scope;
		Date startDate;
		String startDateString;
		String endDateString;
		startDate = null;
		Date endDate;
		endDate = null;
		String description;
		String managerEmail;
		String userRole;
		List<Employee> employeesList;
		
		boolean nameOk = false;
		boolean scopeOk = false;
		boolean startDateOk = false;
		boolean endDateOk = false;
		boolean descriptionOk = false;
		boolean managerEmailOk = false;
		boolean roleOk = false;
		//Ruolo admin 2 userRole attributo della sessione
		
		name = request.getParameter(PROJECT_NAME);
		scope = request.getParameter(PROJECT_SCOPE);
		//Prendo la data come una stringa, setto il formatter e converto String in Date
		startDateString = request.getParameter(PROJECT_START_DATE);
		endDateString = request.getParameter(PROJECT_END_DATE);
		description = request.getParameter(PROJECT_DESCRIPTION);
		managerEmail = request.getParameter(PROJECT_MANAGER);
		admin = (Admin) request.getSession().getAttribute(USER);
		userRole = (String) request.getSession().getAttribute(USER_ROLE);
		employeesList = (List<Employee>) request.getSession().getAttribute(EMPLOYEES_LIST);			
		
		/**
		 * Project Parameters checks
		 */
		if(userRole.equals("Admin")) {
			roleOk = true;
		}
		
		if(name.matches("^[A-Za-z0-9]+$") && name.length() > 3 && name.length() < 16 && !name.equals("") && !(name==null)) {
			//Controllo se esiste nel db un progetto con lo stesso nome
			nameOk = true;
		}
		
		if(scope.matches("^[A-Za-z\\s]+$") && scope.length() > 2 && scope.length() < 26 && !scope.equals("") && !(scope==null)) {
			scopeOk = true;
		}
		
		if(startDateString.matches("^(19|20)\\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$") && !startDateString.equals("") && !(startDateString==null)) {
			startDateOk = true;
		}
		
		if(endDateString.matches("^(19|20)\\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$") && !endDateString.equals("") && !(endDateString==null)) {
			endDateOk = true;
		}
		
		if(description.matches("^[\\s\\S]+$") && description.length() >=20 && description.length() <= 250) {
			descriptionOk = true;
		}
		
		/**
		 * Checks if the manager is in the Database
		 */
		manager = new Employee();
		try {
			manager = employeeDao.retrieveByEmail(managerEmail);
		} catch(Exception e) {
			String url= response.encodeURL("ProjectInsertion.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			throw new IllegalArgumentException();
		}
		
		if(!(manager==null) && managerEmail.matches("^[a-z]{1}\\.[a-z]+[1-9]*\\@wlb.it$") && !managerEmail.equals("") && !(managerEmail==null)) {
		managerEmailOk = true;
		}
		
		/**
		 * Formats the dates in format yyyy-MM-dd
		 */
		if(startDateOk && endDateOk) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	// La m (minuscolo) nel format è minute
			try {
				startDate = formatter.parse(startDateString);
				endDate = formatter.parse(endDateString);
				if(!endDate.after(startDate)) {
					startDateOk = false;
					endDateOk = false;
				}
			} catch(Exception e) {
				// Annulla l'inserimento poichè il formato della data è errato
				String url= response.encodeURL("ProjectInsertion.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				throw new IllegalArgumentException();
			}
		}
		
		if(nameOk && scopeOk && startDateOk && endDateOk && descriptionOk && managerEmailOk && roleOk) {
			project = new Project();
			project.setName(name);
			project.setScope(scope);
			project.setStartDate(startDate);
			project.setEndDate(endDate);
			project.setDescription(description);
			project.setEmployee(manager);
			project.setAdmin(admin);
			project.setEmployees(employeesList);
			/**
			 * Creation of the new project
			 */
			projectDao.create(project);
			
			/**
			 * Updating manager with the insertion of the project
			 */
			manager.addProjects1(project);
			employeeDao.update(manager);
		
			// Rimando il controllo alla servlet che inserirà i dipendenti al progetto
			request.setAttribute("Project", project);
			String url= response.encodeURL("/AddEmployeeToProjectServlet.java");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			request.setAttribute("result", "success");
		} else {
			String url= response.encodeURL("ProjectInsertion.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		
	}
	
}
