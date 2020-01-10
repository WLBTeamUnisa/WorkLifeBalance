package it.unisa.wlb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.utils.LoggerSingleton;
import it.unisa.wlb.utils.Utils;

/**
 * The aim of this Servlet is registering an Employee/Manager into the system.
 * 
 * @author Simranjit, Sabato
 *
 */
@WebServlet(name = "EmployeeRegistrationServlet", urlPatterns = "/EmployeeRegistrationServlet")
@Interceptors({LoggerSingleton.class})
public class EmployeeRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IEmployeeDAO employeeDao;

	public EmployeeRegistrationServlet() {
		super();
	}

	/**
	 * This constructor is used during testing in order to simulate the behaviour of the dao class
	 * 
	 * @param employeeDao
	 */
	public EmployeeRegistrationServlet(IEmployeeDAO employeeDao) {
		super();
		this.employeeDao=employeeDao;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				

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

		if( email==null ||  !email.matches("^[a-z]{1}\\.[a-z]+[0-9]+\\@wlb.it$") || email.equals("") || (email.length()-7)<5 || (email.length()-7)>30 )
			emailOk=false;
		else {
			Employee employee = null;
			try {
				employee = employeeDao.retrieveByEmail(email);
			} catch (Exception e) {
				;
			}		
			if(employee==null)
				emailOk=true;
			else
				emailOk=false;						
		}

		if(password== null || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\.!@#\\$%\\^&\\*]).{8,20}$") || password.length()<8 || password.length()>20)
			passwordOk=false;
		else
			passwordOk=true;

		if(verifyPassword== null || !verifyPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\.!@#\\$%\\^&\\*]).{8,20}$") || verifyPassword.length()<8 || verifyPassword.length()>20 || !verifyPassword.equals(password))
			verifyPasswordOk=false;
		else
			verifyPasswordOk=true;

		if(status.equals("Employee") || status.equals("Manager") ) {
			if(status.equals("Employee"))
				statusOk=true;
			statusInt=0;
			if(status.equals("Manager")) {
				statusOk=true;
				statusInt=1;
			}
		}
		else
			statusOk=false;

		if(nameOk && surnameOk && emailOk && passwordOk && verifyPasswordOk && statusOk) {
			/**
			 * Creation of a new Employee with form parameters
			 */
			Employee employee= new Employee();
			employee.setName(name);
			employee.setSurname(surname);
			employee.setEmail(email);
			employee.setPassword(Utils.generatePwd(password));
			employee.setStatus(statusInt);

			/**
			 * Insert on of the new Employee into the database
			 */
			try {
				employeeDao.create(employee);
				request.setAttribute("result", "success");
			} catch (Exception e) {
				request.setAttribute("result", "failure");
			}					

//			String url=response.encodeURL("WEB-INF/EmployeesList.jsp");
//			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//			dispatcher.forward(request, response);
			request.getRequestDispatcher("EmployeesListPage").forward(request, response);
		}
		else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Inserimento di parametri non validi per la registrazione del dipendente");			
			response.getWriter().flush();
			throw new IllegalArgumentException("Inserimento di parametri non validi per la registrazione del dipendente");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
