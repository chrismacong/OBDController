package com.fix.obd.web.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fix.obd.web.service.SOSMobileService;

@Controller
@RequestMapping("/sosmobile")
public class SOSMobileControl {
	@Resource
	private SOSMobileService sosMobileService;
	public SOSMobileService getSosMobileService() {
		return sosMobileService;
	}
	public void setSosMobileService(SOSMobileService sosMobileService) {
		this.sosMobileService = sosMobileService;
	}
	@RequestMapping(value = "/publishnativesos", method = RequestMethod.GET)
	public void publishNativeSOS(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		JSONObject jsonObject = new JSONObject(); 
		String result = "0";
		try {
			String email = request.getParameter("email");
			String info = request.getParameter("info");
			info=new String(info.getBytes("ISO8859-1"),"UTF-8");
			Map map = sosMobileService.publishNativeSOSMessage(email, info);
			result = (String) map.get("result");
			if("4".equals(result)){
				int mid = (int) map.get("mid");
				jsonObject.put("mid", mid);
			}
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
	@RequestMapping(value = "/publishmembersos", method = RequestMethod.GET)
	public void publishMemberSOS(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		JSONObject jsonObject = new JSONObject(); 
		String result = "0";
		try {
			String email = request.getParameter("email");
			String bid = request.getParameter("bid");
			String info = request.getParameter("info");
			info=new String(info.getBytes("ISO8859-1"),"UTF-8");
			Map map = sosMobileService.publishMemberSOSMessage(email, bid, info);
			result = (String) map.get("result");
			if("5".equals(result)){
				int mid = (int) map.get("mid");
				int rid = (int) map.get("rid");
				jsonObject.put("mid", mid);
				jsonObject.put("rid", rid);
			}
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
	@RequestMapping(value = "/giveupbroadcastsos", method = RequestMethod.GET)
	public void giveUpBroadcastSOS(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		JSONObject jsonObject = new JSONObject(); 
		try {
			String mid = request.getParameter("mid");
			boolean result = sosMobileService.confirmGiveup(mid);
			jsonObject.put("result", result);
			response.getWriter().write(jsonObject.toString());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/sosfailed", method = RequestMethod.GET)
	public void sosFailed(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		JSONObject jsonObject = new JSONObject(); 
		try {
			String mid = request.getParameter("mid");
			String info = request.getParameter("info");
			info=new String(info.getBytes("ISO8859-1"),"UTF-8");
			boolean result = sosMobileService.confirmFailed(mid, info);
			jsonObject.put("result", result);
			response.getWriter().write(jsonObject.toString());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/confirmcompletesos", method = RequestMethod.GET)
	public void confirmCompleteSOS(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		JSONObject jsonObject = new JSONObject(); 
		try {
			String rid = request.getParameter("rid");
			boolean result = sosMobileService.confirmCompleted(rid);
			jsonObject.put("result", result);
			response.getWriter().write(jsonObject.toString());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/choosebusiness", method = RequestMethod.GET)
	public void chooseBusiness(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		JSONObject jsonObject = new JSONObject(); 
		try {
			String mid = request.getParameter("mid");
			String bid = request.getParameter("bid");
			Map map = sosMobileService.nativeUserChooseBusiness(mid, bid);
			String result = (String) map.get("result");
			if("2".equals(result)){
				int rid = (int) map.get("rid"); 
				jsonObject.put("rid",rid);
			}
			jsonObject.put("result", result);
			response.getWriter().write(jsonObject.toString());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
