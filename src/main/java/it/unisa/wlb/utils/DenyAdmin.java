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

/**
 * This filter deny a resource to an employee
 * 
 * @author Luigi Cerrone
 */
@WebFilter("/DenyAdmin")
public class DenyAdmin implements Filter {

    /**
     * Default constructor.
     */
    public DenyAdmin() {

    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Object user = ((HttpServletRequest) request).getSession().getAttribute("user");
        if (user.getClass().getSimpleName().equals("Admin")) {
            ((HttpServletRequest) request).getRequestDispatcher("WEB-INF/DenyAccess.jsp").forward(request, response);
            ;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
