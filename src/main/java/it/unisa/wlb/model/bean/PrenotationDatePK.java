package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PRENOTATION_DATE database table.
 * 
 */
@Embeddable
public class PrenotationDatePK implements Serializable {
  //default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Temporal(TemporalType.DATE)
  @Column(name="DATE", nullable=false)
  private java.util.Date date;

  @Column(name="EMPLOYEE_EMAIL",columnDefinition="varchar(37)", nullable=false)
  private String employee;
  
  @Column(name="ID_PRENOTATION_SW", columnDefinition="int(20)", nullable=false)
  private int smartWorkingPrenotation;


  public PrenotationDatePK() {
  }
  public java.util.Date getDate() {
    return this.date;
  }
  public void setDate(java.util.Date date) {
    this.date = date;
  }
  public int getIdPrenotationSw() {
    return this.smartWorkingPrenotation;
  }
  public void setIdPrenotationSw(int idPrenotationSw) {
    this.smartWorkingPrenotation = idPrenotationSw;
  }
  public String getEmployeeEmail() {
    return this.employee;
  }
  public void setEmployeeEmail(String employeeEmail) {
    this.employee = employeeEmail;
  }

  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof PrenotationDatePK)) {
      return false;
    }
    PrenotationDatePK castOther = (PrenotationDatePK)other;
    return 
        this.date.equals(castOther.date)
        && (this.smartWorkingPrenotation == castOther.smartWorkingPrenotation)
        && this.employee.equals(castOther.employee);
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.date.hashCode();
    hash = hash * prime + this.smartWorkingPrenotation;
    hash = hash * prime + this.employee.hashCode();

    return hash;
  }
}