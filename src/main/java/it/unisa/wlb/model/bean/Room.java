package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ROOM database table.
 * 
 */
@Entity
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
public class Room implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private RoomPK id;

  //bi-directional many-to-one association to Floor
  @ManyToOne
  @JoinColumn(name="NUM_FLOOR")
  private Floor floor;

  //bi-directional many-to-one association to Workstation
  @OneToMany(mappedBy="room")
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

}