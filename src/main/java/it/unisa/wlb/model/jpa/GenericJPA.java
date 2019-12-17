package it.unisa.wlb.model.jpa;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IGenericDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericJpa<E> implements IGenericDAO<E> {

  @PersistenceContext
  protected EntityManager entityManager;

  public GenericJpa() {}

  public E create(E entity) {
	  entityManager.persist(entity);
	  return entity;
  }
  
  public void remove(E entity) {
	  entityManager.remove(entityManager.merge(entity));
	  entityManager.flush(); 
  }
  
  public E update(E entity) {
	  return entityManager.merge(entity);
  }
 
  
  

}
