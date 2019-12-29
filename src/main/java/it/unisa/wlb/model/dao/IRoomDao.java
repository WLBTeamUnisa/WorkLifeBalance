package it.unisa.wlb.model.dao;

import java.util.List;

import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Room;

/**
 * This interface defines public methods offered by RoomJpa class
 * 
 * @author Sabato, Michele
 *
 */
public interface IRoomDao extends IGenericDAO<Room>{
	
	/**
	 * It is used to retrieve the list of rooms of a certain floor
	 * 
	 * @param idFloor
	 * @return the list of rooms of a certain floor
	 */
	List<Room> retrieveByFloor(int idFloor);
		
	/**
	 * It is used to count the total number of rooms of a certain floor
	 * 
	 * @param idFloor
	 * @return the total number of rooms of a certain floor
	 */
	int countMaxByFloor(int idFloor);

}
