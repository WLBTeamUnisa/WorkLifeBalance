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
 * The aim of this Servlet is redirecting to EmployeeRegistration.jsp
 * 
 * @author Emmanuel Tesauro
 */
@WebServlet(name = "EmployeeInsertPageServlet", urlPatterns = "/EmployeeInsertPage")
@Interceptors({ LoggerSingleton.class })
public class EmployeeInsertPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeInsertPageServlet() {
        super();
    }

    /**
     * @param request
     *            Object that identifies an HTTP request
     * @param response
     *            Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/EmployeeRegistration.jsp").forward(request, response);
    }

    /**
     * @param request
     *            Object that identifies an HTTP request
     * @param response
     *            Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
