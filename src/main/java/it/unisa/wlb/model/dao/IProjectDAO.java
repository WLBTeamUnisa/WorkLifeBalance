package it.unisa.wlb.model.dao;

import java.util.List;


import javax.ejb.Local;

import it.unisa.wlb.model.bean.Project;

@Local
public interface IProjectDAO extends IGenericDAO<Project> {

  public List<Project> retrieveByManager(String email);

  public List<Project> searchByName(String name);

  public Project retrieveById(int id);

  public List<Project> retrieveByEmployee(String email);

  public void insertEmployeeToProject(String email, int id_project);
  
  public Project retrieveByName(String name);
}
