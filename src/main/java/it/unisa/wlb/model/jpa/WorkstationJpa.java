package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.dao.IWorkstationDao;

@Stateless
public class WorkstationJpa implements IWorkstationDao{

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager = factor.createEntityManager();

	@Override
	public List<Workstation> retrieveByFloorAndRoom(int idFloor, int idRoom) {
		entityManager.getTransaction().begin();
		TypedQuery<Workstation> query = entityManager.createNamedQuery("Workstation.retrieveByFloorAndRoom", Workstation.class).setParameter(1, idFloor).setParameter(2, idRoom);
		entityManager.getTransaction().commit();
		return (List<Workstation>) query.getResultList();
	}
	@Override
	public Workstation retrieveById(int idFloor, int idRoom, int idWorkstation) {
		entityManager.getTransaction().begin();
		TypedQuery<Workstation> query = entityManager.createNamedQuery("Workstation.retrieveById", Workstation.class).setParameter(1, idWorkstation).setParameter(2, idFloor).setParameter(3, idRoom);
		entityManager.getTransaction().commit();
		return (Workstation) query.getResultList();
	}
	@Override
	public int countMaxByFloorAndRoom(int idFloor, int idRoom) {
		entityManager.getTransaction().begin();
		TypedQuery<Long> query = entityManager.createNamedQuery("Workstation.countMaxByFloorAndRoom", Long.class).setParameter(1, idFloor).setParameter(2, idRoom);
		entityManager.getTransaction().commit();
		return query.getSingleResult().intValue();
	}
	@Override
	public Workstation create(Workstation entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}
	@Override
	public void remove(Workstation entityClass) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityClass);
		entityManager.getTransaction().commit();		
	}
	@Override
	public Workstation update(Workstation entityClass) {
		entityManager.getTransaction().begin();
		entityManager.merge(entityClass);
		entityManager.getTransaction().commit();
		return entityClass;
	}
	@Override
	public List<Workstation> retrieveAll() {
		entityManager.getTransaction().begin();
		TypedQuery<Workstation> query = entityManager.createNamedQuery("Workstation.findAll", Workstation.class);
		entityManager.getTransaction().commit();
		return (List<Workstation>) query.getResultList();
	}

}
