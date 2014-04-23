package com.fix.obd.web.control;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fix.obd.web.service.YY_EditPasswordService;
import com.fix.obd.web.service.YY_LoginService;
@Controller 
@RequestMapping("/login") 
public class YY_LogControl {
	
	//login
	@Resource
	private YY_LoginService loginService;
	public YY_LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(YY_LoginService loginService) {
		this.loginService = loginService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		return new ModelAndView("YY_LoginPage");
	}
	@RequestMapping(value = "/login_check", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Map<String,Object> model = new HashMap<String,Object>();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		String rolename = loginService.getRoleName(email);
		if(loginService.askCheckUser(email,password)){
			session.setAttribute("email",email);
			session.setAttribute("rolename", rolename);
			if(rememberMe.equals("on")){
				System.out.println("email"+email);
				 
				 Cookie passwordCookie=new Cookie("password",password);
				 Cookie emailCookie=new Cookie("email",email);
				    emailCookie.setMaxAge(30 * 24 * 60 * 60); 
				    passwordCookie.setMaxAge(30 * 24 * 60 * 60);
				    passwordCookie.setPath("/");
			        emailCookie.setPath("/");
			        response.addCookie(emailCookie);
			        response.addCookie(passwordCookie);
			 
			}
			String terminalId = loginService.getTerminalIdByEmail(email);
			if(terminalId!=null)
				session.setAttribute("terminalId", terminalId);
		    if(rolename.equals("manager")){
		    	return new ModelAndView(new RedirectView("/OBDController/main.html"));
		    }else if(rolename.equals("member")){
		    	session.setAttribute("rolename", "loggedmember");
		    	return new ModelAndView(new RedirectView("/OBDController/personal.html"));
		    }else{
		    	return new ModelAndView(new RedirectView("/OBDController/login.html"));
		    }
			
		}
		System.out.println(email+"?");
		System.out.println(password+"?");
		
		model.put("email", email);
		model.put("password_message", "√‹¬ÎªÚ” œ‰¥ÌŒÛ£¨«Î÷ÿ–¬ ‰»Î");
		return new ModelAndView(new RedirectView("/OBDController/login.html?email="+email+"&password_message=√‹¬ÎªÚ” œ‰¥ÌŒÛ£¨«Î÷ÿ–¬ ‰»Î"));
		//return new ModelAndView("YY_LoginPage",model);
		
 	}
	/*
	//logout
	@RequestMapping(value = "/yy_logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
	    Cookie deleteCookie=new Cookie("user",null);
        deleteCookie.setMaxAge(0); //…æ≥˝∏√Cookie
        deleteCookie.setPath("/"); 
        response.addCookie(deleteCookie); 
        System.out.println("delete cookie now");
		HttpSession session= request.getSession(true);
		session.setAttribute("email",null);
		Map<String,Object> model = new HashMap<String,Object>();
		return new ModelAndView("YY_LoginPage",model);
 	}
	*/
	//welcome
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> model = new HashMap<String,Object>();
		return new ModelAndView(new RedirectView("login.html"),model);
 	}
	
	//editpassword
	@Resource
	private YY_EditPasswordService editPasswordService;
	public YY_EditPasswordService getEditPasswordService() {
		return editPasswordService;
	}
	public void setEditPasswordService(YY_EditPasswordService editPasswordService) {
		this.editPasswordService = editPasswordService;
	}
	
	@RequestMapping(value = "/editpassword", method = RequestMethod.POST)
	public void changepsw(HttpServletRequest request,HttpServletResponse response){
		
		//Map<String,Object> model = new HashMap<String,Object>();
		String newpass = request.getParameter("newpass");
		String email = request.getParameter("email");
		System.out.println(email);
		System.out.println(newpass);
		editPasswordService.askEditPassword(email, newpass);
		
		Cookie newpasswordCookie=new Cookie("password",newpass);
		    newpasswordCookie.setMaxAge(30 * 24 * 60 * 60);
		    newpasswordCookie.setPath("/");
	        response.addCookie(newpasswordCookie);
	
		
 	}
}
