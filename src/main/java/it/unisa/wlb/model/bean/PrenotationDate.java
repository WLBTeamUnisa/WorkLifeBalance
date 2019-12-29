package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRENOTATION_DATE database table.
 * 
 */

@Entity
@Table(name="PRENOTATION_DATE")
@NamedQuery(name="PrenotationDate.findAll", query="SELECT p FROM PrenotationDate p")
public class PrenotationDate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PrenotationDatePK id;

	//bi-directional many-to-one association to SmartWorkingPrenotation
	@ManyToOne
	@JoinColumns(value={
			@JoinColumn(name="EMPLOYEE_EMAIL", columnDefinition="varchar(37)", nullable=false),
			@JoinColumn(name="ID_PRENOTATION_SW", columnDefinition="int(20)", nullable=false)
	})
	@MapsId(value="PrenotationDatePK")
	private SmartWorkingPrenotation smartWorkingPrenotation;

	public PrenotationDate() {
	}

	public PrenotationDatePK getId() {
		return this.id;
	}

	public void setId(PrenotationDatePK id) {
		this.id = id;
	}

	public SmartWorkingPrenotation getSmartWorkingPrenotation() {
		return this.smartWorkingPrenotation;
	}

	public void setSmartWorkingPrenotation(SmartWorkingPrenotation smartWorkingPrenotation) {
		this.smartWorkingPrenotation = smartWorkingPrenotation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((smartWorkingPrenotation == null) ? 0 : smartWorkingPrenotation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrenotationDate other = (PrenotationDate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (smartWorkingPrenotation == null) {
			if (other.smartWorkingPrenotation != null)
				return false;
		} else if (!smartWorkingPrenotation.equals(other.smartWorkingPrenotation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PrenotationDate [id=" + id + ", smartWorkingPrenotation=" + smartWorkingPrenotation + "]";
	}

}