package it.unisa.wlb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDAO;

/**
 * Servlet implementation class ShowProjectPageManagerServlet
 */
@WebServlet(urlPatterns = "/ShowProjectPageManager", name = "ShowProjectPageManagerServlet")
public class ShowProjectPageManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	IProjectDAO projectDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProjectPageManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("projectName");
		Project project = null;
		if(name!=null) {
			project = projectDao.retrieveByName(name);
			if(project!=null) {
				request.setAttribute("project", project);
				request.getRequestDispatcher("WEB-INF/ProjectForManager.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher(".").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
