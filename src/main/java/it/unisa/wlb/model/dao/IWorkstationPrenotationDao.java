package it.unisa.wlb.model.dao;

import java.util.Date;
import java.util.List;

import it.unisa.wlb.model.bean.WorkstationPrenotation;

/**
 * This interface defines public methods offered by WorkstationPrenotationJpa class
 * 
 * @author Luigi Cerrone, Vincenzo Fabiano, Sabato Nocera
 *
 */
public interface IWorkstationPrenotationDao extends IGenericDao<WorkstationPrenotation> {
	
	/**
	 * It is used to retrieve a list of Workstation Prenotation through employee's email
	 * 
	 * @param email
	 * @return a list of Workstation Prenotation
	 */
	List<WorkstationPrenotation> retrieveByEmployee(String email);
	
	/**
	 * It is used to retrieve a list of Workstation Prenotation through calendar week, year and employee's email
	 * 
	 * @param calendarWeek
	 * @param year
	 * @param email
	 * @return a list of Workstation Prenotation
	 */
	List<WorkstationPrenotation> retrieveByWeeklyPlanning(int calendarWeek, int year, String email);
	
	/**
	 * It is used to retrieve a list of Workstation Prenotation through date, floor and room
	 * 
	 * @param date
	 * @param floor
	 * @param room
	 * @return a list of Workstation Prenotation
	 */
	List<WorkstationPrenotation> retrieveByWorkstationDate(Date date, int floor, int room);

}
