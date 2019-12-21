package it.unisa.wlb.model.dao;

import javax.ejb.Local;

import it.unisa.wlb.model.bean.Admin;

@Local
public interface IAdminDAO extends IGenericDAO<Admin> {
	
	public Admin retrieveByEmailPassword(String email, String password);
	
}
