package it.unisa.wlb.model.jpa;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

/**
 * Session Bean implementation class EmployeeJpa, used to perform operations on the entity "Employee" of the database.
 * @author Sabato
 *
 */
@Stateless
public class EmployeeJpa implements IEmployeeDAO{
	
	@PersistenceContext
	protected EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public EmployeeJpa() {

    }

	@Override
	@Transactional
	public Employee create(Employee entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	@Transactional
	public void remove(Employee entityClass) {
		entityManager.remove(entityManager.merge(entityClass));		
	}

	@Override
	@Transactional
	public Employee update(Employee entityClass) {
		entityManager.merge(entityClass);
		return entityClass;
	}

	@Override
	@Transactional
	public List<Employee> retrieveAll(Employee entityClass) {
		return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
	}

	@Override
	@Transactional
	public Employee retrieveByEmail(String email) {
		return entityManager.createQuery("SELECT e FROM Employee e WHERE e.email = ?1", Employee.class).setParameter(1, email).getSingleResult();
	}

	@Override
	@Transactional
	public List<Employee> searchByEmail(String email) {
		return entityManager.createQuery("SELECT e FROM Employee e WHERE e.email LIKE %?1%", Employee.class).setParameter(1, email).getResultList();
	}

	@Override
	@Transactional
	public List<Employee> retrieveByProjectId(String ProjectId) {
		return null;
	}

}
