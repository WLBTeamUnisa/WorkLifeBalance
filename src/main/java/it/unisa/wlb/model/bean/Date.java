package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DATE database table.
 * 
 */
@Entity
@NamedQuery(name="Date.findAll", query="SELECT d FROM Date d")
public class Date implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatePK id;

	//bi-directional many-to-one association to SmartWorkingPrenotation
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="EMPLOYEE_EMAIL", referencedColumnName="EMPLOYEE_EMAIL"),
		@JoinColumn(name="ID_PRENOTATION_SW", referencedColumnName="ID")
		})
	private SmartWorkingPrenotation smartWorkingPrenotation;

	public Date() {
	}

	public DatePK getId() {
		return this.id;
	}

	public void setId(DatePK id) {
		this.id = id;
	}

	public SmartWorkingPrenotation getSmartWorkingPrenotation() {
		return this.smartWorkingPrenotation;
	}

	public void setSmartWorkingPrenotation(SmartWorkingPrenotation smartWorkingPrenotation) {
		this.smartWorkingPrenotation = smartWorkingPrenotation;
	}

}