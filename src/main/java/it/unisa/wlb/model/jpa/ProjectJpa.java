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
      private EntityManager entitymanager = factor.createEntityManager();;

  @Override
  public Project create(Project entity) {
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
    Query q = entitymanager.createQuery("SELECT project FROM Project project");
    entitymanager.getTransaction().commit();
    return (List<Project>) q.getResultList();
  }

  @Override
  public List<Project> retrieveByManager(String email) {
    Query query=entitymanager.createQuery("SELECT project FROM Project project WHERE project.EMAIL_MANAGER=?1");
    query.setParameter(1, email);
    return (List<Project>) query.getResultList();
  }

  @Override
  public List<Project> searchByName(String name) {
    Query query=entitymanager.createQuery("SELECT project FROM Project project WHERE LIKE :custName%");
    query.setParameter("custName", name);
    return (List<Project>) query.getResultList();
  }

  @Override
  public Project retrieveById(int id) {
    Query query=entitymanager.createQuery("SELECT project FROM Project project WHERE project.id=?1");
    query.setParameter(1, id);
    return (Project) query.getSingleResult();
  }

  @Override
  public List<Project> retrieveByEmployee(String email) {
    Query query=entitymanager.createQuery("SELECT works FROM Works works WHERE works.email_employee=?1");
    query.setParameter(1, email);
    return (List<Project>) query.getResultList();
  }

  @Override
  public void insertEmployeeToProject(String email, int id_project) {
    Query query=entitymanager.createQuery("INSERT INTO works (ID_PROJECT,EMAIL_EMPLOYEE) VALUES (=?1,=?2);");
    query.setParameter(1, email);
    query.setParameter(2, id_project);
  }

  @Override
  public Project retrieveByName(String name) {
    Query query=entitymanager.createQuery("SELECT project FROM Project project WHERE project.NAME=?1");
    query.setParameter(1, name);
    return (Project) query.getSingleResult();
  }

}