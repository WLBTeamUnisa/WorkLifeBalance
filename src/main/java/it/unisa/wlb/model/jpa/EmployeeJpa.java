package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

@Stateless
public class EmployeeJpa implements IEmployeeDAO{

  private static final EntityManagerFactory factor =
  Persistence.createEntityManagerFactory("WorkLifeBalance");
  private EntityManager entityManager;
  
  @Override
  public Employee create(Employee entity) {
    entityManager = factor.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(entity);
    entityManager.getTransaction().commit();
    return entity;
  }

  @Override
  public void remove(Employee entityClass) {
    entityManager.getTransaction().begin();
    entityManager.remove(entityClass);
    entityManager.getTransaction().commit();
  }

  @Override
  public Employee update(Employee entityClass) {
    entityManager.getTransaction().begin();
    entityManager.merge(entityClass);
    entityManager.getTransaction().commit();
    return entityClass;
  }

  @Override
  public List<Employee> retrieveAll() {
    entityManager.getTransaction().begin();
    Query q = entityManager.createQuery("SELECT * FROM Employee");
    entityManager.getTransaction().commit();
    return (List<Employee>) q.getResultList();
  }

  @Override
  public Employee retrieveByEmail(String email) {
    entityManager.getTransaction().begin();
    Query q = entityManager.createQuery("SELECT * FROM Employee employee WHERE employee.EMAIL=?1");
    q.setParameter(1, email);
    entityManager.getTransaction().commit();
    return (Employee) q.getSingleResult();
  }

  @Override
  public List<Employee> searchByEmail(String email) {
    entityManager.getTransaction().begin();
    Query q = entityManager.createQuery("SELECT * FROM Employee employee WHERE employee.EMAIL LIKE :custEmail%");
    q.setParameter("custEmail", email);
    entityManager.getTransaction().commit();
    return (List<Employee>) q.getResultList();
  }

  @Override
  public List<Employee> retrieveByProjectId(String ProjectId) {
    entityManager.getTransaction().begin();
    Query q = entityManager.createQuery("SELECT * FROM WORKS works WHERE works.ID_PROJECT=?1");
    q.setParameter(1,ProjectId);
    entityManager.getTransaction().commit();
    return (List<Employee>) q.getResultList();
  }
  

}
