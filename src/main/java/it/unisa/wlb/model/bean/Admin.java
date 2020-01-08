package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.interceptor.Interceptors;
import javax.persistence.*;

import it.unisa.wlb.utils.LoggerSingleton;

import java.util.List;


/**
 * The persistent class for the ADMIN database table.
 * 
 */
@Entity
@Table(name="ADMIN")
@NamedQueries({
	@NamedQuery(name="Admin.findAll", query="SELECT a FROM Admin a"),
	@NamedQuery(name = "Admin.findByEmailPassword", query = "SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password")
})
@Interceptors({LoggerSingleton.class})
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMAIL", length=37, nullable=false)
	private String email;

	@Column(name="NAME", length=20, nullable=false)
	private String name;

	@Column(name="PASSWORD", length=40, nullable=false)
	private String password;

	@Column(name="SURNAME", length=20, nullable=false)
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (floors == null) {
			if (other.floors != null)
				return false;
		} else if (!floors.equals(other.floors))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (projects == null) {
			if (other.projects != null)
				return false;
		} else if (!projects.equals(other.projects))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Admin [email=" + email + ", name=" + name + ", password=" + password + ", surname=" + surname
				+ "]";
	}

}