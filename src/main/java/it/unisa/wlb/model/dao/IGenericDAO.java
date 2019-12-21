package it.unisa.wlb.model.dao;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public interface IGenericDAO<E> {

	public E create(E entity);

	public void remove(E entityClass);

	public E update(E entityClass);

	public List<E> retrieveAll();
	
}
