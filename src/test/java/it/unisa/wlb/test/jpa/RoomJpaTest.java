package it.unisa.wlb.test.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.jpa.RoomJpa;

class RoomJpaTest {

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;
	private RoomJpa roomJpa;
	private Floor firstFloor;
	private Floor secondFloor;
	private RoomPK roomPk;
	private RoomPK secondRoomPk;
	private RoomPK thirdRoomPk;
	private Room firstRoom;
	private Room secondRoom;
	private Room thirdRoom;
	private Admin admin;
	@BeforeEach
	void setUp() throws Exception {
		roomJpa = new RoomJpa();
		firstFloor = new Floor();
		roomPk = new RoomPK();
		secondRoomPk = new RoomPK();
		thirdRoomPk = new RoomPK();
		firstRoom = new Room();
		secondRoom = new Room();
		thirdRoom = new Room();
		admin = new Admin();
		
		admin.setEmail("m.rossi1@wlbadmin.it");
		admin.setName("Mario");
		admin.setSurname("Rossi");
		admin.setPassword("Ciao1234.");
		
		firstFloor.setAdmin(admin);
		firstFloor.setNumFloor(1);

		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(admin);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		roomPk.setNumFloor(firstFloor.getNumFloor());
		secondRoomPk.setNumFloor(firstFloor.getNumFloor());
		thirdRoomPk.setNumFloor(firstFloor.getNumFloor());
		
		roomPk.setNumRoom(1);
		secondRoomPk.setNumRoom(2);
		thirdRoomPk.setNumRoom(3);
		firstRoom.setFloor(firstFloor);
		firstRoom.setId(roomPk);
		secondRoom.setFloor(firstFloor);
		secondRoom.setId(secondRoomPk);
		thirdRoom.setFloor(firstFloor);
		thirdRoom.setId(thirdRoomPk);
	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(firstRoom));
			entityManager.remove(entityManager.merge(firstFloor));
			entityManager.remove(entityManager.merge(admin));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Test
	void createTest() {
		Room created = roomJpa.create(firstRoom);
		assertEquals(created.getId().getNumRoom(), 1);
	}

	@Test
	void removeTest() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(firstRoom);
			entityManager.persist(secondRoom);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		roomJpa.remove(firstRoom);
		boolean check = true;
		List<Room> roomsList = roomJpa.retrieveByFloor(firstFloor.getNumFloor());
		for(Room aRoom : roomsList) {
			if(aRoom.getId().getNumRoom() == firstRoom.getId().getNumRoom()) check = false;
		}
		assertTrue(check);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(secondRoom));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
	

	@Test 
	void updateTest() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(firstRoom);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		firstRoom.getId().setNumRoom(2);
		Room updated = roomJpa.update(firstRoom);
		assertEquals(updated.getId().getNumRoom(), 2);
		
		Room aRoom = new Room();
		RoomPK aRoomPk = new RoomPK();
		aRoomPk.setNumFloor(1);
		aRoomPk.setNumRoom(1);
		aRoom.setFloor(firstFloor);
		aRoom.setId(aRoomPk);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(aRoom));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
	
	@Test
	void retrieveByFloorTest() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(firstRoom);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		List<Room> roomsList = roomJpa.retrieveByFloor(firstFloor.getNumFloor());
		boolean check = false;
		for(Room aRoom : roomsList) {
			if(aRoom.getFloor().getNumFloor() == firstFloor.getNumFloor()) check = true;
		}
		assertTrue(check);
	}
	
	@Test
	void countMaxByFloorTest() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(firstRoom);
			entityManager.persist(secondRoom);
			entityManager.persist(thirdRoom);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		int count = roomJpa.countMaxByFloor(firstFloor.getNumFloor());
		assertEquals(count, 3);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(secondRoom));
			entityManager.remove(entityManager.merge(thirdRoom));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
	
	@Test
	void retrieveAllTest() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(firstRoom);
			entityManager.persist(secondRoom);
			entityManager.persist(thirdRoom);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		boolean checkfirstRoom = false;
		boolean checkSecondRoom = false;
		boolean checkThridRoom = false;
		List<Room> roomsList = roomJpa.retrieveAll();
		for(Room aRoom : roomsList) {
			if(aRoom.getId().getNumRoom() == firstRoom.getId().getNumRoom()) checkfirstRoom = true;
			if(aRoom.getId().getNumRoom() == secondRoom.getId().getNumRoom()) checkSecondRoom = true;
			if(aRoom.getId().getNumRoom() == thirdRoom.getId().getNumRoom()) checkThridRoom = true;
		}
		assertTrue(checkfirstRoom);
		assertTrue(checkSecondRoom);
		assertTrue(checkThridRoom);
		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(secondRoom));
			entityManager.remove(entityManager.merge(thirdRoom));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
}
