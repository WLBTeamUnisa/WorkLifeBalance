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
 * The aim of this servlet is redirecting to ProjectInsertion.jsp 
 * 
 * @author Luigi Cerrone
 * 
 */
@WebServlet(name="ProjectInsertPageServlet", urlPatterns="/ProjectInsertPage")
@Interceptors({LoggerSingleton.class})
public class ProjectInsertPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public ProjectInsertPageServlet() {
        super();
    }

    /**
	 * @param request Object that identifies an HTTP request
	 * @param response Object that identifies an HTTP response
	 * @pre request != null
	 * @pre response != null
	 * @post request.getSession().getAttribute("employeeList")==null
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("employeeList");
		request.getRequestDispatcher("WEB-INF/ProjectInsertion.jsp").forward(request, response);
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
