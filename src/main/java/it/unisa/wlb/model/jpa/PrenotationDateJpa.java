package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.unisa.wlb.model.bean.PrenotationDate;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrenotationDate> retrieveBySmartWorking(int idSmartWorking, String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
