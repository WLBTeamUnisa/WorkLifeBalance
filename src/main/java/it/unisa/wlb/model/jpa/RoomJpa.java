package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.dao.IRoomDao;

@Stateless
public class RoomJpa implements IRoomDao{
	
	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager = factor.createEntityManager();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Room> retrieveByFloor(int idFloor) {
		entityManager.getTransaction().begin();
		TypedQuery<Room> query = entityManager.createNamedQuery("Room.retrieveByFloor", Room.class).setParameter(1, idFloor);
		entityManager.getTransaction().commit();
		return (List<Room>) query.getSingleResult();
	}
	@Override
	public int countMaxByFloor(int idFloor) {
		entityManager.getTransaction().begin();
		TypedQuery<Long> query = entityManager.createNamedQuery("Room.countMaxByFloor", Long.class).setParameter(1, idFloor);
		entityManager.getTransaction().commit();
		return query.getSingleResult().intValue();
	}
	@Override
	public Room create(Room entity) {
		entityManager.getTransaction().begin();
	    entityManager.persist(entity);
	    entityManager.getTransaction().commit();
	    return entity;
	}
	@Override
	public void remove(Room entityClass) {
		entityManager.getTransaction().begin();
	    entityManager.remove(entityClass);
	    entityManager.getTransaction().commit();		
	}
	@Override
	public Room update(Room entityClass) {
		entityManager.getTransaction().begin();
	    entityManager.merge(entityClass);
	    entityManager.getTransaction().commit();
	    return entityClass;
	}
	@Override
	public List<Room> retrieveAll() {
		entityManager.getTransaction().begin();
		TypedQuery<Room> query = entityManager.createNamedQuery("Room.findAll", Room.class);
		entityManager.getTransaction().commit();
		return (List<Room>) query.getResultList();
	}

}
