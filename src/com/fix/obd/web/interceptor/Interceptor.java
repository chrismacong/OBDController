package com.fix.obd.web.interceptor;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;


@Controller
public class Interceptor extends HandlerInterceptorAdapter implements InitializingBean{  
	private static final Logger logger = Logger.getLogger(Interceptor.class);

	@Override  
	public boolean preHandle(HttpServletRequest request,  HttpServletResponse response, Object handler) throws Exception {  
		// TODO Auto-generated method stub  
		logger.debug("==============preHandle================"); 
		String email = (String) request.getSession().getAttribute("email");
		MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();
		if("register".equals(methodNameResolver.getHandlerMethodName(request))){
			System.out.println("register");
			return true;
		}
		if("check_register".equals(methodNameResolver.getHandlerMethodName(request))){
			System.out.println("check_register");
			return true;
		}
		if(email==null||email.equals("")){
			System.out.println("redirect to login.html");
			 response.sendRedirect(request.getContextPath()+"/jsp/YY_LoginPage.jsp");
			 return true;
		}
		return true;
		
		
	}  

	//在业务处理器处理请求执行完成后,生成视图之前执行的动作   
	@Override  
	public void postHandle(HttpServletRequest request,  
			HttpServletResponse response, Object handler,  
			ModelAndView modelAndView) throws Exception {  
		// TODO Auto-generated method stub  
		logger.debug("==============postHandle================");  
		
	}  

	/** 
	 * 在DispatcherServlet完全处理完请求后被调用  
	 *  
	 *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
	 */  
	@Override  
	public void afterCompletion(HttpServletRequest request,  
			HttpServletResponse response, Object handler, Exception ex)  
					throws Exception {  
		// TODO Auto-generated method stub  
		logger.debug("==============afterCompletion================");  
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=======初始化DetailInterceptor拦截器========="); 
	}  
}

