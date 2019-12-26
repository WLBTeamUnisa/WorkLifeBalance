package it.unisa.wlb.model.dao;

import java.util.List;

import javax.ejb.Local;

import it.unisa.wlb.model.bean.SmartWorkingPrenotation;

@Local
public interface ISmartWorkingPrenotationDAO extends IGenericDAO<SmartWorkingPrenotation>{

	SmartWorkingPrenotation retrieveByWeeklyPlanning(int calendarWeek, int year, String email);
	
	List<SmartWorkingPrenotation> retrieveByEmployee(String email);
	
}
