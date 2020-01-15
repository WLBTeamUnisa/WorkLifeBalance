package it.unisa.wlb.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPK;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotationPK;

/**
 * The aim of this class is testing Workstation.java and WorkstationPK.java
 * 
 * @author Sabato Nocera
 *
 */
class WorkstationTest {

    private Workstation workstation;
    private WorkstationPK id;
    private Room room;
    private List<WorkstationPrenotation> workstationPrenotations;

    @BeforeEach
    void setUp() throws Exception {
        workstation = new Workstation();

        int numFloor = 1;
        int numRoom = 1;
        int numWorkstation = 1;

        /**
         * Room
         */
        room = new Room();
        Floor floor = new Floor();

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

        RoomPK idRoom = new RoomPK();
        idRoom.setNumFloor(numFloor);
        idRoom.setNumRoom(numRoom);
        room.setId(idRoom);

        List<Workstation> workstations = new ArrayList<Workstation>();
        workstations.add(workstation);
        room.setWorkstations(workstations);

        /**
         * WorkstationPK
         */
        workstation.setRoom(room);
        id = new WorkstationPK();
        id.setFloor(numFloor);
        id.setRoom(numRoom);
        id.setWorkstation(numWorkstation);
        workstation.setId(id);

        /**
         * WorkstationPrenotations
         */
        workstationPrenotations = new ArrayList<WorkstationPrenotation>();

        Calendar localCalendar = Calendar.getInstance();
        TimeZone timeZone = localCalendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        LocalDate today = LocalDateTime.ofInstant(localCalendar.toInstant(), zoneId).toLocalDate();
        localCalendar.setTime(Date.from(today.atStartOfDay().atZone(zoneId).toInstant()));
        int currentCalendarWeek = localCalendar.get(Calendar.WEEK_OF_YEAR);

        WorkstationPrenotation workstationPrenotation = new WorkstationPrenotation();
        workstationPrenotation.setCalendarWeek(currentCalendarWeek);

        Employee employee = new Employee();
        employee.setEmail("m.rossi1@wlb.it");
        employee.setName(surname);
        employee.setPassword(password);
        employee.setStatus(0);
        employee.setSurname(surname);
        workstationPrenotation.setEmployee(employee);
        WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
        workstationPrenotationPK.setEmailEmployee("m.rossi1@wlb.it");
        workstationPrenotationPK.setPrenotationDate(new Date());
        workstationPrenotation.setId(workstationPrenotationPK);
        workstationPrenotation.setYear(localCalendar.get(Calendar.YEAR));
        workstationPrenotation.setWorkstation(workstation);

        workstationPrenotations.add(workstationPrenotation);

        workstation.setWorkstationPrenotations(workstationPrenotations);
    }

    @Test
    public void getIdTest() {
        assertEquals(workstation.getId(), id);
    }

    @Test
    public void setIdTest() {
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(id.getFloor());
        workstationPK.setRoom(id.getRoom());
        workstationPK.setWorkstation(id.getWorkstation() + 1);
        workstation.setId(workstationPK);
        assertEquals(workstation.getId(), workstationPK);
    }

    @Test
    public void getRoomTest() {
        assertEquals(workstation.getRoom(), room);
    }

    @Test
    public void setRoomTest() {
        Room room2 = new Room();
        room2.setFloor(room.getFloor());
        room2.setId(room.getId());
        room2.getId().setNumRoom(room2.getId().getNumRoom() + 1);
        room2.setWorkstations(room2.getWorkstations());
        workstation.setRoom(room2);
        assertEquals(workstation.getRoom(), room2);
    }

    @Test
    public void getWorkstationPrenotationsTest() {
        List<WorkstationPrenotation> list = workstation.getWorkstationPrenotations();
        if (list.size() != workstationPrenotations.size())
            assertTrue(false);
        for (WorkstationPrenotation temp : list)
            if (!workstationPrenotations.contains(temp)) {
                assertTrue(false);
                return;
            }
        assertTrue(true);
    }

    @Test
    public void setWorkstationPrenotationsTest() {
        Calendar localCalendar = Calendar.getInstance();
        TimeZone timeZone = localCalendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        LocalDate today = LocalDateTime.ofInstant(localCalendar.toInstant(), zoneId).toLocalDate();
        localCalendar.setTime(Date.from(today.atStartOfDay().atZone(zoneId).toInstant()));
        int currentCalendarWeek = localCalendar.get(Calendar.WEEK_OF_YEAR);

        WorkstationPrenotation workstationPrenotation = new WorkstationPrenotation();
        workstationPrenotation.setCalendarWeek(currentCalendarWeek);

        Employee employee = new Employee();
        employee.setEmail("m.rossi1@wlb.it");
        employee.setName("Giuseppe");
        employee.setPassword("Giuseppe1234.");
        employee.setStatus(1);
        employee.setSurname("Verdi");
        workstationPrenotation.setEmployee(employee);
        WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
        workstationPrenotationPK.setEmailEmployee("m.rossi1@wlb.it");
        workstationPrenotationPK.setPrenotationDate(new Date());
        workstationPrenotation.setId(workstationPrenotationPK);
        workstationPrenotation.setYear(localCalendar.get(Calendar.YEAR));
        workstationPrenotation.setWorkstation(workstation);

        List<WorkstationPrenotation> workstationPrenotationsList = new ArrayList<WorkstationPrenotation>();
        workstationPrenotationsList.add(workstationPrenotation);

        workstation.setWorkstationPrenotations(workstationPrenotationsList);

        if (!workstation.getWorkstationPrenotations().contains(workstationPrenotation)) {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }

    @Test
    public void addWorkstationPrenotationTest() {
        Calendar localCalendar = Calendar.getInstance();
        TimeZone timeZone = localCalendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        LocalDate today = LocalDateTime.ofInstant(localCalendar.toInstant(), zoneId).toLocalDate();
        localCalendar.setTime(Date.from(today.atStartOfDay().atZone(zoneId).toInstant()));
        int currentCalendarWeek = localCalendar.get(Calendar.WEEK_OF_YEAR);

        WorkstationPrenotation workstationPrenotation = new WorkstationPrenotation();
        workstationPrenotation.setCalendarWeek(currentCalendarWeek);

        Employee employee = new Employee();
        employee.setEmail("m.rossi1@wlb.it");
        employee.setName("Giuseppe");
        employee.setPassword("Giuseppe1234.");
        employee.setStatus(1);
        employee.setSurname("Verdi");
        workstationPrenotation.setEmployee(employee);
        WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
        workstationPrenotationPK.setEmailEmployee("m.rossi1@wlb.it");
        workstationPrenotationPK.setPrenotationDate(new Date());
        workstationPrenotation.setId(workstationPrenotationPK);
        workstationPrenotation.setYear(localCalendar.get(Calendar.YEAR));
        workstationPrenotation.setWorkstation(workstation);

        workstation.addWorkstationPrenotation(workstationPrenotation);

        List<WorkstationPrenotation> list = workstation.getWorkstationPrenotations();

        if (!list.contains(workstationPrenotation)) {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }

    @Test
    public void removeWorkstationPrenotationTest() {
        Calendar localCalendar = Calendar.getInstance();
        TimeZone timeZone = localCalendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        LocalDate today = LocalDateTime.ofInstant(localCalendar.toInstant(), zoneId).toLocalDate();
        localCalendar.setTime(Date.from(today.atStartOfDay().atZone(zoneId).toInstant()));
        int currentCalendarWeek = localCalendar.get(Calendar.WEEK_OF_YEAR);

        WorkstationPrenotation workstationPrenotation = new WorkstationPrenotation();
        workstationPrenotation.setCalendarWeek(currentCalendarWeek);

        Employee employee = new Employee();
        employee.setEmail("m.rossi1@wlb.it");
        employee.setName("Giuseppe");
        employee.setPassword("Giuseppe1234.");
        employee.setStatus(1);
        employee.setSurname("Verdi");
        workstationPrenotation.setEmployee(employee);
        WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
        workstationPrenotationPK.setEmailEmployee("m.rossi1@wlb.it");
        workstationPrenotationPK.setPrenotationDate(new Date());
        workstationPrenotation.setId(workstationPrenotationPK);
        workstationPrenotation.setYear(localCalendar.get(Calendar.YEAR));
        workstationPrenotation.setWorkstation(workstation);

        workstationPrenotations.add(workstationPrenotation);

        List<WorkstationPrenotation> list = workstation.getWorkstationPrenotations();

        if (list.contains(workstationPrenotation)) {
            workstation.removeWorkstationPrenotation(workstationPrenotation);
            if (!list.contains(workstationPrenotation)) {
                assertTrue(true);
                return;
            }
        }
        assertTrue(false);
    }

    @Test
    public void toStringTest() {
        String string = "Workstation [id=" + id + ", room=" + room + "]";
        assertEquals(string, workstation.toString());
    }

    @Test
    public void equalsTest1() {
        Workstation workstation2 = new Workstation();
        workstation2.setId(id);
        workstation2.setRoom(room);
        workstation2.setWorkstationPrenotations(workstationPrenotations);
        assertEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest2() {
        Workstation workstation2 = new Workstation();
        workstation2.setId(new WorkstationPK());
        workstation2.setRoom(room);
        workstation2.setWorkstationPrenotations(workstationPrenotations);
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest3() {
        Workstation workstation2 = new Workstation();
        workstation2.setId(id);
        workstation2.setRoom(new Room());
        workstation2.setWorkstationPrenotations(workstationPrenotations);
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest4() {
        Workstation workstation2 = new Workstation();
        workstation2.setId(id);
        workstation2.setRoom(room);
        List<WorkstationPrenotation> list = new ArrayList<WorkstationPrenotation>();
        list.add(new WorkstationPrenotation());
        workstation2.setWorkstationPrenotations(list);
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest5() {
        Workstation workstation2 = new Workstation();
        workstation2.setId(null);
        workstation2.setRoom(room);
        workstation2.setWorkstationPrenotations(workstationPrenotations);
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest6() {
        Workstation workstation2 = new Workstation();
        workstation2.setId(id);
        workstation2.setRoom(null);
        workstation2.setWorkstationPrenotations(workstationPrenotations);
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest7() {
        Workstation workstation2 = new Workstation();
        workstation2.setId(id);
        workstation2.setRoom(room);
        workstation2.setWorkstationPrenotations(null);
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest8() {
        workstation.setId(null);
        Workstation workstation2 = new Workstation();
        workstation2.setId(id);
        workstation2.setRoom(room);
        workstation2.setWorkstationPrenotations(workstationPrenotations);
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest9() {
        workstation.setRoom(null);
        Workstation workstation2 = new Workstation();
        workstation2.setId(id);
        workstation2.setRoom(room);
        workstation2.setWorkstationPrenotations(workstationPrenotations);
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest10() {
        workstation.setWorkstationPrenotations(null);
        Workstation workstation2 = new Workstation();
        workstation2.setId(id);
        workstation2.setRoom(room);
        workstation2.setWorkstationPrenotations(workstationPrenotations);
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest11() {
        String workstation2 = "";
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest12() {
        Workstation workstation2 = null;
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest13() {
        workstation = null;
        Workstation workstation2 = new Workstation();
        workstation2.setId(id);
        workstation2.setRoom(room);
        workstation2.setWorkstationPrenotations(workstationPrenotations);
        assertNotEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest14() {
        Workstation workstation2 = workstation;
        assertEquals(workstation, workstation2);
    }

    @Test
    public void equalsTest15() {
        String idPk = "";
        assertNotEquals(id, idPk);
    }

    @Test
    public void equalsTest16() {
        WorkstationPK idPk = new WorkstationPK();
        idPk.setFloor(id.getFloor());
        idPk.setRoom(id.getRoom() + 1);
        idPk.setWorkstation(id.getWorkstation());
        assertNotEquals(id, idPk);
    }

    @Test
    public void equalsTest17() {
        WorkstationPK idPk = new WorkstationPK();
        idPk.setFloor(id.getFloor());
        idPk.setRoom(id.getRoom());
        idPk.setWorkstation(id.getWorkstation());
        assertEquals(id, idPk);
    }

    @Test
    public void equalsTest18() {
        WorkstationPK idPk = new WorkstationPK();
        idPk.setFloor(id.getFloor() + 1);
        idPk.setRoom(id.getRoom());
        idPk.setWorkstation(id.getWorkstation());
        assertNotEquals(id, idPk);
    }

    @Test
    public void equalsTest19() {
        WorkstationPK idPk = new WorkstationPK();
        idPk.setFloor(id.getFloor());
        idPk.setRoom(id.getRoom());
        idPk.setWorkstation(id.getWorkstation() + 1);
        assertNotEquals(id, idPk);
    }

}
