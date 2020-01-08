package it.unisa.wlb.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.ShowPlanimetryPageServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.dao.IRoomDao;

/**
 * The aim of this class is testing ShowPlanimetryPageServlet.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class ShowPlanimetryPageServletTest {

	@Mock 
	private IRoomDao roomDao;
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private ShowPlanimetryPageServlet servlet;
	private Employee employee;
	
	private Floor floor;
	private Room room;
	private RoomPK roomPk;
	private List<Room> roomList;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ShowPlanimetryPageServlet();
		

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
		
		
		roomList = new ArrayList<>();
		roomList.add(room);
		floor.setRooms(roomList);
	}

	@Test
	void showPlanimetrySuccess() throws ServletException, IOException {
		employee = new Employee();
		String json = "[{\"floor\":1,\"room\":1}]";
		request.getSession().setAttribute("user", employee);
		when(roomDao.retrieveAll()).thenReturn(roomList);
		servlet.setRoomDao(roomDao);
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("insertedPlanimetry");
		assertEquals(json, attribute);
	}
	
	@Test
	void employeeIsNull() throws ServletException, IOException {
		request.getSession().setAttribute("user", null);
		servlet.doPost(request, response);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void planimetryNotExists() throws ServletException, IOException {
		String errorMessage = "Planimetria assente nel database";
		employee = new Employee();
		request.getSession().setAttribute("user", employee);
		when(roomDao.retrieveAll()).thenThrow(Exception.class);
		servlet.setRoomDao(roomDao);
		try {
			servlet.doPost(request, response);
		} catch (Exception e) {
			;
		} finally {
			assertTrue(response.getContentAsString().contains(errorMessage) && response.getStatus()==HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
