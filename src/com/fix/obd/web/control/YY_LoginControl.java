package com.fix.obd.web.control;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.service.AGPSService;
import com.fix.obd.web.service.YY_LoginService;

@Controller
@RequestMapping("/login") 
public class YY_LoginControl {

	@Resource
	private YY_LoginService loginService;
	public YY_LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(YY_LoginService loginService) {
		this.loginService = loginService;
	}
	@RequestMapping(value = "/checkuser", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> model = new HashMap<String,Object>();
		//String terminalId = request.getParameter("terminalId");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		System.out.println("rememberMe"+":"+rememberMe);
		Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
	    Matcher m = p.matcher(email);
	       
        if(email.equals("")){
        	
    	    model.put("email_message", "请输入邮箱地址"); 
    	   return new ModelAndView("YY_LoginPage",model);
    	   
  		}else if(!m.matches()){
  		 
  		  model.put("email_message", "请输入正确邮箱地址!"); 
   	      return new ModelAndView("YY_LoginPage",model);
  		}else if(password.equals("")){
  			model.put("email", email);
  		   model.put("password_message", "请输入密码");
  		   return new ModelAndView("YY_LoginPage",model);
  	  	}
       
		if(loginService.askCheckUser(email,password)){
			try{
			 
		      if(rememberMe.equals("on")){
                  //表示用户点击了【记住密码】按钮
                  //创建cookie
                  Cookie user = new Cookie("user", email + "-" + password);
                  user.setMaxAge(60*60*24);
                  user.setPath("/"); 
                  response.addCookie(user);
                  System.out.println("remember cookie now");
               }
                
			}catch(NullPointerException e){
				    Cookie deleteCookie=new Cookie("user",null);
	                deleteCookie.setMaxAge(0); //删除该Cookie
	                deleteCookie.setPath("/"); 
	                response.addCookie(deleteCookie); 
	                System.out.println("delete cookie now");
					System.out.println("exception");
			}
			
			return new ModelAndView("YY_RegisterPage");
			
		}
		System.out.println(email+"?");
		System.out.println(password+"?");
		//terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		//model.put("terminalId", terminalId);
		//agpsService.askSendAGPS(terminalId);
		//return new ModelAndView("AGPSPage",model);
		model.put("email", email);
		model.put("password_message", "密码或邮箱错误，请重新输入");
		return new ModelAndView("YY_LoginPage",model);
 	}

}
