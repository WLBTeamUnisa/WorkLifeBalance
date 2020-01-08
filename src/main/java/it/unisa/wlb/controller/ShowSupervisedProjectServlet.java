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
import it.unisa.wlb.model.dao.IProjectDAO;
import it.unisa.wlb.model.dao.IRoomDao;
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
	IProjectDAO projectDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowSupervisedProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void setProjectDao(IProjectDAO projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("projectName");
		System.out.println(name);
		Project project = null;
		if(name!=null) {
			project = projectDao.retrieveByName(name);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
