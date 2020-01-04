package it.unisa.wlb.model.jpa;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IProjectDAO;

@Stateless
public class ProjectJpa implements IProjectDAO {

	private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
	private EntityManager entityManager;

	@Override
	public Project create(Project entity) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public void remove(Project entityClass) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(entityClass));
			entityManager.getTransaction().commit();
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public Project update(Project entityClass) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(entityClass);
			entityManager.getTransaction().commit();
			return entityClass;
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public List<Project> retrieveAll() {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<Project> query = entityManager.createNamedQuery("Project.findAll", Project.class);
			entityManager.getTransaction().commit();
			return (List<Project>) query.getResultList();
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public List<Project> retrieveByManager(String email) {
		try {
			entityManager = factor.createEntityManager();
			TypedQuery<Project> query = entityManager.createNamedQuery("Project.findByManager", Project.class);
			query.setParameter("email", email);
			return (List<Project>) query.getResultList();
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public List<Project> searchByName(String name) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<Project> query = entityManager.createNamedQuery("Project.searchByName", Project.class);
			query.setParameter("name", name);
			entityManager.getTransaction().commit();
			return (List<Project>) query.getResultList();
		}

		finally {
			entityManager.close();
		}
	}

	@Override
	public Project retrieveByName(String name) {
		try {
			entityManager = factor.createEntityManager();
			entityManager.getTransaction().begin();
			Query query = entityManager.createQuery("SELECT project FROM Project project WHERE project.name=?1");
			query.setParameter(1, name);
			entityManager.getTransaction().commit();
			return (Project) query.getSingleResult();
		}

		finally {
			entityManager.close();
		}
	}

}