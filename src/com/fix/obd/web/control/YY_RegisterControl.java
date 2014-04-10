package com.fix.obd.web.control;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.servlet.ModelAndView;

import com.fix.obd.web.service.YY_RegisterService;

@Controller
public class YY_RegisterControl {

	@Resource
	private YY_RegisterService registerService;
	public YY_RegisterService getRegisterService() {
		return registerService;
	}
	public void setRegisterService(YY_RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response){
		
		//Map<String,Object> model = new HashMap<String,Object>();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("I'm here");
		System.out.println(email);
		String realname = request.getParameter("realname");
		System.out.println("realname"+realname);
        String idnumber = request.getParameter("idnumber");
        String nickname = request.getParameter("nickname");
        String tel = request.getParameter("tel");
        String cartype = request.getParameter("cartype");
		//try {
			//cartype = new String(request.getParameter("cartype").getBytes("iso-8859-1") ,"GBK");
	    System.out.println("cartype"+cartype);
		String obdnumber = request.getParameter("obdnumber");
		if(registerService.askRegisterUser(email, password, realname, idnumber, nickname, tel, cartype, obdnumber)){
			return new ModelAndView("YY_LoginPage");
		}
		//model.put("email", email);
		//model.put("password_message", "√‹¬ÎªÚ” œ‰¥ÌŒÛ£¨«Î÷ÿ–¬ ‰»Î");
		//} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		return new ModelAndView("YY_RegisterPage");
 	}

}
