package it.unisa.wlb.model.dao;
import java.util.List;

import javax.ejb.Local;

import it.unisa.wlb.model.bean.PrenotationDate;

/**
 * This interface defines public methods offered by PrenotationDateJpa class
 * 
 * @author Vincenzo Fabiano, Luigi Cerrone
 *
 */
@Local
public interface IPrenotationDateDAO extends IGenericDAO<PrenotationDate>{	
	
	/**
	 * It is used to retrieve a list of Prenotation Date through email and Smart Working Id
	 * @param idSmartWorking
	 * @param email
	 * @return
	 */
	List<PrenotationDate> retrieveBySmartWorking(int idSmartWorking, String email);
	
}
