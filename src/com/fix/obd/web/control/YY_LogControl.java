package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fix.obd.util.MessageUtil;
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
		 String email = "";
		 String rolename = "";
		Cookie[] cookies = request.getCookies();
		 if(cookies!=null){
		    for (int i = 0; i < cookies.length; i++) {
		        Cookie c = cookies[i];
		        if(c.getName().equalsIgnoreCase("email"))
		         {
		             email = c.getValue();
		        }else if(c.getName().equalsIgnoreCase("rolename")){
		    	     rolename = c.getValue();
		    	}
		     
		 } //end for
		 } //if
		 if(email.equals("")||rolename.equals("")){
			 return new ModelAndView("YY_LoginPage");
		 }else if(rolename.equals("manager")||rolename.equals("member")){
			 System.out.println("^^^^^^^^^^^^^" + email);
			 session.setAttribute("email", email);
			 
			 Cookie emailCookie=new Cookie("email",email);
			 emailCookie.setMaxAge(30 * 24 * 60 * 60); 
			 emailCookie.setPath("/");
		     response.addCookie(emailCookie);
		     Cookie rolenameCookie=new Cookie("rolename",rolename);
		     rolenameCookie.setMaxAge(30 * 24 * 60 * 60);
		     rolenameCookie.setPath("/");
			 response.addCookie(rolenameCookie);
			 String terminalId = loginService.getTerminalIdByEmail(email);
			 if(terminalId!=null)
				    session.setAttribute("terminalId", terminalId);
			 else
				 	session.setAttribute("terminalId","00000000000000000000");
			 if(rolename.equals("manager")){
				 	session.setAttribute("rolename", "manager");
			    	return new ModelAndView(new RedirectView("/OBDController/main.html"));
			  }else if(rolename.equals("member")){
			    	session.setAttribute("rolename", "loggedmember");
			    	return new ModelAndView(new RedirectView("/OBDController/personal.html"));
			  }
		     
		        
		        
			 
		 }
		 return new ModelAndView("YY_LoginPage");
	}
	/*
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
	    //return new ModelAndView(new RedirectView("/OBDController/login.html"),model);
		return new ModelAndView(new RedirectView("/OBDController/login.html?email="+email+"&password_message=√‹¬ÎªÚ” œ‰¥ÌŒÛ£¨«Î÷ÿ–¬ ‰»Î"));
		//return new ModelAndView("YY_LoginPage",model);
		
 	}
 	*/
	
	@RequestMapping(value = "/certcode_check", method = RequestMethod.GET)
	public void certcode_check(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
		try {
			 
			 String certcode = request.getParameter("certcode");
			 
			 System.out.println("certcode:"+certcode);
			 PrintWriter pw = null;
			 pw=response.getWriter();
			 JSONObject jsonObject = new JSONObject(); 
			 String rand = (String) session.getAttribute("rand");
			 
			if(rand.equals(certcode)){
				      jsonObject.put("success", "true");
					  pw.print(jsonObject.toString());
				 	  pw.flush();
				 	  pw.close();
				 
				 	  return;
				    	
			}else{
					 jsonObject.put("success", "false");
					 pw.print(jsonObject.toString());
				 	 pw.flush();
				 	 pw.close();
				 
				 	 return;
			}
				}catch(IOException e){
					
				}
	}
	
	
	@RequestMapping(value = "/login_check", method = RequestMethod.GET)
	public void login(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
		 Map<String,Object> model = new HashMap<String,Object>();
		 String email = request.getParameter("email");
		 String password = request.getParameter("password");
		 String rememberMe = request.getParameter("rememberMe");
		 System.out.println("rememberMe:"+rememberMe);
		 PrintWriter pw = null;
		 pw=response.getWriter();
		 JSONObject jsonObject = new JSONObject(); 
		if(loginService.askCheckUser(email,password)){
			String rolename = loginService.getRoleName(email);
			session.setAttribute("email",email);
			session.setAttribute("rolename", rolename);
			String terminalId = loginService.getTerminalIdByEmail(email);
			 if(terminalId!=null)
				    session.setAttribute("terminalId", terminalId);
			 else
				 	session.setAttribute("terminalId","00000000000000000000");			
			if(rememberMe.equals("on")){
				
				System.out.println("º«◊°√‹¬Î£°");
				 
				// Cookie passwordCookie=new Cookie("password",password);
				 Cookie emailCookie=new Cookie("email",email);
				    emailCookie.setMaxAge(30 * 24 * 60 * 60); 
				    //passwordCookie.setMaxAge(30 * 24 * 60 * 60);
				   // passwordCookie.setPath("/");
			        emailCookie.setPath("/");
			        response.addCookie(emailCookie);
			       // response.addCookie(passwordCookie);
			        Cookie rolenameCookie=new Cookie("rolename",rolename);
				     rolenameCookie.setMaxAge(30 * 24 * 60 * 60);
				     rolenameCookie.setPath("/");
					 response.addCookie(rolenameCookie);
			 
			}
			try{
			 if(rolename.equals("manager")){
				  jsonObject.put("success", "true");
				  jsonObject.put("rolename", "manager");
				  pw.print(jsonObject.toString());
				  pw.flush();
				  pw.close();
				  return;
			    	
			 }else if(rolename.equals("member")){
				  jsonObject.put("success", "true");
				  jsonObject.put("rolename", "member");
			      session.setAttribute("rolename", "loggedmember");
			      pw.print(jsonObject.toString());
			 	  pw.flush();
			 	  pw.close();
			 	 // RequestDispatcher dispatcher = request.getRequestDispatcher("/OBDController/personal.html");
			 	  // dispatcher.forward(request, response);
				
			 	  return;
			    	
			 }
			}catch(NullPointerException e){
				
			}
			
		
		}
		
		jsonObject.put("success", "false");
		model.put("email", email);
	    model.put("password_message", "√‹¬ÎªÚ” œ‰¥ÌŒÛ£¨«Î÷ÿ–¬ ‰»Î");
	    pw.print(jsonObject.toString());
		pw.flush();
		pw.close();
		return ;
	    //return new ModelAndView(new RedirectView("/OBDController/login.html"),model);
		//return new ModelAndView(new RedirectView("/OBDController/login.html?email="+email+"&password_message=√‹¬ÎªÚ” œ‰¥ÌŒÛ£¨«Î÷ÿ–¬ ‰»Î"));
		//return new ModelAndView("YY_LoginPage",model);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
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
		/*
		Cookie newpasswordCookie=new Cookie("password",newpass);
		    newpasswordCookie.setMaxAge(30 * 24 * 60 * 60);
		    newpasswordCookie.setPath("/");
	        response.addCookie(newpasswordCookie);
	
		*/
 	}
	/*
	@RequestMapping(value = "/password_reget", method = RequestMethod.GET)
	public ModelAndView passwordReget(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("YY_PasswordRegetPage");
	}
   */
	@RequestMapping(value = "/mobilelogin", method = RequestMethod.GET)
	public void mobileLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 String email = request.getParameter("email");
		 String password = request.getParameter("password");
		 String result = null;
	    if(loginService.askCheckUser(email,password)){
			 String terminalId = loginService.getTerminalIdByEmail(email);
			 if(terminalId==null){
				 terminalId = "Œ¥∞Û∂®…Ë±∏";
			 }
   	        result = "1,";
			result = result + terminalId;
		}else{
			result = "0,null";
		}
	    try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}

}
