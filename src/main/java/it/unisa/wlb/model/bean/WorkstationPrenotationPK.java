package it.unisa.wlb.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.interceptor.Interceptors;
import javax.persistence.*;

import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The primary key class for the WORKSTATION_PRENOTATION database table.
 * 
 */
@Embeddable
@Interceptors({LoggerSingleton.class})
public class WorkstationPrenotationPK implements Serializable {

	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="EMPLOYEE_EMAIL", length=37, nullable=false)
	private String emailEmployee;

	@Temporal(TemporalType.DATE)
	@Column(name="PRENOTATION_DATE", nullable=false)
	private Date prenotationDate;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", columnDefinition="int(20)", nullable=false)
	private int id;

	public WorkstationPrenotationPK() {
	}

	public String getEmailEmployee() {
		return this.emailEmployee;
	}
	public void setEmailEmployee(String emailEmployee) {
		this.emailEmployee = emailEmployee;
	}

	public Date getPrenotationDate() {
		return prenotationDate;
	}

	public void setPrenotationDate(Date prenotationDate) {
		this.prenotationDate = prenotationDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkstationPrenotationPK other = (WorkstationPrenotationPK) obj;
		if (emailEmployee == null) {
			if (other.emailEmployee != null)
				return false;
		} else if (!emailEmployee.equals(other.emailEmployee))
			return false;
		if (id != other.id)
			return false;
		if (prenotationDate == null) {
			if (other.prenotationDate != null)
				return false;
		} else if (!prenotationDate.equals(other.prenotationDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WorkstationPrenotationPK [emailEmployee=" + emailEmployee + ", prenotationDate=" + prenotationDate
				+ ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	
}
