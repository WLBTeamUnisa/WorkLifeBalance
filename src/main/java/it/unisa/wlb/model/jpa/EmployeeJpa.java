package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

@Stateless
public class EmployeeJpa implements IEmployeeDAO {

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;

	@Override
	public Employee create(Employee entity) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public void remove(Employee entityClass) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityClass);
			entityManager.getTransaction().commit();
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public Employee update(Employee entityClass) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(entityClass);
			entityManager.getTransaction().commit();
			return entityClass;
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public List<Employee> retrieveAll() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findAll", Employee.class);
			entityManager.getTransaction().commit();
			return (List<Employee>) query.getResultList();
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public Employee retrieveByEmail(String email) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByEmail", Employee.class);
			query.setParameter("email", email);
			entityManager.getTransaction().commit();
			return (Employee) query.getSingleResult();
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public List<Employee> searchByEmail(String email) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.searchByEmail", Employee.class);
			query.setParameter("email", email);
			entityManager.getTransaction().commit();
			return (List<Employee>) query.getResultList();
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public List<Employee> retrieveByProjectId(String ProjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee retrieveByEmailPassword(String email, String password) {
		try {
			entityManager = factor.createEntityManager();
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByEmailPassword", Employee.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			entityManager.getTransaction().commit();
			return (Employee) query.getSingleResult();
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public List<Employee> retrieveSuggestsEmployeeByEmail(String email) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findSuggestsEmployeeByEmail",
					Employee.class);
			query.setParameter("email", email);
			entityManager.getTransaction().commit();
			return (List<Employee>) query.getResultList();
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public List<Employee> retrieveSuggestsManagerByEmail(String email) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findSuggestsManagerByEmail",
					Employee.class);
			query.setParameter("email", email);
			entityManager.getTransaction().commit();
			return (List<Employee>) query.getResultList();
		}

		finally {
			entityManager.close();
		}
	}
}
