package it.unisa.wlb.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import it.unisa.wlb.controller.AddProjectServlet;
import it.unisa.wlb.controller.ModifyProjectServlet;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IProjectDAO;

public class ModifyProjectTest extends Mockito {
	
		private MockHttpServletRequest request;
		private MockHttpServletResponse response;
		private ModifyProjectServlet servlet;
		private IProjectDAO projectDao;
		private IEmployeeDAO employeeDao;
		
		
		@BeforeEach
		public void setUp() {
			
			request = new MockHttpServletRequest();
			response = new MockHttpServletResponse();
			servlet = new ModifyProjectServlet();
			request.getSession().setAttribute("userRole", "Admin");
			request.getSession().setAttribute("user", new Admin());
					
		}
		
		/**
		 *	name field not inserted - TC_2.1_1
		 *
		 * 	@throws ServletException
		 * 	@throws IOException
		 */
		
		@Test
		public void TC_2_3_1() throws ServletException,IOException{
			
			request.addParameter("name", "");
			request.addParameter("scope", "SmartWorking");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("manager","m.rossi1@wlb.it");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
		});
}
		/**
		 *  name field inserted doesn't respect the specified lenght  - TC_2.2_2
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_2() throws ServletException, IOException {
			request.addParameter("name", "WLBWLBWLBWLBWLBWLV");
			request.addParameter("scope", "SmartWorking");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("manager","m.rossi1@wlb.it");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
			});
		}

		/**
		 *  name field doesn't respect the specified format  -  TC_2.2_3
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_3() throws ServletException, IOException {
			request.addParameter("name", "WLBè");
			request.addParameter("scope", "SmartWorking");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("manager","m.rossi1@wlb.it");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
			});
		}

		/**
		 *  name field already exists  -  TC_2.2_4
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_4() throws ServletException, IOException {

			String commonName = "WLB13PO";
			Project pr = new Project();
			Date dateS = new Date(2019,11,02);
			Date dateE = new Date(2019,12,02);
			Employee em = new Employee();
			List<Employee> li = new ArrayList<Employee>();
			li.add(em);

			pr.setName("WLB13PO");
			pr.setScope("SmartWorking");
			pr.setStartDate(dateS);
			pr.setEndDate(dateE);
			pr.setDescription("Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			pr.setEmployees(li);
			pr.setEmployee(em);

			IProjectDAO projectDao = mock(IProjectDAO.class);
			IEmployeeDAO employeeDao = mock(IEmployeeDAO.class);
			when(projectDao.retrieveByName(commonName)).thenReturn(pr);		
			AddProjectServlet tmp = new AddProjectServlet(projectDao, employeeDao);


			request.addParameter("name", commonName);
			request.addParameter("scope", "SmartWorking");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("managerEmail","m.rossi1@wlb.it");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			assertThrows(IllegalArgumentException.class, () -> {
				tmp.doPost(request, response);
			});

		}

		/**
		 *  scope field not inserted  -  TC_2.2_5
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_5() throws ServletException, IOException {
			request.addParameter("name", "WLB13PO");
			request.addParameter("scope", "");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("managerEmail","m.rossi1@wlb.it");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
			});
		}

		/**
		 *  scope field doesn't respect the specified lenght -  TC_2.2_6
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_6() throws ServletException, IOException {
			request.addParameter("name", "WLB13PO");
			request.addParameter("scope", "VBFHBVHDVBDFHJBVDFVFVHFVH FHFBVHDJBFDK");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("managerEmail","m.rossi1@wlb.it");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
			});
		}

		/**
		 *  scope field doesn't respect the specified format  -  TC_2.2_7
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_7() throws ServletException, IOException {
			request.addParameter("name", "WLB13PO");
			request.addParameter("scope", "SmartWòrking");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("managerEmail","m.rossi1@wlb.it");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
			});
		}

		/**
		 *  start date field doesn't respect the specified format  -  TC_2.2_8
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_8() throws ServletException, IOException {
			request.addParameter("name", "WLB13PO");
			request.addParameter("scope", "SmartWorking");
			request.addParameter("startDate", "2-29-200");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("managerEmail","m.rossi1@wlb.it");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
			});
		}

		/**
		 *  end date field doesn't respect the specified format   -  TC_2.2_9
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_9() throws ServletException, IOException {
			request.addParameter("name", "WLB13PO");
			request.addParameter("scope", "SmartWorking");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2-29-2000");
			request.addParameter("managerEmail","m.rossi1@wlb.it");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
			});
		}

		/**
		 *  Manager E-mail doesn't exist into database -  TC_2.2_10
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_10() throws ServletException, IOException {
			request.addParameter("name", "WLB13PO");
			request.addParameter("scope", "SmartWorking");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("managerEmail","m.rossi2@wlb.it");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
			});
		}

		/**
		 *  inserted email doesn't correspond to any manager -  TC_2.2_11
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_11() throws ServletException, IOException {
			request.addParameter("name", "WLB13PO");
			request.addParameter("scope", "SmartWorking");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("managerEmail","");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
			});
		}

		/**
		 *  description field doesn't respect the specified lenght -  TC_2.2_12
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_12() throws ServletException, IOException {
			request.addParameter("name", "WLB13PO");
			request.addParameter("scope", "SmartWorking");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("managerEmail","m.rossi1@wlb.it");
			request.addParameter("description", "");
			
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
			});
		}

		/**
		 *  description field doesn't respect the specified lenght  -  TC_2.2_13
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_13() throws ServletException, IOException {
			request.addParameter("name", "WLB13PO");
			request.addParameter("scope", "SmartWorking");
			request.addParameter("startDate", "2019-11-02");
			request.addParameter("endDate", "2019-12-02");
			request.addParameter("managerEmail","m.rossi1@wlb.it");
			request.addParameter("description", "Ebcbjdbcdhbcd cdshdcbdh hbcs cjd bjhsbchdjsbhjs cjs d dj d cd cd hc dhc dshjc jdhsc jdc dscdc cdhc djsc dhc jdc dc jdchjdhdjhdjschdcbjdsbchbsjb. Hchdbchdbhcbdhcbdfh fVc dfhvbfhvbfhvbdjhfbvhdfvdjfvdjvvf"
					+ "							 vdcndkjvnfkvnfkvhfdcbdhcdcd c. dcbhdcbhdbchdbcjdbcdc. C. c hcdbchbdjcdhcbdjcbhdcjbd d cd chdchdbchdbhcbdcdnchdn. Vnjfdnvjdnvjndjvndvnjdnvjdnvn. Fdfdgfgfhfhffhfjhjhgjfhfhfdgfvhfv. d");
			
			assertThrows(IllegalArgumentException.class, () -> {
				servlet.doPost(request, response);
			});
		}

		/**
		 *  Project inserted with succcess  -  TC_2.2_14
		 *  
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@Test
		public void TC_2_2_14() throws ServletException, IOException {

			
			Project project = new Project();
			Date dateS = new Date(2019,11,02);
			Date dateE = new Date(2019,12,02);
			Employee employee = new Employee();
			List<Employee> lista = new ArrayList<Employee>();
			lista.add(employee);

			project.setName("WLB13PO");
			project.setScope("SmartWorking");
			project.setStartDate(dateS);
			project.setEndDate(dateE);
			project.setDescription("Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");
			project.setEmployees(lista);
			project.setEmployee(employee);


			request.addParameter("name","WLB13AB");
			request.addParameter("scope", "SmartWork");
			request.addParameter("startDate", "2019-08-02");
			request.addParameter("endDate", "2019-10-02");
			request.addParameter("managerEmail","m.rossi1@wlb.it");
			request.addParameter("description", "Il progetto si occupera della realizzazione di una piattaforma che consentir?��? ai dipendenti di organizzare le proprie giornate lavorative.");

			assertEquals("success", request.getAttribute("result"));
			
}
}
