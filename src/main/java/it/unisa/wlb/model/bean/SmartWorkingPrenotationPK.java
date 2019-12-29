package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SMART_WORKING_PRENOTATION database table.
 * 
 */
@Embeddable
public class SmartWorkingPrenotationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", columnDefinition="int(20)", nullable=false)
	private int id;

	@Column(name="EMPLOYEE_EMAIL",length=37, nullable=false)
	private String employeeEmail;

	public SmartWorkingPrenotationPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
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
		if (!(other instanceof SmartWorkingPrenotationPK)) {
			return false;
		}
		SmartWorkingPrenotationPK castOther = (SmartWorkingPrenotationPK)other;
		return 
				(this.id == castOther.id)
				&& this.employeeEmail.equals(castOther.employeeEmail);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.employeeEmail.hashCode();

		return hash;
	}
	
	@Override
	public String toString() {
		return "SmartWorkingPrenotationPK [id=" + id + ", employeeEmail=" + employeeEmail + "]";
	}
}