//package it.unisa.wlb.test.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//
//import it.unisa.wlb.controller.EmployeeInsertPageServlet;
//import it.unisa.wlb.controller.WorkstationsAvailabilityServlet;
//import it.unisa.wlb.model.bean.Admin;
//import it.unisa.wlb.model.dao.IAdminDAO;
//import it.unisa.wlb.model.dao.IFloorDao;
//import it.unisa.wlb.model.dao.IRoomDao;
//import it.unisa.wlb.model.dao.IWorkstationDao;
//import it.unisa.wlb.model.dao.IWorkstationPrenotationDao;
//
//public class WorkstationAvailabilityServletTest {
//
//
//private MockHttpServletRequest request;
//	
//private MockHttpServletResponse response;
//	
//
//private WorkstationsAvailabilityServlet servlet;
//
//@Mock
//private IFloorDao floorDao;
//@Mock
//private IRoomDao roomDao;
//@Mock
//private IWorkstationDao workstationDao;
//@Mock
//private IWorkstationPrenotationDao workstationPrenotationDao;
//@Mock
//private HttpSession session;
//@Mock
//private RequestDispatcher dispatcher;
//
//private int floor;
//private int room;
//String datePrenotation;
//
//@BeforeEach
//void setUp() throws Exception {
//	MockitoAnnotations.initMocks(this);
//	
//	servlet = new WorkstationsAvailabilityServlet();
//	floor = 1;
//	room = 1;
//	datePrenotation="2020-10-10";
//	
//	request = new MockHttpServletRequest();
//	response= new MockHttpServletResponse();
//
//}
//
//
//@Test
//void userRoleAdmin() throws ServletException, IOException {
//	when(floorDao.countMax()).thenReturn(1);
//	when(roomDao.countMaxByFloor(floor)).thenReturn(1);
//	servlet.doPost(request, response);
//	assertEquals("<no/>",response.getContentAsString());
//
//}
//}
