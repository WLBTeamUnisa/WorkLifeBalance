package it.unisa.wlb.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import it.unisa.wlb.model.bean.Employee;

/**
 * This filter deny a resource to an employee
 * 
 * @author Luigi Cerrone
 */
public class DenyEmployee implements Filter {

    /**
     * Default constructor.
     */
    public DenyEmployee() {

    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Object user = ((HttpServletRequest) request).getSession().getAttribute("user");
        if (user.getClass().getSimpleName().equals("Employee") && ((Employee) user).getStatus() == 0) {
            ((HttpServletRequest) request).getRequestDispatcher("WEB-INF/DenyAccess.jsp").forward(request, response);
            ;
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
