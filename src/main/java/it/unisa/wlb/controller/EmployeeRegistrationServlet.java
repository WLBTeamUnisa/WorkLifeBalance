package it.unisa.wlb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;

/**
 * Servlet implementation class EmployeeRegistrationServlet
 */
@WebServlet("/EmployeeRegistrationServlet")
public class EmployeeRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@EJB
	private IEmployeeDAO employeeDao;
	
    public EmployeeRegistrationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter p = response.getWriter();
				
		/**
	     * Employee Registration parameters
	     */
		String name= request.getParameter("name");
		String surname= request.getParameter("surname");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String verifyPassword= request.getParameter("verifyPassword");
		String status= request.getParameter("status");
		int statusInt=0;
		
		System.out.println(name);
		System.out.println(surname);
		System.out.println(email);
		System.out.println(password);
		System.out.println(verifyPassword);
		System.out.println(status);
		
		boolean nameOk = false;
		boolean surnameOk = false;
		boolean emailOk = false;
		boolean passwordOk = false; 
		boolean verifyPasswordOk = false;
		boolean statusOk = false;
		
		/**
	     * Employee Registration parameters check
	     */
		if(name==null || !name.matches("^[A-Za-z]+$") || name.equals("") || name.length()<2 || name.length()>20)
			nameOk=false;
		else
			nameOk=true;
		
		if( surname==null || !surname.matches("^[A-Za-z\\s]+$") || surname.equals("") || surname.length()<2 || surname.length()>20)
			surnameOk=false;
		else
			surnameOk=true;
		
		if( email==null ||  !email.matches("^[a-z]{1}\\.[a-z]+[1-9]*\\@wlb.it$") || email.equals("") || email.length()<5 || email.length()>30 )
			emailOk=false;
		else
		{
			Employee employeeExist;
			try {
				employeeExist = employeeDao.retrieveByEmail(email);
				emailOk=false;
			} catch (Exception e) {
				emailOk=true;
			}				
		}
			
		if(password== null || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\.!@#\\$%\\^&\\*]).{8,20}$") || password.length()<8 || password.length()>20)
			passwordOk=false;
		else
			passwordOk=true;
		
		if(verifyPassword== null || !verifyPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\.!@#\\$%\\^&\\*]).{8,20}$") || verifyPassword.length()<8 || verifyPassword.length()>20 || !verifyPassword.equals(password))
			verifyPasswordOk=false;
		else
			verifyPasswordOk=true;
		
		if(status.equals("employee") || status.equals("manager") )
			{
				if(status.equals("employee"))
				statusOk=true;
				statusInt=0;
				if(status.equals("manager"))
				{
					statusOk=true;
					statusInt=1;
				}
			}
		else
			statusOk=false;
		
		if(nameOk && surnameOk && emailOk && passwordOk && verifyPasswordOk && statusOk)
		{
			/**
		     * Create new Employee with form parameters
		     */
			Employee employee= new Employee();
			employee.setName(name);
			employee.setSurname(surname);
			employee.setEmail(email);
			employee.setPassword(password);
			employee.setStatus(statusInt);
			
			System.out.println(employee.getName()+" "+employee.getStatus());
			
			/**
		     * insert into Database
		     */
			employeeDao.create(employee);
			
			System.out.println("Tutto ok");
			
			String url=response.encodeURL("/EmployeeRegistration.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
