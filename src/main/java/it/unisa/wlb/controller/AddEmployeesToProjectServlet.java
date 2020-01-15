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
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.model.dao.IProjectDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this Servlet is to create effectively the project and to add employees that belong to it
 * 
 * @author Luigi Cerrone
 *
 */
@WebServlet(name = "AddEmployeesToProjectServlet", urlPatterns = "/AddEmployeesToProjectServlet")
@Interceptors({ LoggerSingleton.class })
public class AddEmployeesToProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IProjectDao projectDao;

    @EJB
    private IEmployeeDao employeeDao;

    public AddEmployeesToProjectServlet() {
        super();
    }

    /**
     * This set method is used during testing in order to simulate the behaviour of the dao class
     *
     * @param employeeDao
     */
    public void setEmployeeDao(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * This set method is used during testing in order to simulate the behaviour of the dao class
     * 
     * @param projectDao
     */
    public void setProjectDao(IProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * @param request
     *            Object that identifies an HTTP request
     * @param response
     *            Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @pre request.getAttribute("Project") != null
     * @pre request.getSession().getAttribute("userRole").equals("Admin") == true
     * @pre request.getSession().getAttribute("employeeList") != null
     * @post projectList.size() = @pre projectList.size() + 1
     * @post project.getEmployees().size() = @pre project.getEmployees().size() + employeeList.size();
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee manager;
        String userRole = (String) session.getAttribute("userRole");
        Project project = (Project) request.getAttribute("Project");
        List<Employee> employeesList = (List<Employee>) request.getSession().getAttribute("employeeList");
        List<Employee> currentEmployees;
        String status;
        status = (String) request.getAttribute("status");

        /**
         * Check about admin role
         * 
         */
        if (userRole.equalsIgnoreCase("Admin")) {

            /**
             * Taking the project setted thanks to request's attribute
             */
            if (project == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AddEmployeesToProjectServlet");
                dispatcher.forward(request, response);
                throw new IllegalArgumentException();
            } else {

                if (employeesList != null && employeesList.size() >= 1) {
                    if (status.equals("modifying")) {
                        currentEmployees = project.getEmployees();
                        for (Employee anEmployee : employeesList) {
                            currentEmployees.add(anEmployee);
                        }
                        project.setEmployees(currentEmployees);

                        try {
                            projectDao.update(project);
                        } catch (Exception exception) {
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            response.getWriter().write("\nErrore nell'aggiornamento del progetto");
                            response.getWriter().flush();
                            return;
                        }

                    } else {

                        /**
                         * Insertion of employees into works table
                         * 
                         */
                        project.setEmployees(employeesList);

                        /**
                         * Creation of the new project
                         */
                        try {
                            projectDao.create(project);
                        } catch (Exception exception) {
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            response.getWriter().write("\nErrore nella creazione del progetto");
                            response.getWriter().flush();
                            return;
                        }

                        manager = (Employee) request.getAttribute("manager");
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