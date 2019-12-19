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
  private SmartWorkingPrenotationPK id;

  @Column(name="CALENDAR_WEEK")
  private int calendarWeek;

  @Column(name="YEAR")
  private int year;

  //bi-directional many-to-one association to PrenotationDate
  @OneToMany(mappedBy="smartWorkingPrenotation")
  private List<PrenotationDate> prenotationDates;

  //bi-directional many-to-one association to Employee
  @ManyToOne
  @JoinColumn(name="EMPLOYEE_EMAIL", insertable=false, updatable=false)
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

}