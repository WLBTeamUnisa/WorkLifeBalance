package it.unisa.wlb.test.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.bean.Workstation;

/**
 * The aim of this class is testing Floor.java
 * 
 * @author Sabato Nocera
 *
 */
class FloorTest {
	
	private Floor floor;
	private int numFloor;
	private int numRoom;
	private Admin admin;
	private List<Room> rooms;

	@BeforeEach
	void setUp() throws Exception {
		floor = new Floor();
		
		String email = "m.rossi1@wlbadmin.it";	
		String name = "Mario";
		String surname = "Rossi";
		String password = "MarcoRossi1.";
		List<Floor> floors = new ArrayList<Floor>();
		
		admin = new Admin();
		admin.setEmail(email);
		admin.setName(name);
		admin.setSurname(surname);
		admin.setPassword(password);
		floors.add(floor);
		admin.setFloors(floors);
		admin.setProjects(new ArrayList<Project>());
		
		numFloor=1;
		numRoom=1;
		
		floor.setAdmin(admin);
		floor.setNumFloor(numFloor);
		
		rooms = new ArrayList<Room>();
		
		Room room1 = new Room();
		room1.setFloor(floor);
		RoomPK id1 = new RoomPK();
		id1.setNumFloor(numFloor);
		id1.setNumRoom(numRoom);
		room1.setId(id1);		
		room1.setWorkstations(new ArrayList<Workstation>());
		
		floor.setRooms(rooms);
	}

	@Test
	public void getNumFloorTest() {
		assertEquals(numFloor,floor.getNumFloor());
	}
	@Test
	public void setNumFloorTest() {
		floor.setNumFloor(2);
		assertEquals(2,floor.getNumFloor());
	}
	@Test
	public void getAdminTest() {
		assertEquals(admin,floor.getAdmin());
	}
	@Test
	public void setAdminTest() {
		Admin admin2 = new Admin();
		admin2.setEmail("m.rossi2@wlbadmin.it");
		admin2.setName("Marioo");
		admin2.setSurname("Rossii");
		admin2.setPassword("MarioRossi2..");
		admin2.setFloors(admin.getFloors());
		admin2.setProjects(new ArrayList<Project>());
		
		floor.setAdmin(admin2);
		assertEquals(admin2,floor.getAdmin());
	}
	@Test
	public void getRoomsTest() {
		List<Room> list = floor.getRooms();
		for(Room temp:list)
			if(!rooms.contains(temp)) {
				assertTrue(false);
				return ;
			}
		assertTrue(true);
	}
	@Test
	public void setRoomsTest() {
		rooms.clear();
		
		Room room2 = new Room();
		room2.setFloor(floor);
		RoomPK id2 = new RoomPK();
		id2.setNumFloor(numFloor);
		id2.setNumRoom(numRoom+1);
		room2.setId(id2);		
		room2.setWorkstations(new ArrayList<Workstation>());
		
		rooms.add(room2);
		floor.setRooms(rooms);
		
		if(!floor.getRooms().contains(room2)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	@Test
	public void addRoomTest() {
		Room room2 = new Room();
		room2.setFloor(floor);
		RoomPK id2 = new RoomPK();
		id2.setNumFloor(numFloor);
		id2.setNumRoom(numRoom+1);
		room2.setId(id2);		
		room2.setWorkstations(new ArrayList<Workstation>());
		
		floor.addRoom(room2);
		
		List<Room> list = floor.getRooms();
		
		if(!list.contains(room2)) {
			assertTrue(false);
			return ;
		}
		assertTrue(true);
	}
	@Test
	public void removeRoomTest() {
		Room room2 = new Room();
		room2.setFloor(floor);
		RoomPK id2 = new RoomPK();
		id2.setNumFloor(numFloor);
		id2.setNumRoom(numRoom+1);
		room2.setId(id2);		
		room2.setWorkstations(new ArrayList<Workstation>());
		
		rooms.add(room2);
		
		List<Room> list = floor.getRooms();
		
		if(list.contains(room2)) {				
			floor.removeRoom(room2);
			if(!list.contains(room2)) {
				assertTrue(true);
				return ;
			}
		}
		assertTrue(false);
	}
	@Test
	public void equalsTest1() {
		Floor floor2 = new Floor();
		floor2.setAdmin(admin);
		floor2.setNumFloor(numFloor);
		floor2.setRooms(rooms);
		assertEquals(floor, floor2);
	}
	@Test
	public void equalsTest2() {
		Floor floor2 = new Floor();
		floor2.setAdmin(new Admin());
		floor2.setNumFloor(numFloor);
		floor2.setRooms(rooms);
		assertNotEquals(floor, floor2);
	}
	@Test
	public void equalsTest3() {
		Floor floor2 = new Floor();
		floor2.setAdmin(admin);
		floor2.setNumFloor(3);
		floor2.setRooms(rooms);
		assertNotEquals(floor, floor2);
	}
	@Test
	public void equalsTest4() {
		Floor floor2 = new Floor();
		floor2.setAdmin(admin);
		floor2.setNumFloor(numFloor);
		List<Room> list = new ArrayList<Room>();
		list.add(new Room());
		floor2.setRooms(list);
		assertNotEquals(floor, floor2);
	}
	@Test
	public void equalsTest5() {
		Floor floor2 = new Floor();
		floor2.setAdmin(null);
		floor2.setNumFloor(numFloor);
		floor2.setRooms(rooms);
		assertNotEquals(floor, floor2);
	}
	@Test
	public void equalsTest6() {
		Floor floor2 = new Floor();
		floor2.setAdmin(admin);
		floor2.setNumFloor(numFloor);
		floor2.setRooms(null);
		assertNotEquals(floor, floor2);
	}
	@Test
	public void equalsTest7() {
		floor.setAdmin(null);
		Floor floor2 = new Floor();
		floor2.setAdmin(admin);
		floor2.setNumFloor(numFloor);
		floor2.setRooms(rooms);
		assertNotEquals(floor, floor2);
	}
	@Test
	public void equalsTest8() {
		floor.setRooms(null);
		Floor floor2 = new Floor();
		floor2.setAdmin(admin);
		floor2.setNumFloor(numFloor);
		floor2.setRooms(rooms);
		assertNotEquals(floor, floor2);
	}
	@Test
	public void equalsTest9() {
		String error = "";
		assertNotEquals(floor, error);
	}
	@Test
	public void equalsTest10() {
		floor=null;
		Floor floor2 = new Floor();
		floor2.setAdmin(admin);
		floor2.setNumFloor(numFloor);
		floor2.setRooms(rooms);
		assertNotEquals(floor, floor2);
	}
	@Test
	public void equalsTest11() {
		Floor floor2 = null;
		assertNotEquals(floor, floor2);
	}
	@Test
	public void equalsTest12() {
		Floor floor2 = floor;
		assertEquals(floor, floor2);
	}
	@Test
	public void toStringTest() {
		String string = "Floor [numFloor=" + numFloor + ", admin=" + admin + "]";
		assertEquals(string, floor.toString());
	}

}
