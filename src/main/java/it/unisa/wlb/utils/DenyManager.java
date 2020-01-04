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
 * @author Luigi Cerrone
 * 
 * This filter deny a resource to an manager
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
	    if(user.getClass().getSimpleName().equals("Employee") && ((Employee) user).getStatus()==1) {
	    	((HttpServletRequest) request).getRequestDispatcher("WEB-INF/DenyAccess.jsp").forward(request, response);;
	        return;
	      }
	    chain.doFilter(request, response);
	    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
