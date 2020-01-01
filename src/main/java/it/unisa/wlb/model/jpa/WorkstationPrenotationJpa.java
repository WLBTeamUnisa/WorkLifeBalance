package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;

@Stateless
public class WorkstationPrenotationJpa implements IWorkstationPrenotationDao{

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager = factor.createEntityManager();

	@Override
	public WorkstationPrenotation create(WorkstationPrenotation entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void remove(WorkstationPrenotation entityClass) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entityClass);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public WorkstationPrenotation update(WorkstationPrenotation entityClass) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entityClass);
			entityManager.getTransaction().commit();
			return entityClass;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<WorkstationPrenotation> retrieveAll() {
		try {
			entityManager.getTransaction().begin();
			TypedQuery<WorkstationPrenotation> query=entityManager.createNamedQuery("WorkstationPrenotation.findAll", WorkstationPrenotation.class);
			entityManager.getTransaction().commit();
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<WorkstationPrenotation> retrieveByEmployee(String email) {

		try{ 
			entityManager.getTransaction().begin();
			TypedQuery<WorkstationPrenotation> query=entityManager.createNamedQuery("WorkstationPrenotation.findByEmail", WorkstationPrenotation.class);
			query.setParameter("email", email);
			entityManager.getTransaction().commit();
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
	
	@Override
	public List<WorkstationPrenotation> retrieveByWeeklyPlanning(int calendarWeek, int year, String email) {
		try{ 
			entityManager.getTransaction().begin();
			TypedQuery<WorkstationPrenotation> query=entityManager.createNamedQuery("WorkstationPrenotation.findByWeeklyPlanning", WorkstationPrenotation.class);
			query.setParameter("calendarWeek", calendarWeek);
			query.setParameter("year", year);
			query.setParameter("email", email);
			entityManager.getTransaction().commit();
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

}
