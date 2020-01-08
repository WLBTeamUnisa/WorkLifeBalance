package it.unisa.wlb.model.bean;

import java.io.Serializable;

import javax.interceptor.Interceptors;
import javax.persistence.*;

import it.unisa.wlb.utils.LoggerSingleton;

import java.util.List;


/**
 * The persistent class for the FLOOR database table.
 * 
 */
@Entity
@Table(name="FLOOR")
@NamedQueries({
	@NamedQuery(name="Floor.findAll", query="SELECT f FROM Floor f"),
	@NamedQuery(name="Floor.findById", query="SELECT f FROM Floor f WHERE f.numFloor=?1"),
	@NamedQuery(name="Floor.countMax", query="SELECT COUNT(f) FROM Floor f")	
})
@Interceptors({LoggerSingleton.class})
public class Floor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUM_FLOOR", columnDefinition="int(3)", nullable=false)
	private int numFloor;

	//bi-directional many-to-one association to Admin
	@ManyToOne
	@JoinColumn(name="EMAIL_ADMIN",columnDefinition="varchar(37)", nullable=false)
	private Admin admin;

	//bi-directional many-to-one association to Room
	@OneToMany(mappedBy="floor")
	private List<Room> rooms;

	public Floor() {
	}

	public int getNumFloor() {
		return this.numFloor;
	}

	public void setNumFloor(int numFloor) {
		this.numFloor = numFloor;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room addRoom(Room room) {
		getRooms().add(room);
		room.setFloor(this);

		return room;
	}

	public Room removeRoom(Room room) {
		getRooms().remove(room);
		room.setFloor(null);

		return room;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Floor other = (Floor) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (numFloor != other.numFloor)
			return false;
		if (rooms == null) {
			if (other.rooms != null)
				return false;
		} else if (!rooms.equals(other.rooms))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Floor [numFloor=" + numFloor + ", admin=" + admin + "]";
	}
}