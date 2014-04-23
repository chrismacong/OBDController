package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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

import com.fix.obd.web.service.PersonalService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/personal")
public class PersonalControl {
	private static final Logger logger = LoggerFactory.getLogger(PersonalControl.class);
	@Resource
	private PersonalService personalService;
	
	public PersonalService getPersonalService() {
		return personalService;
	}
	public void setPersonalService(PersonalService personalService) {
		this.personalService = personalService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		logger.debug("--------personal main--------");
		Map<String,Object> model = new HashMap<String,Object>();
		Object o = session.getAttribute("email");
		Object t = session.getAttribute("terminalId");
		if(o!=null&&t!=null){
			String useremail = o.toString();
			model.put("useremail", useremail);
			String terminalId = t.toString();
			model.put("terminalId",terminalId);
		}
		return new ModelAndView("PersonalMainPage",model);
	}
	
	@RequestMapping(value = "/refreshParameter", method = RequestMethod.GET)
	public void refreshParameter(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		try {
			logger.debug("--------refreshParameter--------");
			String useremail = request.getParameter("useremail");
			String parameter_name = new String(request.getParameter("parameter_name").getBytes("ISO8859-1"),"UTF-8");
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			double answer = personalService.getParameterResultByName(useremail, parameter_name);
			jsonObject.put("success", "true");
			jsonObject.put("answer", answer);
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
