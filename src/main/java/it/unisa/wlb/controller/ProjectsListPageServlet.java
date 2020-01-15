package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this Servlet is redirecting to ProjectList.jsp
 * 
 * @author Michele Montano
 * 
 */
@WebServlet(name = "ProjectsListPageServlet", urlPatterns = "/ProjectsListPage")
@Interceptors({ LoggerSingleton.class })
public class ProjectsListPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IProjectDao projectDao;

    /**
     * Default constructor
     */
    public ProjectsListPageServlet() {
        super();
    }

    /**
     * This contructor is used during the testing to simulate the behaviour of the dao class
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
     * @post request.getAttribute("projectList")!=null
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Project> list = null;
        try {
            list = projectDao.retrieveAll();
            request.setAttribute("projectList", list);
            request.getRequestDispatcher("WEB-INF/ProjectList.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher(".").forward(request, response);
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
