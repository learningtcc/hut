package com.hut.file.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Bruce
 * @version  1.0.2 在spring mvc 环境下的 工具类,获得spring环境下的 一些对象.
 */
public abstract class Springs {

	public static HttpServletRequest getRequest(){
		  return ((ServletRequestAttributes)
				  RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static ApplicationContext getRootApplicationContext() {
		return ContextLoader.getCurrentWebApplicationContext();
	}

	public static ApplicationContext getServletApplicationContext(ServletContext sc) {
		return RequestContextUtils.getWebApplicationContext(getRequest(),sc);
	}
	public static ApplicationContext getServletApplicationContext(HttpServletRequest request, ServletContext sc) {
		return RequestContextUtils.getWebApplicationContext(request,sc);
	}

	public static boolean isAjax(){
		return isAjax(getRequest());
	}
	
	public static boolean isAjax(HttpServletRequest request){
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
	
	
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getRemoteAddr();  
        }  
        
        if(ipAddress.equals("0:0:0:0:0:0:0:1")){  
            ipAddress= "127.0.0.1";  
        }  
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ipAddress!=null && ipAddress.indexOf(",")>0){ 
               ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
        }  
        return ipAddress; 
	}
	
}
