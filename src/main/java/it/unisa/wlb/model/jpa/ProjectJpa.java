package it.unisa.wlb.model.jpa;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDAO;

public class ProjectJpa implements IProjectDAO {
  
  private static final EntityManagerFactory factor =
      Persistence.createEntityManagerFactory("WorkLifeBalance");
      private EntityManager entitymanager;

  @Override
  public Project create(Project entity) {
    entitymanager = factor.createEntityManager();
    entitymanager.getTransaction().begin();
    entitymanager.persist(entity);
    entitymanager.getTransaction().commit();
    return entity;
  }

  @Override
  public void remove(Project entityClass) {
    entitymanager.getTransaction().begin();
    entitymanager.remove(entityClass);
    entitymanager.getTransaction().commit();
  }

  @Override
  public Project update(Project entityClass) {
    entitymanager.getTransaction().begin();
    entitymanager.merge(entityClass);
    entitymanager.getTransaction().commit();
    return entityClass;
  }

  @Override
  public List<Project> retrieveAll() {
    entitymanager.getTransaction().begin();
    Query q = entitymanager.createQuery("SELECT * FROM Project");
    entitymanager.getTransaction().commit();
    return (List<Project>) q.getResultList();
  }

  @Override
  public List<Project> retrieveByManager(String email) {
    Query query=entitymanager.createQuery("SELECT * FROM Project project WHERE project.EMAIL_MANAGER=?1");
    query.setParameter(1, email);
    return (List<Project>) query.getResultList();
  }

  @Override
  public List<Project> searchByName(String name) {
    Query query=entitymanager.createQuery("SELECT * FROM project WHERE LIKE :custName%");
    query.setParameter("custName", name);
    return (List<Project>) query.getResultList();
  }

  @Override
  public Project retrieveById(int id) {
    Query query=entitymanager.createQuery("SELECT * FROM project WHERE project.id=?1");
    query.setParameter(1, id);
    return (Project) query.getSingleResult();
  }

  @Override
  public List<Project> retrieveByEmployee(String email) {
    Query query=entitymanager.createQuery("SELECT * FROM works WHERE works.email_employee=?1");
    query.setParameter(1, email);
    return (List<Project>) query.getResultList();
  }

}
