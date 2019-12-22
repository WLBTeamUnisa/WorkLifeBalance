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
	private static final String ADMIN_EMAIL = "adminEmail";
	private static final String EMPLOYEES_LIST = "employeesList";
	
    public AddProjectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project project = new Project();
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
		List<Employee> employeesList;
		
		boolean nameOk = false;
		boolean scopeOk = false;
		boolean startDateOk = false;
		boolean endDateOk = false;
		boolean descriptionOk = false;
		boolean managerEmailOk = false;
		
		//Ruolo admin 2 userRole attributo della sessione
		
		name = request.getParameter(PROJECT_NAME);
		scope = request.getParameter(PROJECT_SCOPE);
		//Prendo la data come una stringa, setto il formatter e converto String in Date
		startDateString = request.getParameter(PROJECT_START_DATE);
		endDateString = request.getParameter(PROJECT_END_DATE);
		description = request.getParameter(PROJECT_DESCRIPTION);
		managerEmail = request.getParameter(PROJECT_MANAGER);
		admin = (Admin) request.getSession().getAttribute(ADMIN_EMAIL);
		employeesList = (List<Employee>) request.getSession().getAttribute(EMPLOYEES_LIST);			
		
		//Controlli sui parametri
		if(name.matches("[A-Za-z0-9]+") && name.length() > 3 && name.length() < 16) {
			//Controllo se esiste nel db un progetto con lo stesso nome
			nameOk = true;
		}
		
		if(scope.matches("[A-Za-z\\s]+") && scope.length() > 2 && scope.length() < 26) {
			scopeOk = true;
		}
		
		if(startDateString.matches("(19|20)\\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])")) startDateOk = true;
		if(endDateString.matches("(19|20)\\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])")) endDateOk = true;
		
		if(description.matches("[\\s\\S]+") && description.length() >=20 && description.length() <= 250) {
			descriptionOk = true;
		}
		// Controllo se esiste il manager nel db
		manager = employeeDao.retrieveByEmail(managerEmail);
		if(managerEmail.matches("[a-z]{1}\\.[a-z]+[1-9]*\\@wlb.it") && manager == null) {
			managerEmailOk = true;
		}
		
		if(startDateOk && endDateOk) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	// La m (minuscolo) nel format è minute
			try {
				startDate = formatter.parse(startDateString);
				endDate = formatter.parse(endDateString);
			} catch(Exception e) {
				// Annulla l'inserimento poichè il formato della data è errato
			}
		}
		
		if(nameOk && scopeOk && startDateOk && endDateOk && descriptionOk && managerEmailOk) {
			project.setName(name);
			project.setScope(scope);
			project.setStartDate(startDate);
			project.setEndDate(endDate);
			project.setDescription(description);
			project.setEmployee(manager);
			project.setAdmin(admin);
			project.setEmployees(employeesList);
			projectDao.create(project);
			
			//Aggiorno il manager inserendo il nuovo progetto che supervisiona
			manager.addProjects1(project);
			employeeDao.update(manager);
		
			// Rimando il controllo alla servlet che inserirà i dipendenti al progetto
			request.setAttribute("Project", project);
			String url= response.encodeURL("AddEmployeesToProjectServlet.java");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} else {
			String url= response.encodeURL("AddProjectServlet.java");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
		
	}
	
}
