package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;

@Stateless
public class SmartWorkingPrenotationJpa implements ISmartWorkingPrenotationDAO{

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;
	
	@Override
	public SmartWorkingPrenotation create(SmartWorkingPrenotation entity) {
		try
		{
			entityManager= factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		}
		
		finally
		{
			entityManager.close();
		}
	}

	@Override
	public void remove(SmartWorkingPrenotation entity) {
		try{
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
		    entityManager.remove(entity);
		    entityManager.getTransaction().commit();
		}
		finally{
			entityManager.close();
		}
	}

	@Override
	public SmartWorkingPrenotation update(SmartWorkingPrenotation entity) {
		try
		{
			entityManager= factor.createEntityManager();
			entityManager.getTransaction().begin();
		    entityManager.merge(entity);
		    entityManager.getTransaction().commit();
		    return entity;
		}
		finally{
			entityManager.close();
		}
		
	}

	@Override
	public List<SmartWorkingPrenotation> retrieveAll() {
		try{
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<SmartWorkingPrenotation> query = entityManager.createNamedQuery("SmartWorkingPrenotation.findAll", SmartWorkingPrenotation.class);
			entityManager.getTransaction().commit();
			return (List<SmartWorkingPrenotation>) query.getResultList();
		}
		finally{
			entityManager.close();
		}
	}

	@Override
	public SmartWorkingPrenotation retrieveByWeeklyPlanning(int calendarWeek, int year, String email) {
		try{
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<SmartWorkingPrenotation> query = entityManager.createNamedQuery("SmartWorkingPrenotation.findByWeeklyPlanning", SmartWorkingPrenotation.class);
			query.setParameter("employee", email);
			query.setParameter("calendarWeek", calendarWeek);
			query.setParameter("year", year);
			entityManager.getTransaction().commit();
			return query.getSingleResult();
		}
		finally{
			entityManager.close();
		}
	}

	@Override
	public List<SmartWorkingPrenotation> retrieveByEmployee(String email) {
		try{
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<SmartWorkingPrenotation> query = entityManager.createNamedQuery("SmartWorkingPrenotation.findByEmployee", SmartWorkingPrenotation.class);
			query.setParameter("employee", email);
			entityManager.getTransaction().commit();
			return (List<SmartWorkingPrenotation>) query.getResultList();
		}
		finally{
			entityManager.close();
		}
	}

}
