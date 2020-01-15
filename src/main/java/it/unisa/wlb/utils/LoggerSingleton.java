package it.unisa.wlb.utils;

import java.util.Date;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * The aim of this logger is printing a message when a method is called
 * 
 * @author Luigi Cerrone
 *
 */
public class LoggerSingleton {
    /**
     * Method for logging
     * 
     * @param invocationContext
     * @return Object
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    @AroundInvoke
    public Object logMethod(InvocationContext invocationContext) throws Exception {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        Date entryDate = new Date();
        String methodName = invocationContext.getMethod().getName();
        String classOfMethod = invocationContext.getTarget().getClass().getName();
        System.out.println("Calling the method " + methodName + " of " + classOfMethod + " class to the instant "
                + entryDate.toLocaleString());
        try {
            return invocationContext.proceed();
        } finally {
            Date exitDate = new Date();
            System.out.println("The method " + methodName + " of " + classOfMethod + " lasted for "
                    + (exitDate.getTime() - entryDate.getTime()) + " ms");
        }
    }
}
