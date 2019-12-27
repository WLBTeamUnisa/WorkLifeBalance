package it.unisa.wlb.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IProjectDAO;

/**
 * Servlet implementation class SuggestionEmployees
 */
@WebServlet("/SuggestionEmployees")
public class SuggestionEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
    private IProjectDAO projectDao;
   
    @EJB
    private IEmployeeDAO employeeDao;   
	
    private static final String EMAIL_EMPLOYEE = "email";
    private static final String FLAG = "flag";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuggestionEmployees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray lista_json=new JSONArray();
        
        String email_employee=request.getParameter(EMAIL_EMPLOYEE);
        String flagStr = request.getParameter(FLAG);
        
        System.out.println(flagStr);
        
        List<Employee> listaDipendenti=null;
        
        if((email_employee!=null || email_employee!="") && flagStr!=null)
        {
          /**
           * Restituisco una lista di suggerimenti dei dipendenti tramite l'email
           * 
           * */
        	
        	int flag = Integer.parseInt(flagStr);
        	if(flag==0) {
        		listaDipendenti=employeeDao.retrieveSuggestsEmployeeByEmail(email_employee);        		
        	} else if(flag==1) {
        		listaDipendenti=employeeDao.retrieveSuggestsManagerByEmail(email_employee);
        	}
           
        	if(listaDipendenti==null) {
        		//Eccezione
        	}
        	
           for(int i = 0; i<listaDipendenti.size();i++) {
        	   JSONObject obj = new JSONObject();
        	   obj.put("email", listaDipendenti.get(i).getEmail());
        	   lista_json.put(obj);
           }
           
           /**if(listaDipendenti!=null && listaDipendenti.size()>0)
           {
             List<Employee> lista=(List<Employee>) request.getAttribute("listaDipendenti");
             for(int i=0; i<listaDipendenti.size(); i++)
             {
               
                * Facendo questo controllo, evito che l'admin scelga un dipendente che fa già parte della lista
                * 
                *
               if(!lista.contains(listaDipendenti.get(i)));
               lista_json.put(listaDipendenti.get(i).getEmail());
             }
           }
            */
           
           response.setContentType("application/json");
           response.getWriter().append(lista_json.toString());
        }
      }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
