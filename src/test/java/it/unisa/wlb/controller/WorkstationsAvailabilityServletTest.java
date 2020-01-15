package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.WorkstationsAvailabilityServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPK;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotationPK;
import it.unisa.wlb.model.dao.IFloorDao;
import it.unisa.wlb.model.dao.IRoomDao;
import it.unisa.wlb.model.dao.IWorkstationDao;
import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;
import static org.mockito.Mockito.when;

/**
 * The aim of this class is testing WorkstationsAvailabilityServlet.java
 * 
 * @author Vincenzo Fabiano
 *
 */
class WorkstationsAvailabilityServletTest {

    @Mock
    private IFloorDao floorDao;

    @Mock
    private IRoomDao roomDao;

    @Mock
    private IWorkstationPrenotationDao workstationPrenotationDao;

    @Mock
    private IWorkstationDao workstationDao;

    private WorkstationsAvailabilityServlet servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    private Floor floor;
    private Room room;
    private RoomPK roomPk;
    private List<Room> roomList;
    private Workstation workstation1;
    private WorkstationPK workstationPk1;
    private List<Workstation> workstationList;
    private int numberFloor;
    private int numberRoom;
    private WorkstationPrenotation workstationPrenotation;
    private WorkstationPrenotationPK workstationPrenotationPk;
    private WorkstationPK workstationPk;
    private List<WorkstationPrenotation> workstationPrenotationList;
    private Employee employee;
    private String email;
    private String date;
    private Date datePrenotation;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new WorkstationsAvailabilityServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        numberFloor = 1;
        floor = new Floor();
        floor.setNumFloor(numberFloor);
        numberRoom = 1;
        roomPk = new RoomPK();
        roomPk.setNumFloor(numberFloor);
        roomPk.setNumRoom(numberRoom);
        room = new Room();
        room.setFloor(floor);
        room.setId(roomPk);

        int numberWorkstation1 = 1;
        workstationPk1 = new WorkstationPK();
        workstationPk1.setFloor(numberFloor);
        workstationPk1.setRoom(numberRoom);
        workstationPk1.setWorkstation(numberWorkstation1);
        workstation1 = new Workstation();
        workstation1.setId(workstationPk1);
        workstation1.setRoom(room);

        workstationList = new ArrayList<>();
        workstationList.add(workstation1);

        room.setWorkstations(workstationList);

        roomList = new ArrayList<>();
        roomList.add(room);
        floor.setRooms(roomList);

        workstationPk = new WorkstationPK();
        workstationPk.setFloor(numberFloor);
        workstationPk.setRoom(numberRoom);
        workstationPk.setWorkstation(numberWorkstation1);

        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = calendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        LocalDate today = LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDate friday = monday.plusDays(4);
        calendar.setTime(Date.from(friday.atStartOfDay().atZone(zoneId).toInstant()));

        employee = new Employee();
        email = "m.rossi1@wlb.it";
        employee.setName(email);
        date = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        datePrenotation = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        datePrenotation = formatter.parse(date);
        workstationPrenotationPk = new WorkstationPrenotationPK();
        workstationPrenotationPk.setEmailEmployee(email);
        workstationPrenotationPk.setPrenotationDate(datePrenotation);

        workstationPrenotation = new WorkstationPrenotation();
        workstationPrenotation.setEmployee(employee);
        workstationPrenotation.setId(workstationPrenotationPk);
        workstationPrenotation.setCalendarWeek(1);
        workstationPrenotation.setYear(2020);
        workstationPrenotation.setWorkstation(workstation1);

        workstationPrenotationList = new ArrayList<>();
        workstationPrenotationList.add(workstationPrenotation);

    }

    @Test
    void showWorkstationAvailable() throws ServletException, IOException {
        request.setParameter("floor", "1");
        request.setParameter("room", "1");
        request.setParameter("date", date);
        when(floorDao.countMax()).thenReturn(numberFloor);
        when(roomDao.countMaxByFloor(numberFloor)).thenReturn(numberRoom);
        when(workstationPrenotationDao.retrieveByWorkstationDate(datePrenotation, numberFloor, numberRoom))
                .thenReturn(workstationPrenotationList);
        when(workstationDao.retrieveByFloorAndRoom(numberFloor, numberRoom)).thenReturn(workstationList);

        servlet.setFloorDao(floorDao);
        servlet.setRoomDao(roomDao);
        servlet.setWorkstationDao(workstationDao);
        servlet.setWorkstationPrenotationDao(workstationPrenotationDao);
        servlet.doPost(request, response);
        String json = "[{\"workstation\":1,\"status\":1}]";
        assertEquals(json, response.getContentAsString());
    }

    @SuppressWarnings("unchecked")
    @Test
    void retrieveFloorOrRoomFails() throws ServletException, IOException {
        String errorMessage = "\nErrore nel recupero delle informazioni relative al piano massimo, alla stanza massima e alla postazione massima";
        request.setParameter("floor", "1");
        request.setParameter("room", "1");
        request.setParameter("date", date);
        when(floorDao.countMax()).thenThrow(Exception.class);
        when(roomDao.countMaxByFloor(numberFloor)).thenThrow(Exception.class);
        servlet.setFloorDao(floorDao);
        servlet.setRoomDao(roomDao);
        try {
            servlet.doPost(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertEquals(errorMessage, response.getContentAsString());
        }
    }

    @Test
    void formatNotCorrect() throws ServletException, IOException {
        String dateWrong = "01-01-2020";
        String errorMessage = "\nI parametri inseriti non rispettano il formato/lunghezza";
        request.setParameter("floor", "1");
        request.setParameter("room", "1");
        request.setParameter("date", dateWrong);
        when(floorDao.countMax()).thenReturn(numberFloor);
        when(roomDao.countMaxByFloor(numberFloor)).thenReturn(numberRoom);

        servlet.setFloorDao(floorDao);
        servlet.setRoomDao(roomDao);
        try {
            servlet.doPost(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertEquals(errorMessage, response.getContentAsString());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    void getPrenotationsFails() throws ServletException, IOException {
        String errorMessage = "\nErrore nel recupero delle prenotazioni";
        request.setParameter("floor", "1");
        request.setParameter("room", "1");
        request.setParameter("date", date);
        when(floorDao.countMax()).thenReturn(numberFloor);
        when(roomDao.countMaxByFloor(numberFloor)).thenReturn(numberRoom);
        when(workstationPrenotationDao.retrieveByWorkstationDate(datePrenotation, numberFloor, numberRoom))
                .thenThrow(Exception.class);

        servlet.setFloorDao(floorDao);
        servlet.setRoomDao(roomDao);
        servlet.setWorkstationPrenotationDao(workstationPrenotationDao);
        servlet.doPost(request, response);
        assertEquals(errorMessage, response.getContentAsString());
    }

    @SuppressWarnings("unchecked")
    @Test
    void getWorkstationFails() throws ServletException, IOException {
        String errorMessage = "\nErrore nel recupero delle postazioni";
        request.setParameter("floor", "1");
        request.setParameter("room", "1");
        request.setParameter("date", date);
        when(floorDao.countMax()).thenReturn(numberFloor);
        when(roomDao.countMaxByFloor(numberFloor)).thenReturn(numberRoom);
        when(workstationPrenotationDao.retrieveByWorkstationDate(datePrenotation, numberFloor, numberRoom))
                .thenReturn(workstationPrenotationList);
        when(workstationDao.retrieveByFloorAndRoom(numberFloor, numberRoom)).thenThrow(Exception.class);

        servlet.setFloorDao(floorDao);
        servlet.setRoomDao(roomDao);
        servlet.setWorkstationPrenotationDao(workstationPrenotationDao);
        servlet.doPost(request, response);
        assertEquals(errorMessage, response.getContentAsString());
    }

}
