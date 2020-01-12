package it.unisa.wlb.controller;

import java.io.IOException;

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
 * This servlet aims to show a project supervised by a manager
 * 
 * @author Luigi Cerrone
 */
@WebServlet(urlPatterns = "/ShowSupervisedProject", name = "ShowSupervisedProjectServlet")
@Interceptors({LoggerSingleton.class})
public class ShowSupervisedProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IProjectDao projectDao;
    
	/**
     * Default constructor
     */
    public ShowSupervisedProjectServlet() {
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
	 * @param request Object that identifies an HTTP request
	 * @param response Object that identifies an HTTP response
	 * @pre request != null
	 * @pre response != null
	 * @pre request.getParameter("projectName") != null
	 * @post request.getAttribute("project") != null || request.getAttribute("result")
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("projectName");
		Project project = null;
		if(name!=null) {
			try {
				project = projectDao.retrieveByName(name);
			} catch(Exception exception) {
				request.setAttribute("result", "error");
				request.getRequestDispatcher(".").forward(request, response);
			}
			if(project!=null) {
				request.setAttribute("project", project);
				request.getRequestDispatcher("WEB-INF/SupervisedProject.jsp").forward(request, response);
			} else {
				request.setAttribute("result", "error");
				request.getRequestDispatcher(".").forward(request, response);
			}
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
