package com.fix.obd.web.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fix.obd.web.model.Business;
import com.fix.obd.web.service.BusinessService;
import com.fix.obd.web.service.YY_LoginService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/business")
public class BusinessControl {
	private static final Logger logger = LoggerFactory.getLogger(BusinessControl.class);
	@Resource
	private BusinessService businessService;
	public BusinessService getBusinessService() {
		return businessService;
	}
	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}
	@Resource
	private YY_LoginService yy_loginService;
	public YY_LoginService getYy_loginService() {
		return yy_loginService;
	}
	public void setYy_loginService(YY_LoginService yy_loginService) {
		this.yy_loginService = yy_loginService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		logger.debug("--------business--------");
		Map<String,Object> model = new HashMap<String,Object>();
		String bid = (String)session.getAttribute("bid");
		String email = (String) session.getAttribute("email");
		String uid_str = yy_loginService.getBusinessIdByEmail(email);
		Business business = businessService.getBusinessByBid(Integer.parseInt(uid_str));
		session.setAttribute("uid", uid_str);
		if(business!=null){
			model.put("bid", bid);
			model.put("business", business);
		}
		return new ModelAndView("BusinessTestPage",model);
	}
	@RequestMapping(value = "/modifybusinessinfo", method = RequestMethod.GET)
	public void modifyBusinessInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		JSONObject jsonObject = new JSONObject(); 
		String result = "0";
		try {
			String bid = request.getParameter("bid");
			String tel = request.getParameter("tel");
			String baktel = request.getParameter("baktel");
			String introduction = request.getParameter("introduction");
			introduction=new String(introduction.getBytes("ISO8859-1"),"UTF-8");
			String bMemberInfo = request.getParameter("bmemberInfo");
			bMemberInfo=new String(bMemberInfo.getBytes("ISO8859-1"),"UTF-8");
			Business business = businessService.getBusinessByBid(Integer.parseInt(bid));
			business.setTel(tel);
			business.setBaktel(baktel);
			business.setIntroduction(introduction);
			business.setBmemberinfo(bMemberInfo);
			if(businessService.updateBusiness(business))
				result = "1";
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			jsonObject.put("result", result);
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/checkbusinesslogin", method = RequestMethod.GET)
	public void checkBusinessLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		JSONObject jsonObject = new JSONObject(); 
		try {
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			String result = businessService.checkBusinessUser(email, pwd);
			jsonObject.put("result", result);
			if("2".equals(result)){
				String uid_str = yy_loginService.getBusinessIdByEmail(email);
				Business business = businessService.getBusinessByBid(Integer.parseInt(uid_str));
				jsonObject.put("bid", uid_str);
				jsonObject.put("bname", business.getBname());
				jsonObject.put("address", business.getAddress());
				jsonObject.put("blng", business.getLongitute());
				jsonObject.put("blat", business.getLatitude());
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getbusinessinfo", method = RequestMethod.GET)
	public void getBusinessInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		JSONObject jsonObject = new JSONObject(); 
		try {
			String bid = request.getParameter("bid");
			Business business = businessService.getBusinessByBid(Integer.parseInt(bid));
			jsonObject.put("bname", business.getBname());
			jsonObject.put("address", business.getAddress());
			jsonObject.put("blng", business.getLongitute());
			jsonObject.put("blat", business.getLatitude());
			jsonObject.put("tel", business.getTel());
			jsonObject.put("baktel", business.getBaktel());
			jsonObject.put("introduction", business.getIntroduction());
			jsonObject.put("bmemberinfo", business.getBmemberinfo());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
