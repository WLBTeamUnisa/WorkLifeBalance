package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import java.util.Date;


/**
 * The persistent class for the MESSAGE database table.
 * 
 */
@Entity
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="ID", columnDefinition="int(20)", nullable=false)
  private int id;

  @Temporal(TemporalType.DATE)
  @Column(name="DATE", nullable=false)
  private Date date;

  @Column(name="TEXT",length=250, nullable=false)
  private String text;

  //bi-directional many-to-one association to Employee
  @ManyToOne
  @JoinColumn(name="EMPLOYEE_EMAIL", columnDefinition="varchar(37)", nullable=false)
  private Employee employee;

  //bi-directional many-to-one association to Project
  @ManyToOne
  @JoinColumn(name="ID_PROJECT", columnDefinition="int(20)", nullable=false)
  private Project project;

  public Message() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Project getProject() {
    return this.project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

}