package it.unisa.wlb.model.dao;

import java.util.List;

import javax.ejb.Stateless;

/**
 * This interface defines public methods offered by Jpa classes
 * 
 * @author Sabato Nocera
 *
 * @param <E>
 */
@Stateless
public interface IGenericDao<E> {

    /**
     * It is used to store an element into the database
     * 
     * @param entity
     *            that is meant to be stored into the database
     * @return entity created
     */
    public E create(E entity);

    /**
     * It is used to remove an element from the database
     * 
     * @param entityClass
     *            represents the element that is meant to be removed from the database
     */
    public void remove(E entityClass);

    /**
     * It is used to update an element of the database
     * 
     * @param entityClass
     *            represents the element that is meant to be updated
     * @return entityClass updated
     */
    public E update(E entityClass);

    /**
     * It is used to retrieve all the instances of an entity from the database
     * 
     * @return a list of E
     */
    public List<E> retrieveAll();

}
