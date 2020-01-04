package it.unisa.wlb.test;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
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
/**
 * This test class follows the specification of the section "3.2.3 TC_2.3 Modifica progetto" of the document "Test Case Specification"
 * 
 * @author nello
 *
 */
public class ModifyProjectTest extends Mockito {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private ModifyProjectServlet servlet;
	private IProjectDAO projectDao;
	private IEmployeeDAO employeeDao;
	private Employee manager;
	private Project oldProject;

	@BeforeEach
	public void setUp() {

		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ModifyProjectServlet();
		request.getSession().setAttribute("userRole", "Admin");
		request.getSession().setAttribute("user", new Admin());
		manager = new Employee();
		manager.setName("Luca");
		manager.setSurname("Rossi");
		manager.setEmail("l.rossi1@wlb.it");
		manager.setStatus(1);
		manager.setPassword("Ciao123.");
		oldProject = new Project();
		oldProject.setName("WLB13PO");
		oldProject.setScope("SmartWorking");
		oldProject.setStartDate(new Date(2019, 11, 02));
		oldProject.setEndDate(new Date(2019, 12, 02));
		oldProject.setDescription(
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		oldProject.setEmployee(manager);
		request.getSession().setAttribute("oldProject", oldProject);
	}

	/**
	 * Name field not inserted - TC_2.1_1
	 *
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_1() throws ServletException, IOException {

		IProjectDAO projectDao = mock(IProjectDAO.class);

		request.addParameter("name", "");
		request.addParameter("scope", "SmartWorkingasd");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentira ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);

		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);

		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);

		});
	}

	/**
	 * Name field inserted doesn't respect the specified lenght - TC_2.2_2
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_2() throws ServletException, IOException {
		
		IProjectDAO projectDao = mock(IProjectDAO.class);
		
		request.addParameter("name", "WLBWLBWLBWLBWLBWLV");
		request.addParameter("scope", "SmartWorkingasd");
		request.addParameter("startDate", "2019-10-31");
		request.addParameter("endDate", "2020-01-10");
		request.addParameter("description",
				"Il progetto si occupera della realizzazione di una piattaforma che consentira ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);

		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);

		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);

		});
	}

	/**
	 * Name field doesn't respect the specified format - TC_2.2_3
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_3() throws ServletException, IOException {
		request.addParameter("name", "WLBè");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("manager", "m.rossi1@wlb.it");
		request.addParameter("description",
				"Il progetto si occupera della realizzazione di una piattaforma che consentir??  ?  ? ai dipendenti di organizzare le proprie giornate lavorative.");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	/**
	 * Name field already exists - TC_2.2_4
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_4() throws ServletException, IOException {

		String commonName = "WLB13PO";
		Project project = new Project();
		Date dateS = new Date(2019, 11, 02);
		Date dateE = new Date(2019, 12, 02);
		Employee employee = new Employee();
		List<Employee> lista = new ArrayList<Employee>();
		lista.add(employee);

		project.setName("WLB13PO");
		project.setScope("SmartWorking");
		project.setStartDate(dateS);
		project.setEndDate(dateE);
		project.setDescription(
				"Il progetto si occupera della realizzazione di una piattaforma che consentir??  ?  ? ai dipendenti di organizzare le proprie giornate lavorative.");
		project.setEmployees(lista);
		project.setEmployee(employee);

		IProjectDAO projectDao = mock(IProjectDAO.class);
		IEmployeeDAO employeeDao = mock(IEmployeeDAO.class);
		when(projectDao.retrieveByName(commonName)).thenReturn(project);
		AddProjectServlet tmp = new AddProjectServlet(projectDao, employeeDao);

		request.addParameter("name", commonName);
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail", "m.rossi1@wlb.it");
		request.addParameter("description",
				"Il progetto si occupera della realizzazione di una piattaforma che consentir??  ?  ? ai dipendenti di organizzare le proprie giornate lavorative.");
		assertThrows(IllegalArgumentException.class, () -> {
			tmp.doPost(request, response);
		});

	}

	/**
	 * Scope field not inserted - TC_2.2_5
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_5() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail", "m.rossi1@wlb.it");
		request.addParameter("description",
				"Il progetto si occupera della realizzazione di una piattaforma che consentir??  ?  ? ai dipendenti di organizzare le proprie giornate lavorative.");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	/**
	 * Scope field doesn't respect the specified lenght - TC_2.2_6
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_6() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "VBFHBVHDVBDFHJBVDFVFVHFVH FHFBVHDJBFDK");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail", "m.rossi1@wlb.it");
		request.addParameter("description",
				"Il progetto si occupera della realizzazione di una piattaforma che consentir??  ?  ? ai dipendenti di organizzare le proprie giornate lavorative.");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	/**
	 * Scope field doesn't respect the specified format - TC_2.2_7
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_7() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWòrking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail", "m.rossi1@wlb.it");
		request.addParameter("description",
				"Il progetto si occupera della realizzazione di una piattaforma che consentir??  ?  ? ai dipendenti di organizzare le proprie giornate lavorative.");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	/**
	 * Start date field doesn't respect the specified format - TC_2.2_8
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_8() throws ServletException, IOException {
		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2-29-200");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail", "m.rossi1@wlb.it");
		request.addParameter("description",
				"Il progetto si occupera della realizzazione di una piattaforma che consentir??  ?  ? ai dipendenti di organizzare le proprie giornate lavorative.");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	/**
	 * End date field doesn't respect the specified format - TC_2.2_9
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_9() throws ServletException, IOException {

		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2-29-2000");
		request.addParameter("managerEmail", "m.rossi1@wlb.it");
		request.addParameter("description",
				"Il progetto si occupera della realizzazione di una piattaforma che consentir??  ?  ? ai dipendenti di organizzare le proprie giornate lavorative.");
		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	/**
	 * Manager E-mail doesn't exist into database - TC_2.2_10
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_10() throws ServletException, IOException {

		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail", "m.rossi2@wlb.it");
		request.addParameter("description",
				"Il progetto si occupera della realizzazione di una piattaforma che consentir??  ?  ? ai dipendenti di organizzare le proprie giornate lavorative.");

		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	/**
	 * Inserted email doesn't correspond to any manager - TC_2.2_11
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_11() throws ServletException, IOException {

		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail", "");
		request.addParameter("description",
				"Il progetto si occupera della realizzazione di una piattaforma che consentir??  ?  ? ai dipendenti di organizzare le proprie giornate lavorative.");

		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	/**
	 * Description field doesn't respect the specified lenght - TC_2.2_12
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_12() throws ServletException, IOException {

		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail", "m.rossi1@wlb.it");
		request.addParameter("description", "");

		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	/**
	 * Description field doesn't respect the specified lenght - TC_2.2_13
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_13() throws ServletException, IOException {

		request.addParameter("name", "WLB13PO");
		request.addParameter("scope", "SmartWorking");
		request.addParameter("startDate", "2019-11-02");
		request.addParameter("endDate", "2019-12-02");
		request.addParameter("managerEmail", "m.rossi1@wlb.it");
		request.addParameter("description",
				"Ebcbjdbcdhbcd cdshdcbdh hbcs cjd bjhsbchdjsbhjs cjs d dj d cd cd hc dhc dshjc jdhsc jdc dscdc cdhc djsc dhc jdc dc jdchjdhdjhdjschdcbjdsbchbsjb. Hchdbchdbhcbdhcbdfh fVc dfhvbfhvbfhvbdjhfbvhdfvdjfvdjvvf"
						+ "							 vdcndkjvnfkvnfkvhfdcbdhcdcd c. dcbhdcbhdbchdbcjdbcdc. C. c hcdbchbdjcdhcbdjcbhdcjbd d cd chdchdbchdbhcbdcdnchdn. Vnjfdnvjdnvjndjvndvnjdnvjdnvn. Fdfdgfgfhfhffhfjhjhgjfhfhfdgfvhfv. d");

		assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
	}

	/**
	 * Project inserted with succcess - TC_2.2_14
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */

	@Test
	public void TC_2_3_14() throws ServletException, IOException {

		IProjectDAO projectDao = mock(IProjectDAO.class);
		IEmployeeDAO employeeDao = mock(IEmployeeDAO.class);

		request.addParameter("name", "WLB13BA");
		request.addParameter("scope", "SmartWorkingasd");
		request.addParameter("startDate", "2019-10-02");
		request.addParameter("endDate", "2019-11-02");
		request.addParameter("description",
				"Il progetto si occuperà della realizzazione di una piattaforma che consentir ? ai dipendenti di organizzare le proprie giornate lavorative.");
		request.addParameter("managerEmail", "l.rossi1@wlb.it");

		Project newProject = (Project) request.getSession().getAttribute("oldProject");

		when(projectDao.update(oldProject)).thenReturn(newProject);
		when(employeeDao.retrieveByEmail(manager.getEmail())).thenReturn(manager);
		
		servlet.setProjectDao(projectDao);
		servlet.setEmployeeDao(employeeDao);
		
		servlet.doPost(request, response);

		assertEquals("success", request.getAttribute("result"));

	}
}
