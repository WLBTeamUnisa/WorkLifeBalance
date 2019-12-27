package it.unisa.wlb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.dao.IProjectDAO;

/**
 * This servlet is used to retrieve project suggestions.
 * @author Michele
 */
@WebServlet(name = "SearchProjectServlet", urlPatterns = "/SearchProjectServlet")
public class SearchProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB
    private IProjectDAO projectDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

    /**
     * This contructor is used during the testing to simulate the behaviour of the dao class
     *  
     * @param projectDao
     */
    
    public SearchProjectServlet(IProjectDAO projectDao) {
    	this.projectDao = projectDao;
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
