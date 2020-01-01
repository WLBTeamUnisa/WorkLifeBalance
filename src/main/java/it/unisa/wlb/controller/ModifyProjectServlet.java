package it.unisa.wlb.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IProjectDAO;

/**
 * The aim of this Servlet is to modify a project created
 * 
 * @author Michele Montano, Luigi Cerrone
 *
 */

@WebServlet(name="ModifyProjectServlet", urlPatterns="/ModifyProjectServlet")
public class ModifyProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IProjectDAO projectDao;

	@EJB
	private IEmployeeDAO employeeDao;

	private static final String PROJECT_NAME = "name"; 
	private static final String PROJECT_SCOPE = "scope";
	private static final String PROJECT_START_DATE = "startDate"; 
	private static final String PROJECT_END_DATE = "endDate";
	private static final String PROJECT_DESCRIPTION = "description";
	private static final String PROJECT_MANAGER = "managerEmail"; 
	private static final String USER_ROLE = "userRole";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyProjectServlet() {
		super();
	}
	
	public void setProjectDao(IProjectDAO projectDao) {
		this.projectDao=projectDao;
	}
	
	public void setEmployeeDao(IEmployeeDAO employeeDao) {
		this.employeeDao=employeeDao;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Declaration of variables of instance
		 */
		Project oldProject;
		Employee manager;
		Employee oldManager;
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

		boolean nameOk = false;
		boolean scopeOk = false;
		boolean startDateOk = false;
		boolean endDateOk = false;
		boolean descriptionOk = false;
		boolean managerEmailOk = false;
		boolean roleOk = false;

		name = request.getParameter(PROJECT_NAME);
		scope = request.getParameter(PROJECT_SCOPE);
		/**
		 * Taking dates as strings
		 */
		startDateString = request.getParameter(PROJECT_START_DATE);
		endDateString = request.getParameter(PROJECT_END_DATE);
		description = request.getParameter(PROJECT_DESCRIPTION);
		managerEmail = request.getParameter(PROJECT_MANAGER);
		userRole = (String) request.getSession().getAttribute(USER_ROLE);
		oldProject = (Project) request.getSession().getAttribute("oldProject");
		oldManager = oldProject.getEmployee();

		/**
		 * Project Parameters checks
		 */
		if(userRole.equals("Admin")) {
			roleOk = true;
		}

		if(name.matches("^[A-Za-z0-9]+$") && name.length() > 3 && name.length() < 16 && !name.equals("") && !(name==null)) {
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
			request.getRequestDispatcher("/ProjectsListPage").forward(request, response);
			throw new IllegalArgumentException();
		}

		if(!(manager==null) && managerEmail.matches("^[a-z]{1}\\.[a-z]+[1-9]*\\@wlb.it$") && !managerEmail.equals("") && !(managerEmail==null) && manager.getStatus()==1) {
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
				/**
				 * Format error
				 */
				request.getRequestDispatcher("/ProjectsListPage").forward(request, response);
				throw new IllegalArgumentException();
			}
		}

		if(nameOk && scopeOk && startDateOk && endDateOk && descriptionOk && managerEmailOk && roleOk) {
			if(!managerEmail.equals(oldProject.getEmployee().getEmail())) {
				/**
				 * Checking if the manager is changed
				 */
				oldManager.removeProjects1(oldProject);
				manager.addProjects1(oldProject);
				employeeDao.update(oldManager);
			}
			
			/**
			 * Update of project's attributes 
			 */
			oldProject.setName(name);
			oldProject.setScope(scope);
			oldProject.setStartDate(startDate);
			oldProject.setEndDate(endDate);
			oldProject.setDescription(description);
			oldProject.setEmployee(manager);			
			projectDao.update(oldProject);

			request.setAttribute("result", "success");
			request.getSession().removeAttribute("oldProject");
			request.getSession().removeAttribute("startDate");
			request.getSession().removeAttribute("endDate");
			request.getRequestDispatcher("/ProjectsListPage").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
