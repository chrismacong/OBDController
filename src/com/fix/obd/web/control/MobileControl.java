package com.fix.obd.web.control;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.model.DTCDefect;
import com.fix.obd.web.service.DTCService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/mobile")
public class MobileControl {
	@Resource
	private DTCService dtcService;
	public DTCService getDtcService() {
		return dtcService;
	}
	public void setDtcService(DTCService dtcService) {
		this.dtcService = dtcService;
	}
	private static final Logger logger = LoggerFactory.getLogger(MobileControl.class);
	@RequestMapping(value = "/vehicleexm", method = RequestMethod.POST)
	public void vehicleexm(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		Map map = dtcService.askDTCDefectInRespond(terminalId);
		String result = "";
		boolean hasData = (boolean) map.get("hasData");
		boolean isActual = (boolean) map.get("isActual");
		if(!hasData){
			result += "0;0";
		}
		else if(isActual){
			result += "1;2;";
			DTCDefect dtc = (DTCDefect) map.get("dtc");
			result += dtc.getDate() + ";" + dtc.getInfo();
		}
		else{
			result += "1;1;";
			DTCDefect dtc = (DTCDefect) map.get("dtc");
			result += dtc.getDate() + ";" + dtc.getInfo();
		}
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
}
