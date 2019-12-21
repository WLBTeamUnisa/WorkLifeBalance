package it.unisa.wlb.model.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

public class EmployeeJpa implements IEmployeeDAO{

  private static final EntityManagerFactory factor =
  Persistence.createEntityManagerFactory("WorkLifeBalance");
  private EntityManager entitymanager;
  
  @Override
  public Employee create(Employee entity) {
    entitymanager = factor.createEntityManager();
    entitymanager.getTransaction().begin();
    entitymanager.persist(entity);
    entitymanager.getTransaction().commit();
    return entity;
  }

  @Override
  public void remove(Employee entityClass) {
    entitymanager.getTransaction().begin();
    entitymanager.remove(entityClass);
    entitymanager.getTransaction().commit();
  }

  @Override
  public Employee update(Employee entityClass) {
    entitymanager.getTransaction().begin();
    entitymanager.merge(entityClass);
    entitymanager.getTransaction().commit();
    return entityClass;
  }

  @Override
  public List<Employee> retrieveAll() {
    entitymanager.getTransaction().begin();
    Query q = entitymanager.createQuery("SELECT * FROM Employee");
    entitymanager.getTransaction().commit();
    return (List<Employee>) q.getResultList();
  }

  @Override
  public Employee retrieveByEmail(String email) {
    entitymanager.getTransaction().begin();
    Query q = entitymanager.createQuery("SELECT * FROM Employee employee WHERE employee.EMAIL=?1");
    q.setParameter(1, email);
    entitymanager.getTransaction().commit();
    return (Employee) q.getSingleResult();
  }

  @Override
  public List<Employee> searchByEmail(String email) {
    entitymanager.getTransaction().begin();
    Query q = entitymanager.createQuery("SELECT * FROM Employee employee WHERE employee.EMAIL LIKE :custEmail%");
    q.setParameter("custEmail", email);
    entitymanager.getTransaction().commit();
    return (List<Employee>) q.getResultList();
  }

  @Override
  public List<Employee> retrieveByProjectId(String ProjectId) {
    entitymanager.getTransaction().begin();
    Query q = entitymanager.createQuery("SELECT * FROM WORKS works WHERE works.ID_PROJECT=?1");
    q.setParameter(1,ProjectId);
    entitymanager.getTransaction().commit();
    return (List<Employee>) q.getResultList();
  }
  

}
