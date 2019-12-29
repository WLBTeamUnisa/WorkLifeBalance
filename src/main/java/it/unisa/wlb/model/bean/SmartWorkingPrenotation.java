package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SMART_WORKING_PRENOTATION database table.
 * 
 */
@Entity
@Table(name="SMART_WORKING_PRENOTATION")
@NamedQuery(name="SmartWorkingPrenotation.findAll", query="SELECT s FROM SmartWorkingPrenotation s")
public class SmartWorkingPrenotation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SmartWorkingPrenotationPK id;

	@Column(name="CALENDAR_WEEK",columnDefinition="int(2)", nullable=false)
	private int calendarWeek;

	@Column(name="YEAR", columnDefinition="int(4)", nullable=false)
	private int year;

	//bi-directional many-to-one association to PrenotationDate
	@OneToMany(mappedBy="smartWorkingPrenotation")
	private List<PrenotationDate> prenotationDates;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_EMAIL", columnDefinition="varchar(37)", nullable=false)
	@MapsId("employeeEmail")
	private Employee employee;

	public SmartWorkingPrenotation() {
	}

	public SmartWorkingPrenotationPK getId() {
		return this.id;
	}

	public void setId(SmartWorkingPrenotationPK id) {
		this.id = id;
	}

	public int getCalendarWeek() {
		return this.calendarWeek;
	}

	public void setCalendarWeek(int calendarWeek) {
		this.calendarWeek = calendarWeek;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<PrenotationDate> getPrenotationDates() {
		return this.prenotationDates;
	}

	public void setPrenotationDates(List<PrenotationDate> prenotationDates) {
		this.prenotationDates = prenotationDates;
	}

	public PrenotationDate addPrenotationDate(PrenotationDate prenotationDate) {
		getPrenotationDates().add(prenotationDate);
		prenotationDate.setSmartWorkingPrenotation(this);

		return prenotationDate;
	}

	public PrenotationDate removePrenotationDate(PrenotationDate prenotationDate) {
		getPrenotationDates().remove(prenotationDate);
		prenotationDate.setSmartWorkingPrenotation(null);

		return prenotationDate;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + calendarWeek;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((prenotationDates == null) ? 0 : prenotationDates.hashCode());
		result = prime * result + year;
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
		SmartWorkingPrenotation other = (SmartWorkingPrenotation) obj;
		if (calendarWeek != other.calendarWeek)
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (prenotationDates == null) {
			if (other.prenotationDates != null)
				return false;
		} else if (!prenotationDates.equals(other.prenotationDates))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SmartWorkingPrenotation [id=" + id + ", calendarWeek=" + calendarWeek + ", year=" + year
				+ ", prenotationDates=" + prenotationDates + ", employee=" + employee + "]";
	}

}