package it.unisa.wlb.model.dao;

import java.util.List;

import javax.ejb.Local;

import it.unisa.wlb.model.bean.SmartWorkingPrenotation;

/**
 * This interface defines public methods offered by SmartWorkingPrenotationJpa class
 * 
 * @author Vincenzo Fabiano, Luigi Cerrone
 *
 */
@Local
public interface ISmartWorkingPrenotationDAO extends IGenericDAO<SmartWorkingPrenotation>{
	
	/**
	 * It is used to retrieve a Smart Working Prenotation through his calendar week, year and email
	 * 
	 * @param calendarWeek
	 * @param year
	 * @param email
	 * @return
	 */
	SmartWorkingPrenotation retrieveByWeeklyPlanning(int calendarWeek, int year, String email);
	
	/**
	 * It is used to retrieve a List of Smart Working Prenotation through his email
	 * 
	 * @param email
	 * @return
	 */
	List<SmartWorkingPrenotation> retrieveByEmployee(String email);
	
}
