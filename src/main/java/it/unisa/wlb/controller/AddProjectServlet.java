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
		startDate = null;
		Date endDate;
		endDate = null;
		String description;
		String managerEmail;
		String adminEmail;
		List<Employee> employeesList;
		
		name = request.getParameter(PROJECT_NAME);
		scope = request.getParameter(PROJECT_SCOPE);
		//Prendo la data come una stringa, setto il formatter e converto String in Date
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	// La m (minuscolo) nel format è minute
		try {
			startDate = formatter.parse(request.getParameter(PROJECT_START_DATE));
			endDate = formatter.parse(request.getParameter(PROJECT_END_DATE));
		} catch(Exception e) {
			// Annulla l'inserimento poichè il formato della data è errato
		}
		description = request.getParameter(PROJECT_DESCRIPTION);
		managerEmail = request.getParameter(PROJECT_MANAGER);
		adminEmail = request.getParameter(ADMIN_EMAIL);
		employeesList = (List<Employee>) request.getSession().getAttribute(EMPLOYEES_LIST);	
		
		manager = employeeDao.retrieveByEmail(managerEmail);
		admin = adminDao.retrieveAll().get(0);
		
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
	}
	
}
