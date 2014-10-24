package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
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
		
//		System.out.println("I'm here .password: "+password);
//		System.out.println(email);
		String realname = request.getParameter("realname");
//		System.out.println("realname"+realname);
        String idnumber = request.getParameter("idnumber");
        String nickname = request.getParameter("nickname");
        String tel = request.getParameter("tel");
        String cartype = request.getParameter("cartype");
		
//	    System.out.println("cartype"+cartype);
		String obdnumber = request.getParameter("obdnumber");
        String carnumber = request.getParameter("carnumber");
		if(registerService.askRegisterUser(email, password, realname, idnumber, nickname, tel, cartype, obdnumber, carnumber)){
			Cookie user = new Cookie("user", email + "-" + password);
            user.setMaxAge(0);
            user.setPath("/"); 
            response.addCookie(user);
            session.setAttribute("email",email);
			return new ModelAndView(new RedirectView("/OBDController/login.html"));
		}
		else{
			model.put("register_message", "” œ‰“—◊¢≤·£¨«Î÷ÿ–¬ ‰»Î∆‰À˚” œ‰µÿ÷∑£°");
			System.out.println("” œ‰“—◊¢≤·");
			return new ModelAndView(new RedirectView("/OBDController/register.html"), model);
		}
		
 	}
	
	@RequestMapping(value = "/check_business_register", method = RequestMethod.GET)
	public void business_register(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String user_tel = request.getParameter("user_tel");
			String bName = request.getParameter("bName");
			bName=new String(bName.getBytes("ISO8859-1"),"UTF-8");
			String address = request.getParameter("address");
			address=new String(address.getBytes("ISO8859-1"),"UTF-8");
			String latitude = request.getParameter("latitude");
			String longitute = request.getParameter("longitute");
			String tel = request.getParameter("tel");
			String baktel = request.getParameter("baktel");
			String introduction = request.getParameter("introduction");
			introduction=new String(introduction.getBytes("ISO8859-1"),"UTF-8");
			String bmemberInfo = request.getParameter("bmemberInfo");
			bmemberInfo=new String(bmemberInfo.getBytes("ISO8859-1"),"UTF-8");
			String bphotoPath = "Nil";
			String responseContext = "false";
			if(registerService.askRegisterBusinessUser(email, password, user_tel, bName, 
					address, longitute, latitude, tel, baktel, introduction, bmemberInfo, bphotoPath))
				responseContext = "true";
			out.println(responseContext);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/check_email", method = RequestMethod.GET)
	public void checkEmail(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String responseContext = "true";
		if(!registerService.askRegisterEmail(email)){
			responseContext = "false";
		}
		out.println(responseContext);
		out.flush();
		out.close();
	}
	
	@RequestMapping(value = "/check_tel", method = RequestMethod.GET)
	public void checkTel(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String tel = request.getParameter("tel");
		String responseContext = "true";
		if(!registerService.askRegisterTel(tel)){
			responseContext = "false";
		}
		System.out.println("responseContext:"+responseContext);
		out.println(responseContext);
		out.flush();
		out.close();
	}
	
	@RequestMapping(value = "/check_bname", method = RequestMethod.GET)
	public void checkBname(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String bname = request.getParameter("bname");
		bname=new String(bname.getBytes("ISO8859-1"),"UTF-8");
		String responseContext = "true";
		if(!registerService.askBusinessRegisterByName(bname)){
			responseContext = "false";
		}
		System.out.println("responseContext:"+responseContext);
		out.println(responseContext);
		out.flush();
		out.close();
	}

}
