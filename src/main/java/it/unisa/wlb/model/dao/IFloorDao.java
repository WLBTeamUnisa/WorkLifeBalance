package it.unisa.wlb.model.dao;

import it.unisa.wlb.model.bean.Floor;

/**
 * This interface defines public methods offered by FloorJpa class
 * 
 * @author Sabato, Michele
 *
 */
public interface IFloorDao extends IGenericDao<Floor>{
	
	/**
	 * It is used to count the total number of floors
	 * 
	 * @return the total number of floors
	 */
	int countMax();
	
	/**
	 * It is used to retrieve a floor
	 * 
	 * @param idFloor
	 * @return floor
	 */
	Floor retrieveById(int idFloor);
}
