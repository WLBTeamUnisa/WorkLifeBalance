package it.unisa.wlb.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchEmployeeServlet
 */
@WebServlet("/SearchEmployeeServlet")
public class SearchEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployeeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * NECESSITA DI IMPLEMENTARE PRIMA JPA E BEAN
		 * 
		JSONArray videogiochiJson = new JSONArray();
		String query = request.getParameter("testoParziale");
		List<VideogiocoBean> videogiochi=null;
		if (query != null) {			
			try {
				videogiochi = videogiocoDAO.doRetrieveByTitolo(query);
			} catch (SQLException e) {
				;
			}
			for (VideogiocoBean x : videogiochi) {
				System.out.println("Inserisco in videogiochiJson: " + x.getTitolo());
				videogiochiJson.put(x.getTitolo());
			}
		}
		System.out.println("videogiochiJson.toString():\t"+videogiochiJson.toString());
		response.setContentType("application/json");
		response.getWriter().append(videogiochiJson.toString());
		*/
		//Non forward/redirecto in quanto viene chiamata via AJAX
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
