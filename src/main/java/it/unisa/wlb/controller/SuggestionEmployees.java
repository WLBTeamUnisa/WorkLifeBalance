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
        List<Employee> lista_dipendenti=null;
        
        if(email_employee!=null || email_employee!="")
        {
        
          //restituisco una lista di suggerimenti dei dipendenti tramite l'email
           lista_dipendenti=employeeDao.searchByEmail(email_employee);
           if(lista_dipendenti!=null && lista_dipendenti.size()>0)
           {
             List<Employee> lista=(List<Employee>) request.getAttribute("lista_dipendenti");
             for(int i=0; i<lista_dipendenti.size(); i++)
             {
               //Facendo questo controllo, evito che il dipendente scelga un dipendente che fa già parte della lista
               if(!lista.contains(lista_dipendenti.get(i)));
               lista_json.put(lista_dipendenti.get(i).getEmail());
             }
           }
           
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
