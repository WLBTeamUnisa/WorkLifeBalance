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

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.model.dao.IProjectDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * This Servlet aims to suggest employees
 * 
 * @author Emmanuel Tesauro
 * 
 */
@WebServlet(name = "SuggestionEmployees", urlPatterns = "/SuggestionEmployees")
@Interceptors({ LoggerSingleton.class })
public class SuggestionEmployeesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String EMAIL_EMPLOYEE = "email";
    private static final String FLAG = "flag";

    @EJB
    private IProjectDao projectDao;

    @EJB
    private IEmployeeDao employeeDao;

    /**
     * Default constructor
     */
    public SuggestionEmployeesServlet() {
        super();
    }

    /**
     * This set method is used during testing in order to simulate the behaviour of the dao class
     * 
     * @param employeeDao
     */
    public void setEmployeeDao(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * @param request
     *            Object that identifies an HTTP request
     * @param response
     *            Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @pre request.getParameter(EMAIL_EMPLOYEE) != null
     * @pre request.getParameter(FLAG) != null
     * @post jsonList.toString()!=null
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONArray jsonList = new JSONArray();

        String employeeEmail = request.getParameter(EMAIL_EMPLOYEE);
        String flagString = request.getParameter(FLAG);

        List<Employee> employeesList = null;

        if ((employeeEmail != null && employeeEmail != "") && flagString != null) {
            int flag = Integer.parseInt(flagString);
            if (flag == 0) {
                employeesList = employeeDao.suggestByEmail(employeeEmail);
            } else if (flag == 1) {
                employeesList = employeeDao.retrieveSuggestsManagerByEmail(employeeEmail);
            }

            for (int i = 0; i < employeesList.size(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("email", employeesList.get(i).getEmail());
                jsonList.put(obj);
            }

            response.setContentType("application/json");
            response.getWriter().append(jsonList.toString());
        }
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
