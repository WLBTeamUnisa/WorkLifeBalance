package it.unisa.wlb.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.dao.IPrenotationDateDao;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this class is implementing methods of IPrenotationDateDao
 * 
 * @author Luigi Cerrone, Vincenzo Fabiano
 *
 */
@Stateless
@Interceptors({ LoggerSingleton.class })
public class PrenotationDateJpa implements IPrenotationDateDao {

    private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
    private EntityManager entityManager;

    @Override
    public PrenotationDate create(PrenotationDate entity) {
        try {
            entityManager = factor.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return entity;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void remove(PrenotationDate entityClass) {
        try {
            entityManager = factor.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(entityClass));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public PrenotationDate update(PrenotationDate entityClass) {
        try {
            entityManager = factor.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(entityClass);
            entityManager.getTransaction().commit();
            return entityClass;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<PrenotationDate> retrieveAll() {
        try {
            entityManager = factor.createEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<PrenotationDate> query = entityManager.createNamedQuery("PrenotationDate.findAll",
                    PrenotationDate.class);
            entityManager.getTransaction().commit();
            return (List<PrenotationDate>) query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<PrenotationDate> retrieveBySmartWorking(int idSmartWorking, String email) {
        try {
            entityManager = factor.createEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<PrenotationDate> query = entityManager.createNamedQuery("PrenotationDate.findBySmartWorking",
                    PrenotationDate.class);
            query.setParameter(1, idSmartWorking);
            query.setParameter(2, email);
            entityManager.getTransaction().commit();
            return (List<PrenotationDate>) query.getResultList();
        } finally {
            entityManager.close();
        }
    }

}
