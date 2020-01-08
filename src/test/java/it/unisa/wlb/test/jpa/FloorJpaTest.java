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
import it.unisa.wlb.model.jpa.FloorJpa;

class FloorJpaTest {

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;
	private FloorJpa floorJpa;
	private Floor firstFloor;
	private Floor secondFloor;
	private Admin admin;
	private Admin newAdmin;
	@BeforeEach
	void setUp() throws Exception {
		floorJpa = new FloorJpa();
		firstFloor = new Floor();
		secondFloor = new Floor();
		admin = new Admin();
		newAdmin = new Admin();
		
		newAdmin.setEmail("t.caio1@wlb.it");
		newAdmin.setName("Tizio");
		newAdmin.setSurname("Caio");
		newAdmin.setPassword("Ciao1234.");
		
		admin.setEmail("m.rossi1@wlbadmin.it");
		admin.setName("Mario");
		admin.setSurname("Rossi");
		admin.setPassword("Ciao1234.");
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(admin);
			entityManager.persist(newAdmin);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		
		firstFloor.setAdmin(admin);
		firstFloor.setNumFloor(1);
		secondFloor.setAdmin(admin);
		secondFloor.setNumFloor(2);
		

	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(firstFloor));
			entityManager.remove(entityManager.merge(admin));
			entityManager.remove(entityManager.merge(newAdmin));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Test
	void createTest() {
		Floor created = floorJpa.create(firstFloor);
		assertEquals(created.getNumFloor(), 1);
	}
	
	@Test 
	void updateTest() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(firstFloor);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		firstFloor.setAdmin(newAdmin);
		Floor updated = floorJpa.update(firstFloor);
		assertEquals(updated.getAdmin().getEmail(), "t.caio1@wlb.it");
	}
	
	@Test
	void removeTest() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(firstFloor);
			entityManager.persist(secondFloor);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		floorJpa.remove(secondFloor);
		assertThrows(NoResultException.class, ()->{
			floorJpa.retrieveById(2);
		}); 
	}
	
	@Test 
	void retrieveAllTest() {
		boolean checkFirstFloor = false;
		boolean checkSecondFloor = false;
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(firstFloor);
			entityManager.persist(secondFloor);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		List<Floor> floorsList = floorJpa.retrieveAll();
		for(Floor aFloor : floorsList) {
			if(aFloor.getNumFloor() == 1) checkFirstFloor = true;
			if(aFloor.getNumFloor() == 2) checkSecondFloor = true;
		}
		assertTrue(checkFirstFloor);
		assertTrue(checkSecondFloor);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(secondFloor));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
	
	@Test
	void retrieveByIdTest() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(firstFloor);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		Floor retrieved = floorJpa.retrieveById(1);
		assertEquals(retrieved.getNumFloor(), 1);
	}
	
	@Test 
	void countMaxTest() {
		int count;
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(firstFloor);
			entityManager.persist(secondFloor);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		count = floorJpa.countMax();
		assertEquals(count, 2);
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(secondFloor));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

}