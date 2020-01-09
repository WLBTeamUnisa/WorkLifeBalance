package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.interceptor.Interceptors;
import javax.persistence.*;

import it.unisa.wlb.utils.LoggerSingleton;

import java.util.Date;


/**
 * The persistent class for the MESSAGE database table.
 * 
 */
@Entity
@Table(name="message")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
@Interceptors({LoggerSingleton.class})
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id != other.id)
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", date=" + date + ", text=" + text + ", employee=" + employee + ", project="
				+ project + "]";
	}

}