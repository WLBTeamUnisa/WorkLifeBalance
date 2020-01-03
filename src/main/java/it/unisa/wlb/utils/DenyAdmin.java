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

/**
 * @author Luigi Cerrone
 * 
 * This filter deny a resource to an employee
 */
@WebFilter("/DenyAdmin")
public class DenyAdmin implements Filter {

    /**
     * Default constructor. 
     */
    public DenyAdmin() {
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
	    if(user.getClass().getSimpleName().equals("Admin")) {
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
