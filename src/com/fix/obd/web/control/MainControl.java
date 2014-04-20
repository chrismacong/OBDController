package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import com.fix.obd.web.service.MainService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/main") 
public class MainControl {
	private static final Logger logger = LoggerFactory.getLogger(MainControl.class);
	@Autowired
	private MainService mainService;
	public MainService getMainService() {
		return mainService;
	}
	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpSession session){
		logger.debug("--------main--------");
		Map<String,Object> model = new HashMap<String,Object>();
		List<String> onlineId_list = mainService.getOnlineTerminals();
		String list = "";
		for(int i=0;i<onlineId_list.size();i++)
			list += onlineId_list.get(i) + ";";
		if(onlineId_list.size()!=0)
			list = list.substring(0,list.lastIndexOf(";"));
		Object email = session.getAttribute("email");
		if(email!=null)
			model.put("useremail",email.toString());
		model.put("onlineId_list", list);
		int online_terminal_refresh_minute = mainService.getTerminalOnlineRefreshMinute();
		model.put("online_terminal_refresh_minute",online_terminal_refresh_minute);
		return new ModelAndView("OBDMainPage",model);
	}
	@RequestMapping(value = "/getUpdate", method = RequestMethod.GET)
	public void getUpdate(HttpServletRequest request,HttpServletResponse response){
		try {
			logger.debug("-----getUpdate-----");
			response.setCharacterEncoding("utf-8");
			List<String> onlineId_list = mainService.getOnlineTerminals();
			String list = "";
			for(int i=0;i<onlineId_list.size();i++)
				list += onlineId_list.get(i) + ";";
			if(onlineId_list.size()!=0)
				list = list.substring(0,list.lastIndexOf(";"));
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			jsonObject.put("newlist", list);
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
