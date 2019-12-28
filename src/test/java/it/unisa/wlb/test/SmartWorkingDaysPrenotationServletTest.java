package it.unisa.wlb.test;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.SmartWorkingDaysPrenotationServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.PrenotationDate;
import it.unisa.wlb.model.bean.PrenotationDatePK;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.SmartWorkingPrenotationPK;
import it.unisa.wlb.model.dao.IPrenotationDateDAO;
import it.unisa.wlb.model.dao.ISmartWorkingPrenotationDAO;

/**
 * This test class follows the specification of the section "3.5.2 TC_5.2 Prenota giorni di Smart Working" of the document "Test Case Specification"
 * 
 * @author Vincenzo Fabiano
 *
 */
public class SmartWorkingDaysPrenotationServletTest extends Mockito{

	private SmartWorkingDaysPrenotationServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private Employee e;
	
	
	@BeforeEach
	void setUp() throws Exception {
		servlet = new SmartWorkingDaysPrenotationServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		e = new Employee();
		e.setEmail("m.rossi1@wlb.it");
		e.setName("Marco");
		e.setSurname("Rossi");
		e.setStatus(0);
		e.setPassword("MarcoRossi1.");
		request.getSession().setAttribute("user", e);
	}

	/**
	 * The number of dates inserted is greater than three
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void TC_5_2_1() throws ServletException, IOException {
		final String message = "Non puoi prenotare più di 3 date";
		String[] dates = {"2019-11-25", "2019-11-26", "2019-11-27", "2019-11-28"};
		request.setParameter("dates", dates);
		try {
			servlet.doGet(request, response);
		} catch (Exception e) {			
			
		} finally {
			assertTrue(response.getStatus()==HttpServletResponse.SC_BAD_REQUEST && response.getContentAsString().toString().contains(message));
		}	
	}
	
	/**
	 * Dates don't respect the format
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void TC_5_2_2() throws ServletException, IOException{
		final String message = "formatError";
		String[] dates = {"22-11-2019"};
		request.setParameter("dates", dates);
		try {
			servlet.doGet(request, response);
		} catch (Exception e) {
			
		} finally {
			String attr = (String) request.getAttribute("result");
			assertEquals(message, attr);
		}
	}
	
	/**
	 * Smart Working Prenotation insertion ended with success
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void TC_5_2_3() throws ServletException, IOException {
		String[] dates = {"2019-11-15"};
		int swId = 1;
		int calendarWeek = 46;
		int year = 2019;
		request.setParameter("dates", dates);
		
		ISmartWorkingPrenotationDAO smartWorkingDao = mock(ISmartWorkingPrenotationDAO.class);
		IPrenotationDateDAO prenotationDateDao = mock(IPrenotationDateDAO.class);
		
		SmartWorkingPrenotation sw = new SmartWorkingPrenotation();
		SmartWorkingPrenotationPK swPk = new SmartWorkingPrenotationPK();
		swPk.setId(swId);
		swPk.setEmployeeEmail(e.getEmail());
		sw.setCalendarWeek(calendarWeek);
		sw.setYear(year);
		sw.setEmployee(e);
		sw.setId(swPk);
		
		when(smartWorkingDao.create(any(SmartWorkingPrenotation.class))).thenReturn(sw);
		when(smartWorkingDao.retrieveByWeeklyPlanning(calendarWeek, year, sw.getEmployee().getEmail()).getId().getId()).thenReturn(swId);
		when(smartWorkingDao.update(sw)).thenReturn(sw);
		
		servlet.setSmartWorkingPrenotationDao(smartWorkingDao);
	
		PrenotationDate pd = new PrenotationDate();
		PrenotationDatePK pdPk = new PrenotationDatePK();
		pd.setSmartWorkingPrenotation(sw);
		LocalDate localDate = LocalDate.parse("2019-11-15");
		pdPk.setDate(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		
		pdPk.setEmployeeEmail(e.getEmail());
		pdPk.setIdPrenotationSw(sw.getId().getId());
		when(prenotationDateDao.create(any(PrenotationDate.class))).thenReturn(pd);
		servlet.setPrenotationDateDao(prenotationDateDao);
			
		servlet.doGet(request, response);
	
	
	}

}
