package it.unisa.wlb.model.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;

/**
 * The primary key class for the WORKSTATION_PRENOTATION database table.
 * 
 */
@Embeddable
public class WorkstationPrenotationPK implements Serializable {

  //default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(name="EMPLOYEE_EMAIL", length=37, nullable=false)
  private String emailEmployee;
  
  @Temporal(TemporalType.DATE)
  @Column(name="PRENOTATION_DATE", nullable=false)
  private Date prenotationDate;
  
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="ID", columnDefinition="int(20)", nullable=false)
  private int id;

  public WorkstationPrenotationPK() {
  }

  public String getEmailEmployee() {
    return this.emailEmployee;
  }
  public void setEmailEmployee(String emailEmployee) {
    this.emailEmployee = emailEmployee;
  }

  public Date getPrenotationDate() {
    return prenotationDate;
  }

  public void setPrenotationDate(Date prenotationDate) {
    this.prenotationDate = prenotationDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((emailEmployee == null) ? 0 : emailEmployee.hashCode());
    result = prime * result + ((prenotationDate == null) ? 0 : prenotationDate.hashCode());
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
    WorkstationPrenotationPK other = (WorkstationPrenotationPK) obj;
    if (emailEmployee == null) {
      if (other.emailEmployee != null)
        return false;
    } else if (!emailEmployee.equals(other.emailEmployee))
      return false;
    if (prenotationDate == null) {
      if (other.prenotationDate != null)
        return false;
    } else if (!prenotationDate.equals(other.prenotationDate))
      return false;
    return true;
  }

  

}
