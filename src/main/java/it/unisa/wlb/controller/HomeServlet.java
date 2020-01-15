package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDao;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * This servlet aims to redirect to the Homepage
 * 
 * @author Emmanuel Tesauro
 */
@WebServlet("")
@Interceptors({ LoggerSingleton.class })
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private ISmartWorkingPrenotationDao smartWorkingDao;

    @EJB
    private IWorkstationPrenotationDao workstationPrenotationDao;

    /**
     * This set method is used during testing in order to simulate the behaviour of the dao class
     *
     * @param smartWorkingDao
     */
    public void setSmartWorkingDao(ISmartWorkingPrenotationDao smartWorkingDao) {
        this.smartWorkingDao = smartWorkingDao;
    }

    /**
     * This set method is used during testing in order to simulate the behaviour of the dao class
     * 
     * @param workstationPrenotationDao
     */
    public void setWorkstationPrenotationDao(IWorkstationPrenotationDao workstationPrenotationDao) {
        this.workstationPrenotationDao = workstationPrenotationDao;
    }

    /**
     * @param request
     *            Object that identifies an HTTP request
     * @param response
     *            Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @pre request.getSession().getAttribute("user") != null
     * @pre request.getSession().getAttribute("userRole") == null
     * @pre request.getSession().getAttribute("user") != null
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("user") == null) {
            request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request, response);
        } else {
            JSONArray jsonList = new JSONArray();
            if (request.getSession().getAttribute("userRole") == null) {

                Employee employee = (Employee) request.getSession().getAttribute("user");

                /**
                 * Get information about the next calendar week (number and year)
                 */
                Calendar calendar = Calendar.getInstance();
                TimeZone timeZone = calendar.getTimeZone();
                ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
                LocalDate today = LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
                LocalDate monday = today.with(DayOfWeek.MONDAY);
                LocalDate friday = monday.plusDays(4);
                calendar.setTime(Date.from(friday.atStartOfDay().atZone(zoneId).toInstant()));
                int calendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
                int year = calendar.get(Calendar.YEAR);

                /**
                 * Get SmartWorkingPrenotation list
                 */
                SmartWorkingPrenotation smartWorkingPrenotation = null;
                try {
                    smartWorkingPrenotation = smartWorkingDao.retrieveByWeeklyPlanning(calendarWeek, year,
                            employee.getEmail());
                } catch (Exception exception) {

                }

                List<PrenotationDate> smartWorkingPrenotationDateList = null;
                try {
                    smartWorkingPrenotationDateList = smartWorkingPrenotation.getPrenotationDates();
                } catch (Exception exception) {
                }
                if (smartWorkingPrenotationDateList != null) {
                    for (int i = 0; i < smartWorkingPrenotationDateList.size(); i++) {
                        Date tempDate = smartWorkingPrenotationDateList.get(i).getId().getDate();
                        LocalDate tempDateConverted = new Date(tempDate.getTime()).toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate();
                        JSONObject object = new JSONObject();
                        object.put("date", tempDateConverted);
                        object.put("type", "smartWorking");
                        jsonList.put(object);
                    }
                }

                /**
                 * Get WorkstationPrenotationList
                 */
                List<WorkstationPrenotation> workstationPrenotations = null;
                try {
                    workstationPrenotations = workstationPrenotationDao.retrieveByWeeklyPlanning(calendarWeek, year,
                            employee.getEmail());
                } catch (Exception exception) {
                }

                if (workstationPrenotations != null) {
                    for (int i = 0; i < workstationPrenotations.size(); i++) {
                        Date tempDate = workstationPrenotations.get(i).getId().getPrenotationDate();
                        LocalDate tempDateConverted = new Date(tempDate.getTime()).toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate();
                        JSONObject object = new JSONObject();
                        object.put("date", tempDateConverted);
                        object.put("floor",
                                workstationPrenotations.get(i).getWorkstation().getRoom().getFloor().getNumFloor());
                        object.put("room",
                                workstationPrenotations.get(i).getWorkstation().getRoom().getId().getNumRoom());
                        object.put("workstation",
                                workstationPrenotations.get(i).getWorkstation().getId().getWorkstation());
                        object.put("type", "workstation");
                        jsonList.put(object);
                    }
                }

            }

            request.setAttribute("jsonList", jsonList);
            request.getRequestDispatcher("WEB-INF/Homepage.jsp").forward(request, response);
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
