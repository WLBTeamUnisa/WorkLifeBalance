package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.dao.IAdminDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this class is implementing methods of IAdminDao
 * 
 * @author Vincenzo Fabiano
 *
 */
@Stateless
@Interceptors({LoggerSingleton.class})
public class AdminJpa implements IAdminDao {
	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;

	
	public Admin create(Admin entity) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		} finally {
			entityManager.close();
		}
	}
	
	@Override
	public void remove(Admin entityClass) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(entityClass));
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Admin update(Admin entityClass) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(entityClass);
			entityManager.getTransaction().commit();
			return entityClass;
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> retrieveAll() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			Query q = entityManager.createQuery("SELECT admin FROM Admin admin");
			entityManager.getTransaction().commit();
			return (List<Admin>) q.getResultList();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Admin retrieveByEmailPassword(String email, String password) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<Admin> q = entityManager.createNamedQuery("Admin.findByEmailPassword", Admin.class);
			q.setParameter("email", email);
			q.setParameter("password", password);
			entityManager.getTransaction().commit();
			return (Admin) q.getSingleResult();
		} finally {
			entityManager.close();
		}
	}

}