package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.interceptor.Interceptors;
import javax.persistence.*;

import it.unisa.wlb.utils.LoggerSingleton;

import java.util.List;

/**
 * The persistent class for the ROOM database table.
 * 
 */
@Entity
@Table(name = "room")
@NamedQueries({ @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
        @NamedQuery(name = "Room.countMaxByFloor", query = "SELECT COUNT(r) FROM Room r WHERE r.id.numFloor =?1"),
        @NamedQuery(name = "Room.retrieveByFloor", query = "SELECT r FROM Room r WHERE r.id.numFloor =?1"), })
@Interceptors({ LoggerSingleton.class })
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RoomPK id;

    /**
     * bi-directional many-to-one association to Floor
     */
    @ManyToOne
    @JoinColumn(name = "NUM_FLOOR", columnDefinition = "int(3)", nullable = false)
    @MapsId("numFloor")
    private Floor floor;

    /**
     * bi-directional many-to-one association to Workstation
     */
    @OneToMany(mappedBy = "room")
    private List<Workstation> workstations;

    public Room() {
    }

    public RoomPK getId() {
        return this.id;
    }

    public void setId(RoomPK id) {
        this.id = id;
    }

    public Floor getFloor() {
        return this.floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public List<Workstation> getWorkstations() {
        return this.workstations;
    }

    public void setWorkstations(List<Workstation> workstations) {
        this.workstations = workstations;
    }

    public Workstation addWorkstation(Workstation workstation) {
        getWorkstations().add(workstation);
        workstation.setRoom(this);

        return workstation;
    }

    public Workstation removeWorkstation(Workstation workstation) {
        getWorkstations().remove(workstation);
        workstation.setRoom(null);

        return workstation;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", floor=" + floor + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Room other = (Room) obj;
        if (floor == null) {
            if (other.floor != null)
                return false;
        } else if (!floor.equals(other.floor))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (workstations == null) {
            if (other.workstations != null)
                return false;
        } else if (!workstations.equals(other.workstations))
            return false;
        return true;
    }
}