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

import it.unisa.wlb.model.dao.IProjectDAO;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * Servlet implementation class CheckProjectServlet
 */
@WebServlet(name="CheckProjectServlet", urlPatterns="/CheckProject")
@Interceptors({LoggerSingleton.class})
public class CheckProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IProjectDAO projectDao;

	public void setProjectDao(IProjectDAO projectDao) {
		this.projectDao = projectDao;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		JSONObject obj = new JSONObject();
		System.out.println(name);
		
		if( (name!=null) && (name.trim().length()>=4) && (name.trim().length()<=15) && (name.matches("^[A-Za-z0-9]+$")) ){
			try {
				projectDao.retrieveByName(name);
				obj.put("available", "no");
			} catch (Exception e) {
				obj.put("available", "yes");
			}		
		} else {
			obj.put("available", "no");
		}
		
		response.setContentType("application/json");
		response.getWriter().append(obj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
