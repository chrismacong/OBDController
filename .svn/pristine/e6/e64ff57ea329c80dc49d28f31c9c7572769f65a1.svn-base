package com.fix.obd.web.control;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fix.obd.web.service.YY_LoginService;
@Controller
@RequestMapping("/mobilelogin")         //mobilelogin.html
public class YY_MoblieClientLoginControl {

	@Resource
	private YY_LoginService loginService;
	public YY_LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(YY_LoginService loginService) {
		this.loginService = loginService;
	}
	@RequestMapping(value = "/checkuser", method = RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response){
		//Map<String,Object> model = new HashMap<String,Object>();
		//HttpSession session= request.getSession(true);
		//String terminalId = request.getParameter("terminalId");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//String rememberMe = request.getParameter("rememberMe");
		Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//∏¥‘”∆•≈‰
	    Matcher m = p.matcher(email);
	       
        if(email.equals("")){ 
    	   return "result=0,userid=null";       //emailŒ™ø’                     
    	   
  		}else if(!m.matches()){ 
   	      return "result=0,userid=null";         //” œ‰∏Ò Ω¥ÌŒÛ
  		}else if(password.equals("")){
  			return "result=0,userid=null";          //√‹¬ÎŒ™ø’
  	  	}
       //—È÷§” œ‰
		if(loginService.askCheckUser(email,password)){
			//session.setAttribute("email",email);
			return "result=1,userid="+email;              //µ«¬Ω≥…π¶
			
		}else{
			return "result=0,userid=null";           //”√ªß√˚¥ÌŒÛªÚ√‹¬Î¥ÌŒÛ,µ«¬º ß∞‹
		}
 	}

}
