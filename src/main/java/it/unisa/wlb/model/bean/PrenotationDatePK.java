package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PRENOTATION_DATE database table.
 * 
 */
@Embeddable
public class PrenotationDatePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private java.util.Date date;

	@Column(name="ID_PRENOTATION_SW", insertable=false, updatable=false)
	private int idPrenotationSw;

	@Column(name="EMPLOYEE_EMAIL", insertable=false, updatable=false)
	private String employeeEmail;

	public PrenotationDatePK() {
	}
	public java.util.Date getDate() {
		return this.date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public int getIdPrenotationSw() {
		return this.idPrenotationSw;
	}
	public void setIdPrenotationSw(int idPrenotationSw) {
		this.idPrenotationSw = idPrenotationSw;
	}
	public String getEmployeeEmail() {
		return this.employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

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
			&& (this.idPrenotationSw == castOther.idPrenotationSw)
			&& this.employeeEmail.equals(castOther.employeeEmail);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.date.hashCode();
		hash = hash * prime + this.idPrenotationSw;
		hash = hash * prime + this.employeeEmail.hashCode();
		
		return hash;
	}
}