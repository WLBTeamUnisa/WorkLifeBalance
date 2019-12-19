package it.unisa.wlb.model.dao;

import java.util.List;

public interface IGenericDAO<E> {

	public E create(E entity);

	public void remove(E entityClass);

	public E update(E entityClass);

	public List<E> retrieveAll();
	
}
