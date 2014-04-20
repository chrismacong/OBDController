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
		 URLFunctionMapping urlFunctionMapping = (URLFunctionMapping)ac.getBean("urlFunctionMap");          //�õ�bean
		 Map<String,String> map = urlFunctionMapping.getUrlMapToFunction();
		 String url = request.getRequestURI();
		 System.out.println(request.getRequestURI());
		 String urlMapToValue = map.get(url).toString();            //��spring�еõ�URLFunction��bean�����л�ȡ����map,�õ�url��Ӧ��valueֵ
//		 System.out.println(urlMapToValue);
		 List<String> authorityList = roleAuthorityService.getAuthorityList(rolename);
		 for(int i=0;i<authorityList.size();i++){
			 if(urlMapToValue.equals(authorityList.get(i))){         //ƥ��ɹ����û���Ȩ�޷���ҳ��
				 System.out.println("ƥ��ɹ�����Ȩ����");
				 return true;
			 }
		 }
		 //ƥ�䲻�ɹ���ת������ҳ��
		 System.out.println("ƥ�䲻�ɹ���ת�����ҳ��");
		 response.sendRedirect(request.getContextPath()+"/jsp/YY_NoPermission.jsp");
		return true;  
	}  

	//��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ���   
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
	 * ��DispatcherServlet��ȫ����������󱻵���  
	 *  
	 *   �����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion() 
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
		System.out.println("=======��ʼ��DetailInterceptor������========="); 
	}  
}
