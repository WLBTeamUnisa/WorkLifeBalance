package it.unisa.wlb.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import it.unisa.wlb.controller.WorkstationPrenotationServlet;
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

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * This test class follows the specification of the section "3.5.1 TC_5.1 Prenota postazione di lavoro" of the document
 * "Test Case Specification"
 * 
 * @author Sabato Nocera, Aniello Romano
 *
 */
public class WorkstationPrenotationServletTest extends Mockito {

    private final static String JSON_STRING = "jsonObject";

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private WorkstationPrenotationServlet servlet;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        servlet = new WorkstationPrenotationServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        employee = new Employee();

        request.getSession().setAttribute("userRole", "Employee");
        request.getSession().setAttribute("user", employee);
    }

    /**
     * TC_3.1_1: Floor field doesn't respect the specified format
     *
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_1() throws ServletException, IOException {
        String jsonString = "{\"date\":\"2019-11-25\",\"workstation\":15,\"floor\":\"fdjhas\",\"room\":2}";

        request.setParameter(JSON_STRING, jsonString);

        String errorMessage = "\nErrore nel recupero della prenotazione";

        try {
            servlet.doPost(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertTrue(response.getStatus() == HttpServletResponse.SC_BAD_REQUEST
                    && response.getContentAsString().toString().contains(errorMessage));
        }
    }

    /**
     * TC_3.1_2: Number of floors inserted is less than minimum
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_2() throws ServletException, IOException {
        String jsonString = "{\"date\":\"2019-11-25\",\"workstation\":15,\"floor\":0,\"room\":2}";

        request.setParameter(JSON_STRING, jsonString);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_3.1_3: Number of floors inserted exceeds the maximum
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_3() throws ServletException, IOException {
        String jsonString = "{\"date\":\"2019-11-25\",\"workstation\":15,\"floor\":8,\"room\":2}";

        request.setParameter(JSON_STRING, jsonString);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_3.1_4: Floor field doesn't exists into the database
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void TC_3_1_4() throws ServletException, IOException {
        String string = "{\"date\":\"2019-11-25\",\"workstation\":15,\"floor\":7,\"room\":2}";
        int existingFloor = 7;
        int existingRoom = 2;
        int existingWorkstation = 15;

        Floor floor = new Floor();
        floor.setNumFloor(existingFloor);

        Room room = new Room();
        room.setFloor(floor);
        RoomPK roomPk = new RoomPK();
        roomPk.setNumFloor(floor.getNumFloor());
        roomPk.setNumRoom(existingRoom);
        room.setId(roomPk);

        Workstation workstation = new Workstation();
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(room.getId().getNumFloor());
        workstationPK.setRoom(room.getId().getNumRoom());
        workstationPK.setWorkstation(existingWorkstation);
        workstation.setId(workstationPK);
        workstation.setRoom(room);

        IFloorDao floorDao = mock(IFloorDao.class);
        IRoomDao roomDao = mock(IRoomDao.class);
        IWorkstationDao workstationDao = mock(IWorkstationDao.class);

        when(floorDao.countMax()).thenReturn(existingFloor);
        when(roomDao.countMaxByFloor(existingFloor)).thenReturn(existingRoom);
        when(workstationDao.countMaxByFloorAndRoom(existingFloor, existingRoom)).thenReturn(existingWorkstation);
        when(workstationDao.retrieveById(existingFloor, existingRoom, existingWorkstation)).thenThrow(
                IllegalArgumentException.class, NoResultException.class, NonUniqueResultException.class,
                IllegalStateException.class, QueryTimeoutException.class, TransactionRequiredException.class,
                PessimisticLockException.class, LockTimeoutException.class, PersistenceException.class);

        servlet.setWorkstationDao(workstationDao);
        servlet.setFloorDao(floorDao);
        servlet.setRoomDao(roomDao);

        request.setParameter(JSON_STRING, string);

        String errorMessage = "\nErrore nel recupero della postazione di lavoro dal database";

        try {
            servlet.doPost(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertTrue(response.getStatus() == HttpServletResponse.SC_BAD_REQUEST
                    && response.getContentAsString().toString().contains(errorMessage));
        }
    }

    /**
     * TC_3.1_5: Room field doesn't respect the specified format
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_5() throws ServletException, IOException {
        String jsonString = "{\"date\":\"2019-11-25\",\"workstation\":15,\"room\":\"fdjhas\",\"floor\":6}";

        request.setParameter(JSON_STRING, jsonString);

        String errorMessage = "\nErrore nel recupero della prenotazione";

        try {
            servlet.doPost(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertTrue(response.getStatus() == HttpServletResponse.SC_BAD_REQUEST
                    && response.getContentAsString().toString().contains(errorMessage));
        }
    }

    /**
     * TC_3.1_6: Number of rooms inserted is less than minimum
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_6() throws ServletException, IOException {
        String jsonString = "{\"date\":\"2019-11-25\",\"workstation\":15,\"floor\":6,\"room\":0}";

        request.setParameter(JSON_STRING, jsonString);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_3.1_7: Number of rooms inserted exceeds the maximum
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_7() throws ServletException, IOException {
        String jsonString = "{\"date\":\"2019-11-25\",\"workstation\":15,\"floor\":6,\"room\":18}";

        request.setParameter(JSON_STRING, jsonString);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_3.1_8: Room inserted doesn't exists into the database
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void TC_3_1_8() throws ServletException, IOException {
        String string = "{\"date\":\"2019-11-25\",\"workstation\":15,\"floor\":7,\"room\":18}";
        int existingFloor = 7;
        int existingRoom = 18;
        int existingWorkstation = 15;

        Floor floor = new Floor();
        floor.setNumFloor(existingFloor);

        Room room = new Room();
        room.setFloor(floor);
        RoomPK roomPk = new RoomPK();
        roomPk.setNumFloor(floor.getNumFloor());
        roomPk.setNumRoom(existingRoom);
        room.setId(roomPk);

        Workstation workstation = new Workstation();
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(room.getId().getNumFloor());
        workstationPK.setRoom(room.getId().getNumRoom());
        workstationPK.setWorkstation(existingWorkstation);
        workstation.setId(workstationPK);
        workstation.setRoom(room);

        IFloorDao floorDao = mock(IFloorDao.class);
        IRoomDao roomDao = mock(IRoomDao.class);
        IWorkstationDao workstationDao = mock(IWorkstationDao.class);

        when(floorDao.countMax()).thenReturn(existingFloor);
        when(roomDao.countMaxByFloor(existingFloor)).thenReturn(existingRoom);
        when(workstationDao.countMaxByFloorAndRoom(existingFloor, existingRoom)).thenReturn(existingWorkstation);
        when(workstationDao.retrieveById(existingFloor, existingRoom, existingWorkstation)).thenThrow(
                IllegalArgumentException.class, NoResultException.class, NonUniqueResultException.class,
                IllegalStateException.class, QueryTimeoutException.class, TransactionRequiredException.class,
                PessimisticLockException.class, LockTimeoutException.class, PersistenceException.class);

        servlet.setWorkstationDao(workstationDao);
        servlet.setFloorDao(floorDao);
        servlet.setRoomDao(roomDao);

        request.setParameter(JSON_STRING, string);

        String errorMessage = "\nErrore nel recupero della postazione di lavoro dal database";

        try {
            servlet.doPost(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertTrue(response.getStatus() == HttpServletResponse.SC_BAD_REQUEST
                    && response.getContentAsString().toString().contains(errorMessage));
        }
    }

    /**
     * TC_3.1_9: Workstation is unavailable
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void TC_3_1_9() throws ServletException, IOException {
        String string = "{\"date\":\"2019-11-25\",\"workstation\":3,\"floor\":6,\"room\":8}";
        int existingFloor = 6;
        int existingRoom = 8;
        int existingWorkstation = 3;
        String datePrenotation = "2019-11-25";

        Floor floor = new Floor();
        floor.setNumFloor(existingFloor);

        Room room = new Room();
        room.setFloor(floor);
        RoomPK roomPk = new RoomPK();
        roomPk.setNumFloor(floor.getNumFloor());
        roomPk.setNumRoom(existingRoom);
        room.setId(roomPk);

        Workstation workstation = new Workstation();
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(room.getId().getNumFloor());
        workstationPK.setRoom(room.getId().getNumRoom());
        workstationPK.setWorkstation(existingWorkstation);
        workstation.setId(workstationPK);
        workstation.setRoom(room);

        LocalDate date = LocalDate.parse(datePrenotation, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = calendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        Date workstationPrenotationDate = Date.from(date.atStartOfDay().atZone(zoneId).toInstant());
        calendar.setTime(workstationPrenotationDate);

        int calendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);

        WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
        workstationPrenotationPK.setEmailEmployee(employee.getEmail());
        workstationPrenotationPK.setPrenotationDate(workstationPrenotationDate);

        WorkstationPrenotation workstationPrenotation = new WorkstationPrenotation();

        workstationPrenotation.setCalendarWeek(calendarWeek);
        workstationPrenotation.setEmployee(employee);
        workstationPrenotation.setId(workstationPrenotationPK);
        workstationPrenotation.setWorkstation(workstation);
        workstationPrenotation.setYear(year);

        IFloorDao floorDao = mock(IFloorDao.class);
        IRoomDao roomDao = mock(IRoomDao.class);
        IWorkstationDao workstationDao = mock(IWorkstationDao.class);
        IWorkstationPrenotationDao workstationPrenotationDao = mock(IWorkstationPrenotationDao.class);

        when(floorDao.countMax()).thenReturn(existingFloor);
        when(roomDao.countMaxByFloor(existingFloor)).thenReturn(existingRoom);
        when(workstationDao.countMaxByFloorAndRoom(existingFloor, existingRoom)).thenReturn(existingWorkstation);
        when(workstationDao.retrieveById(existingFloor, existingRoom, existingWorkstation)).thenReturn(workstation);
        when(workstationPrenotationDao.create(workstationPrenotation)).thenThrow(IllegalArgumentException.class,
                NoResultException.class, NonUniqueResultException.class, IllegalStateException.class,
                QueryTimeoutException.class, TransactionRequiredException.class, PessimisticLockException.class,
                LockTimeoutException.class, PersistenceException.class);

        servlet.setWorkstationDao(workstationDao);
        servlet.setFloorDao(floorDao);
        servlet.setRoomDao(roomDao);
        servlet.setWorkstationPrenotationDao(workstationPrenotationDao);

        request.setParameter(JSON_STRING, string);

        String errorMessage = "\nErrore nella prenotazione della postazione: " + workstationPrenotation.toString();

        try {
            servlet.doPost(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertTrue(response.getStatus() == HttpServletResponse.SC_BAD_REQUEST
                    && response.getContentAsString().toString().contains(errorMessage));
        }
    }

    /**
     * TC_3.1_10: Workstation field doesn't respect the specified format
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_10() throws ServletException, IOException {
        String jsonString = "{\"date\":\"2019-11-25\",\"workstation\":\"bfjfd\",\"floor\":6,\"room\":8}";

        request.setParameter(JSON_STRING, jsonString);

        String errorMessage = "\nErrore nel recupero della prenotazione";

        try {
            servlet.doPost(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertTrue(response.getStatus() == HttpServletResponse.SC_BAD_REQUEST
                    && response.getContentAsString().toString().contains(errorMessage));
        }
    }

    /**
     * TC_3.1_11: Number of workstation inserted is less than the minimum
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_11() throws ServletException, IOException {
        String jsonString = "{\"date\":\"2019-11-25\",\"workstation\":0,\"floor\":6,\"room\":8}";

        request.setParameter(JSON_STRING, jsonString);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_3.1_12: Number of workstation inserted exceeds the maximum
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_12() throws ServletException, IOException {
        String jsonString = "{\"date\":\"2019-11-25\",\"workstation\":26,\"floor\":6,\"room\":8}";

        request.setParameter(JSON_STRING, jsonString);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_3.1_13: Workstation doesn't exists into the database
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void TC_3_1_13() throws ServletException, IOException {
        String string = "{\"date\":\"2019-11-25\",\"workstation\":3,\"floor\":6,\"room\":8}";
        int existingFloor = 6;
        int existingRoom = 8;
        int existingWorkstation = 3;

        Floor floor = new Floor();
        floor.setNumFloor(existingFloor);

        Room room = new Room();
        room.setFloor(floor);
        RoomPK roomPk = new RoomPK();
        roomPk.setNumFloor(floor.getNumFloor());
        roomPk.setNumRoom(existingRoom);
        room.setId(roomPk);

        Workstation workstation = new Workstation();
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(room.getId().getNumFloor());
        workstationPK.setRoom(room.getId().getNumRoom());
        workstationPK.setWorkstation(existingWorkstation);
        workstation.setId(workstationPK);
        workstation.setRoom(room);

        IFloorDao floorDao = mock(IFloorDao.class);
        IRoomDao roomDao = mock(IRoomDao.class);
        IWorkstationDao workstationDao = mock(IWorkstationDao.class);

        when(floorDao.countMax()).thenReturn(existingFloor);
        when(roomDao.countMaxByFloor(existingFloor)).thenReturn(existingRoom);
        when(workstationDao.countMaxByFloorAndRoom(existingFloor, existingRoom)).thenReturn(existingWorkstation);
        when(workstationDao.retrieveById(existingFloor, existingRoom, existingWorkstation)).thenThrow(
                IllegalArgumentException.class, NoResultException.class, NonUniqueResultException.class,
                IllegalStateException.class, QueryTimeoutException.class, TransactionRequiredException.class,
                PessimisticLockException.class, LockTimeoutException.class, PersistenceException.class);

        servlet.setWorkstationDao(workstationDao);
        servlet.setFloorDao(floorDao);
        servlet.setRoomDao(roomDao);

        request.setParameter(JSON_STRING, string);

        String errorMessage = "\nErrore nel recupero della postazione di lavoro dal database";

        try {
            servlet.doPost(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertTrue(response.getStatus() == HttpServletResponse.SC_BAD_REQUEST
                    && response.getContentAsString().toString().contains(errorMessage));
        }
    }

    /**
     * TC_3.1_14: Date field doesn't respect the specified format
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_14() throws ServletException, IOException {
        String jsonString = "{\"date\":\"22-02-19\",\"workstation\":27,\"floor\":6,\"room\":8}";

        request.setParameter(JSON_STRING, jsonString);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
    }

    /**
     * TC_3.1_15: Workstation prenotation ended with success
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_15() throws ServletException, IOException {
        String string = "{\"date\":\"2019-11-25\",\"workstation\":27,\"floor\":6,\"room\":8}";
        int existingFloor = 6;
        int existingRoom = 8;
        int existingWorkstation = 27;
        String datePrenotation = "2019-11-25";

        Floor floor = new Floor();
        floor.setNumFloor(existingFloor);

        Room room = new Room();
        room.setFloor(floor);
        RoomPK roomPk = new RoomPK();
        roomPk.setNumFloor(floor.getNumFloor());
        roomPk.setNumRoom(existingRoom);
        room.setId(roomPk);

        Workstation workstation = new Workstation();
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(room.getId().getNumFloor());
        workstationPK.setRoom(room.getId().getNumRoom());
        workstationPK.setWorkstation(existingWorkstation);
        workstation.setId(workstationPK);
        workstation.setRoom(room);

        LocalDate date = LocalDate.parse(datePrenotation, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = calendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        Date workstationPrenotationDate = Date.from(date.atStartOfDay().atZone(zoneId).toInstant());
        calendar.setTime(workstationPrenotationDate);

        int calendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);

        WorkstationPrenotationPK workstationPrenotationPK = new WorkstationPrenotationPK();
        workstationPrenotationPK.setEmailEmployee(employee.getEmail());
        workstationPrenotationPK.setPrenotationDate(workstationPrenotationDate);

        WorkstationPrenotation workstationPrenotation = new WorkstationPrenotation();

        workstationPrenotation.setCalendarWeek(calendarWeek);
        workstationPrenotation.setEmployee(employee);
        workstationPrenotation.setId(workstationPrenotationPK);
        workstationPrenotation.setWorkstation(workstation);
        workstationPrenotation.setYear(year);

        IFloorDao floorDao = mock(IFloorDao.class);
        IRoomDao roomDao = mock(IRoomDao.class);
        IWorkstationDao workstationDao = mock(IWorkstationDao.class);
        IWorkstationPrenotationDao workstationPrenotationDao = mock(IWorkstationPrenotationDao.class);

        when(floorDao.countMax()).thenReturn(existingFloor);
        when(roomDao.countMaxByFloor(existingFloor)).thenReturn(existingRoom);
        when(workstationDao.countMaxByFloorAndRoom(existingFloor, existingRoom)).thenReturn(existingWorkstation);
        when(workstationDao.retrieveById(existingFloor, existingRoom, existingWorkstation)).thenReturn(workstation);
        when(workstationPrenotationDao.create(workstationPrenotation)).thenReturn(workstationPrenotation);

        servlet.setWorkstationDao(workstationDao);
        servlet.setFloorDao(floorDao);
        servlet.setRoomDao(roomDao);
        servlet.setWorkstationPrenotationDao(workstationPrenotationDao);

        request.setParameter(JSON_STRING, string);

        servlet.doPost(request, response);
    }
}
