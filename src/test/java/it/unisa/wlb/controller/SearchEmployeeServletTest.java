package it.unisa.wlb.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import it.unisa.wlb.controller.SearchEmployeeServlet;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;
import it.unisa.wlb.utils.Utils;

/**
 * This test class follows the specification of the section "TC_1.2 Ricerca dipendente" Test Case Specification"
 * 
 * @author Vincenzo Fabiano
 * 
 */
class SearchEmployeeServletTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private SearchEmployeeServlet servlet;
    private IEmployeeDao employeeDao;
    private String email;
    private Employee employee;
    private String password;
    private String name;
    private String surname;
    private List<Employee> employeeSuggestList;
    private List<Employee> employeeAll;
    private JSONArray employeeEmailList;
    private JSONObject employeeObject;
    private String json;
    private Map<String, String> object;

    @BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        employeeDao = mock(IEmployeeDao.class);

        email = "m.rossi1@wlb.it";
        password = "Ciao1234";
        name = "Marco";
        surname = "Rossi";
        employee = new Employee();
        employee.setEmail(email);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPassword(Utils.generatePwd(password));
        employee.setStatus(0);

        employeeAll = new ArrayList<Employee>();
        employeeAll.add(employee);
        employeeSuggestList = new ArrayList<Employee>();
        employeeSuggestList.add(employee);

        employeeEmailList = new JSONArray();
        object = new LinkedHashMap<String, String>();
        object.put("email", email);
        object.put("name", name);
        object.put("surname", surname);

        employeeObject = new JSONObject(object);
        employeeEmailList.put(employeeObject);
        json = "[{\"surname\":\"Rossi\",\"name\":\"Marco\",\"email\":\"m.rossi1@wlb.it\"}]";
    }

    /**
     * TC_1.2_1: email doesn't respect the length - FAIL
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    void TC_1_2_1() throws ServletException, IOException {
        String errorMessage = "Il parametro email non rispetta la lunghezza";
        request.setParameter("email", "marcomarcomarcomarcomarcomarcomarcomarcomarco");
        servlet = new SearchEmployeeServlet();
        try {
            servlet.doPost(request, response);
        } catch (Exception e) {
            ;
        } finally {
            assertEquals(errorMessage, response.getContentAsString());
        }
    }

    /**
     * TC_1.2_2: Retrieve employees based on the suggestions - SUCCESS
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    void TC_1_2_2() throws ServletException, IOException {
        when(employeeDao.suggestByEmail(email)).thenReturn(employeeSuggestList);
        request.addParameter("email", email);
        servlet = new SearchEmployeeServlet(employeeDao);
        servlet.doPost(request, response);
        assertEquals(json, response.getContentAsString().toString());
    }

}
