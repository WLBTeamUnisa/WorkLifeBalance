package it.unisa.wlb.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IAdminDao;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.model.dao.IProjectDao;
import it.unisa.wlb.utils.LoggerSingleton;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;

/**
 * The aim of this Servlet is to prepare the creation of the project
 * 
 * @author Michele Montano
 * 
 */
@WebServlet(name = "AddProjectServlet", urlPatterns = "/AddProjectServlet")
@Interceptors({ LoggerSingleton.class })
public class AddProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IProjectDao projectDao;
    @EJB
    private IEmployeeDao employeeDao;
    @EJB
    private IAdminDao adminDao;

    private static final String PROJECT_NAME = "name";
    private static final String PROJECT_SCOPE = "scope";
    private static final String PROJECT_START_DATE = "startDate";
    private static final String PROJECT_END_DATE = "endDate";
    private static final String PROJECT_DESCRIPTION = "description";
    private static final String PROJECT_MANAGER = "managerEmail";
    private static final String USER = "user";
    private static final String USER_ROLE = "userRole";

    public AddProjectServlet() {
        super();
    }

    /**
     * This constructor is used during testing in order to simulate the behaviour of the dao class
     * 
     * @param projectDao
     * @param employeeDao
     */
    public AddProjectServlet(IProjectDao projectDao, IEmployeeDao employeeDao) {
        super();
        this.projectDao = projectDao;
        this.employeeDao = employeeDao;
    }

    /**
     * @param request
     *            Object that identifies an HTTP request
     * @param response
     *            Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @pre request.getParameter("name") != null
     * @pre request.getParameter("scope") != null
     * @pre request.getParameter("startDate") != null
     * @pre request.getParameter("endDate") != null
     * @pre request.getParameter("description") != null
     * @pre request.getParameter("managerEmail") != null
     * @pre request.getParameter("user") != null
     * @pre request.getSession().getAttribute("userRole").equals("Admin")
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

        boolean nameOk = false;
        boolean scopeOk = false;
        boolean startDateOk = false;
        boolean endDateOk = false;
        boolean descriptionOk = false;
        boolean managerEmailOk = false;
        boolean roleOk = false;

        name = request.getParameter(PROJECT_NAME);
        scope = request.getParameter(PROJECT_SCOPE);
        startDateString = request.getParameter(PROJECT_START_DATE);
        endDateString = request.getParameter(PROJECT_END_DATE);
        description = request.getParameter(PROJECT_DESCRIPTION);
        managerEmail = request.getParameter(PROJECT_MANAGER);
        admin = (Admin) request.getSession().getAttribute(USER);
        userRole = (String) request.getSession().getAttribute(USER_ROLE);

        /**
         * Project Parameters checks
         */
        if (userRole.equals("Admin")) {
            roleOk = true;
        }

        if (!(name == null) && !name.equals("") && name.matches("^[A-Za-z0-9]+$") && name.length() > 3
                && name.length() < 16) {
            nameOk = true;
        }

        if (!(scope == null) && !scope.equals("") && scope.matches("^[A-Za-z\\s]+$") && scope.length() > 2
                && scope.length() < 26) {
            scopeOk = true;
        }

        if (!(startDateString == null) && !startDateString.equals("")
                && startDateString.matches("^(19|20)\\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$")) {
            startDateOk = true;
        }

        if (!(endDateString == null) && !endDateString.equals("")
                && endDateString.matches("^(19|20)\\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$")) {
            endDateOk = true;
        }

        if (description.matches("^[\\s\\S]+$") && description.length() >= 20 && description.length() <= 250) {
            descriptionOk = true;
        }

        /**
         * Checks if the manager is in the Database
         */
        manager = new Employee();
        try {
            manager = employeeDao.retrieveByEmail(managerEmail);
        } catch (Exception e) {
            request.getRequestDispatcher("WEB-INF/ProjectList.jsp").forward(request, response);
            throw new IllegalArgumentException();
        }

        if (!(managerEmail == null) && !managerEmail.equals("")
                && managerEmail.matches("^[a-z]{1}\\.[a-z]+[0-9]+\\@wlb.it$") && !(manager == null)
                && manager.getStatus() == 1) {
            managerEmailOk = true;
        }

        /**
         * Formats the dates in format yyyy-MM-dd
         */
        if (startDateOk && endDateOk) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                startDate = formatter.parse(startDateString);
                endDate = formatter.parse(endDateString);
                if (!endDate.after(startDate)) {
                    startDateOk = false;
                    endDateOk = false;
                }
            } catch (Exception e) {
                request.getRequestDispatcher("WEB-INF/ProjectList.jsp").forward(request, response);
                throw new IllegalArgumentException();
            }
        }

        if (nameOk && scopeOk && startDateOk && endDateOk && descriptionOk && managerEmailOk && roleOk) {
            project = new Project();
            project.setName(name);
            project.setScope(scope);
            project.setStartDate(startDate);
            project.setEndDate(endDate);
            project.setDescription(description);
            project.setEmployee(manager);
            project.setAdmin(admin);

            request.setAttribute("Project", project);
            request.setAttribute("manager", manager);
            request.setAttribute("result", "success");
            request.setAttribute("status", "creating");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AddEmployeesToProjectServlet");
            dispatcher.forward(request, response);
        } else {
            request.getSession().removeAttribute("oldProject");
            request.setAttribute("result", "error");
            request.getRequestDispatcher("WEB-INF/ProjectList.jsp").forward(request, response);
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param request
     *            Object that identifies an HTTP request
     * @param response
     *            Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}