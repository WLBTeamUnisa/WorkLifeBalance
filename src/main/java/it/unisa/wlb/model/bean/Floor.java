package it.unisa.wlb.model.bean;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import java.util.List;


/**
 * The persistent class for the FLOOR database table.
 * 
 */
@Entity
@Table(name="FLOOR")
@NamedQuery(name="Floor.findAll", query="SELECT f FROM Floor f")
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

}