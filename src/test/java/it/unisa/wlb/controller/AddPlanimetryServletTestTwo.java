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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;

/**
 * This test class follows the specification of the section "3.3.1 TC_3.1 Inserisci planimetria" of the document "Test
 * Case Specification"
 * 
 * @author Sabato Nocera
 *
 */
public class AddPlanimetryServletTestTwo extends Mockito {

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

    @Test
    public void jsonStringNull() throws ServletException, IOException {
        String string = null;

        request.setParameter(JSON_STRING, string);

        assertThrows(IllegalArgumentException.class, () -> {
            servlet.doGet(request, response);
        });
    }

    @Test
    public void equalsFloorsNumber() throws ServletException, IOException {
        String string = "[{\"workstation\":20,\"floor\":1,\"room\":5},{\"workstation\":20,\"floor\":1,\"room\":5}]";
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