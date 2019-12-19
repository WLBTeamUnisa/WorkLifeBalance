package it.unisa.wlb.model.jpa;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDAO;

public class ProjectJpa implements IProjectDAO {
  
  @PersistenceContext
  private EntityManager entitymanager;

  @Override
  public Project create(Project entity) {
    entitymanager.persist(entity);
    return entity;
  }

  @Override
  public void remove(Project entityClass) {
    entitymanager.remove(entityClass);
  }

  @Override
  public Project update(Project entityClass) {
    entitymanager.merge(entityClass);
    return entityClass;
  }

  @Override
  public List<Project> retrieveAll() {
    Query query=entitymanager.createQuery("SELECT project FROM Project project");
    return (List<Project>) query.getResultList();
  }

  @Override
  public List<Project> retrieveByManager(String email) {
    Query query=entitymanager.createQuery("SELECT project FROM Project project WHERE project.email_manager=?1");
    query.setParameter(1, email);
    return (List<Project>) query.getResultList();
  }

  @Override
  public List<Project> searchByName(String name) {
    Query query=entitymanager.createQuery("SELECT project FROM project WHERE project.name=?1");
    query.setParameter(1, name);
    return (List<Project>) query.getResultList();
  }

  @Override
  public Project retrieveById(int id) {
    Query query=entitymanager.createQuery("SELECT project FROM project WHERE project.id=?1");
    query.setParameter(1, id);
    return (Project) query.getSingleResult();
  }

  @Override
  public List<Project> retrieveByEmployee(String email) {
    Query query=entitymanager.createQuery("SELECT id_project FROM works WHERE works.email_employee=?1");
    query.setParameter(1, email);
    return (List<Project>) query.getResultList();
  }

}
