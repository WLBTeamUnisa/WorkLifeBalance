package it.unisa.wlb.model.dao;

import java.util.List;

import it.unisa.wlb.model.bean.Employee;

public interface IEmployeeDao extends IGenericDAO<Employee> {

	public Employee retrieveByEmail(String email);

	public List<Employee> searchByEmail(String email);

	public List<Employee> retrieveByProjectId(String ProjectId);

}
