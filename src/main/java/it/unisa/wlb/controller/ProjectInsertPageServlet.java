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

    public ProjectInsertPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("employeeList");
		request.getRequestDispatcher("WEB-INF/ProjectInsertion.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
