package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PROJECT database table.
 * 
 */
@Entity
@Table(name="PROJECT")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
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

}