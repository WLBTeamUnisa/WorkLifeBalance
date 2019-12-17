package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ADMIN database table.
 * 
 */
@Entity
@NamedQuery(name="Admin.findAll", query="SELECT a FROM Admin a")
public class Admin implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String email;

  private String name;

  private String password;

  private String surname;

  //bi-directional many-to-one association to Floor
  @OneToMany(mappedBy="admin")
  private List<Floor> floors;

  //bi-directional many-to-one association to Project
  @OneToMany(mappedBy="admin")
  private List<Project> projects;

  public Admin() {
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

  public String getSurname() {
    return this.surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public List<Floor> getFloors() {
    return this.floors;
  }

  public void setFloors(List<Floor> floors) {
    this.floors = floors;
  }

  public Floor addFloor(Floor floor) {
    getFloors().add(floor);
    floor.setAdmin(this);

    return floor;
  }

  public Floor removeFloor(Floor floor) {
    getFloors().remove(floor);
    floor.setAdmin(null);

    return floor;
  }

  public List<Project> getProjects() {
    return this.projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public Project addProject(Project project) {
    getProjects().add(project);
    project.setAdmin(this);

    return project;
  }

  public Project removeProject(Project project) {
    getProjects().remove(project);
    project.setAdmin(null);

    return project;
  }

}