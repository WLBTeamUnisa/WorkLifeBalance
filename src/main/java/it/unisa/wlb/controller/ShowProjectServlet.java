package it.unisa.wlb.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this Servlet is to show a specific project
 * 
 * @author Emmanuel Tesauro
 *
 */
@WebServlet(name = "ShowProjectServlet", urlPatterns = "/ShowProjectServlet")
@Interceptors({ LoggerSingleton.class })
public class ShowProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IProjectDao projectDao;

    /**
     * Default constructor
     */
    public ShowProjectServlet() {
        super();
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
     * @pre request.getParameter("name") != null
     * @pre request.getSession().getAttribute("userRole").equals("Admin")
     * @post request.getAttribute("result")!=null && request.getAttribute("projectList")==null
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("employeeList");
        session.removeAttribute("currentEmployees");
        if (session.getAttribute("userRole").equals("Admin")) {
            String name = request.getParameter("name");

            if (name != null && name != "") {
                try {
                    String startDateString;
                    String endDateString;

                    Project project = projectDao.retrieveByName(name);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    startDateString = formatter.format(project.getStartDate());
                    endDateString = formatter.format(project.getEndDate());
                    session.setAttribute("startDate", startDateString);
                    session.setAttribute("endDate", endDateString);
                    session.setAttribute("oldProject", project);
                    session.setAttribute("currentEmployees", project.getEmployees());
                    request.setAttribute("result", "ok");
                    request.getRequestDispatcher("WEB-INF/Project.jsp").forward(request, response);
                } catch (Exception exception) {
                    request.setAttribute("result", "error");
                    request.removeAttribute("projectList");
                    session.removeAttribute("currentEmployees");
                    request.getRequestDispatcher("/ProjectsListPage").forward(request, response);
                }
            } else {
                request.setAttribute("result", "error");
                request.removeAttribute("projectList");
                session.removeAttribute("currentEmployees");
                request.getRequestDispatcher("/ProjectsListPage").forward(request, response);
            }
        } else {
            request.setAttribute("result", "error");
            request.removeAttribute("projectList");
            session.removeAttribute("currentEmployees");
            request.getRequestDispatcher("/ProjectsListPage").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
