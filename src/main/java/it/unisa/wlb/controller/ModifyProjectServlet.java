package it.unisa.wlb.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.model.dao.IProjectDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this Servlet is to modify a project created
 * 
 * @author Michele Montano, Luigi Cerrone
 *
 */
@WebServlet(name="ModifyProjectServlet", urlPatterns="/ModifyProjectServlet")
@Interceptors({LoggerSingleton.class})
public class ModifyProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IProjectDao projectDao;

	@EJB
	private IEmployeeDao employeeDao;

	private static final String PROJECT_NAME = "name"; 
	private static final String PROJECT_SCOPE = "scope";
	private static final String PROJECT_START_DATE = "startDate"; 
	private static final String PROJECT_END_DATE = "endDate";
	private static final String PROJECT_DESCRIPTION = "description";
	private static final String PROJECT_MANAGER = "managerEmail"; 
	private static final String USER_ROLE = "userRole";

	public ModifyProjectServlet() {
		super();
	}
	
	/**
	 * This set method is used during testing in order to simulate the behaviour of the dao class
	 *
	 * @param projectDao
	 */
	public void setProjectDao(IProjectDao projectDao) {
		this.projectDao=projectDao;
	}
	
	/**
	 * This set method is used during testing in order to simulate the behaviour of the dao class
	 * 
	 * @param employeeDao
	 */
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao=employeeDao;
	}

	/**
	 * @param request Object that identifies an HTTP request
	 * @param response Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @pre request.getParameter("name") != null
     * @pre request.getParameter("scope") != null
     * @pre request.getParameter("startDate") != null
     * @pre request.getParameter("endDate") != null
     * @pre request.getParameter("description") != null
     * @pre request.getParameter("managerEmail") != null
     * @pre request.getSession().getAttribute("oldProject") != null
     * @pre request.getSession().getAttribute("userRole").equals("Admin") == true
     * @pre request.getSession().getAttribute("employeeList") != null OR request.getSession().getAttribute("employeeList") == null
     * @post oldProject.getName() == request.getParameter("name")
     * @post oldProject.getScope() == request.getParameter("scope") 
     * @post oldProject.getStartDate() == request.getParameter("startDate")
     * @post oldProject.getEndDate() == request.getParameter("endDate")
     * @post oldProject.getDescription() == request.getParameter("description")
     * @post oldProject.getEmployee().getEmail() = request.getParameter("managerEmail")
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
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
		List<Employee> employeesList = (List<Employee>) request.getSession().getAttribute("employeeList");
		oldManager = oldProject.getEmployee();

		/**
		 * Project Parameters checks
		 */
		if(userRole.equals("Admin")) {
			roleOk = true;
		}

		if(!(name==null) && !name.equals("") && name.matches("^[A-Za-z0-9]+$") && name.length() > 3 && name.length() < 16) {
			nameOk = true;
		}

		if(!(scope==null) && !scope.equals("") && scope.matches("^[A-Za-z\\s]+$") && scope.length() > 2 && scope.length() < 26) {
			scopeOk = true;
		}

		if(!(startDateString==null) && !startDateString.equals("") && startDateString.matches("^(19|20)\\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$")) {
			startDateOk = true;
		}

		if(!(endDateString==null) && !endDateString.equals("") && endDateString.matches("^(19|20)\\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$")) {
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
			request.setAttribute("result", "error");
			request.getRequestDispatcher("/ProjectsListPage").forward(request, response);
			throw new IllegalArgumentException();
		}

		if(!managerEmail.equals("") && managerEmail.matches("^[a-z]{1}\\.[a-z]+[1-9]+\\@wlb.it$") && manager.getStatus()==1) {
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
			try { 
				projectDao.update(oldProject);
			}

			catch(Exception exception) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("\nErrore nell'aggiornamento del progetto");			
				response.getWriter().flush();
				return;
			}

			request.setAttribute("result", "success");
			request.setAttribute("status", "modifying");
			request.setAttribute("Project", oldProject);
			request.getSession().removeAttribute("oldProject");
			request.getSession().removeAttribute("startDate");
			request.getSession().removeAttribute("endDate");
			
			if(employeesList == null || employeesList.isEmpty()) {
				request.getRequestDispatcher("/ProjectsListPage").forward(request, response);
			} else {
				request.getRequestDispatcher("/AddEmployeesToProjectServlet").forward(request, response);
			}
		} else {
			request.setAttribute("result", "error");
			request.getRequestDispatcher("/ProjectsListPage").forward(request, response);
		}
	}

	/**
	 * @param request Object that identifies an HTTP request
	 * @param response Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
