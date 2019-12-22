package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.dao.IAdminDAO;

@Stateless
public class AdminJpa implements IAdminDAO {
  private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
  private EntityManager entityManager = factor.createEntityManager();

  public Admin create(Admin entity) {
    entityManager.getTransaction().begin();
    entityManager.persist(entity);
    entityManager.getTransaction().commit();
    return entity;
  }

  @Override
  public void remove(Admin entityClass) {
    entityManager.getTransaction().begin();
    entityManager.remove(entityClass);
    entityManager.getTransaction().commit();
  }

  @Override
  public Admin update(Admin entityClass) {
    entityManager.getTransaction().begin();
    entityManager.merge(entityClass);
    entityManager.getTransaction().commit();
    return entityClass;
  }

  @Override
  public List<Admin> retrieveAll() {
    entityManager.getTransaction().begin();
    Query q = entityManager.createQuery("SELECT admin FROM Admin admin");
    entityManager.getTransaction().commit();

    return (List<Admin>) q.getResultList();
  }

@Override
public Admin retrieveByEmailPassword(String email, String password) {
	entityManager.getTransaction().begin();
	TypedQuery<Admin> q = entityManager.createNamedQuery("Admin.findByEmailPassword", Admin.class);
	q.setParameter("email", email);
	q.setParameter("password", password);
	entityManager.getTransaction().commit();
	return (Admin) q.getSingleResult();
}

}
