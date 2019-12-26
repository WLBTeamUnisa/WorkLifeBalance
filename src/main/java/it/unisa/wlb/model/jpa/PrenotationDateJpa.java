package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.dao.IPrenotationDateDAO;

public class PrenotationDateJpa implements IPrenotationDateDAO{

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager = factor.createEntityManager();
	
	@Override
	public PrenotationDate create(PrenotationDate entity) {
		entityManager.getTransaction().begin();
	    entityManager.persist(entity);
	    entityManager.getTransaction().commit();
	    return entity;
	}

	@Override
	public void remove(PrenotationDate entityClass) {
		entityManager.getTransaction().begin();
	    entityManager.merge(entityClass);
	    entityManager.getTransaction().commit();	
	}

	@Override
	public PrenotationDate update(PrenotationDate entityClass) {
		entityManager.getTransaction().begin();
	    entityManager.persist(entityClass);
	    entityManager.getTransaction().commit();
	    return entityClass;
	}

	@Override
	public List<PrenotationDate> retrieveAll() {
		entityManager.getTransaction().begin();
		TypedQuery<PrenotationDate> query = entityManager.createNamedQuery("PrenotationDate.findAll", PrenotationDate.class);
		entityManager.getTransaction().commit();
		return (List<PrenotationDate>) query.getResultList();
	}

	@Override
	public List<PrenotationDate> retrieveBySmartWorking(int idSmartWorking, String email) {
		entityManager.getTransaction().begin();
		TypedQuery<PrenotationDate> query = entityManager.createNamedQuery("PrenotationDate.findAll", PrenotationDate.class);
		query.setParameter("id.idPrenotationSw", idSmartWorking);
		query.setParameter("id.employeeEmail", email);
		entityManager.getTransaction().commit();
		return (List<PrenotationDate>) query.getResultList();
	}

}
