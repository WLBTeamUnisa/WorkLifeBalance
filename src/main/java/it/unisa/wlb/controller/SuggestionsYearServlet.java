package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDao;

/**
 * 
 * This servlet aims to suggest years for the visualization of Calendar History
 * 
 * @author Luigi Cerrone
 */
@WebServlet(name="SuggestionsYearServlet", urlPatterns="/SuggestionsYear")
public class SuggestionsYearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IEmployeeDao employeeDao;
	
    public SuggestionsYearServlet() {
        super();
    }

    public void setEmployeeDAO(IEmployeeDao employeeDao){
    	this.employeeDao=employeeDao;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee=(Employee) request.getSession().getAttribute("user");
        if(employee!=null)
        {
        	JSONArray yearsListJson = new JSONArray();
           /**
            * Insertion of years in a JsonArray 
            * 
            */
        	Date date=new Date();
        	Calendar calendar=Calendar.getInstance();
        	calendar.setTime(date);
        	int currentYear=calendar.get(Calendar.YEAR);
        	while(currentYear>=2019)
        	{
        		JSONObject object=new JSONObject();
        		object.put("year", currentYear);
        		yearsListJson.put(object);
        		currentYear--;
        	}
           
           response.setContentType("application/json");
           response.getWriter().append(yearsListJson.toString());
           
        }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
