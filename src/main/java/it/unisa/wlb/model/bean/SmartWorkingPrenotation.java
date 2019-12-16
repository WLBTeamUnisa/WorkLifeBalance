package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
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
	private SmartWorkingPrenotation id;

	@Column(name="CALENDAR_WEEK")
	private int calendarWeek;

	@Temporal(TemporalType.DATE)
	private Date year;

	//bi-directional many-to-one association to Date
	@OneToMany(mappedBy="smartWorkingPrenotation")
	private List<Date> dates;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	private Employee employee;

	public SmartWorkingPrenotation() {
	}

	public SmartWorkingPrenotation getId() {
		return this.id;
	}

	public void setId(SmartWorkingPrenotation id) {
		this.id = id;
	}

	public int getCalendarWeek() {
		return this.calendarWeek;
	}

	public void setCalendarWeek(int calendarWeek) {
		this.calendarWeek = calendarWeek;
	}

	public Date getYear() {
		return this.year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public List<Date> getDates() {
		return this.dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}

	public Date addDate(Date date) {
		getDates().add(date);
		date.setSmartWorkingPrenotation(this);

		return date;
	}

	public Date removeDate(Date date) {
		getDates().remove(date);
		date.setSmartWorkingPrenotation(null);

		return date;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}