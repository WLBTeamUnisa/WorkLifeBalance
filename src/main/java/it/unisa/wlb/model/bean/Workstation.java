package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the WORKSTATION database table.
 * 
 */
@Entity
@NamedQuery(name="Workstation.findAll", query="SELECT w FROM Workstation w")
public class Workstation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkstationPK id;

	//bi-directional many-to-one association to WorkstationPrenotation
	@OneToMany(mappedBy="workstation")
	private List<WorkstationPrenotation> workstationPrenotations;

	public WorkstationPK() {
	}

	public WorkstationPK getId() {
		return this.id;
	}

	public void setId(WorkstationPK id) {
		this.id = id;
	}

	public List<WorkstationPrenotation> getWorkstationPrenotations() {
		return this.workstationPrenotations;
	}

	public void setWorkstationPrenotations(List<WorkstationPrenotation> workstationPrenotations) {
		this.workstationPrenotations = workstationPrenotations;
	}

	public WorkstationPrenotation addWorkstationPrenotation(WorkstationPrenotation workstationPrenotation) {
		getWorkstationPrenotations().add(workstationPrenotation);
		workstationPrenotation.setWorkstation(this);

		return workstationPrenotation;
	}

	public WorkstationPrenotation removeWorkstationPrenotation(WorkstationPrenotation workstationPrenotation) {
		getWorkstationPrenotations().remove(workstationPrenotation);
		workstationPrenotation.setWorkstation(null);

		return workstationPrenotation;
	}

}