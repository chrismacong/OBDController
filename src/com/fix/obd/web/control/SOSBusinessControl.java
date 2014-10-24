package com.fix.obd.web.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fix.obd.web.model.util.SOSMessageBroadcastWithExtra;
import com.fix.obd.web.model.util.SOSMessageDealWithExtra;
import com.fix.obd.web.service.SOSBusinessService;

@Controller
@RequestMapping("/sosbusiness")
public class SOSBusinessControl {
	@Resource
	private SOSBusinessService sosBusinessService;

	public SOSBusinessService getSosBusinessService() {
		return sosBusinessService;
	}

	public void setSosBusinessService(SOSBusinessService sosBusinessService) {
		this.sosBusinessService = sosBusinessService;
	}
	@RequestMapping(value = "/getbroadcastsos", method = RequestMethod.GET)
	public void getNativeSOS(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		JSONArray jsonArray = new JSONArray(); 
		try {
			String bid = request.getParameter("bid");
			List<SOSMessageBroadcastWithExtra> list = sosBusinessService.getBroadcastSOSMessage(bid);
			jsonArray = JSONArray.fromObject(list);
			response.getWriter().write(jsonArray.toString());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@RequestMapping(value = "/getdealsos", method = RequestMethod.GET)
	public void getDealSOS(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		JSONArray jsonArray = new JSONArray(); 
		try {
			String bid = request.getParameter("bid");
			List<SOSMessageDealWithExtra> list = sosBusinessService.getDealSOSMessage(bid);
			jsonArray = JSONArray.fromObject(list);
			response.getWriter().write(jsonArray.toString());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@RequestMapping(value = "/grabsos", method = RequestMethod.GET)
	public void grabSOS(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		JSONObject jsonObject = new JSONObject(); 
		try {
			String rid = request.getParameter("rid");
			boolean result = sosBusinessService.grabBroadcastSOSMessage(rid);
			jsonObject.put("result", result);
			response.getWriter().write(jsonObject.toString());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
	@RequestMapping(value = "/giveupbroadcastsos", method = RequestMethod.GET)
	public void giveUpSOS(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		JSONObject jsonObject = new JSONObject(); 
		try {
			String rid = request.getParameter("rid");
			boolean result = sosBusinessService.giveUpBroadcastSOSMessage(rid);
			jsonObject.put("result", result);
			response.getWriter().write(jsonObject.toString());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
}
