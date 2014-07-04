package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fix.obd.util.CityNumPropertiesUtil;
import com.fix.obd.web.model.PositionData;
import com.fix.obd.web.model.util.TodayTravelReport;
import com.fix.obd.web.model.util.VehicleExmnationReport;
import com.fix.obd.web.service.PositionInfoService;
import com.fix.obd.web.service.TravelInfoService;
import com.fix.obd.web.service.VehicleExmnationService;
import com.fix.obd.web.service.YY_EditPasswordService;
import com.fix.obd.web.service.YY_LoginService;
import com.fix.obd.web.util.JSONHelper;
import com.fix.obd.web.util.MD5Util;
@Controller 
@RequestMapping("/login") 
public class YY_LogControl {
	private final String API_KEY = "15cf002106718ce6a60a7841ea39f127";
	//login
	@Resource
	private YY_LoginService loginService;
	public YY_LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(YY_LoginService loginService) {
		this.loginService = loginService;
	}
	@Resource
	private VehicleExmnationService vehicleExmnationService;
	public VehicleExmnationService getVehicleExmnationService() {
		return vehicleExmnationService;
	}
	public void setVehicleExmnationService(
			VehicleExmnationService vehicleExmnationService) {
		this.vehicleExmnationService = vehicleExmnationService;
	}
	@Resource
	private TravelInfoService travelInfoService;
	public TravelInfoService getTravelInfoService() {
		return travelInfoService;
	}
	public void setTravelInfoService(TravelInfoService travelInfoService) {
		this.travelInfoService = travelInfoService;
	}
	@Resource
	private PositionInfoService positionInfoService;
	public PositionInfoService getPositionInfoService() {
		return positionInfoService;
	}
	public void setPositionInfoService(PositionInfoService positionInfoService) {
		this.positionInfoService = positionInfoService;
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
	   model.put("password_message", "密码或邮箱错误，请重新输入");
	    //return new ModelAndView(new RedirectView("/OBDController/login.html"),model);
		return new ModelAndView(new RedirectView("/OBDController/login.html?email="+email+"&password_message=密码或邮箱错误，请重新输入"));
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
				
				System.out.println("记住密码！");
				 
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
	    model.put("password_message", "密码或邮箱错误，请重新输入");
	    pw.print(jsonObject.toString());
		pw.flush();
		pw.close();
		return ;
	    //return new ModelAndView(new RedirectView("/OBDController/login.html"),model);
		//return new ModelAndView(new RedirectView("/OBDController/login.html?email="+email+"&password_message=密码或邮箱错误，请重新输入"));
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
        deleteCookie.setMaxAge(0); //删除该Cookie
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
	@RequestMapping(value = "/mobilelogin", method = RequestMethod.POST)
	public void mobileLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 String email = request.getParameter("email");
		 String password = request.getParameter("pwd");
		 password = MD5Util.MD5(password);
		 String result = null;
	    if(loginService.askCheckUser(email,password)){
			 String terminalId = loginService.getTerminalIdByEmail(email);
			 if(terminalId==null){
				 terminalId = "未绑定设备";
			 }
   	        result = "1;";
			result = result + terminalId + ";";
			VehicleExmnationReport vp = vehicleExmnationService.getVehicleExmnationReport(terminalId);
			result += vp.getVehicle_exm_score() + ";";
			result += vp.getVehicle_exm_main_solution() + ";";
			PositionData pd = positionInfoService.getLatestPositionInfo(terminalId);
			
			String point_latitude = pd.getInfo().substring(pd.getInfo().lastIndexOf("纬度:"));
			point_latitude = point_latitude.substring(0,point_latitude.indexOf(";"));
			point_latitude = point_latitude.split(":")[1];
			point_latitude = point_latitude.replaceAll("\\.", "");
			point_latitude = point_latitude.replaceAll("°", ".");
			String tempStrPart = point_latitude.split("\\.")[1];
			tempStrPart = "0." + tempStrPart;
			double tempD = Double.parseDouble(tempStrPart)/60*100;
			point_latitude = Integer.parseInt(point_latitude.split("\\.")[0]) + tempD + "";
			
			String point_longitute = pd.getInfo().substring(pd.getInfo().lastIndexOf("经度:"));
			point_longitute = point_longitute.substring(0,point_longitute.indexOf(";"));
			point_longitute = point_longitute.split(":")[1];
			point_longitute = point_longitute.replaceAll("\\.", "");
			point_longitute = point_longitute.replaceAll("°", ".");
			String _tempStrPart = "0." + point_longitute.split("\\.")[1];
			double _tempD = Double.parseDouble(_tempStrPart)/60*100;
			point_longitute = Integer.parseInt(point_longitute.split("\\.")[0]) + _tempD + "";
			String point = point_longitute + "," + point_latitude;
			String city = "";
			String cityNum = "";
			try {
				String point_str_for_api = "http://api.map.baidu.com/geoconv/v1/?coords=" + point + "&from=1&to=5&ak=" + API_KEY;
				org.json.JSONObject json1 = JSONHelper.readJsonFromUrl(point_str_for_api);
				String point_after = (String) (json1.getJSONArray("result").getString(0));
				org.json.JSONObject json_after = new org.json.JSONObject(point_after);
				String _x = json_after.getString("x");
				String _y = json_after.getString("y");
				String _str = _y + "," + _x;
				String point_str2_for_api =  "http://api.map.baidu.com/geocoder?location=" + _str + "&output=json&ak=" + API_KEY;
				org.json.JSONObject json2 = JSONHelper.readJsonFromUrl(point_str2_for_api);
				String address_after = (String) (json2.getString("result"));
				String temp_str = address_after.split("\"city\":\"")[1];
				city = temp_str.substring(0,temp_str.indexOf("\""));
				city = city.replaceAll("市", "");
				CityNumPropertiesUtil p = new CityNumPropertiesUtil();
				cityNum = p.getCityNumByCity(city);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TodayTravelReport ttp = travelInfoService.getTodayTravelReport(terminalId);
			result += ttp.buildReportStr() + ";";
			result += city + ";";
			result += cityNum;
		}else{
			result = "0;null";
		}
	    try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
	@RequestMapping(value = "/login_check_by_tel", method = RequestMethod.GET)
	public void loginByTel(HttpServletRequest request,HttpServletResponse response,HttpSession session){
			try {
			 Map<String,Object> model = new HashMap<String,Object>();
			 String tel = request.getParameter("tel");
			 String password = request.getParameter("password");
			 String rememberMe = request.getParameter("rememberMe");
			 System.out.println("rememberMe:"+rememberMe);
			 PrintWriter pw = null;
			 pw=response.getWriter();
			 JSONObject jsonObject = new JSONObject(); 
			if(loginService.askCheckUserByTel(tel,password)){
				String email = loginService.getEmail(tel);
				String rolename = loginService.getRoleName(email);
				session.setAttribute("email",email);
				session.setAttribute("rolename", rolename);
				String terminalId = loginService.getTerminalIdByEmail(email);
				 if(terminalId!=null)
					    session.setAttribute("terminalId", terminalId);
				 else
					 	session.setAttribute("terminalId","00000000000000000000");
				if(rememberMe.equals("on")){
					System.out.println("记住密码！");
					 Cookie emailCookie=new Cookie("email",email);
					    emailCookie.setMaxAge(30 * 24 * 60 * 60); 
				        emailCookie.setPath("/");
				        response.addCookie(emailCookie);
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
					  System.out.println("444444");
					  return;
				    	
				 }else if(rolename.equals("member")){
					  jsonObject.put("success", "true");
					  jsonObject.put("rolename", "member");
				      session.setAttribute("rolename", "loggedmember");
				      pw.print(jsonObject.toString());
				 	  pw.flush();
				 	  pw.close();
				 	  System.out.println("333333");
					
				 	  return;
				    	
				 }
				}catch(NullPointerException e){
					
				}
				
			
			}
			
			jsonObject.put("success", "false");
			model.put("tel", tel);
		    model.put("password_message", "密码或手机号错误，请重新输入");
		    pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
			return ;
		    //return new ModelAndView(new RedirectView("/OBDController/login.html"),model);
			//return new ModelAndView(new RedirectView("/OBDController/login.html?email="+email+"&password_message=密码或邮箱错误，请重新输入"));
			//return new ModelAndView("YY_LoginPage",model);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
	}


}
