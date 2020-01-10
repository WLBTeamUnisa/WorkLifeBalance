package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.PlanimetryInsertionPageServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPK;
import it.unisa.wlb.model.dao.IRoomDao;

/**
 * The aims of this class is testing PlanimetryInsertionPageServlet.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class PlanimetryInsertionPageServletTest {

	@Mock
	private IRoomDao roomDao;
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private PlanimetryInsertionPageServlet servlet;
	
	private Floor floor;
	private Room room;
	private RoomPK roomPk;
	private List<Room> roomList;
	private Workstation workstation1;
	private Workstation workstation2;
	private WorkstationPK workstationPk1;
	private WorkstationPK workstationPk2;
	private List<Workstation> workstationList;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		
		servlet = new PlanimetryInsertionPageServlet();
		
		int numberFloor = 1;
		floor = new Floor();
		floor.setNumFloor(numberFloor);
		int numberRoom = 1;
		roomPk = new RoomPK();
		roomPk.setNumFloor(numberFloor);
		roomPk.setNumRoom(numberRoom);
		room = new Room();
		room.setFloor(floor);
		room.setId(roomPk);
		
		int numberWorkstation1 = 1;
		workstationPk1 = new WorkstationPK();
		workstationPk1.setFloor(numberFloor);
		workstationPk1.setRoom(numberRoom);
		workstationPk1.setWorkstation(numberWorkstation1);
		workstation1 = new Workstation();
		workstation1.setId(workstationPk1);
		workstation1.setRoom(room);
		
		int numberWorkstation2 = 2;
		workstationPk2 = new WorkstationPK();
		workstationPk2.setFloor(numberFloor);
		workstationPk2.setRoom(numberRoom);
		workstationPk2.setWorkstation(numberWorkstation2);
		workstation2 = new Workstation();
		workstation2.setId(workstationPk1);
		workstation2.setRoom(room);
		
		workstationList = new ArrayList<>();
		workstationList.add(workstation1);
		workstationList.add(workstation2);
		
		room.setWorkstations(workstationList);
		
		roomList = new ArrayList<>();
		roomList.add(room);
		floor.setRooms(roomList);
		
	}

	@Test
	void planimetryExists() throws ServletException, IOException {
		Employee employee = new Employee();
		String json = "[{\"workstation\":2,\"floor\":1,\"room\":1}]";
		request.getSession().setAttribute("user", employee);
		when(roomDao.retrieveAll()).thenReturn(roomList);
		servlet.setRoomDao(roomDao);
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("insertedPlanimetry");
		assertEquals(json, attribute);
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void planimetryNotExists() throws ServletException, IOException {
		Employee employee = new Employee();
		String errorMessage = "Planimetria assente nel database";
		request.getSession().setAttribute("user", employee);
		when(roomDao.retrieveAll()).thenThrow(Exception.class);
		servlet.setRoomDao(roomDao);
		servlet.doPost(request, response);
		assertTrue(response.getContentAsString().contains(errorMessage));
		
	}
	

}
