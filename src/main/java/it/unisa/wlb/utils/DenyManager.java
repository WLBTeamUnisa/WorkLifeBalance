package it.unisa.wlb.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.wlb.model.bean.Employee;

/**
 * Servlet Filter implementation class DenyManager
 */
@WebFilter("/DenyManager")
public class DenyManager implements Filter {

    /**
     * Default constructor. 
     */
    public DenyManager() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Object user=((HttpServletRequest) request).getSession().getAttribute("user");
	    if(user.getClass().getSimpleName().equals("Admin") && ((Employee) user).getStatus()==1) {
	        ((HttpServletResponse) response).getWriter().println("Accesso Negato");
	        return;
	      }
	    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
