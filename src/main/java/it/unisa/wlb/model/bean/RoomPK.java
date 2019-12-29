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

	@Override
	public String toString() {
		return "RoomPK [numRoom=" + numRoom + ", numFloor=" + numFloor + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numFloor;
		result = prime * result + numRoom;
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
		RoomPK other = (RoomPK) obj;
		if (numFloor != other.numFloor)
			return false;
		if (numRoom != other.numRoom)
			return false;
		return true;
	}

}