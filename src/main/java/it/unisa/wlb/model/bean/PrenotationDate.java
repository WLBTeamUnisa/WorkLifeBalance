package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * The persistent class for the PRENOTATION_DATE database table.
 * 
 */

@Entity
@Table(name="PRENOTATION_DATE")
@NamedQueries({
	@NamedQuery(name="PrenotationDate.findAll", query="SELECT p FROM PrenotationDate p"),
	@NamedQuery(name="PrenotationDate.findBySmartWorking", query="SELECT p FROM PrenotationDate p WHERE p.id.employee = :employeeEmail AND p.id.smartWorkingPrenotation = :idPrenotationSw")
})

public class PrenotationDate implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private PrenotationDatePK id;

  //bi-directional many-to-one association to SmartWorkingPrenotation
  @ManyToOne
  @JoinColumns(value={
      @JoinColumn(name="EMPLOYEE_EMAIL", columnDefinition="varchar(37)", nullable=false, updatable=false, insertable=false),
      @JoinColumn(name="ID_PRENOTATION_SW", columnDefinition="int(20)", nullable=false, updatable=false, insertable=false)
    })
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

}