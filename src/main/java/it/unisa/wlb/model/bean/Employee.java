package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the EMPLOYEE database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String email;

  private String name;

  private String password;

  private byte status;

  private String surname;

  //bi-directional many-to-one association to Message
  @OneToMany(mappedBy="employee")
  private List<Message> messages;

  //bi-directional many-to-one association to Project
  @OneToMany(mappedBy="employee")
  private List<Project> projects1;

  //bi-directional many-to-many association to Project
  @ManyToMany(mappedBy="employees")
  private List<Project> projects2;

  //bi-directional many-to-one association to SmartWorkingPrenotation
  @OneToMany(mappedBy="employee")
  private List<SmartWorkingPrenotation> smartWorkingPrenotations;

  //bi-directional many-to-one association to WorkstationPrenotation
  @OneToMany(mappedBy="employee")
  private List<WorkstationPrenotation> workstationPrenotations;

  public Employee() {
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public byte getStatus() {
    return this.status;
  }

  public void setStatus(byte status) {
    this.status = status;
  }

  public String getSurname() {
    return this.surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public List<Message> getMessages() {
    return this.messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

  public Message addMessage(Message message) {
    getMessages().add(message);
    message.setEmployee(this);

    return message;
  }

  public Message removeMessage(Message message) {
    getMessages().remove(message);
    message.setEmployee(null);

    return message;
  }

  public List<Project> getProjects1() {
    return this.projects1;
  }

  public void setProjects1(List<Project> projects1) {
    this.projects1 = projects1;
  }

  public Project addProjects1(Project projects1) {
    getProjects1().add(projects1);
    projects1.setEmployee(this);

    return projects1;
  }

  public Project removeProjects1(Project projects1) {
    getProjects1().remove(projects1);
    projects1.setEmployee(null);

    return projects1;
  }

  public List<Project> getProjects2() {
    return this.projects2;
  }

  public void setProjects2(List<Project> projects2) {
    this.projects2 = projects2;
  }

  public List<SmartWorkingPrenotation> getSmartWorkingPrenotations() {
    return this.smartWorkingPrenotations;
  }

  public void setSmartWorkingPrenotations(List<SmartWorkingPrenotation> smartWorkingPrenotations) {
    this.smartWorkingPrenotations = smartWorkingPrenotations;
  }

  public SmartWorkingPrenotation addSmartWorkingPrenotation(SmartWorkingPrenotation smartWorkingPrenotation) {
    getSmartWorkingPrenotations().add(smartWorkingPrenotation);
    smartWorkingPrenotation.setEmployee(this);

    return smartWorkingPrenotation;
  }

  public SmartWorkingPrenotation removeSmartWorkingPrenotation(SmartWorkingPrenotation smartWorkingPrenotation) {
    getSmartWorkingPrenotations().remove(smartWorkingPrenotation);
    smartWorkingPrenotation.setEmployee(null);

    return smartWorkingPrenotation;
  }

  public List<WorkstationPrenotation> getWorkstationPrenotations() {
    return this.workstationPrenotations;
  }

  public void setWorkstationPrenotations(List<WorkstationPrenotation> workstationPrenotations) {
    this.workstationPrenotations = workstationPrenotations;
  }

  public WorkstationPrenotation addWorkstationPrenotation(WorkstationPrenotation workstationPrenotation) {
    getWorkstationPrenotations().add(workstationPrenotation);
    workstationPrenotation.setEmployee(this);

    return workstationPrenotation;
  }

  public WorkstationPrenotation removeWorkstationPrenotation(WorkstationPrenotation workstationPrenotation) {
    getWorkstationPrenotations().remove(workstationPrenotation);
    workstationPrenotation.setEmployee(null);

    return workstationPrenotation;
  }

}