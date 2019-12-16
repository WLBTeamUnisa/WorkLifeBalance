package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the WORKSTATION_PRENOTATION database table.
 * 
 */
@Embeddable
public class WorkstationPrenotationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(name="EMAIL_EMPLOYEE", insertable=false, updatable=false)
	private String emailEmployee;

	public WorkstationPrenotationPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailEmployee() {
		return this.emailEmployee;
	}
	public void setEmailEmployee(String emailEmployee) {
		this.emailEmployee = emailEmployee;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WorkstationPrenotationPK)) {
			return false;
		}
		WorkstationPrenotationPK castOther = (WorkstationPrenotationPK)other;
		return 
			(this.id == castOther.id)
			&& this.emailEmployee.equals(castOther.emailEmployee);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.emailEmployee.hashCode();
		
		return hash;
	}
}