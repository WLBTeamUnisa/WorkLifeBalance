package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRENOTATION_DATE database table.
 * 
 */
@Entity
@Table(name="PRENOTATION_DATE")
@NamedQuery(name="PrenotationDate.findAll", query="SELECT p FROM PrenotationDate p")
public class PrenotationDate implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private PrenotationDatePK id;

  //bi-directional many-to-one association to SmartWorkingPrenotation
  @ManyToOne
  @JoinColumns({
    @JoinColumn(name="EMPLOYEE_EMAIL", referencedColumnName="EMPLOYEE_EMAIL"),
    @JoinColumn(name="ID_PRENOTATION_SW", referencedColumnName="ID")
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