package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.dao.IRoomDao;

/**
 * Servlet implementation class PlanimetryInsertionPageServlet. It is used to access to PlanimetryInsertion.jsp.
 * 
 * @author Sabato
 *
 */
@WebServlet("/PlanimetryInsertionPage")
public class PlanimetryInsertionPageServlet extends HttpServlet {
	
	private final static String FLOOR = "floor";
	private final static String ROOM = "room";
	private final static String WORKSTATIONS = "workstation";
	private final static String PLANIMETRY = "insertedPlanimetry";
	
	@EJB
	private IRoomDao roomDao;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlanimetryInsertionPageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!(request.getSession().getAttribute("user")==null) || !(request.getSession().getAttribute("userRole").equals("Admin"))) {
			//exception
		}
		
		try {
			List<Room> rooms = roomDao.retrieveAll();
			if(rooms!=null && rooms.size()>0) {
				JSONArray jsonArray = new JSONArray();
				for(Room room :  rooms) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put(FLOOR, room.getId().getNumFloor());
					jsonObject.put(ROOM, room.getId().getNumRoom());
					jsonObject.put(WORKSTATIONS, room.getWorkstations().size());
					
					System.err.println(jsonObject.toString());
					
					jsonArray.put(jsonObject);				
				}

				request.setAttribute(PLANIMETRY, jsonArray.toString());				
			}			
		} catch (Exception e) {
			response.getWriter().append("Planimetria assente nel database");
		}
		
		request.getRequestDispatcher("WEB-INF/PlanimetryInsertion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
