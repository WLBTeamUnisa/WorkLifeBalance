package it.unisa.wlb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;

/**
 * Servlet implementation class EmployeeRegistrationServlet
 */
@WebServlet("/EmployeeRegistrationServlet")
public class EmployeeRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
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
		String name= request.getParameter("Name");
		String surname= request.getParameter("Surname");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String verifyPassword= request.getParameter("verifyPassword");
		String status= request.getParameter("status");
		byte statusByte=0;
		

		System.out.println(name);
		System.out.println(surname);
		System.out.println(email);
		System.out.println(password);
		System.out.println(verifyPassword);
		System.out.println(status);
		
		boolean nameOk,surnameOk,emailOk,passwordOk,verifyPasswordOk,statusOk=false;
		
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
		
		//MANCA IL CONTROLLO SE LA EMAIL ESISTE GIA' NEL DB
		if( email==null ||  !email.matches("^[a-z]{1}\\.[a-z]+[1-9]*\\@wlb.it$") || email.equals("") || email.length()<5 || email.length()>30 )
			emailOk=false;
		else
			emailOk=true;
		
		if(password.length()<8 || password.length()>20 || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\.!@#\\$%\\^&\\*]).{8,20}$") )
			passwordOk=false;
		else
			passwordOk=true;
		
		if(!verifyPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\.!@#\\$%\\^&\\*]).{8,20}$") || verifyPassword.length()<8 || verifyPassword.length()>20 || !verifyPassword.equals(password))
			verifyPasswordOk=false;
		else
			verifyPasswordOk=true;
		
		
		if(status.equals("employee") && !status.equals(""))
			{
				statusOk=true;
				statusByte=0;
	
			}
		if(status.equals("manager")&& !status.equals(""))
		{
			statusOk=true;
			statusByte=1;
		}
		
		
		if(nameOk && surnameOk && emailOk && passwordOk && verifyPasswordOk && statusOk)
		{
			
			Employee Employee1= new Employee();
			Employee1.setName(name);
			Employee1.setSurname(surname);
			Employee1.setEmail(email);
			Employee1.setPassword(password);
			Employee1.setStatus(statusByte);
		//manca inserimento nel db
	    	
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
