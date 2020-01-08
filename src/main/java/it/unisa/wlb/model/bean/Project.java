package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.interceptor.Interceptors;
import javax.persistence.*;

import it.unisa.wlb.utils.LoggerSingleton;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PROJECT database table.
 * 
 */
@Entity
@Table(name="PROJECT")
@NamedQueries({
	@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p"),
	@NamedQuery(name="Project.findByManager", query="SELECT project FROM Project project WHERE project.employee.email=:email"),
	@NamedQuery(name="Project.searchByName", query="SELECT project FROM Project project WHERE project.name LIKE CONCAT(:name,'%')"),
	@NamedQuery(name="Project.findByName", query="SELECT project FROM Project project WHERE project.name=?1")
})
@Interceptors({LoggerSingleton.class})
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", columnDefinition="int(20)", nullable=false)
	private int id;

	@Column(name="DESCRIPTION", length=250, nullable=false)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE", nullable=false)
	private Date endDate;

	@Column(name="NAME", length=15, nullable=false)
	private String name;

	@Column(name="SCOPE", length=50, nullable=false)
	private String scope;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE", nullable=false)
	private Date startDate;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="project")
	private List<Message> messages;

	//bi-directional many-to-one association to Admin
	@ManyToOne
	@JoinColumn(name="EMAIL_ADMIN", columnDefinition="varchar(37)", nullable=false)
	private Admin admin;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="EMAIL_MANAGER", columnDefinition="varchar(37)", nullable=false)
	private Employee employee;

	//bi-directional many-to-many association to Employee
	@ManyToMany
	@JoinTable(
			name="WORKS"
			, joinColumns={
					@JoinColumn(name="ID_PROJECT", columnDefinition="int(20)", nullable=false)
			}
			, inverseJoinColumns={
					@JoinColumn(name="EMAIL_EMPLOYEE", columnDefinition="varchar(37)", nullable=false)
			}
			)
	private List<Employee> employees;

	public Project() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setProject(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setProject(null);

		return message;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (employees == null) {
			if (other.employees != null)
				return false;
		} else if (!employees.equals(other.employees))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
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
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", description=" + description + ", endDate=" + endDate + ", name=" + name
				+ ", scope=" + scope + ", startDate=" + startDate + ", admin=" + admin
				+ ", employee=" + employee + "]";
	}

}