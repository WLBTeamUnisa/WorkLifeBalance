package it.unisa.wlb.model.dao;

import javax.ejb.Local;

import it.unisa.wlb.model.bean.Admin;

/**
 * This interface defines public methods offered by AdminJpa class
 * 
 * @author Vincenzo Fabiano
 *
 */
@Local
public interface IAdminDao extends IGenericDao<Admin> {
	/**
	 * It is used to retrieve an Admin through his email and password
	 * 
	 * @param email
	 * @param password
	 * @return an Admin
	 */
	public Admin retrieveByEmailPassword(String email, String password);
	
}
