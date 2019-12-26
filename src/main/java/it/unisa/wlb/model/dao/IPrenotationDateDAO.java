package it.unisa.wlb.model.dao;
import java.util.List;

import it.unisa.wlb.model.bean.PrenotationDate;

public interface IPrenotationDateDAO extends IGenericDAO<PrenotationDate>{	
	
	List<PrenotationDate> retrieveBySmartWorking(int idSmartWorking, String email);
	
}
