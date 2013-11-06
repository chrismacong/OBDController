package com.fix.obd.web.control;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.service.AGPSService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/agps") 
public class AGPSControl {
	private static final Logger logger = LoggerFactory.getLogger(AGPSControl.class);
	@Resource
	private AGPSService agpsService;
	public AGPSService getAgpsService() {
		return agpsService;
	}
	public void setAgpsService(AGPSService agpsService) {
		this.agpsService = agpsService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		logger.debug("--------agps--------");
		Map<String,Object> model = new HashMap<String,Object>();
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		model.put("terminalId", terminalId);
		return new ModelAndView("AGPSPage",model);
	}
	@RequestMapping(value = "/sendagps", method = RequestMethod.GET)
	public ModelAndView sendagps(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> model = new HashMap<String,Object>();
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		model.put("terminalId", terminalId);
		agpsService.askSendAGPS(terminalId);
		return new ModelAndView("AGPSPage",model);
 	}
	@RequestMapping(value = "/checkagps", method = RequestMethod.GET)
	public ModelAndView checkagps(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> model = new HashMap<String,Object>();
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		model.put("terminalId", terminalId);
		agpsService.askCheckAGPS(terminalId);
		return new ModelAndView("AGPSPage",model);
	}
}
