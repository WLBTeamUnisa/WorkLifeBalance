package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ROOM database table.
 * 
 */
@Embeddable
public class RoomPK implements Serializable {
  //default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;
  
  @Column(name="NUM_ROOM", columnDefinition="int(2)", nullable=false)
  private int numRoom;

  @Column(name="NUM_FLOOR", columnDefinition="int(3)", nullable=false)
  private int numFloor;

  public RoomPK() {
  }
  public int getNumRoom() {
    return this.numRoom;
  }
  public void setNumRoom(int numRoom) {
    this.numRoom = numRoom;
  }
  public int getNumFloor() {
    return this.numFloor;
  }
  public void setNumFloor(int numFloor) {
    this.numFloor = numFloor;
  }

  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof RoomPK)) {
      return false;
    }
    RoomPK castOther = (RoomPK)other;
    return 
        (this.numRoom == castOther.numRoom)
        && (this.numFloor == castOther.numFloor);
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.numRoom;
    hash = hash * prime + this.numFloor;

    return hash;
  }
}