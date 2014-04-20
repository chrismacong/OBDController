package com.fix.obd.web.control;

import java.io.UnsupportedEncodingException;
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

import com.fix.obd.web.service.YY_RegisterService;

@Controller
@RequestMapping("/register") 
public class YY_RegisterControl {

	@Resource
	private YY_RegisterService registerService;
	public YY_RegisterService getRegisterService() {
		return registerService;
	}
	public void setRegisterService(YY_RegisterService registerService) {
		this.registerService = registerService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		return new ModelAndView("YY_RegisterPage");
	}
	@RequestMapping(value = "/check_register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
		Map<String,Object> model = new HashMap<String,Object>();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("I'm here .password: "+password);
		System.out.println(email);
		String realname = request.getParameter("realname");
		System.out.println("realname"+realname);
        String idnumber = request.getParameter("idnumber");
        String nickname = request.getParameter("nickname");
        String tel = request.getParameter("tel");
        String cartype = request.getParameter("cartype");
		
	    System.out.println("cartype"+cartype);
		String obdnumber = request.getParameter("obdnumber");
        String carnumber = request.getParameter("carnumber");
		if(registerService.askRegisterUser(email, password, realname, idnumber, nickname, tel, cartype, obdnumber, carnumber)){
			Cookie user = new Cookie("user", email + "-" + password);
            user.setMaxAge(0);
            user.setPath("/"); 
            response.addCookie(user);
            session.setAttribute("email",email);
			return new ModelAndView(new RedirectView("/OBDController/login.html"));
		}else{
			model.put("register_message", "邮箱已注册，请重新输入其他邮箱地址！");
			System.out.println("邮箱已注册");
			return new ModelAndView(new RedirectView("/OBDController/register.html"), model);
		}
		
 	}

}
