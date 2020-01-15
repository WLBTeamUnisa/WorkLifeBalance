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

import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * This servlet is used to retrieve project suggestions.
 * 
 * @author Michele Montano
 */
@WebServlet(name = "SearchProjectServlet", urlPatterns = "/SearchProjectServlet")
@Interceptors({LoggerSingleton.class})
public class SearchProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IProjectDao projectDao;

	/**
     * Default constructor
     */
	public SearchProjectServlet() {
		super();
	}

	/**
	 * This contructor is used during the testing to simulate the behaviour of the dao class
	 *  
	 * @param projectDao
	 */
	public SearchProjectServlet(IProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * @param request Object that identifies an HTTP request
	 * @param response Object that identifies an HTTP response
	 * @pre request != null
	 * @pre response != null
	 * @pre request.getParameter("name")!=null
	 * @post projectList.toString()!=null
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectName;
		projectName = request.getParameter("name");
		List<Project> list = null;

		if(projectName.length() > 15) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Il parametro non rispetta la lunghezza");
			response.getWriter().flush();
		}
		JSONArray projectList = new JSONArray();
		if(projectName != null) {
			if(projectName.equals(""))
				list=projectDao.retrieveAll();
			else
				list = projectDao.searchByName(projectName);

			for(int i = 0; i < list.size(); i++) {
				JSONObject object = new JSONObject();
				object.put("name", list.get(i).getName());
				projectList.put(object);
			} 
			response.setContentType("application/json");
			response.getWriter().append(projectList.toString());
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
