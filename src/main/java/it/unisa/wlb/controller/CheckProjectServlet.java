package it.unisa.wlb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import it.unisa.wlb.model.dao.IProjectDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this Servlet is checking a Project 
 * 
 * @author Emmanuel Tesauro
 */
@WebServlet(name="CheckProjectServlet", urlPatterns="/CheckProject")
@Interceptors({LoggerSingleton.class})
public class CheckProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IProjectDao projectDao;

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
     * @pre request.getParameter("name") != null
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		JSONObject object = new JSONObject();
		
		if( (name!=null) && (name.trim().length()>=4) && (name.trim().length()<=15) && (name.matches("^[A-Za-z0-9]+$")) ){
			try {
				projectDao.retrieveByName(name);
				object.put("available", "no");
			} catch (Exception e) {
				object.put("available", "yes");
			}		
		} else {
			object.put("available", "no");
		}
		
		response.setContentType("application/json");
		response.getWriter().append(object.toString());
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
