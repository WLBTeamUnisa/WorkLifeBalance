package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.interceptor.Interceptors;
import javax.persistence.*;

import it.unisa.wlb.utils.LoggerSingleton;

import java.util.List;


/**
 * The persistent class for the EMPLOYEE database table.
 * 
 */
@Entity
@Table(name="EMPLOYEE")
@NamedQueries({
	@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e"),
	@NamedQuery(name="Employee.findByEmail", query="SELECT e FROM Employee e WHERE e.email = :email"),
	@NamedQuery(name="Employee.findByEmailPassword", query="SELECT e FROM Employee e WHERE e.email = :email and e.password = :password"),
	@NamedQuery(name="Employee.findSuggestsEmployeeByEmail", query="SELECT e FROM Employee e WHERE e.email LIKE CONCAT(:email,'%') AND e.status = 0"),
	@NamedQuery(name="Employee.findSuggestsManagerByEmail", query="SELECT e FROM Employee e WHERE e.email LIKE CONCAT(:email,'%') AND e.status = 1"),
	@NamedQuery(name="Employee.findSuggestsByEmail", query="SELECT e FROM Employee e WHERE e.email LIKE CONCAT(:email,'%')"),
})
@Interceptors({LoggerSingleton.class})
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMAIL", length=37, nullable=false)
	private String email;

	@Column(name="NAME", length=20, nullable=false)
	private String name;

	@Column(name="PASSWORD", length=40, nullable=false)
	private String password;

	@Column(name="STATUS", columnDefinition="int(1)", nullable=false)
	private int status;

	@Column(name="SURNAME", length=20, nullable=false)
	private String surname;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="employee")
	private List<Message> messages;

	//bi-directional many-to-one association to Project (manager foreign key)
	@OneToMany(mappedBy="employee")
	private List<Project> projects1;

	//bi-directional many-to-many association to Project (works table)
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

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
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
		if (projects1 == null) {
			if (other.projects1 != null)
				return false;
		} else if (!projects1.equals(other.projects1))
			return false;
		if (projects2 == null) {
			if (other.projects2 != null)
				return false;
		} else if (!projects2.equals(other.projects2))
			return false;
		if (smartWorkingPrenotations == null) {
			if (other.smartWorkingPrenotations != null)
				return false;
		} else if (!smartWorkingPrenotations.equals(other.smartWorkingPrenotations))
			return false;
		if (status != other.status)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (workstationPrenotations == null) {
			if (other.workstationPrenotations != null)
				return false;
		} else if (!workstationPrenotations.equals(other.workstationPrenotations))
			return false;
		return true;
	}

	@Override
	public String toString() {				
		return "Employee [email=" + email + ", name=" + name + ", password=" + password + ", status=" + status
				+ ", surname=" + surname + "]";
	}
}