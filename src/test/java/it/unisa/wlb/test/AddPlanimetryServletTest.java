package it.unisa.wlb.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;

import org.json.*;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import it.unisa.wlb.controller.AddPlanimetryServlet;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.dao.IFloorDao;
import it.unisa.wlb.model.dao.IRoomDao;
import it.unisa.wlb.model.dao.IWorkstationDao;

public class AddPlanimetryServletTest extends Mockito {
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private AddPlanimetryServlet servlet;
	
	private IFloorDao fDao;
	private IRoomDao rDao;
	private IWorkstationDao wDao;
	
	
	@BeforeEach
	public void setUp() {
		servlet = new AddPlanimetryServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		
		request.getSession().setAttribute("userRole", "Admin");
		request.getSession().setAttribute("user", new Admin());

}
	
	/*
	 *  Floor field doesn't respect the specified format
	 */
	@Test
	public void TC_3_1_1() throws UnsupportedEncodingException,ServletException, IOException {
	
		String str="[{\"workstations\":50,\"floor\":\"jsbdkj\",\"room\":1}]";
		
		request.setAttribute("jsonObject", str);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
			System.out.println(response.getContentAsString().toString());
		});
		
	}

	/*
	 * Number of floors inserted is less than 1
	 */
	@Test
	public void TC_3_1_2() throws ServletException, IOException {

		String str="[{\"workstations\":50,\"floor\":0,\"room\":1}]";
		
		request.setAttribute("jsonObject", str);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
			System.out.println(response.getContentAsString().toString());
		});
}
	/*
	 * Number of floors inserted is more than 200
	 */
	@Test
	public void TC_3_1_3() throws ServletException, IOException {
	
		String str="[{\"workstations\":50,\"floor\":200,\"room\":1}]";
		
		request.setAttribute("jsonObject", str);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
			System.out.println(response.getContentAsString().toString());
		});
}
	/*
	 * Room field doesn't respect the specified format
	 */
	@Test
	public void TC_3_1_4() throws ServletException, IOException {
		
		String str="[{\"workstations\":50,\"floor\":4,\"room\":shdha}]";
		
		request.setAttribute("jsonObject", str);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
			System.out.println(response.getContentAsString().toString());
		});
}
	/*
	 * Number of rooms inserted is less than 1
	 */
	@Test
	public void TC_3_1_5() throws ServletException, IOException {
		
		String str="[{\"workstations\":50,\"floor\":4,\"room\":0}]";
		
		request.setAttribute("jsonObject", str);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
			System.out.println(response.getContentAsString().toString());
		});
	
}
	/*
	 * Number of rooms inserted is more than 20
	 */
	@Test
	public void TC_3_1_6() throws ServletException, IOException {
		
		String str="[{\"workstations\":50,\"floor\":4,\"room\":25}]";
		
		request.setAttribute("jsonObject", str);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
			System.out.println(response.getContentAsString().toString());
		});
}
	/*
	 * Floor inserted doesn't exists
	 */
	@Test
	public void TC_3_1_7() throws ServletException, IOException {
		
		Floor fl = new Floor();
		fl.setNumFloor(4);
		IFloorDao floordao = mock(IFloorDao.class);
		when(floordao.countMax()==5).thenThrow(IllegalArgumentException.class);
	
			
}
	
	/*
	 * Workstation field doesn't respect the specified format
	 */
	@Test
	public void TC_3_1_8() throws ServletException, IOException {
		
		String str="[{\"workstations\":wdbkdhw,\"floor\":4,\"room\":5}]";
		
		request.setAttribute("jsonObject", str);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
			System.out.println(response.getContentAsString().toString());
		});
}
	
	/*
	 * Number of workstation inserted is less than 0
	 */
	@Test
	public void TC_3_1_9() throws ServletException, IOException {
		
		String str="[{\"workstations\":0,\"floor\":4,\"room\":5}]";
		
		request.setAttribute("jsonObject", str);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
			System.out.println(response.getContentAsString().toString());
		});
}
	
	/*
	 * Number of workstation inserted is more than 100
	 */
	@Test
	public void TC_3_1_10() throws ServletException, IOException {
		
		String str="[{\"workstations\":102,\"floor\":4,\"room\":5}]";
		
		request.setAttribute("jsonObject", str);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doGet(request, response);
			System.out.println(response.getContentAsString().toString());
		});
}
	
	/*
	 * Room inserted doesn't exists
	 */
	@Test
	public void TC_3_1_11() throws ServletException, IOException {
		
	
}
	
	/*
	 * Planimetry insertion ended with success
	 */
	@Test
	public void TC_3_1_12() throws ServletException, IOException {
		
	
}
}