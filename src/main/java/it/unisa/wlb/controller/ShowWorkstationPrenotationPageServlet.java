package it.unisa.wlb.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.dao.IPrenotationDateDao;
import it.unisa.wlb.model.dao.IRoomDao;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDao;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * This servlet aims to redirect to Workstation Prenotation Page
 * 
 * @author Vincenzo Fabiano, Luigi Cerrone, Sabato Nocera
 *
 */
@WebServlet(name = "ShowWorkstationPrenotationPageServlet", urlPatterns = "/ShowWorkstationPrenotationPage")
@Interceptors({ LoggerSingleton.class })
public class ShowWorkstationPrenotationPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final static String FLOOR = "floor";
    private final static String ROOM = "room";
    private final static String PLANIMETRY = "insertedPlanimetry";

    @EJB
    private IRoomDao roomDao;

    @EJB
    private ISmartWorkingPrenotationDao smartWorkingDao;

    @EJB
    private IPrenotationDateDao prenotationDateDao;

    @EJB
    private IWorkstationPrenotationDao workstationPrenotationDao;

    /**
     * Default constructor
     */
    public ShowWorkstationPrenotationPageServlet() {
        super();
    }

    /**
     * This set method is used during testing in order to simulate the behaviour of the dao class
     * 
     * @param roomDao
     */
    public void setRoomDao(IRoomDao roomDao) {
        this.roomDao = roomDao;
    }

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
     * @pre request.getParameter(FLAG) != null
     * @post request.getAttribute("error") != null || request.getAttribute("availableDates") != null
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = (Employee) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            request.getRequestDispatcher("WEB-INF/Index.jsp").forward(request, response);
        } else {
            /**
             * Get information about the next calendar week (number and year)
             */
            Calendar calendar = Calendar.getInstance();
            TimeZone timeZone = calendar.getTimeZone();
            ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
            LocalDate today = LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
            LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
            LocalDate newDate = nextMonday.plusDays(7);
            calendar.setTime(Date.from(newDate.atStartOfDay().atZone(zoneId).toInstant()));
            int nextCalendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
            int year = calendar.get(Calendar.YEAR);
            if ((nextMonday.getYear() == newDate.getYear()) && nextCalendarWeek == 1) {
                year = year + 1;
            }
            List<LocalDate> listDates = new ArrayList<>();
            listDates.add(newDate);
            listDates.add(newDate.plusDays(1));
            listDates.add(newDate.plusDays(2));
            listDates.add(newDate.plusDays(3));
            listDates.add(newDate.plusDays(4));

            SmartWorkingPrenotation smartWorkingPrenotation = null;
            try {
                smartWorkingPrenotation = smartWorkingDao.retrieveByWeeklyPlanning(nextCalendarWeek, year,
                        employee.getEmail());
            } catch (Exception exception) {
                request.setAttribute("error", "Prima devi prenotare i giorni di Smart Working!");
                request.getRequestDispatcher("/ShowSmartWorkingPrenotation").forward(request, response);
                ;
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
                    listDates.remove(tempDateConverted);
                }
            }

            List<WorkstationPrenotation> workstationPrenotations = null;
            try {
                workstationPrenotations = workstationPrenotationDao.retrieveByWeeklyPlanning(nextCalendarWeek, year,
                        employee.getEmail());
            } catch (Exception exception) {
                ;
            }
            if (workstationPrenotations != null) {
                ;
                for (int i = 0; i < workstationPrenotations.size(); i++) {
                    Date tempDate = workstationPrenotations.get(i).getId().getPrenotationDate();
                    LocalDate tempDateConverted = new Date(tempDate.getTime()).toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate();
                    listDates.remove(tempDateConverted);
                }
            }

            try {
                List<Room> rooms = roomDao.retrieveAll();
                if (rooms != null && rooms.size() > 0) {
                    JSONArray jsonArray = new JSONArray();
                    for (Room room : rooms) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put(FLOOR, room.getId().getNumFloor());
                        jsonObject.put(ROOM, room.getId().getNumRoom());
                        jsonArray.put(jsonObject);
                    }

                    request.setAttribute(PLANIMETRY, jsonArray.toString());
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Planimetria assente nel database");
                response.getWriter().flush();
                return;
            }

            request.setAttribute("availableDates", listDates);
            request.getRequestDispatcher("WEB-INF/WorkstationPrenotation.jsp").forward(request, response);
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
