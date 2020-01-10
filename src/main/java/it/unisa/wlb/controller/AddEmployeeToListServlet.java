package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IProjectDAO;
import it.unisa.wlb.utils.LoggerSingleton;

/**
 * The aim of this Servlet is to insert an employee into a dynamic list thanks 
 * to JSON Object
 * 
 * @author Luigi Cerrone, Emmanuel Tesauro
 *
 */
@WebServlet(name="AddEmployeeToListServlet", urlPatterns="/AddEmployeeToList")
@Interceptors({LoggerSingleton.class})
public class AddEmployeeToListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
    private IProjectDAO projectDao;
   
    @EJB
    private IEmployeeDAO employeeDao; 
    
    private static final String EMAIL = "email";
    private static final String EMAILMANAGER = "emailManager";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeToListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void setEmployeeDao(IEmployeeDAO employeeDao) {
    	this.employeeDao = employeeDao;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String emailEmployee=request.getParameter(EMAIL);
	    String emailManager=request.getParameter(EMAILMANAGER);
	    
	    /**
	     * Check about the existence of submitted email in the database 
	     * 
	     * */
	    if(!emailEmployee.equals(emailManager))
	    {
	    	Employee employee = employeeDao.retrieveByEmail(emailEmployee);
	    	if(employee==null)
	    	{
	    		response.getWriter().append("The employee is not valid");
	    	}
	    
	    	else
	    	{
	    		HttpSession session=request.getSession();
	    		List<Employee> currentEmployeeList = (List<Employee>) session.getAttribute("currentEmployees");
	    		int flagList=0;
	    		if(currentEmployeeList!=null)
	    		{
	    			for(int i=0; i<currentEmployeeList.size() && flagList==0; i++)
	    			{
	    				if(currentEmployeeList.get(i).getEmail().equals(employee.getEmail()))
	    				{	
	    					flagList=1;
	    				}
	    			}
	    		}
	    			
	        
	        /**
	         * If employee exists, the employee will be inserted into the arraylist
	         * 
	         * */
	    	if(flagList==0)
	    	{
	    		JSONObject obj = new JSONObject();
	    		obj.put("emailEmployee", employee.getEmail());
	    		ArrayList<Employee> list=(ArrayList<Employee>) session.getAttribute("employeeList");
	        
	        /**
	         * If the list, initially is empty, it will be created
	         * 
	         */
	    		if(list==null)
	    		{
	    			list=new ArrayList<Employee>();
	    			list.add(employee);
	    			session.setAttribute("employeeList", list);
	    			response.getWriter().append(obj.toString());
	    			response.setContentType("application/json");
	    		}
	        
	        
	        /**
	         * Only if the employee there isn't yet into the list, it will be added 
	         * 
	         * */
	    		else
	    		{
	    			int flag=0;
	    			for(int i=0; i<list.size() && flag==0; i++)
	    			{
	    				if(list.get(i).getEmail().equals(employee.getEmail()))
	    				{
	    					flag=1;
	    				}
	    			}
	          
	    			if(flag==0)
	    			{
	    				list.add(employee);
	    				session.setAttribute("employeeList", list);
	    				response.getWriter().append(obj.toString());
	    				response.setContentType("application/json");
	    			}
	    		}
	    	}
	    }
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}