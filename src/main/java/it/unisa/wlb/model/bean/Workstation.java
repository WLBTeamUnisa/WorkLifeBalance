package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.interceptor.Interceptors;
import javax.persistence.*;

import it.unisa.wlb.utils.LoggerSingleton;

import java.util.List;


/**
 * The persistent class for the WORKSTATION database table.
 * 
 */
@Entity
@Table(name="workstation")
@NamedQueries({
	@NamedQuery(name="Workstation.findAll", query="SELECT w FROM Workstation w"),
	@NamedQuery(name="Workstation.retrieveByFloorAndRoom", query="SELECT w FROM Workstation w WHERE w.id.floor=?1 AND w.id.room=?2"),
	@NamedQuery(name="Workstation.retrieveById", query="SELECT w FROM Workstation w WHERE w.id.workstation=?1 AND w.id.floor=?2 AND w.id.room=?3"),
	@NamedQuery(name="Workstation.countMaxByFloorAndRoom", query="SELECT COUNT(w) FROM Workstation w WHERE w.id.floor=?1 AND w.id.room=?2"),
})
@Interceptors({LoggerSingleton.class})
public class Workstation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkstationPK id;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumns(value={
			@JoinColumn(name="NUM_FLOOR", columnDefinition="int(3)", nullable=false), @JoinColumn(name="NUM_ROOM",columnDefinition="int(2)", nullable=false)
	})
	@MapsId(value="WorkstationPK")
	private Room room;

	//bi-directional many-to-one association to WorkstationPrenotation
	@OneToMany(mappedBy="workstation")
	private List<WorkstationPrenotation> workstationPrenotations;

	public Workstation() {
	}

	public WorkstationPK getId() {
		return this.id;
	}

	public void setId(WorkstationPK id) {
		this.id = id;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
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

	@Override
	public String toString() {		
		return "Workstation [id=" + id + ", room=" + room + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Workstation other = (Workstation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (workstationPrenotations == null) {
			if (other.workstationPrenotations != null)
				return false;
		} else if (!workstationPrenotations.equals(other.workstationPrenotations))
			return false;
		return true;
	}

}