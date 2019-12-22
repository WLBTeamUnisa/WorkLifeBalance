package it.unisa.wlb.model.dao;

import java.util.List;

import javax.ejb.Local;

import it.unisa.wlb.model.bean.Employee;

@Local
public interface IEmployeeDAO extends IGenericDAO<Employee> {

	public Employee retrieveByEmail(String email);

	public List<Employee> searchByEmail(String email);

	public List<Employee> retrieveByProjectId(String ProjectId);
	
	public Employee retrieveByEmailPassword(String email, String password);
	
	public List<Employee> retrieveSuggestsByEmail(String email);
	
}
