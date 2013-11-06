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
import com.fix.obd.web.service.UpdateConfigService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/updateconfig") 
public class UpdateConfigControl {
	private static final Logger logger = LoggerFactory.getLogger(UpdateConfigControl.class);
	@Resource
	private UpdateConfigService updateConfigService;
	public UpdateConfigService getUpdateConfigService() {
		return updateConfigService;
	}
	public void setUpdateConfigService(UpdateConfigService updateConfigService) {
		this.updateConfigService = updateConfigService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------updateconfig:" + terminalId + "--------");
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("terminalId", terminalId);
		return new ModelAndView("UpdateConfigPage",model);
	}
	@RequestMapping(value = "/configserver", method = RequestMethod.GET)
	public void configserver(HttpServletRequest request,HttpServletResponse response){
		try {
			logger.debug("--------configserver--------");
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			String selected_index = request.getParameter("selected_index");
			String server_port = request.getParameter("server_port");
			String server_ip = request.getParameter("server_ip");
			String server_apn = request.getParameter("server_apn");
			boolean temp = updateConfigService.askUpdate(terminalId, selected_index, server_port, server_ip, server_apn);
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
