package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

  @Temporal(TemporalType.DATE)
  @Column(name="PRENOTATION_DATE", nullable=false)
  private Date prenotationDate;

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

  public Date getPrenotationDate() {
    return this.prenotationDate;
  }

  public void setPrenotationDate(Date prenotationDate) {
    this.prenotationDate = prenotationDate;
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

}