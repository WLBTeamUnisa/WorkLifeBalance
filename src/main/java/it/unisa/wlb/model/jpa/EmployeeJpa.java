package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

@Stateless
public class EmployeeJpa implements IEmployeeDAO{

	  private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
		 private EntityManager entitymanager = factor.createEntityManager();
	
	@Override
	public Employee create(Employee entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Employee entityClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee update(Employee entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> retrieveAll() {
		// TODO Auto-generated method stub
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

	@Override
	public Employee retrieveByEmailPassword(String email, String password) {
		entitymanager.getTransaction().begin();
		TypedQuery<Employee> query = entitymanager.createNamedQuery("Employee.findByEmailPassword", Employee.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		entitymanager.getTransaction().commit();
		return (Employee) query.getSingleResult();
	}

	@Override
	public List<Employee> retrieveSuggestsByEmail(String email) {
		entitymanager.getTransaction().begin();
		TypedQuery<Employee> query = entitymanager.createNamedQuery("Employee.findSuggestsByEmail", Employee.class);
		query.setParameter("email", email);
		entitymanager.getTransaction().commit();
		return (List<Employee>) query.getResultList();
	}

}
