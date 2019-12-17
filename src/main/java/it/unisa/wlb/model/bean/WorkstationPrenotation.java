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

  @Column(name="CALENDAR_WEEK")
  private int calendarWeek;

  @Temporal(TemporalType.DATE)
  @Column(name="PRENOTATION_DATE")
  private Date prenotationDate;

  @Temporal(TemporalType.DATE)
  private Date year;

  //bi-directional many-to-one association to Employee
  @ManyToOne
  @JoinColumn(name="EMAIL_EMPLOYEE")
  private Employee employee;

  //bi-directional many-to-one association to Workstation
  @ManyToOne
  @JoinColumns({
    @JoinColumn(name="NUM_FLOOR", referencedColumnName="NUM_FLOOR"),
    @JoinColumn(name="NUM_ROOM", referencedColumnName="NUM_ROOM"),
    @JoinColumn(name="NUM_WORKSTATION", referencedColumnName="NUM_WORKSTATION")
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

  public Date getYear() {
    return this.year;
  }

  public void setYear(Date year) {
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