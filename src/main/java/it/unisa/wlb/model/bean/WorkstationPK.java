package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WorkstationPK implements Serializable{

  private static final long serialVersionUID = 1L;
  
  @Column(name="NUM_WORKSTATION", columnDefinition="int(3)", nullable=false)
  private int workstation;

  @Column(name="NUM_FLOOR", columnDefinition="int(3)", nullable=false)
  private int floor;

  @Column(name="NUM_ROOM", columnDefinition="int(2)", nullable=false)
  private int room;

  public WorkstationPK() {}

  public int getWorkstation() {
    return workstation;
  }

  public void setWorkstation(int workstation) {
    this.workstation = workstation;
  }

  public int getFloor() {
    return floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public int getRoom() {
    return room;
  }

  public void setRoom(int room) {
    this.room = room;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + floor;
    result = prime * result + room;
    result = prime * result + workstation;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    WorkstationPK other = (WorkstationPK) obj;
    if (floor != other.floor)
      return false;
    if (room != other.room)
      return false;
    if (workstation != other.workstation)
      return false;
    return true;
  }


}

