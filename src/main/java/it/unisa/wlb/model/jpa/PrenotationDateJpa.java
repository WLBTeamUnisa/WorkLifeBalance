package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.dao.IPrenotationDateDAO;
import it.unisa.wlb.utils.LoggerSingleton;

@Stateless
@Interceptors({LoggerSingleton.class})
public class PrenotationDateJpa implements IPrenotationDateDAO{

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;
	
	@Override
	public PrenotationDate create(PrenotationDate entity) {
		
		try
		{
			entityManager= factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			return entity;
		}
		
		finally
		{
			entityManager.close();
		}
	}

	@Override
	public void remove(PrenotationDate entityClass) {
		try
		{
		entityManager = factor.createEntityManager();
		entityManager.getTransaction().begin();
	    entityManager.merge(entityClass);
	    entityManager.getTransaction().commit();	
		}
		
		finally
		{
			entityManager.close();
		}
	}

	@Override
	public PrenotationDate update(PrenotationDate entityClass) {
		try
		{
		entityManager = factor.createEntityManager();
		entityManager.getTransaction().begin();
	    entityManager.persist(entityClass);
	    entityManager.getTransaction().commit();
	    return entityClass;
		}
		
		finally
		{
			entityManager.close();
		}
	}

	@Override
	public List<PrenotationDate> retrieveAll() {
		try
		{
		entityManager = factor.createEntityManager();
		entityManager.getTransaction().begin();
		TypedQuery<PrenotationDate> query = entityManager.createNamedQuery("PrenotationDate.findAll", PrenotationDate.class);
		entityManager.getTransaction().commit();
		return (List<PrenotationDate>) query.getResultList();
		}
		
		finally
		{
			entityManager.close();
		}
	}

	@Override
	public List<PrenotationDate> retrieveBySmartWorking(int idSmartWorking, String email) {
		try
		{
		entityManager = factor.createEntityManager();
		entityManager.getTransaction().begin();
		TypedQuery<PrenotationDate> query = entityManager.createNamedQuery("PrenotationDate.findAll", PrenotationDate.class);
		query.setParameter("id.idPrenotationSw", idSmartWorking);
		query.setParameter("id.employeeEmail", email);
		entityManager.getTransaction().commit();
		return (List<PrenotationDate>) query.getResultList();
		}
		
		finally
		{
			entityManager.close();
		}
	}

}
