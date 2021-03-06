package it.unisa.wlb.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.dao.IFloorDao;
import it.unisa.wlb.model.dao.IRoomDao;
import it.unisa.wlb.model.dao.IWorkstationDao;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this Servlet is to return the availability of the workstations of a room of a certain floor
 * 
 * @author Sabato Nocera, Luigi Cerrone
 *
 */
@WebServlet(name = "WorkstationAvailabilityServlet", urlPatterns = "/WorkstationsAvailability")
@Interceptors({ LoggerSingleton.class })
public class WorkstationsAvailabilityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String WORKSTATION = "workstation";
    private static final String STATUS = "status";
    private static final String FLOOR = "floor";
    private static final String ROOM = "room";
    private static final String DATE = "date";
    private final static int MIN = 1;

    private int maxFloor;
    private int maxRoom;

    @EJB
    private IFloorDao floorDao;

    @EJB
    private IRoomDao roomDao;

    @EJB
    private IWorkstationDao workstationDao;

    @EJB
    private IWorkstationPrenotationDao workstationPrenotationDao;

    /**
     * Default constructor
     */
    public WorkstationsAvailabilityServlet() {
        super();
    }

    /**
     * This set method is used during testing in order to simulate the behaviour of the dao class
     * 
     * @param floorDao
     */
    public void setFloorDao(IFloorDao floorDao) {
        this.floorDao = floorDao;
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
     * @param workstationDao
     */
    public void setWorkstationDao(IWorkstationDao workstationDao) {
        this.workstationDao = workstationDao;
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
     * The jsp calls this method through AJAX; it returns in the response the availability of the workstations of a room
     * of a certain floor
     * 
     * @param request
     *            Object that identifies an HTTP request
     * @param response
     *            Object that identifies an HTTP response
     * @pre request != null
     * @pre response != null
     * @pre request.getParameter(FLOOR) != null
     * @pre request.getParameter(ROOM) != null
     * @pre request.getParameter(DATE) != null
     * @post jsonArray.toString()!=null
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int floor = Integer.parseInt(request.getParameter(FLOOR));
        int room = Integer.parseInt(request.getParameter(ROOM));
        String datePrenotation = request.getParameter(DATE);

        try {
            maxFloor = floorDao.countMax();
            maxRoom = roomDao.countMaxByFloor(floor);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(
                    "\nErrore nel recupero delle informazioni relative al piano massimo, alla stanza massima e alla postazione massima");
            response.getWriter().flush();
            return;
        }

        if (floor < MIN || floor > maxFloor || room < MIN || room > maxRoom || (datePrenotation == null)
                || !datePrenotation.matches("^(19|20)\\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$")
                || datePrenotation.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("\nI parametri inseriti non rispettano il formato/lunghezza");
            response.getWriter().flush();
            throw new IllegalArgumentException("I parametri inseriti non rispettano il formato/lugnhezza");
        }

        JSONArray jsonArray = new JSONArray();

        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formatter.parse(datePrenotation);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("\nErrore nel parsing della data ricevuta come parametro");
            response.getWriter().flush();
            return;
        }

        List<WorkstationPrenotation> workstationPrenotations = null;
        try {
            workstationPrenotations = workstationPrenotationDao.retrieveByWorkstationDate(date, floor, room);
        } catch (Exception exception) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("\nErrore nel recupero delle prenotazioni");
            response.getWriter().flush();
            return;
        }

        List<Workstation> workstations = null;
        try {
            workstations = workstationDao.retrieveByFloorAndRoom(floor, room);
        } catch (Exception exception) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("\nErrore nel recupero delle postazioni");
            response.getWriter().flush();
            return;
        }

        for (Workstation workstation : workstations) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(WORKSTATION, workstation.getId().getWorkstation());
            jsonObject.put(STATUS, 0);
            jsonArray.put(jsonObject);
        }

        if (workstationPrenotations != null) {
            for (WorkstationPrenotation workstationPrenotation : workstationPrenotations) {
                Workstation workstation = workstationPrenotation.getWorkstation();
                JSONObject jsonObject = jsonArray.getJSONObject(workstation.getId().getWorkstation() - 1);
                jsonObject.remove(STATUS);
                jsonObject.put(STATUS, 1);
            }
        }

        response.setContentType("application/json");
        response.getWriter().append(jsonArray.toString());
    }

    /**
     * The jsp calls this method through AJAX; it returns in the response the availability of the workstations of a room
     * of a certain floor
     * 
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
