package com.fix.obd.web.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.service.TerminalInfoService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/terminalinfo") 
public class TerminalInfoControl {
	private static final Logger logger = LoggerFactory.getLogger(TerminalInfoControl.class);
	@Autowired
	private TerminalInfoService terminalInfoService;
	public TerminalInfoService getTerminalInfoService() {
		return terminalInfoService;
	}
	public void setTerminalInfoService(TerminalInfoService terminalInfoService) {
		this.terminalInfoService = terminalInfoService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------terminal info:" + terminalId + "--------");
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("terminalId", terminalId);
		OBDTerminalInfo info = terminalInfoService.getTerminalInfo(terminalId);
		String lastUpdateDate = terminalInfoService.getLastUpdateDate(terminalId);
		model.put("info",info);
		model.put("lastUpdateDate", lastUpdateDate);
		return new ModelAndView("TerminalInfoPage",model);
	}
}
