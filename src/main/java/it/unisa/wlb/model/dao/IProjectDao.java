package it.unisa.wlb.model.dao;

import java.util.List;

import javax.ejb.Local;

import it.unisa.wlb.model.bean.Project;

/**
 * This interface defines public methods offered ProjectJpa class
 * 
 * @author Michele Montano, Luigi Cerrone
 *
 */
@Local
public interface IProjectDao extends IGenericDao<Project> {

    /**
     * It is used to retrieve a list of Projects through their manager's email
     * 
     * @param email
     * @return a list of Projects
     */
    public List<Project> retrieveByManager(String email);

    /**
     * It is used to search a project through his name
     * 
     * @param name
     * @return a list of Projects
     */
    public List<Project> searchByName(String name);

    /**
     * It is used to retrieve a project through his name
     * 
     * @param name
     * @return a Project
     */
    public Project retrieveByName(String name);

}
