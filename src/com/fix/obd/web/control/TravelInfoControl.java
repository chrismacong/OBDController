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
import com.fix.obd.web.model.TravelInfo;
import com.fix.obd.web.model.util.OBDSeperateUtilModel;
import com.fix.obd.web.service.TravelInfoService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/travelinfo") 
public class TravelInfoControl {
	private static final Logger logger = LoggerFactory.getLogger(TravelInfoControl.class);
	@Resource
	private TravelInfoService travelInfoService;
	public TravelInfoService getTravelInfoService() {
		return travelInfoService;
	}
	public void setTravelInfoService(TravelInfoService travelInfoService) {
		this.travelInfoService = travelInfoService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------travel info:" + terminalId + "--------");
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("terminalId", terminalId);
		TravelInfo latest_data = travelInfoService.getTravelInfo(terminalId);
		ArrayList<OBDSeperateUtilModel> list = new ArrayList<OBDSeperateUtilModel>();
		if(latest_data!=null){
			String[] seperates = latest_data.getInfo().substring(0,latest_data.getInfo().lastIndexOf("@")).split("@");
			for(int i=0;i<seperates.length;i++){
				if(seperates[i]!=null&&!"".equals(seperates[i])){
					System.out.println(seperates[i]);
					OBDSeperateUtilModel o = new OBDSeperateUtilModel();
					String[] character_sentense = seperates[i].split(";");
					o.setName(character_sentense[0]);
					if(character_sentense.length>1)
						o.setContent(character_sentense[1]);
					if(character_sentense.length>2)
						o.setExtra(character_sentense[2]);
					list.add(o);
				}
			}
			model.put("travelinfo_date", latest_data.getDate());
		}
		model.put("character_list", list);
		return new ModelAndView("TravelInfoPage",model);
	}
	@RequestMapping(value = "/askLatestTravelInfo", method = RequestMethod.GET)
	public void askLatestTravelInfo(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		try {
			logger.debug("--------askLatestTravelInfo--------");
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			boolean temp = travelInfoService.askLastestTravelInfo(terminalId);
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
	@RequestMapping(value = "/askTravelInfo", method = RequestMethod.GET)
	public void askTravelInfo(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		try {
			logger.debug("--------askLatestTravelInfo--------");
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			String index = request.getParameter("index");
			boolean temp = travelInfoService.askTravelInfo(terminalId, index);
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
