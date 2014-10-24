package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
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

import com.fix.obd.protocol.ODBProtocol;
import com.fix.obd.util.IdPropertiesUtil;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.model.OBDLogs;
import com.fix.obd.web.service.TerminalLogService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/terminallog") 
public class TerminalLogControl {
	private static final Logger logger = LoggerFactory.getLogger(TerminalInfoControl.class);
	@Autowired
	private TerminalLogService terminalLogService;
	public TerminalLogService getTerminalLogService() {
		return terminalLogService;
	}
	public void setTerminalLogService(TerminalLogService terminalLogService) {
		this.terminalLogService = terminalLogService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------terminal log:" + terminalId + "--------");
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("terminalId", terminalId);
		List<OBDLogs> logs_list = terminalLogService.getTerminalLogsById(terminalId,pageSize);
		model.put("logs_list", logs_list);
		model.put("page_size",pageSize);
		return new ModelAndView("OBDLogPage",model);
	}
	@RequestMapping(value = "/removeall", method = RequestMethod.GET)
	public ModelAndView removeall(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		Map<String,Object> model = new HashMap<String,Object>();		
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------remove all logs of " + terminalId + "--------");
		terminalLogService.deleteAllLogsById(terminalId);
		model.put("terminalId", terminalId);
		return new ModelAndView("redirect:/terminallog.html?terminalId=" + terminalId + "&pageSize=50",model);
	}
	@RequestMapping(value = "/analysis", method = RequestMethod.GET)
	public void analysis(HttpServletRequest request,HttpServletResponse response){
		try {
			String logId = request.getParameter("logId");
			String logMessage = terminalLogService.getLogDetailMessage(logId);
			if(logMessage!=null&&!"".equals(logMessage)){
				String operationId = logMessage.substring(26,30);
				IdPropertiesUtil p = new IdPropertiesUtil();
				String propertyName = p.getProtocolById(operationId);
				if(propertyName!=null&&!"".equals(propertyName)){
					Constructor con = Class.forName("com.fix.obd.protocol.impl." + propertyName).getConstructor(String.class);
					if(logMessage!=null&&!"".equals(logMessage)){
						ODBProtocol odbProtocol = (ODBProtocol) con.newInstance(logMessage);
						odbProtocol.DBOperation(false);
						String divStr = odbProtocol.getStrForDiv();
						System.out.println(divStr);
						PrintWriter pw = null;
						pw=response.getWriter();
						JSONObject jsonObject = new JSONObject(); 
						jsonObject.put("divStr", divStr);
						jsonObject.put("success", "true");
						pw.print(jsonObject.toString());
						pw.flush();
						pw.close();
					}
					else{
						PrintWriter pw = null;
						pw=response.getWriter();
						JSONObject jsonObject = new JSONObject(); 
						jsonObject.put("divStr", "");
						jsonObject.put("success", "true");
						pw.print(jsonObject.toString());
						pw.flush();
						pw.close();
					}
				}
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
