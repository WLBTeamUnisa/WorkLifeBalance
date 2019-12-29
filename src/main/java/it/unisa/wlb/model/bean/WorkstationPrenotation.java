package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the WORKSTATION_PRENOTATION database table.
 * 
 */
@Entity
@Table(name="WORKSTATION_PRENOTATION")
@NamedQuery(name="WorkstationPrenotation.findAll", query="SELECT w FROM WorkstationPrenotation w")
public class WorkstationPrenotation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkstationPrenotationPK id;

	@Column(name="CALENDAR_WEEK", columnDefinition="int(2)", nullable=false)
	private int calendarWeek;

	@Column(name="YEAR", columnDefinition="int(4)", nullable=false)
	private int year;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_EMAIL", insertable=false, updatable=false,nullable=false,columnDefinition="varchar(37)")
	private Employee employee;

	//bi-directional many-to-one association to Workstation
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="NUM_FLOOR", columnDefinition="int(3)", nullable=false), @JoinColumn(name="NUM_ROOM", columnDefinition="int(2)", nullable=false), @JoinColumn(name="NUM_WORKSTATION", columnDefinition="int(3)", nullable=false)
	})
	private Workstation workstation;

	public WorkstationPrenotation() {
	}

	public WorkstationPrenotationPK getId() {
		return this.id;
	}

	public void setId(WorkstationPrenotationPK id) {
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

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Workstation getWorkstation() {
		return this.workstation;
	}

	public void setWorkstation(Workstation workstation) {
		this.workstation = workstation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + calendarWeek;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((workstation == null) ? 0 : workstation.hashCode());
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
		WorkstationPrenotation other = (WorkstationPrenotation) obj;
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
		if (workstation == null) {
			if (other.workstation != null)
				return false;
		} else if (!workstation.equals(other.workstation))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WorkstationPrenotation [id=" + id + ", calendarWeek=" + calendarWeek + ", year=" + year + ", employee="
				+ employee + ", workstation=" + workstation + "]";
	}

}