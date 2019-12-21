package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
		Query q = entitymanager.createNamedQuery("SELECT e FROM Employee e WHERE email = :email AND password = :password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		entitymanager.getTransaction().commit();
		return (Employee) q.getSingleResult();
	}

}
