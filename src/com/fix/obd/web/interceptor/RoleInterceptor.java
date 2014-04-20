package com.fix.obd.web.interceptor;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

import com.fix.obd.web.service.YY_RoleAuthorityService;
import com.fix.obd.web.util.URLFunctionMapping;
@Controller
public class RoleInterceptor extends HandlerInterceptorAdapter implements InitializingBean{  
	private static final Logger logger = Logger.getLogger(RoleInterceptor.class);
	@Resource
	private YY_RoleAuthorityService roleAuthorityService;
	public YY_RoleAuthorityService getRoleAuthorityService() {
		return roleAuthorityService;
	}

	public void setRoleAuthorityService(YY_RoleAuthorityService roleAuthorityService) {
		this.roleAuthorityService = roleAuthorityService;
	}

	@Override  
	public boolean preHandle(HttpServletRequest request,  HttpServletResponse response, Object handler) throws Exception {  
		// TODO Auto-generated method stub  
		logger.debug("==============preHandle================"); 
		String rolename = (String) request.getSession().getAttribute("rolename");
		MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();
		System.out.println("methodName="+methodNameResolver.getHandlerMethodName(request));
		System.out.println("Rolename is:" + rolename);
		if("login_check".equals(methodNameResolver.getHandlerMethodName(request))){
			return true;
		}
//		if("main".equals(methodNameResolver.getHandlerMethodName(request))){
//			if(("manager").equals(rolename))
//				response.sendRedirect("main.html");
//			else if(("loggedmember").equals(rolename))
//				response.sendRedirect("personal.html");
//			else
//				response.sendRedirect("login.html");
//		}
		 ApplicationContext ac = new ClassPathXmlApplicationContext("../springMVC-servlet.xml");
		 URLFunctionMapping urlFunctionMapping = (URLFunctionMapping)ac.getBean("urlFunctionMap");          //得到bean
		 Map<String,String> map = urlFunctionMapping.getUrlMapToFunction();
		 String url = request.getRequestURI();
		 System.out.println(request.getRequestURI());
		 String urlMapToValue = map.get(url).toString();            //从spring中得到URLFunction的bean，从中获取属性map,得到url对应的value值
//		 System.out.println(urlMapToValue);
		 List<String> authorityList = roleAuthorityService.getAuthorityList(rolename);
		 for(int i=0;i<authorityList.size();i++){
			 if(urlMapToValue.equals(authorityList.get(i))){         //匹配成功，用户有权限访问页面
				 System.out.println("匹配成功，有权访问");
				 return true;
			 }
		 }
		 //匹配不成功，转到错误页面
		 System.out.println("匹配不成功，转向错误页面");
		 response.sendRedirect(request.getContextPath()+"/jsp/YY_NoPermission.jsp");
		return true;  
	}  

	//在业务处理器处理请求执行完成后,生成视图之前执行的动作   
	@Override  
	public void postHandle(HttpServletRequest request,  
			HttpServletResponse response, Object handler,  
			ModelAndView modelAndView) throws Exception {  
		// TODO Auto-generated method stub  
		logger.debug("==============postHandle================");  
		String username = (String) request.getSession().getAttribute("username");
		String url=request.getRequestURL().toString();  
		System.out.println(url); 
		System.out.println(username);
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
