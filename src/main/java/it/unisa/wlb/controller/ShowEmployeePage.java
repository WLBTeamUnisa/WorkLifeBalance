package it.unisa.wlb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.dao.IEmployeeDAO;
import it.unisa.wlb.model.dao.IProjectDAO;

/**
 * Servlet implementation class ShowEmployeePage
 */
@WebServlet("/ShowEmployeePage")
public class ShowEmployeePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@EJB
	private IEmployeeDAO employeeDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowEmployeePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!(request.getSession().getAttribute("user")==null) || !(request.getSession().getAttribute("userRole").equals("Admin"))) {
			HttpSession session=request.getSession();
			if(session.getAttribute("userRole").equals("Admin"))
			{
				//EmployeeLists.jsp send email parameter
				String email=request.getParameter("email");
				if(email!=null && email!="")
				{
					try
					{
						
						Employee employee=employeeDao.retrieveByEmail(email);session.setAttribute("employee", employee);
						request.setAttribute("result", "ok");
						request.getRequestDispatcher("WEB-INF/Employee.jsp").forward(request, response);
					}
					catch(Exception ex)
					{
						request.setAttribute("result", "error");
						request.getRequestDispatcher("/EmployeeListPageServlet").forward(request, response);
					}
				}
				else //if email is null or email is empty
				{
					request.setAttribute("result", "error");
					request.getRequestDispatcher("/EmployeeListPageServlet").forward(request, response);
				}
			}
			else//if the user is different to admin
			{
				request.setAttribute("result", "error");
				request.getRequestDispatcher("/EmployeeListPageServlet").forward(request, response);
			}
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
