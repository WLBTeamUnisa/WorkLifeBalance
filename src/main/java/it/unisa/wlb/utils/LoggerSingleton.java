package it.unisa.wlb.utils;

import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggerSingleton {

	@SuppressWarnings("deprecation")
	@AroundInvoke
	public Object logMethod(InvocationContext ic) throws Exception
	{
		Date entryDate=new Date();
		String methodName=ic.getMethod().getName();
		String classOfMethod=ic.getTarget().getClass().getSimpleName();
		System.out.println("Calling the method "+methodName +" of "+classOfMethod+" class to the instant "+entryDate.toLocaleString());
		try {
			return ic.proceed();
		}
		
		finally {
			Date exitDate=new Date();
			System.out.println("The method "+ methodName+" of "+classOfMethod+" lasted for "+ (exitDate.getTime()-entryDate.getTime())+" ms");
		}
	}
}
