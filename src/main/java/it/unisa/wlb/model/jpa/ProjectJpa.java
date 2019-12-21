package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDAO;

@Stateless
public class ProjectJpa implements IProjectDAO {
  
  private static final EntityManagerFactory factor =
      Persistence.createEntityManagerFactory("WorkLifeBalance");
      private EntityManager entityManager;

  @Override
  public Project create(Project entity) {
    entityManager = factor.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(entity);
    entityManager.getTransaction().commit();
    return entity;
  }

  @Override
  public void remove(Project entityClass) {
    entityManager.getTransaction().begin();
    entityManager.remove(entityClass);
    entityManager.getTransaction().commit();
  }

  @Override
  public Project update(Project entityClass) {
    entityManager.getTransaction().begin();
    entityManager.merge(entityClass);
    entityManager.getTransaction().commit();
    return entityClass;
  }

  @Override
  public List<Project> retrieveAll() {
    entityManager.getTransaction().begin();
    Query q = entityManager.createQuery("SELECT project FROM Project project");
    entityManager.getTransaction().commit();
    return (List<Project>) q.getResultList();
  }

  @Override
  public List<Project> retrieveByManager(String email) {
    Query query=entityManager.createQuery("SELECT project FROM Project project WHERE project.EMAIL_MANAGER=?1");
    query.setParameter(1, email);
    return (List<Project>) query.getResultList();
  }

  @Override
  public List<Project> searchByName(String name) {
    Query query=entityManager.createQuery("SELECT project FROM Project project WHERE LIKE :custName%");
    query.setParameter("custName", name);
    return (List<Project>) query.getResultList();
  }

  @Override
  public Project retrieveById(int id) {
    Query query=entityManager.createQuery("SELECT project FROM Project project WHERE project.id=?1");
    query.setParameter(1, id);
    return (Project) query.getSingleResult();
  }

  @Override
  public List<Project> retrieveByEmployee(String email) {
    Query query=entityManager.createQuery("SELECT works FROM Works works WHERE works.email_employee=?1");
    query.setParameter(1, email);
    return (List<Project>) query.getResultList();
  }

}
