package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

@Stateless
@Local
public class EmployeeJpa implements IEmployeeDAO{

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager em = factor.createEntityManager();;

	@Override
	public Employee create(Employee entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public void remove(Employee entityClass) {
		em.remove(em.merge(entityClass));
	}

	@Override
	public Employee update(Employee entityClass) {
		em.merge(entityClass);
		return entityClass;
	}

	@Override
	public List<Employee> retrieveAll() {
		return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
	}

	@Override
	public Employee retrieveByEmail(String email) {
		return em.createQuery("SELECT e FROM Employee e WHERE e.email=?1", Employee.class).setParameter(1, email).getSingleResult();
	}

	@Override
	public List<Employee> searchByEmail(String email) {
		return em.createQuery("SELECT e FROM Employee e WHERE e.email LIKE ?1%", Employee.class).setParameter(1, email).getResultList();
	}

	@Override
	public List<Employee> retrieveByProjectId(String projectId) {
		return null;
	}

}
