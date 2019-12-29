package it.unisa.wlb.model.jpa;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDAO;

@Stateless
public class ProjectJpa implements IProjectDAO {
  
	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;
	
  @Override
  public Project create(Project entity) {
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
  public void remove(Project entityClass) {
	try
	{
	entityManager = factor.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entityClass);
    entityManager.getTransaction().commit();
	}
	
	finally
	{
		entityManager.close();
	}
  }

  @Override
  public Project update(Project entityClass) {
	try
	{
	entityManager = factor.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.merge(entityClass);
    entityManager.getTransaction().commit();
    return entityClass;
	}
	
	finally
	{
		entityManager.close();
	}
  }

  @Override
  public List<Project> retrieveAll() {
	try
	{
	entityManager = factor.createEntityManager();
    entityManager.getTransaction().begin();
    Query q = entityManager.createQuery("SELECT project FROM Project project");
    entityManager.getTransaction().commit();
    return (List<Project>) q.getResultList();
	}
	
	finally
	{
		entityManager.close();
	}
  }

  @Override
  public List<Project> retrieveByManager(String email) {
	try
	{
	entityManager = factor.createEntityManager();
    Query query=entityManager.createQuery("SELECT project FROM Project project WHERE project.EMAIL_MANAGER=?1");
    query.setParameter(1, email);
    return (List<Project>) query.getResultList();
	}
	
	finally
	{
		entityManager.close();
	}
  }

  @Override
  public List<Project> searchByName(String name) {
	try
	{
	entityManager = factor.createEntityManager();
    Query query=entityManager.createQuery("SELECT project FROM Project project WHERE LIKE :custName%");
    query.setParameter("custName", name);
    return (List<Project>) query.getResultList();
	}
	
	finally
	{
		entityManager.close();
	}
  }

  @Override
  public Project retrieveById(int id) {
	try
	{
	entityManager = factor.createEntityManager();
    Query query=entityManager.createQuery("SELECT project FROM Project project WHERE project.id=?1");
    query.setParameter(1, id);
    return (Project) query.getSingleResult();
  	}
	
    finally
	{
		entityManager.close();
	}
  }

  @Override
  public List<Project> retrieveByEmployee(String email) {
	try
	{
	entityManager = factor.createEntityManager();
    Query query=entityManager.createQuery("SELECT works FROM Works works WHERE works.email_employee=?1");
    query.setParameter(1, email);
    return (List<Project>) query.getResultList();
	}
	
	finally
	{
		entityManager.close();
	}
  }

  @Override
  public void insertEmployeeToProject(String email, int id_project) {
	try
	{
	entityManager = factor.createEntityManager();
    Query query=entityManager.createQuery("INSERT INTO works (ID_PROJECT,EMAIL_EMPLOYEE) VALUE (=?1,=?2)");
    query.setParameter(1, email);
    query.setParameter(2, id_project);
	}
	
	finally
	{
		entityManager.close();
	}
  }

  @Override
  public Project retrieveByName(String name) {
	try
	{
	entityManager = factor.createEntityManager();
    Query query=entityManager.createQuery("SELECT project FROM Project project WHERE project.NAME=?1");
    query.setParameter(1, name);
    return (Project) query.getSingleResult();
	}
	
	finally
	{
		entityManager.close();
	}
  }

}