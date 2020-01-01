package it.unisa.wlb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.Workstation;

/**
 * Servlet implementation class WorkstationPrenotationServlet
 */
@WebServlet("/WorkstationPrenotationServlet")
public class WorkstationPrenotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final static String JSON_STRING = "jsonObject";
	private final static String FLOOR = "floor";
	private final static String ROOM = "room";
	private final static String WORKSTATION = "workstation";	
	private final static int MAX_FLOOR = 200;
	private final static int MAX_ROOM = 20;
	private final static int MAX_WORKSTATIONS = 100;
	private final static int MIN = 1;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkstationPrenotationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = (Employee) request.getSession().getAttribute("user");
		String jsonString = request.getParameter(JSON_STRING);
		JSONObject jsonObject =  new JSONObject(jsonString);
		
		/**
		 * floorsNumber mantains the number of floors currently inserted
		 */	
		
		Floor floor = null;
		Room room = null;
		Workstation workstation = null;

		/**
		 * Insertion of workstations of each room and each floor
		 */
		for(int i=0; i<jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			int floorNumber = 0;
			int roomNumber = 0;
			int workstationsNumber = 0;
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
