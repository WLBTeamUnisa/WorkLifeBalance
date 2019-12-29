package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.dao.IFloorDao;

@Stateless
public class FloorJpa implements IFloorDao{
	
	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager = factor.createEntityManager();
	
	@Override
	public int countMax() {
		entityManager.getTransaction().begin();
		TypedQuery<Integer> query = entityManager.createNamedQuery("Floor.countMax", Integer.class);
		entityManager.getTransaction().commit();
		return (Integer) query.getSingleResult();
	}

	@Override
	public Floor create(Floor entity) {
		entityManager.getTransaction().begin();
	    entityManager.persist(entity);
	    entityManager.getTransaction().commit();
	    return entity;
	}

	@Override
	public void remove(Floor entityClass) {
		entityManager.getTransaction().begin();
	    entityManager.remove(entityClass);
	    entityManager.getTransaction().commit();
		
	}

	@Override
	public Floor update(Floor entityClass) {
		entityManager.getTransaction().begin();
	    entityManager.merge(entityClass);
	    entityManager.getTransaction().commit();
	    return entityClass;
	}

	@Override
	public List<Floor> retrieveAll() {
		entityManager.getTransaction().begin();
		TypedQuery<Floor> query = entityManager.createNamedQuery("Floor.findAll", Floor.class);
		entityManager.getTransaction().commit();
		return (List<Floor>) query.getResultList();
	}

	@Override
	public Floor retrieveById(int idFloor) {
		entityManager.getTransaction().begin();
		TypedQuery<Floor> query = entityManager.createNamedQuery("Floor.findById", Floor.class).setParameter(1, idFloor);
		entityManager.getTransaction().commit();
		return (Floor) query.getSingleResult();
	}

}
