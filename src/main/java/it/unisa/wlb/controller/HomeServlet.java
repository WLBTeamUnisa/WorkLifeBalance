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
 * Servlet implementation class HomeServlet
 */
@WebServlet("")
@Interceptors({LoggerSingleton.class})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")==null) {
			request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request, response);			
		} else {
			request.getRequestDispatcher("WEB-INF/Homepage.jsp").forward(request, response);			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
