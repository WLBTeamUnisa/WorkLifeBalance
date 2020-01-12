package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.interceptor.Interceptors;
import javax.persistence.*;

import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The primary key class for the PRENOTATION_DATE database table.
 * 
 */
@Embeddable
@Interceptors({LoggerSingleton.class})
public class PrenotationDatePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE", nullable=false)
	private java.util.Date date;

	@Column(name="EMPLOYEE_EMAIL",columnDefinition="varchar(37)", nullable=false)
	private String employee;

	@Column(name="ID_PRENOTATION_SW", columnDefinition="int(20)", nullable=false)
	private int smartWorkingPrenotation;


	public PrenotationDatePK() {
	}
	public java.util.Date getDate() {
		return this.date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public int getIdPrenotationSw() {
		return this.smartWorkingPrenotation;
	}
	public void setIdPrenotationSw(int idPrenotationSw) {
		this.smartWorkingPrenotation = idPrenotationSw;
	}
	public String getEmployeeEmail() {
		return this.employee;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employee = employeeEmail;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PrenotationDatePK)) {
			return false;
		}
		PrenotationDatePK castOther = (PrenotationDatePK)other;
		return 
				this.date.equals(castOther.date)
				&& (this.smartWorkingPrenotation == castOther.smartWorkingPrenotation)
				&& this.employee.equals(castOther.employee);
	}
	
	@Override
	public String toString() {
		return "PrenotationDatePK [date=" + date + ", employee=" + employee + ", smartWorkingPrenotation="
				+ smartWorkingPrenotation + "]";
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	
}