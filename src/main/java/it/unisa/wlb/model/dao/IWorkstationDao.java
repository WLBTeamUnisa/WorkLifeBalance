package it.unisa.wlb.model.dao;

import java.util.List;

import it.unisa.wlb.model.bean.Workstation;

/**
 * This interface defines public methods offered by WorkstationJpa class
 * 
 * @author Sabato, Michele
 *
 */
public interface IWorkstationDao extends IGenericDAO<Workstation>{	
 
	 /**
	  * It is used to retrieve the list of workstations of a certain room of a specific floor
	  * 
	  * @param idFloor
	  * @param idRoom
	  * @return the list of workstations of a certain room of a specific floors
	  */
	List<Workstation> retrieveByFloorAndRoom(int idFloor, int idRoom);
	
	/**
	 * It is used to retrieve a specific workstation
	 * 
	 * @param idFloor
	 * @param idRoom
	 * @param idWorkstation
	 * @return a specific workstation
	 */
	Workstation retrieveById(int idFloor, int idRoom, int idWorkstation);
	
	/**
	 * It is used to count the total number of workstation of a specific room of a certain floor
	 * 
	 * @param idFloor
	 * @param idRoom
	 * @return the total number of workstation of a specific room of a certain floor
	 */
	int countMaxByFloorAndRoom(int idFloor, int idRoom);	
}
