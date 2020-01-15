package it.unisa.wlb.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPK;
import it.unisa.wlb.model.bean.WorkstationPrenotation;

/**
 * The aim of this class is testing Room.java and RoomPK.java
 * 
 * @author Sabato Nocera
 *
 */
class RoomTest {
    private Room room;
    private Floor floor;
    private RoomPK id;
    private List<Workstation> workstations;

    @BeforeEach
    void setUp() throws Exception {
        int numFloor = 1;
        int numRoom = 1;
        int numWorkstation = 1;

        room = new Room();

        /**
         * Floor
         */
        floor = new Floor();

        String email = "m.rossi1@wlbadmin.it";
        String name = "Mario";
        String surname = "Rossi";
        String password = "MarcoRossi1.";
        List<Floor> floors = new ArrayList<Floor>();

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setName(name);
        admin.setSurname(surname);
        admin.setPassword(password);
        floors.add(floor);
        admin.setFloors(floors);
        admin.setProjects(new ArrayList<Project>());

        floor.setNumFloor(numFloor);
        floor.setAdmin(admin);
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(room);
        floor.setRooms(rooms);
        room.setFloor(floor);

        /**
         * RoomPk
         */
        id = new RoomPK();
        id.setNumFloor(numFloor);
        id.setNumRoom(numRoom);
        room.setId(id);

        /**
         * Workstations
         */
        workstations = new ArrayList<Workstation>();

        Workstation workstation = new Workstation();
        workstation.setRoom(room);
        workstation.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(numFloor);
        workstationPK.setRoom(numRoom);
        workstationPK.setWorkstation(numWorkstation);
        workstation.setId(workstationPK);

        workstations.add(workstation);

        room.setWorkstations(workstations);
    }

    @Test
    public void getIdTest() {
        assertEquals(id, room.getId());
    }

    @Test
    public void setIdTest() {
        RoomPK id2 = new RoomPK();
        id2.setNumFloor(floor.getNumFloor());
        id2.setNumRoom(2);
        room.setId(id2);
        assertEquals(id2, room.getId());
    }

    @Test
    public void getFloorTest() {
        assertEquals(floor, room.getFloor());
    }

    @Test
    public void setFloorTest() {
        Floor floor2 = new Floor();
        String email = "m.rossi2@wlbadmin.it";
        String name = "Mario2";
        String surname = "Rossi2";
        String password = "MarcoRossi2.";
        List<Floor> floors = new ArrayList<Floor>();

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setName(name);
        admin.setSurname(surname);
        admin.setPassword(password);
        floors.add(floor2);
        admin.setFloors(floors);
        admin.setProjects(new ArrayList<Project>());

        floor2.setNumFloor(room.getId().getNumFloor() + 1);
        floor2.setAdmin(admin);
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(room);
        floor2.setRooms(rooms);
        room.setFloor(floor2);
        assertEquals(floor2, room.getFloor());
    }

    @Test
    public void getWorkstationsTest() {
        List<Workstation> list = room.getWorkstations();
        if (list.size() != workstations.size())
            assertTrue(false);
        for (Workstation workstation : list)
            if (!workstations.contains(workstation)) {
                assertTrue(false);
                return;
            }
        assertTrue(true);
    }

    @Test
    public void setWorkstationsTest() {
        ArrayList<Workstation> workstations2 = new ArrayList<Workstation>();

        Workstation workstation = new Workstation();
        workstation.setRoom(room);
        workstation.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(floor.getNumFloor());
        workstationPK.setRoom(room.getId().getNumRoom());
        workstationPK.setWorkstation(3);
        workstation.setId(workstationPK);

        workstations2.add(workstation);

        room.setWorkstations(workstations2);
        assertEquals(workstations2, room.getWorkstations());
    }

    @Test
    public void addWorkstationTest() {
        Workstation workstation = new Workstation();
        workstation.setRoom(room);
        workstation.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(floor.getNumFloor());
        workstationPK.setRoom(room.getId().getNumRoom());
        workstationPK.setWorkstation(3);
        workstation.setId(workstationPK);

        room.addWorkstation(workstation);

        List<Workstation> list = room.getWorkstations();

        if (!list.contains(workstation)) {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }

    @Test
    public void removeWorkstationTest() {
        Workstation workstation = new Workstation();
        workstation.setRoom(room);
        workstation.setWorkstationPrenotations(new ArrayList<WorkstationPrenotation>());
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(floor.getNumFloor());
        workstationPK.setRoom(room.getId().getNumRoom());
        workstationPK.setWorkstation(3);
        workstation.setId(workstationPK);

        room.addWorkstation(workstation);

        List<Workstation> list = room.getWorkstations();

        if (list.contains(workstation)) {
            room.removeWorkstation(workstation);
            if (!list.contains(workstation)) {
                assertTrue(true);
                return;
            }
        }
        assertTrue(false);
    }

    @Test
    public void toStringTest() {
        String string = "Room [id=" + id + ", floor=" + floor + "]";
        assertEquals(string, room.toString());
    }

    @Test
    public void equalsTest1() {
        Room room2 = new Room();
        room2.setFloor(floor);
        room2.setId(id);
        room2.setWorkstations(workstations);
        assertEquals(room, room2);
    }

    @Test
    public void equalsTest2() {
        Room room2 = new Room();
        room2.setFloor(new Floor());
        room2.setId(id);
        room2.setWorkstations(workstations);
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest3() {
        Room room2 = new Room();
        room2.setFloor(floor);
        room2.setId(new RoomPK());
        room2.setWorkstations(workstations);
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest4() {
        Room room2 = new Room();
        room2.setFloor(floor);
        room2.setId(id);
        List<Workstation> list = new ArrayList<Workstation>();
        list.add(new Workstation());
        room2.setWorkstations(list);
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest5() {
        Room room2 = new Room();
        room2.setFloor(null);
        room2.setId(id);
        room2.setWorkstations(workstations);
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest6() {
        Room room2 = new Room();
        room2.setFloor(floor);
        room2.setId(null);
        room2.setWorkstations(workstations);
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest7() {
        Room room2 = new Room();
        room2.setFloor(floor);
        room2.setId(id);
        room2.setWorkstations(null);
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest8() {
        room.setFloor(null);
        Room room2 = new Room();
        room2.setFloor(floor);
        room2.setId(id);
        room2.setWorkstations(workstations);
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest9() {
        room.setId(null);
        Room room2 = new Room();
        room2.setFloor(floor);
        room2.setId(id);
        room2.setWorkstations(workstations);
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest10() {
        room.setWorkstations(null);
        Room room2 = new Room();
        room2.setFloor(floor);
        room2.setId(id);
        room2.setWorkstations(workstations);
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest11() {
        String room2 = "";
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest12() {
        Room room2 = null;
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest13() {
        room = null;
        Room room2 = new Room();
        room2.setFloor(floor);
        room2.setId(id);
        room2.setWorkstations(workstations);
        assertNotEquals(room, room2);
    }

    @Test
    public void equalsTest14() {
        Room room2 = room;
        assertEquals(room, room2);
    }

    @Test
    public void equalsTest15() {
        String idPk = "";
        assertNotEquals(id, idPk);
    }

    @Test
    public void equalsTest16() {
        RoomPK idPk = new RoomPK();
        idPk.setNumFloor(id.getNumFloor());
        idPk.setNumRoom(id.getNumRoom() + 1);
        assertNotEquals(id, idPk);
    }

    @Test
    public void equalsTest17() {
        RoomPK idPk = new RoomPK();
        idPk.setNumFloor(id.getNumFloor());
        idPk.setNumRoom(id.getNumRoom());
        assertEquals(id, idPk);
    }
}
