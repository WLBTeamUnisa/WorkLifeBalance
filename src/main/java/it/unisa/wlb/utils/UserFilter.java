package it.unisa.wlb.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Luigi Cerrone
 * 
 * This filter ensures that only logged user can access to a resource
 * 
 * @see javax.servlet.Filter
 */
public class UserFilter implements Filter {

  /** Default constructor. */
  public UserFilter() {}

  /**
   * Override.
   * 
   * @see Filter#destroy()
   */
  public void destroy() {}

  /**
   * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
   */

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
	  
    if (((HttpServletRequest) request).getSession().getAttribute("user")!=null) {
    	/**
    	 * User logged
    	 */
      chain.doFilter(request, response);
    } else {
      String path = ((HttpServletRequest) request).getRequestURI();
      if (path.endsWith("/WorkLifeBalance/") || path.endsWith("/LoginServlet")) {
        chain.doFilter(request, response);
      } else {
        ((HttpServletRequest) request).getRequestDispatcher("WEB-INF/DenyAccess.jsp").forward(request, response);;
        return;
      }
    }
  }

  /**
   * Override.
   * 
   * @see Filter#init(FilterConfig)
   */
  public void init(FilterConfig fConfig) throws ServletException {}
}
