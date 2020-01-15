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
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this servlet is to dispatch correctly the employee on CalendarHistory.jsp
 * 
 * @author Luigi Cerrone
 *
 */
@WebServlet(name = "ShowCalendarHistoryPageServlet", urlPatterns = "/ShowCalendarHistoryPage")
@Interceptors({ LoggerSingleton.class })
public class ShowCalendarHistoryPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IEmployeeDao employeeDao;

    /**
     * Default constructor
     */
    public ShowCalendarHistoryPageServlet() {
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
     * @pre request.getSession().getAttribute("user")!=null
     * @post request.getAttribute("result")!=null
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailEmployee = request.getParameter("employeeEmail");
        Employee sessionEmployee = (Employee) session.getAttribute("user");
        if (sessionEmployee != null) {
            if (emailEmployee != null) {
                /**
                 * if the parameter employeeEmail isn't null, it means that the manager want to show an employee's
                 * calendar history, so it needs to verify that the sessionEmployee is a manager
                 */
                if (sessionEmployee.getStatus() == 1) {
                    try {
                        Employee employee = employeeDao.retrieveByEmail(emailEmployee);
                        List<Project> managerProjects = sessionEmployee.getProjects1();
                        List<Project> employeeProjects = employee.getProjects2();
                        int flag = 0;

                        for (int i = 0; i < managerProjects.size() && flag == 0; i++) {
                            for (int j = 0; j < employeeProjects.size() && flag == 0; j++) {
                                if (managerProjects.get(i).getName().equals(employeeProjects.get(j).getName())) {
                                    flag = 1;
                                }
                            }
                        }

                        if (flag == 1) {
                            request.setAttribute("employeeSupervised", employee.getEmail());
                            JSONObject obj = new JSONObject();
                            obj.put("name", employee.getName());
                            obj.put("surname", employee.getSurname());
                            request.setAttribute("employeeJson", obj.toString());
                            request.setAttribute("result", "success");
                            request.getRequestDispatcher("WEB-INF/CalendarHistory.jsp").forward(request, response);
                        }

                        else {
                            request.setAttribute("result", "error");
                            request.getRequestDispatcher(".").forward(request, response);
                        }
                    }

                    catch (Exception exception) {
                        request.setAttribute("result", "error");
                        request.getRequestDispatcher(".").forward(request, response);
                    }
                }

                else {
                    request.setAttribute("result", "error");
                    request.getRequestDispatcher(".").forward(request, response);
                }
            }

            else {
                /**
                 * if the parameter employeeEmail, it means that an employee tries to show his CalendarHistory
                 */
                request.setAttribute("result", "success");
                request.getRequestDispatcher("WEB-INF/CalendarHistory.jsp").forward(request, response);
            }
        }

        else {
            request.setAttribute("result", "error");
            request.getRequestDispatcher(".").forward(request, response);
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
