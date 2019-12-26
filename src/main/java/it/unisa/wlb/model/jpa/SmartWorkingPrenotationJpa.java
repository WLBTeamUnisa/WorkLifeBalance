package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;

@Stateless
public class SmartWorkingPrenotationJpa implements ISmartWorkingPrenotationDAO{

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager = factor.createEntityManager();
	
	@Override
	public SmartWorkingPrenotation create(SmartWorkingPrenotation entity) {
		entityManager.getTransaction().begin();
	    entityManager.persist(entity);
	    entityManager.getTransaction().commit();
	    return entity;
	}

	@Override
	public void remove(SmartWorkingPrenotation entity) {
		entityManager.getTransaction().begin();
	    entityManager.merge(entity);
	    entityManager.getTransaction().commit();	
	}

	@Override
	public SmartWorkingPrenotation update(SmartWorkingPrenotation entity) {
		entityManager.getTransaction().begin();
	    entityManager.persist(entity);
	    entityManager.getTransaction().commit();
	    return entity;
	}

	@Override
	public List<SmartWorkingPrenotation> retrieveAll() {
		entityManager.getTransaction().begin();
		TypedQuery<SmartWorkingPrenotation> query = entityManager.createNamedQuery("SmartWorkingPrenotation.findAll", SmartWorkingPrenotation.class);
		entityManager.getTransaction().commit();
		return (List<SmartWorkingPrenotation>) query.getResultList();
	}

	@Override
	public SmartWorkingPrenotation retrieveByWeeklyPlanning(int calendarWeek, int year, String email) {
		entityManager.getTransaction().begin();
		TypedQuery<SmartWorkingPrenotation> query = entityManager.createNamedQuery("SmartWorkingPrenotation.findByWeeklyPlanning", SmartWorkingPrenotation.class);
		query.setParameter("employee", email);
		query.setParameter("calendarWeek", calendarWeek);
		query.setParameter("year", year);
		entityManager.getTransaction().commit();
		return query.getSingleResult();
	}

	@Override
	public List<SmartWorkingPrenotation> retrieveByEmployee(String email) {
		entityManager.getTransaction().begin();
		TypedQuery<SmartWorkingPrenotation> query = entityManager.createNamedQuery("SmartWorkingPrenotation.findByEmployee", SmartWorkingPrenotation.class);
		query.setParameter("employee", email);
		entityManager.getTransaction().commit();
		return (List<SmartWorkingPrenotation>) query.getResultList();
	}

}
