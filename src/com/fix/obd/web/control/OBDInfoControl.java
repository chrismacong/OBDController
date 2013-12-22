package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.fix.obd.web.model.OBDData;
import com.fix.obd.web.model.util.OBDSeperateUtilModel;
import com.fix.obd.web.service.OBDInfoService;
import com.fix.obd.web.service.PositionInfoService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/obdinfo") 
public class OBDInfoControl {
	private static final Logger logger = LoggerFactory.getLogger(OBDInfoControl.class);
	@Resource
	private OBDInfoService obdInfoService;
	public OBDInfoService getObdInfoService() {
		return obdInfoService;
	}
	public void setObdInfoService(OBDInfoService obdInfoService) {
		this.obdInfoService = obdInfoService;
	}
	@Resource
	private PositionInfoService positionInfoService;
	public PositionInfoService getPositionInfoService() {
		return positionInfoService;
	}
	public void setPositionInfoService(PositionInfoService positionInfoService) {
		this.positionInfoService = positionInfoService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------obd info:" + terminalId + "--------");
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("terminalId", terminalId);
		OBDData latest_data = obdInfoService.getLatestOBDData(terminalId);
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
			model.put("obddate", latest_data.getDate());
		}
		model.put("character_list", list);
		int obd_info_refresh_millisecond = obdInfoService.getOBDDataRefreshMinute();
		model.put("obd_info_refresh_millisecond", obd_info_refresh_millisecond);
		return new ModelAndView("OBDInfoPage",model);
	}
	@RequestMapping(value = "/askOBDInfo", method = RequestMethod.GET)
	public void askOBDInfo(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		try {
			logger.debug("--------askOBDInfo--------");
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			boolean temp = obdInfoService.askOBDInfo(terminalId);
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
	@RequestMapping(value = "/getpgraph", method = RequestMethod.GET)
	public ModelAndView getPGraph(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		Map<String,Object> model = new HashMap<String,Object>();		
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		model.put("terminalId", terminalId);
		List<OBDData> list = obdInfoService.getLatest10OBDInfo(terminalId);
		String date_list = "";
		for(int i=0;i<list.size();i++)
			date_list += list.get(i).getDate() + ",";
		date_list = date_list.substring(0,date_list.lastIndexOf(","));
		String pressure_list = "";
		for(int i=0;i<list.size();i++){
			String tempStr = list.get(i).getInfo().substring(list.get(i).getInfo().lastIndexOf("大气压"));
			tempStr = tempStr.split(";")[1];
			pressure_list += tempStr + ",";
		}
		pressure_list = pressure_list.substring(0,pressure_list.lastIndexOf(","));
		int graph_refresh_minute = positionInfoService.getGraphRefreshMinute();
		model.put("graph_refresh_minute", graph_refresh_minute);
		model.put("date_list", date_list);
		model.put("pressure_list", pressure_list);
		return new ModelAndView("PressureGraphPage",model);
	}
	@RequestMapping(value = "/getdgraph", method = RequestMethod.GET)
	public ModelAndView getDGraph(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		Map<String,Object> model = new HashMap<String,Object>();		
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		model.put("terminalId", terminalId);
		OBDData latest_data = obdInfoService.getLatestOBDData(terminalId);
		String str1 = "0";
		String str2 = "0";
		String str3 = "0";
		if(latest_data!=null){
			String info = latest_data.getInfo();
			if(info.lastIndexOf("燃油液位输入")>-1){
				str1 = info.substring(info.lastIndexOf("燃油液位输入"));
				str1 = str1.split(";")[1];
			}
			if(info.lastIndexOf("绝对节气门位置")>-1){
				str2 = info.substring(info.indexOf("绝对节气门位置"));
				str2 = str2.split(";")[1];
			}
			if(info.lastIndexOf("相对节气门位置")>-1){
				str3 = info.substring(info.indexOf("相对节气门位置"));
				str3 = str3.split(";")[1];
			}
		}
		model.put("oillevel", str1);
		model.put("absolute", str2);
		model.put("relative", str3);
		return new ModelAndView("DashBoardPage",model);
	}
	@RequestMapping(value = "/dashUpdate", method = RequestMethod.GET)
	public void bashUpdate(HttpServletRequest request,HttpServletResponse response){
		try {
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			OBDData latest_data = obdInfoService.getLatestOBDData(terminalId);
			String info = latest_data.getInfo();
			String str1 = "0";
			String str2 = "0";
			String str3 = "0";
			if(info.lastIndexOf("燃油液位输入")>-1){
				str1 = info.substring(info.lastIndexOf("燃油液位输入"));
				str1 = str1.split(";")[1];
			}
			if(info.lastIndexOf("绝对节气门位置")>-1){
				str2 = info.substring(info.indexOf("绝对节气门位置"));
				str2 = str2.split(";")[1];
			}
			if(info.lastIndexOf("相对节气门位置")>-1){
				str3 = info.substring(info.indexOf("相对节气门位置"));
				str3 = str3.split(";")[1];
			}
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			jsonObject.put("oillevel", str1);
			jsonObject.put("absolute", str2);
			jsonObject.put("relative", str3);
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
