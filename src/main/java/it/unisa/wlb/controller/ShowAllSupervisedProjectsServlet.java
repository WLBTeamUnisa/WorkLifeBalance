package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.List;

import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * This servlet aims to show all projects supervised by a manager
 * 
 * @author Luigi Cerrone
 * 
 */
@WebServlet(name = "ShowAllSupervisedProjectsServlet", urlPatterns = "/ShowAllSupervisedProjectsServlet")
@Interceptors({ LoggerSingleton.class })
public class ShowAllSupervisedProjectsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public ShowAllSupervisedProjectsServlet() {
        super();
    }

    /**
     * @param request
     *            Object that identifies an HTTP request
     * @param response
     *            Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @pre request.getSession().getAttribute("user")!=null
     * @post projectListJson.toString()!=null
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = (Employee) request.getSession().getAttribute("user");
        if (employee != null && employee.getStatus() == 1) {
            List<Project> projectList = employee.getProjects1();
            JSONArray projectListJson = new JSONArray();
            /**
             * Insertion of supervised projects in a JsonArray
             * 
             */
            for (int i = 0; i < projectList.size(); i++) {
                JSONObject object = new JSONObject();
                object.put("projectName", projectList.get(i).getName());
                projectListJson.put(object);
            }

            response.setContentType("application/json");
            response.getWriter().append(projectListJson.toString());
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
