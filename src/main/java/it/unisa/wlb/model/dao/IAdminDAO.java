package it.unisa.wlb.model.dao;

import it.unisa.wlb.model.bean.Admin;

public interface IAdminDAO extends IGenericDAO<Admin> {
	
	public Admin retrieveByEmailPassword(String email, String password);
	
}
