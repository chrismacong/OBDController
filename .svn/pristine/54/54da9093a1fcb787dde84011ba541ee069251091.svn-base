package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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

import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.service.OtherService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/other")
public class OtherFuncControl {
	private static final Logger logger = LoggerFactory.getLogger(OtherFuncControl.class);
	@Resource
	private OtherService otherService;
	public OtherService getOtherService() {
		return otherService;
	}
	public void setOtherService(OtherService otherService) {
		this.otherService = otherService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		logger.debug("--------other--------");
		Map<String,Object> model = new HashMap<String,Object>();
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		model.put("terminalId", terminalId);
		return new ModelAndView("OtherFuncPage",model);
	}
	@RequestMapping(value = "/call", method = RequestMethod.GET)
	public ModelAndView call(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> model = new HashMap<String,Object>();
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		String interval = request.getParameter("interval");
		String times = request.getParameter("times");
		model.put("terminalId", terminalId);
		otherService.askRollCall(terminalId, interval, times);
		return new ModelAndView("OtherFuncPage",model);
 	}
	@RequestMapping(value = "/listen", method = RequestMethod.GET)
	public void listen(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			String phone = request.getParameter("phone");
			model.put("terminalId", terminalId);
			boolean temp = otherService.askListen(terminalId, phone);
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
	@RequestMapping(value = "/reboot", method = RequestMethod.GET)
	public void reboot(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			model.put("terminalId", terminalId);
			boolean temp = otherService.askReboot(terminalId);
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/installposition", method = RequestMethod.GET)
	public void installposition(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			model.put("terminalId", terminalId);
			boolean temp = otherService.askInstallPosition(terminalId);
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/clearblind", method = RequestMethod.GET)
	public void clearblind(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			model.put("terminalId", terminalId);
			boolean temp = otherService.askClearBlind(terminalId);
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/arm", method = RequestMethod.GET)
	public void arm(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			model.put("terminalId", terminalId);
			boolean temp = otherService.askArm(terminalId);
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/disarm", method = RequestMethod.GET)
	public void disarm(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			model.put("terminalId", terminalId);
			boolean temp = otherService.askDisarm(terminalId);
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/restore", method = RequestMethod.GET)
	public void restore(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			model.put("terminalId", terminalId);
			boolean temp = otherService.askRestore(terminalId);
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
