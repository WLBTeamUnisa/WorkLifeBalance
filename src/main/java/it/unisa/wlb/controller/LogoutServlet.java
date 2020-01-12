package it.unisa.wlb.controller;

import java.io.IOException;

import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.utils.LoggerSingleton;

/**
 * This servlet allows to logout from the system
 * 
 * @author Vincenzo Fabiano
 */
@WebServlet(name="LogoutServlet", urlPatterns="/LogoutServlet")
@Interceptors({LoggerSingleton.class})
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param request Object that identifies an HTTP request
	 * @param response Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(".");
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
