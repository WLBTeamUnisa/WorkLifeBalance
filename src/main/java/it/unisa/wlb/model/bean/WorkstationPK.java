package it.unisa.wlb.model.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the feedback database table.
 * 
 */
@Embeddable
public class WorkstationPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(name="NUM_WORKSTATION")
  private int workstation;

  @Column(name="NUM_FLOOR", insertable=false, updatable=false)
  private int floor;
  
  @Column(name="NUM_ROOM", insertable=false, updatable=false)
  private int room;
  

  public WorkstationPK() {}
  
	public int getWorkstation() {
		return this.workstation;
	}
	public void setNWorkstation(int workstation) {
		this.workstation = workstation;
	}
  
  	public int getRoom() {
		return this.room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public int getFloor() {
		return this.floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}

  /** Override.
   *
   * @see java.lang.Object#equals(Object)
   */
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof WorkstationPK)) {
      return false;
    }
    WorkstationPK castOther = (WorkstationPK) other;
    return (this.room == castOther.room) && (this.workstation == castOther.workstation) && (this.floor == castOther.floor);
  }

  /** Override.
   * 
   * @see java.lang.Object#hashCode()
   */
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.workstation;
    hash = hash * prime + this.room;
    hash = hash * prime + this.floor;
    return hash;
  }
}
