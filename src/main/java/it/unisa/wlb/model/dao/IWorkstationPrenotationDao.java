package it.unisa.wlb.model.dao;

import java.util.Date;
import java.util.List;

import it.unisa.wlb.model.bean.WorkstationPrenotation;

public interface IWorkstationPrenotationDao extends IGenericDAO<WorkstationPrenotation>{
	
	List<WorkstationPrenotation> retrieveByEmployee(String email);
	
	List<WorkstationPrenotation> retrieveByWeeklyPlanning(int calendarWeek, int year, String email);
	
	List<WorkstationPrenotation> retrieveByWorkstationDate(Date date, int floor, int room);

}
