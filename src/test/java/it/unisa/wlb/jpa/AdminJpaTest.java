package it.unisa.wlb.jpa;

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
import it.unisa.wlb.model.jpa.AdminJpa;

class AdminJpaTest {

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;
	private Admin admin;
	private Admin admin2;
	private AdminJpa adminJpa;
	
	@BeforeEach
	void setUp() throws Exception {
		adminJpa = new AdminJpa();
		admin = new Admin();
		admin.setEmail("m.rossi1@wlbadmin.it");
		admin.setName("Mario");
		admin.setSurname("Rossi");
		admin.setPassword("Ciao1234.");
		
		admin2 = new Admin();
		admin2.setEmail("v.verdi1@wlbadmin.it");
		admin2.setName("Vincenzo");
		admin2.setSurname("Verdi");
		admin2.setPassword("Ciao1234.");
		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(admin);
			entityManager.persist(admin2);
			entityManager.getTransaction().commit();
		}

		finally {
			entityManager.close();
		}
	}

	@AfterEach
	void destroy() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(admin));
			entityManager.remove(entityManager.merge(admin2));
			entityManager.getTransaction().commit();
		}
		finally {
			entityManager.close();
		}
	}
	
	/**
	 * Tests the creation of an admin
	 */
	
	@Test
	void createTest() {
		Admin anAdmin = new Admin();
		anAdmin.setEmail("g.gialli1@wlbadmin.it");
		anAdmin.setName("Giovanni");
		anAdmin.setSurname("Gialli");
		anAdmin.setPassword("Ciao1234.");
		Admin created = adminJpa.create(anAdmin);
		assertEquals("g.gialli1@wlbadmin.it", created.getEmail());
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(anAdmin));
			entityManager.getTransaction().commit();
		}
		finally {
			entityManager.close();
		}
	}
	
	/**
	 * Tests the update done to an admin
	 */
	
	@Test
	void updateTest() {
		admin.setSurname("Rossini");
		Admin modifyied = adminJpa.update(admin);
		assertEquals("Rossini", modifyied.getSurname());
	}
	
	/**
	 * Tests the removal of an admin
	 */
	
	@Test
	void removeTest() {
		Admin anAdmin = new Admin();
		anAdmin.setEmail("g.gialli1@wlbadmin.it");
		anAdmin.setName("Giovanni");
		anAdmin.setSurname("Gialli");
		anAdmin.setPassword("Ciao1234.");
		
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(anAdmin);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
		adminJpa.remove(anAdmin);
		assertThrows(NoResultException.class, ()->{
			adminJpa.retrieveByEmailPassword("g.gialli1@wlb.it", "Ciao1234.");
		});
	}
	
	/**
	 * Tests the retrieve of all admins
	 */
	
	@Test
	void retrieveAllTest() {
		boolean check1 = false;
		boolean check2 = false;
		List<Admin> adminList = adminJpa.retrieveAll();
		for(Admin anAdmin : adminList) {
			String email = anAdmin.getEmail();
			if(email.equals("m.rossi1@wlbadmin.it")) check1 = true;
			if(email.equals("v.verdi1@wlbadmin.it")) check2 = true;
		}
		assertTrue(check1);
		assertTrue(check2);
	}
	
	/**
	 * Tests the retrieve of an admin using his email and password
	 */
	
	@Test
	void retrieveByEmailPasswordTest() {
		Admin retrieved = adminJpa.retrieveByEmailPassword("m.rossi1@wlbadmin.it", "Ciao1234.");
		assertEquals("m.rossi1@wlbadmin.it", retrieved.getEmail());
	}
	
}
