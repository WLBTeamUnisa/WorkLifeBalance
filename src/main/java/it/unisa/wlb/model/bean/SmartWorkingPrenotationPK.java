package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.interceptor.Interceptors;
import javax.persistence.*;

import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The primary key class for the SMART_WORKING_PRENOTATION database table.
 * 
 */
@Embeddable
@Interceptors({LoggerSingleton.class})
public class SmartWorkingPrenotationPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "int(20)", nullable = false)
	private int id;

	@Column(name = "EMPLOYEE_EMAIL", length = 37, nullable = false)
	private String employee;

	public SmartWorkingPrenotationPK() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeEmail() {
		return this.employee;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employee = employeeEmail;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SmartWorkingPrenotationPK)) {
			return false;
		}
		SmartWorkingPrenotationPK castOther = (SmartWorkingPrenotationPK) other;
		return (this.id == castOther.id) && this.employee.equals(castOther.employee);
	}

	@Override
	public String toString() {
		return "SmartWorkingPrenotationPK [id=" + id + ", employee=" + employee + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	

}