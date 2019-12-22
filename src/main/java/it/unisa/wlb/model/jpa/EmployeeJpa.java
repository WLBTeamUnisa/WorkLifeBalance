package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

@Stateless
public class EmployeeJpa implements IEmployeeDAO{

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager = factor.createEntityManager();

	@Override
	public Employee create(Employee entity) {
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
		TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findAll", Employee.class);
		entityManager.getTransaction().commit();
		return (List<Employee>) query.getResultList();
	}

	@Override
	public Employee retrieveByEmail(String email) {
		entityManager.getTransaction().begin();
		TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByEmail", Employee.class);
		query.setParameter("email", email);
		entityManager.getTransaction().commit();
		return (Employee) query.getSingleResult();
	}

	@Override
	public List<Employee> searchByEmail(String email) {
		entityManager.getTransaction().begin();
		TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.searchByEmail", Employee.class);
		query.setParameter("email", email);
		entityManager.getTransaction().commit();
		return (List<Employee>) query.getResultList();		
	}

	
	@Override
	public List<Employee> retrieveByProjectId(String ProjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee retrieveByEmailPassword(String email, String password) {
		entityManager.getTransaction().begin();
		TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByEmailPassword", Employee.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		entityManager.getTransaction().commit();
		return (Employee) query.getSingleResult();
	}

	@Override
	public List<Employee> retrieveSuggestsByEmail(String email) {
		entityManager.getTransaction().begin();
		TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findSuggestsByEmail", Employee.class);
		query.setParameter("email", email);
		entityManager.getTransaction().commit();
		return (List<Employee>) query.getResultList();
	}

}
