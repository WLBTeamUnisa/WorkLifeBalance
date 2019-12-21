package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.dao.IAdminDAO;

@Stateless
public class AdminJpa implements IAdminDAO {
  private static final EntityManagerFactory factor =
      Persistence.createEntityManagerFactory("WorkLifeBalance");
  private EntityManager entitymanager = factor.createEntityManager();

  public Admin create(Admin entity) {
    entitymanager.getTransaction().begin();
    entitymanager.persist(entity);
    entitymanager.getTransaction().commit();
    return entity;
  }

  @Override
  public void remove(Admin entityClass) {
    entitymanager.getTransaction().begin();
    entitymanager.remove(entityClass);
    entitymanager.getTransaction().commit();
  }

  @Override
  public Admin update(Admin entityClass) {
    entitymanager.getTransaction().begin();
    entitymanager.merge(entityClass);
    entitymanager.getTransaction().commit();
    return entityClass;
  }

  @Override
  public List<Admin> retrieveAll() {
    entitymanager.getTransaction().begin();
    Query q = entitymanager.createQuery("SELECT admin FROM Admin admin");
    entitymanager.getTransaction().commit();

    return (List<Admin>) q.getResultList();
  }


}
