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
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPK;
import it.unisa.wlb.model.jpa.WorkstationJpa;

class WorkstationJpaTest {
	
	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;
	private Floor firstFloor;
	private Room firstRoom;
	private Room secondRoom;
	private RoomPK roomPk;
	private RoomPK secondRoomPk;
	private Workstation workstation;
	private WorkstationPK stationPk;
	private WorkstationJpa workstationJpa;
	private Workstation aWorkstation;
	private WorkstationPK aStationPk;
	private Admin admin;
	
	@BeforeEach
	void setUp() throws Exception {
		workstationJpa = new WorkstationJpa();
		firstFloor = new Floor();
		firstRoom = new Room();
		roomPk = new RoomPK();
		secondRoomPk = new RoomPK();
		workstation = new Workstation();
		stationPk = new WorkstationPK();
		admin = new Admin();
		aWorkstation = new Workstation();
		aStationPk = new WorkstationPK();
		
		admin.setEmail("m.rossi1@wlbadmin.it");
		admin.setName("Mario");
		admin.setSurname("Rossi");
		admin.setPassword("Ciao1234.");
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(admin);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		/**
		 * Creating a floor
		 */
		firstFloor.setAdmin(admin);
		firstFloor.setNumFloor(1);
		/**
		 * Creating a room
		 */
		roomPk.setNumFloor(1);
		roomPk.setNumRoom(1);
		firstRoom.setFloor(firstFloor);
		firstRoom.setId(roomPk);
		/**
		 * Creating a workstation
		 */
		stationPk.setFloor(1);
		stationPk.setRoom(1);
		stationPk.setWorkstation(1);
		workstation.setId(stationPk);
		workstation.setRoom(firstRoom);
	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(workstation));
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
		Workstation created;
		created = workstationJpa.create(workstation);
		assertEquals(created.getId().getWorkstation(), workstation.getId().getWorkstation());
	}
	
	@Test
	void removeTest() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(workstation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		workstationJpa.remove(workstation);
		assertThrows(NoResultException.class, ()->{
			workstationJpa.retrieveById(1, 1, 1);
		});
	}
	
	@Test
	void retrieveByFloorAndRoomTest() {
		aStationPk.setFloor(1);
		aStationPk.setRoom(1);
		aStationPk.setWorkstation(2);
		aWorkstation.setRoom(firstRoom);
		aWorkstation.setId(aStationPk);
		boolean check1 = false;
		boolean check2 = false;
		List<Workstation> stationsList;
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(workstation);
			entityManager.persist(aWorkstation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		stationsList = workstationJpa.retrieveByFloorAndRoom(firstFloor.getNumFloor(), firstRoom.getId().getNumRoom());
		for(int i = 0; i < stationsList.size(); i++) {
			if(stationsList.get(i).getId().getWorkstation() == workstation.getId().getWorkstation()) check1 = true;
			if(stationsList.get(i).getId().getWorkstation() == aWorkstation.getId().getWorkstation()) check2 = true;
		}
		assertTrue(check1);
		assertTrue(check2);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(aWorkstation));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
	
	@Test
	void retrieveById() {
		Workstation retrieved;
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(workstation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		retrieved = workstationJpa.retrieveById(firstFloor.getNumFloor(), firstRoom.getId().getNumRoom(), workstation.getId().getWorkstation());
		assertEquals(retrieved.getId().getWorkstation(), workstation.getId().getWorkstation());
	}
	
	@Test
	void countMaxByFloorAndRoomTest() {
		aStationPk.setFloor(1);
		aStationPk.setRoom(1);
		aStationPk.setWorkstation(2);
		aWorkstation.setRoom(firstRoom);
		aWorkstation.setId(aStationPk);
		int count;
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(workstation);
			entityManager.persist(aWorkstation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		count = workstationJpa.countMaxByFloorAndRoom(firstFloor.getNumFloor(), firstRoom.getId().getNumRoom());
		assertEquals(count, 2);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(aWorkstation));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
	
	@Test
	void retrieveAll() {
		secondRoomPk.setNumFloor(firstFloor.getNumFloor());
		secondRoomPk.setNumRoom(2);
		secondRoom = new Room();
		secondRoom.setFloor(firstFloor);
		secondRoom.setId(secondRoomPk);
		aStationPk.setFloor(firstFloor.getNumFloor());
		aStationPk.setRoom(firstRoom.getId().getNumRoom());
		aStationPk.setWorkstation(2);
		aWorkstation.setRoom(secondRoom);
		aWorkstation.setId(aStationPk);
		boolean check1 = false;
		boolean check2 = false;
		List<Workstation> stationsList;
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(workstation);
			entityManager.persist(aWorkstation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		stationsList = workstationJpa.retrieveAll();
		for(int i = 0; i < stationsList.size(); i++) {
			if(stationsList.get(i).getId().getWorkstation() == workstation.getId().getWorkstation()) check1 = true;
			if(stationsList.get(i).getId().getWorkstation() == aWorkstation.getId().getWorkstation()) check2 = true;
		}
		assertTrue(check1);
		assertTrue(check2);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(aWorkstation));
			entityManager.remove(entityManager.merge(secondRoom));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
	
	@Test
	void updateTest() {
		Workstation updated;
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(workstation);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		workstation.getId().setWorkstation(2);
		updated = workstationJpa.update(workstation);
		assertEquals(updated.getId().getWorkstation(), workstation.getId().getWorkstation());
		aStationPk.setFloor(firstFloor.getNumFloor());
		aStationPk.setRoom(firstRoom.getId().getNumRoom());
		aStationPk.setWorkstation(1);
		aWorkstation.setId(aStationPk);
		aWorkstation.setRoom(firstRoom);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(aWorkstation));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

}
