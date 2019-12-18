package it.unisa.wlb.model.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

public class EmployeeJpa implements IEmployeeDAO{

  private EntityManager entityManager;
  @Override
  public Employee create(Employee entity) {
    entityManager.persist(entity);
    return entity;
  }

  @Override
  public void remove(Employee entityClass) {
    entityManager.remove(entityClass);
    entityManager.flush();
  }

  @Override
  public Employee update(Employee entityClass) {
    entityManager.merge(entityClass);
    return entityClass;
  }

  @Override
  public List<Employee> retrieveAll() {
    return null;
  }

  @Override
  public Employee retrieveByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Employee> searchByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Employee> retrieveByProjectId(String ProjectId) {
    // TODO Auto-generated method stub
    return null;
  }
  

}
