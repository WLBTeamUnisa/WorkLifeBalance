package it.unisa.wlb.model.dao;

import java.util.List;

import javax.ejb.Local;

import it.unisa.wlb.model.bean.Employee;

/**
 * This interface defines public methods offered by EmployeeJpa class
 * 
 * @author Sabato
 *
 */
@Local
public interface IEmployeeDAO extends IGenericDAO<Employee> {

	/**
	 * It is used to retrieve an Employee through his email
	 * 
	 * @param email
	 * @return the Employee associated with the email specified
	 */
	public Employee retrieveByEmail(String email);

	/**
	 * It is used to retrieve a list of Employees whose email contains the email specified
	 * 
	 * @param email
	 * @return a list of Employees whose email contains the email specified
	 */
	public List<Employee> suggestByEmail(String email);

	/**
	 * It is used to retrieve the list of Employees who is working to a particular project
	 * 
	 * @param ProjectId represents the id of the project interested
	 * @return the list of Employees who is working to the project whose id is ProjectId
	 */
	public List<Employee> retrieveByProjectId(String ProjectId);
	
	/**
	 * It is used to retrieve an Employee through his email and password
	 * 
	 * @param email
	 * @param password
	 * @return an Employee
	 */
	public Employee retrieveByEmailPassword(String email, String password);
	
	/**
	 * It is used to retrieve an Employee through his email for suggests
	 * 
	 * @param email
	 * @return a list of Employees
	 */
	public List<Employee> retrieveSuggestsEmployeeByEmail(String email);
	
	/**
	 * It is used to retrieve a Manager through his email for suggests
	 * 
	 * @param email
	 * @return
	 */
	public List<Employee> retrieveSuggestsManagerByEmail(String email);
	
}
