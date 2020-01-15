package it.unisa.wlb.controller;

import java.io.IOException;

import it.unisa.wlb.controller.AddPlanimetryServlet;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPK;
import it.unisa.wlb.model.dao.IWorkstationDao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
 * This test class follows the specification of the section "3.3.1 TC_3.1 Inserisci planimetria" of the document "Test
 * Case Specification"
 * 
 * @author Sabato Nocera, Aniello Romano
 *
 */
public class AddPlanimetryServletTest extends Mockito {

    private final static String JSON_STRING = "jsonObject";

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AddPlanimetryServlet servlet;

    private Admin admin;

    @BeforeEach
    public void setUp() {
        servlet = new AddPlanimetryServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        admin = new Admin();

        request.getSession().setAttribute("userRole", "Admin");
        request.getSession().setAttribute("user", admin);
    }

    /**
     * TC_3.1_1: Floor field doesn't respect the specified format
     * 
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_1() throws ServletException, IOException {

        String string = "[{\"workstation\":50,\"floor\":\"jsbdkj\",\"room\":1}]";

        request.setParameter(JSON_STRING, string);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doGet(request, response);
        });

    }

    /**
     * TC_3.1_2: Number of floors inserted is less than minimum
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_2() throws ServletException, IOException {

        String string = "[{\"workstation\":50,\"floor\":0,\"room\":1}]";

        request.setParameter(JSON_STRING, string);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doGet(request, response);
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

        String string = "[{\"workstation\":50,\"floor\":201,\"room\":1}]";

        request.setParameter(JSON_STRING, string);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doGet(request, response);
        });
    }

    /**
     * TC_3.1_4: Room field doesn't respect the specified format
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_4() throws ServletException, IOException {

        String string = "[{\"workstation\":50,\"floor\":4,\"room\":shjdb}]";

        request.setParameter(JSON_STRING, string);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doGet(request, response);
        });
    }

    /**
     * TC_3.1_5: Number of rooms inserted is less than minimum
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_5() throws ServletException, IOException {

        String string = "[{\"workstation\":50,\"floor\":4,\"room\":0}]";

        request.setParameter(JSON_STRING, string);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doGet(request, response);
        });

    }

    /**
     * TC_3.1_6: Number of rooms inserted exceeds the maximum
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_6() throws ServletException, IOException {

        String string = "[{\"workstation\":50,\"floor\":4,\"room\":25}]";

        request.setParameter(JSON_STRING, string);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doGet(request, response);
        });
    }

    /**
     * TC_3.1_7: Floor inserted doesn't exists
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void TC_3_1_7() throws ServletException, IOException {
        String string = "[{\"workstation\":20,\"floor\":5,\"room\":15}]";
        int existingFloor = 5;
        int existingRoom = 15;
        int existingWorkstation = 20;

        Floor floor = new Floor();
        floor.setAdmin(admin);
        floor.setNumFloor(existingFloor);

        Room room = new Room();
        room.setFloor(floor);
        RoomPK roomPk = new RoomPK();
        roomPk.setNumFloor(floor.getNumFloor());
        roomPk.setNumRoom(existingRoom);
        room.setId(roomPk);

        IWorkstationDao workstationDao = mock(IWorkstationDao.class);
        Workstation workstation = new Workstation();
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(room.getId().getNumFloor());
        workstationPK.setRoom(room.getId().getNumRoom());
        workstationPK.setWorkstation(existingWorkstation);
        workstation.setId(workstationPK);
        workstation.setRoom(room);
        when(workstationDao.create(workstation)).thenThrow(IllegalArgumentException.class, NoResultException.class,
                NonUniqueResultException.class, IllegalStateException.class, QueryTimeoutException.class,
                TransactionRequiredException.class, PessimisticLockException.class, LockTimeoutException.class,
                PersistenceException.class);
        servlet.setWorkstationDao(workstationDao);

        request.setParameter(JSON_STRING, string);

        String errorMessage = "Errore nell'inserimento della postazione " + workstationPK.getWorkstation()
                + " per la stanza " + workstationPK.getRoom() + " del piano " + workstationPK.getFloor();

        try {
            servlet.doGet(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertTrue(response.getStatus() == HttpServletResponse.SC_BAD_REQUEST
                    && response.getContentAsString().toString().contains(errorMessage));
        }
    }

    /**
     * TC_3.1_8: Workstation field doesn't respect the specified format
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_8() throws ServletException, IOException {

        String string = "[{\"workstation\":wdbkdhw,\"floor\":4,\"room\":2}]";

        request.setParameter(JSON_STRING, string);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doGet(request, response);
        });
    }

    /**
     * TC_3.1_9: Number of workstation inserted is less than minimum
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_9() throws ServletException, IOException {

        String string = "[{\"workstation\":0,\"floor\":4,\"room\":2}]";

        request.setParameter(JSON_STRING, string);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doGet(request, response);
        });
    }

    /**
     * TC_3.1_10: Number of workstation inserted exceeds the maximum
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_10() throws ServletException, IOException {

        String string = "[{\"workstation\":104,\"floor\":4,\"room\":2}]";

        request.setParameter(JSON_STRING, string);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doGet(request, response);
        });
    }

    /**
     * TC_3.1_11: Room inserted doesn't exists
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void TC_3_1_11() throws ServletException, IOException {
        String string = "[{\"workstation\":20,\"floor\":4,\"room\":8}]";
        int existingFloor = 4;
        int existingRoom = 8;
        int existingWorkstation = 20;

        Floor floor = new Floor();
        floor.setAdmin(admin);
        floor.setNumFloor(existingFloor);

        Room room = new Room();
        room.setFloor(floor);
        RoomPK roomPk = new RoomPK();
        roomPk.setNumFloor(floor.getNumFloor());
        roomPk.setNumRoom(existingRoom);
        room.setId(roomPk);

        IWorkstationDao workstationDao = mock(IWorkstationDao.class);
        Workstation workstation = new Workstation();
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(room.getId().getNumFloor());
        workstationPK.setRoom(room.getId().getNumRoom());
        workstationPK.setWorkstation(existingWorkstation);
        workstation.setId(workstationPK);
        workstation.setRoom(room);
        when(workstationDao.create(workstation)).thenThrow(IllegalArgumentException.class, NoResultException.class,
                NonUniqueResultException.class, IllegalStateException.class, QueryTimeoutException.class,
                TransactionRequiredException.class, PessimisticLockException.class, LockTimeoutException.class,
                PersistenceException.class);
        servlet.setWorkstationDao(workstationDao);

        request.setParameter(JSON_STRING, string);

        String errorMessage = "Errore nell'inserimento della postazione " + workstationPK.getWorkstation()
                + " per la stanza " + workstationPK.getRoom() + " del piano " + workstationPK.getFloor();

        try {
            servlet.doGet(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertTrue(response.getStatus() == HttpServletResponse.SC_BAD_REQUEST
                    && response.getContentAsString().toString().contains(errorMessage));
        }
    }

    /**
     * TC_3.1_12: Planimetry insertion ended with success
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void TC_3_1_12() throws ServletException, IOException {
        String string = "[{\"workstation\":20,\"floor\":4,\"room\":5}]";
        int existingFloor = 4;
        int existingRoom = 5;
        int existingWorkstation = 20;

        Floor floor = new Floor();
        floor.setAdmin(admin);
        floor.setNumFloor(existingFloor);

        Room room = new Room();
        room.setFloor(floor);
        RoomPK roomPk = new RoomPK();
        roomPk.setNumFloor(floor.getNumFloor());
        roomPk.setNumRoom(existingRoom);
        room.setId(roomPk);

        IWorkstationDao workstationDao = mock(IWorkstationDao.class);
        Workstation workstation = new Workstation();
        WorkstationPK workstationPK = new WorkstationPK();
        workstationPK.setFloor(room.getId().getNumFloor());
        workstationPK.setRoom(room.getId().getNumRoom());
        workstationPK.setWorkstation(existingWorkstation);
        workstation.setId(workstationPK);
        workstation.setRoom(room);
        when(workstationDao.create(workstation)).thenReturn(workstation);
        servlet.setWorkstationDao(workstationDao);

        request.setParameter(JSON_STRING, string);

        servlet.doGet(request, response);
        assertEquals("success", request.getAttribute("result"));
    }
}