package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.interceptor.Interceptors;
import javax.persistence.*;
import it.unisa.wlb.utils.LoggerSingleton;

import java.util.List;


/**
 * The persistent class for the SMART_WORKING_PRENOTATION database table.
 * 
 */
@Entity
@Table(name="SMART_WORKING_PRENOTATION")
@NamedQueries({
	@NamedQuery(name="SmartWorkingPrenotation.findAll", query="SELECT s FROM SmartWorkingPrenotation s"),
	@NamedQuery(name="SmartWorkingPrenotation.findByEmployee", 
	query="SELECT s FROM SmartWorkingPrenotation s WHERE s.id.employee = :employee"),
	@NamedQuery(name="SmartWorkingPrenotation.findByWeeklyPlanning", 
	query="SELECT s FROM SmartWorkingPrenotation s WHERE s.id.employee = :employee AND s.calendarWeek = :calendarWeek AND s.year = :year")
})
@Interceptors({LoggerSingleton.class})
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
	@JoinColumn(name="EMPLOYEE_EMAIL", columnDefinition="varchar(37)", nullable=false, insertable=false, updatable=false)
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
				+ ", employee=" + employee + "]";
	}
}