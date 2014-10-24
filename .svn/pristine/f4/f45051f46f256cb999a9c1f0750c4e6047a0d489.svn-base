package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.service.UserService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
@Controller
@RequestMapping("/businessuserconfig") 
public class BusinessUserConfigControl {
	private static final Logger logger = LoggerFactory.getLogger(BusinessUserConfigControl.class);
	@Autowired
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpSession session){
		logger.debug("--------main--------");
		Map<String,Object> model = new HashMap<String,Object>();
		String id_list = userService.getBusinessUserId();
		JSONObject json = userService.getBusinessUserJSON();
		model.put("id_list", id_list);
		model.put("json_object", json);
		return new ModelAndView("BusinessUserConfigPage",model);
	}
	@RequestMapping(value = "/modifybusinessuser", method = RequestMethod.GET)
	public void modifyBusinessUser(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		try {
		String userid = request.getParameter("user_id");
		String email = request.getParameter("email");
		String realname = request.getParameter("realname");
		String tel = request.getParameter("tel");
		YY_User user = new YY_User();
		user.setId(Integer.parseInt(userid));
		user.setEmail(email);
		user.setRealname(new String(realname.getBytes("ISO8859-1"),"UTF-8"));
		user.setTel(tel);
		user.setNickname("Nil");
		user.setObdnumber("Nil");
		user.setCarnumber("Nil");
		user.setCartype("Nil");
		user.setIdnumber("Nil");
		boolean temp = userService.modifyUser(user);
		PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			else
				jsonObject.put("success", "false");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
	public void deleteUser(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		try {
			String selected_id = request.getParameter("selected_id");
			boolean temp = userService.deleteUser(selected_id);
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			else
				jsonObject.put("success", "false");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/addbusinessuser", method = RequestMethod.GET)
	public void addBusinessUser(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		try {
			String email = request.getParameter("email");
			String realname = request.getParameter("realname");
			String tel = request.getParameter("tel");
			YY_User user = new YY_User();
			user.setEmail(email);
			user.setRealname(new String(realname.getBytes("ISO8859-1"),"UTF-8"));
			user.setNickname("Nil");
			user.setObdnumber("Nil");
			user.setCarnumber("Nil");
			user.setCartype("Nil");
			user.setIdnumber("Nil");
			user.setTel(tel);
			user.setRole("business");
			user.setPassword("e10adc3949ba59abbe56e057f20f883e");
			boolean temp = userService.addUser(user);
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			else
				jsonObject.put("success", "false");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
