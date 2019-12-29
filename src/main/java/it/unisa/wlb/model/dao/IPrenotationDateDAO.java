package it.unisa.wlb.model.dao;
import java.util.List;

import javax.ejb.Local;

import it.unisa.wlb.model.bean.PrenotationDate;

@Local
public interface IPrenotationDateDAO extends IGenericDAO<PrenotationDate>{	
	
	List<PrenotationDate> retrieveBySmartWorking(int idSmartWorking, String email);
	
}
