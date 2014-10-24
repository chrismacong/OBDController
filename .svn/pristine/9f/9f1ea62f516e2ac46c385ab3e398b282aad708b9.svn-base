package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.fix.obd.web.model.DTCDefect;
import com.fix.obd.web.model.OBDDefect;
import com.fix.obd.web.model.util.FaultCodeIterator;
import com.fix.obd.web.service.DTCService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/dtc")
public class DTCControl {
	private static final Logger logger = LoggerFactory.getLogger(DTCControl.class);
	@Resource
	private DTCService dtcService;
	public DTCService getDtcService() {
		return dtcService;
	}
	public void setDtcService(DTCService dtcService) {
		this.dtcService = dtcService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		logger.debug("--------dtc--------");
		Map<String,Object> model = new HashMap<String,Object>();
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		boolean isACCOn = dtcService.isACCOn(terminalId);
		DTCDefect DTCDefect = dtcService.getDTCDefect(terminalId);
		OBDDefect OBDDefect = dtcService.getOBDDefect(terminalId);
		ArrayList<FaultCodeIterator> list = new ArrayList<FaultCodeIterator>();
		if(DTCDefect!=null){
			model.put("DTCDefect", DTCDefect.getInfo());
			model.put("DTC_date", DTCDefect.getDate());
			list = dtcService.getFaultCodeIteratorList(DTCDefect.getInfo());
		}
		if(OBDDefect!=null){
			model.put("DTC_status_date",OBDDefect.getDate());
			model.put("OBDDefect", OBDDefect.getInfo());
		}
		model.put("fault_code_list", list);
		model.put("isACCOn", isACCOn);
		model.put("terminalId", terminalId);
		return new ModelAndView("DTCPage",model);
	}
	@RequestMapping(value = "/askDTC", method = RequestMethod.GET)
	public void askDTC(HttpServletRequest request,HttpServletResponse response){
		try {
			logger.debug("--------askDTC--------");
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			boolean temp = dtcService.askDTCDefect(terminalId);
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("terminalId", terminalId);
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
	@RequestMapping(value = "/askDTCStatus", method = RequestMethod.GET)
	public void askDTCStatus(HttpServletRequest request,HttpServletResponse response){
		try {
			logger.debug("--------askDTCStatus--------");
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			boolean temp = dtcService.askOBDDefect(terminalId);
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("terminalId", terminalId);
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
	@RequestMapping(value = "/askClearDTC", method = RequestMethod.GET)
	public void askClearDTC(HttpServletRequest request,HttpServletResponse response){
		try {
			logger.debug("--------askClearDTC--------");
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			boolean temp = dtcService.askClearDTC(terminalId);
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("terminalId", terminalId);
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
