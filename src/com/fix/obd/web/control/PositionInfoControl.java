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

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fix.obd.util.CityNumPropertiesUtil;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.model.OBDData;
import com.fix.obd.web.model.PositionData;
import com.fix.obd.web.model.util.PositionSeperateUtilModel;
import com.fix.obd.web.model.util.TodayTravelReport;
import com.fix.obd.web.model.util.VehicleExmnationReport;
import com.fix.obd.web.service.PositionInfoService;
import com.fix.obd.web.util.JSONHelper;
import com.fix.obd.web.util.MD5Util;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/positioninfo") 
public class PositionInfoControl {
	private static final Logger logger = LoggerFactory.getLogger(PositionInfoControl.class);
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
		logger.debug("--------position info:" + terminalId + "--------");
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("terminalId", terminalId);
		PositionData latest_data = positionInfoService.getLatestPositionInfo(terminalId);
		ArrayList<PositionSeperateUtilModel> alert_list = new ArrayList<PositionSeperateUtilModel>();
		ArrayList<PositionSeperateUtilModel> list = new ArrayList<PositionSeperateUtilModel>();
		if(latest_data!=null){
			String temp_str = latest_data.getInfo().substring(0,latest_data.getInfo().lastIndexOf(";"));
			String[] strs = temp_str.split(";");
			for(int i=0;i<strs.length;i++){
				PositionSeperateUtilModel p = new PositionSeperateUtilModel();
				p.setName(strs[i].split(":")[0]);
				p.setContent(strs[i].split(":")[1]);
				list.add(p);
			}
			temp_str = latest_data.getAlert().substring(0,latest_data.getAlert().lastIndexOf(";"));
			String[] alert_strs = temp_str.split(";");
			for(int i=0;i<alert_strs.length;i++){
				PositionSeperateUtilModel p = new PositionSeperateUtilModel();
				p.setName(alert_strs[i].split(":")[0]);
				p.setContent(alert_strs[i].split(":")[1]);
				alert_list.add(p);
			}
			model.put("positiondate", latest_data.getDate());
		}
		model.put("character_list", list);
		model.put("alert_list",alert_list);
		int position_info_refresh_millisecond = positionInfoService.getPositionDataRefreshMinute();
		model.put("position_info_refresh_millisecond", position_info_refresh_millisecond);
		return new ModelAndView("PositionInfoPage",model);
	}
	@RequestMapping(value = "/getsgraph", method = RequestMethod.GET)
	public ModelAndView getSGraph(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		Map<String,Object> model = new HashMap<String,Object>();		
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		model.put("terminalId", terminalId);
		List<PositionData> list = positionInfoService.getLatest10PostionInfo(terminalId);
		if(list.size()>0){
			String date_list = "";
			for(int i=0;i<list.size();i++)
				date_list += list.get(i).getDate() + ",";
			date_list = date_list.substring(0,date_list.lastIndexOf(","));
			String speed_list = "";
			for(int i=0;i<list.size();i++){
				String tempStr = list.get(i).getInfo().substring(list.get(i).getInfo().lastIndexOf("OBD�ٶ�:"));
				tempStr = tempStr.substring(0,tempStr.lastIndexOf("km/h"));
				tempStr = tempStr.split(":")[1];
				speed_list += tempStr + ",";
			}
			speed_list = speed_list.substring(0,speed_list.lastIndexOf(","));
			model.put("date_list", date_list);
			model.put("speed_list", speed_list);
		}
		int graph_refresh_minute = positionInfoService.getGraphRefreshMinute();
		model.put("graph_refresh_minute", graph_refresh_minute);
		return new ModelAndView("SpeedGraphPage",model);
	}
	@RequestMapping(value = "/gettgraph", method = RequestMethod.GET)
	public ModelAndView getTGraph(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		Map<String,Object> model = new HashMap<String,Object>();		
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		model.put("terminalId", terminalId);
		List<PositionData> list = positionInfoService.getLatest10PostionInfo(terminalId);
		String date_list = "";
		for(int i=0;i<list.size();i++)
			date_list += list.get(i).getDate() + ",";
		date_list = date_list.substring(0,date_list.lastIndexOf(","));
		String temperature_list = "";
		for(int i=0;i<list.size();i++){
			String tempStr = list.get(i).getInfo().substring(list.get(i).getInfo().lastIndexOf("������ˮ��:"));
			tempStr = tempStr.substring(0,tempStr.lastIndexOf("���϶�"));
			tempStr = tempStr.split(":")[1];
			temperature_list += tempStr + ",";
		}
		temperature_list = temperature_list.substring(0,temperature_list.lastIndexOf(","));
		int graph_refresh_minute = positionInfoService.getGraphRefreshMinute();
		model.put("graph_refresh_minute", graph_refresh_minute);
		model.put("date_list", date_list);
		model.put("temperature_list", temperature_list);
		return new ModelAndView("TemperatureGraphPage",model);
	}
	@RequestMapping(value = "/getlgraph", method = RequestMethod.GET)
	public ModelAndView getLGraph(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		Map<String,Object> model = new HashMap<String,Object>();		
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		model.put("terminalId", terminalId);
		List<PositionData> list = positionInfoService.getLatest10GpsPositionInfo(terminalId);
		String date_list = "";
		String latitude_list = "";
		String longitute_list = "";
		if(list.size()>0){
			for(int i=0;i<list.size();i++)
				date_list += list.get(i).getDate() + ",";
			date_list = date_list.substring(0,date_list.lastIndexOf(","));
			for(int i=0;i<list.size();i++){
				String tempStr = list.get(i).getInfo().substring(list.get(i).getInfo().lastIndexOf("γ��:"));
				tempStr = tempStr.substring(0,tempStr.indexOf(";"));
				tempStr = tempStr.split(":")[1];
				tempStr = tempStr.replaceAll("\\.", "");
				tempStr = tempStr.replaceAll("��", ".");
				String tempStrPart = tempStr.split("\\.")[1];
				tempStrPart = "0." + tempStrPart;
				double tempD = Double.parseDouble(tempStrPart)/60*100;
				tempStr = Integer.parseInt(tempStr.split("\\.")[0]) + tempD + "";
				latitude_list += tempStr + ",";
			}
			latitude_list = latitude_list.substring(0,latitude_list.lastIndexOf(","));
			for(int i=0;i<list.size();i++){
				String tempStr = list.get(i).getInfo().substring(list.get(i).getInfo().lastIndexOf("����:"));
				tempStr = tempStr.substring(0,tempStr.indexOf(";"));
				tempStr = tempStr.split(":")[1];
				tempStr = tempStr.replaceAll("\\.", "");
				tempStr = tempStr.replaceAll("��", ".");
				String tempStrPart = "0." + tempStr.split("\\.")[1];
				double tempD = Double.parseDouble(tempStrPart)/60*100;
				tempStr = Integer.parseInt(tempStr.split("\\.")[0]) + tempD + "";
				longitute_list += tempStr + ",";
			}
			longitute_list = longitute_list.substring(0,longitute_list.lastIndexOf(","));
		}
		int graph_refresh_minute = positionInfoService.getGraphRefreshMinute();
		model.put("graph_refresh_minute", graph_refresh_minute);
		model.put("date_list", date_list);
		model.put("latitude_list", latitude_list);
		model.put("longitute_list", longitute_list);
		model.put("terminalId", terminalId);
		return new ModelAndView("LocationGraphPage",model);
	}
	@RequestMapping(value = "/refreshlgraph", method = RequestMethod.GET)
	public void refreshLGraph(HttpServletRequest request,HttpServletResponse response){
		System.out.println("In!!!!!!!!");
		String terminalId = request.getParameter("terminalId");
		Map<String,Object> model = new HashMap<String,Object>();		
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		model.put("terminalId", terminalId);
		List<PositionData> list = positionInfoService.getLatest10GpsPositionInfo(terminalId);
		String date_list = "";
		String latitude_list = "";
		String longitute_list = "";
		if(list.size()>0){
			for(int i=0;i<list.size();i++)
				date_list += list.get(i).getDate() + ",";
			date_list = date_list.substring(0,date_list.lastIndexOf(","));
			for(int i=0;i<list.size();i++){
				String tempStr = list.get(i).getInfo().substring(list.get(i).getInfo().lastIndexOf("γ��:"));
				tempStr = tempStr.substring(0,tempStr.indexOf(";"));
				tempStr = tempStr.split(":")[1];
				tempStr = tempStr.replaceAll("\\.", "");
				tempStr = tempStr.replaceAll("��", ".");
				String tempStrPart = tempStr.split("\\.")[1];
				tempStrPart = "0." + tempStrPart;
				double tempD = Double.parseDouble(tempStrPart)/60*100;
				tempStr = Integer.parseInt(tempStr.split("\\.")[0]) + tempD + "";
				latitude_list += tempStr + ",";
			}
			latitude_list = latitude_list.substring(0,latitude_list.lastIndexOf(","));
			for(int i=0;i<list.size();i++){
				String tempStr = list.get(i).getInfo().substring(list.get(i).getInfo().lastIndexOf("����:"));
				tempStr = tempStr.substring(0,tempStr.indexOf(";"));
				tempStr = tempStr.split(":")[1];
				tempStr = tempStr.replaceAll("\\.", "");
				tempStr = tempStr.replaceAll("��", ".");
				String tempStrPart = "0." + tempStr.split("\\.")[1];
				double tempD = Double.parseDouble(tempStrPart)/60*100;
				tempStr = Integer.parseInt(tempStr.split("\\.")[0]) + tempD + "";
				longitute_list += tempStr + ",";
			}
			longitute_list = longitute_list.substring(0,longitute_list.lastIndexOf(","));
		}
		try {
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			jsonObject.put("refreshed_date_list", date_list);
			jsonObject.put("refreshed_latitude_list", latitude_list);
			jsonObject.put("refreshed_longitute_list", longitute_list);
			jsonObject.put("success", "true");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/gethgraph", method = RequestMethod.GET)
	public ModelAndView getHGraph(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		Map<String,Object> model = new HashMap<String,Object>();		
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		Map map = positionInfoService.getPointsForHotArea(terminalId);
		model.put("terminalId", terminalId);
		model.put("lngs",map.get("lngs"));
		model.put("lats",map.get("lats"));
		return new ModelAndView("HotSpotGraphPage",model);
	}
	@RequestMapping(value = "/mobilegetposition", method = RequestMethod.POST)
	public void mobileLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String terminalId = request.getParameter("terminalId");
		String start_time = request.getParameter("start_time");
		String stop_time = request.getParameter("stop_time");
		List<PositionData> list = positionInfoService.getPositionDataBetweenTime(terminalId, start_time, stop_time);
		String result = "";
		for(int i=0;i<list.size();i++){
			PositionData pd = list.get(i);
			if(pd.getInfo().contains("GPS��λ")){
				if(pd.getInfo().lastIndexOf("γ��:")>0){
					String point_latitude = pd.getInfo().substring(pd.getInfo().lastIndexOf("γ��:"));
					point_latitude = point_latitude.substring(0,point_latitude.indexOf(";"));
					point_latitude = point_latitude.split(":")[1];
					point_latitude = point_latitude.replaceAll("\\.", "");
					point_latitude = point_latitude.replaceAll("��", ".");
					String tempStrPart = point_latitude.split("\\.")[1];
					tempStrPart = "0." + tempStrPart;
					double tempD = Double.parseDouble(tempStrPart)/60*100;
					point_latitude = Integer.parseInt(point_latitude.split("\\.")[0]) + tempD + "";

					String point_longitute = pd.getInfo().substring(pd.getInfo().lastIndexOf("����:"));
					point_longitute = point_longitute.substring(0,point_longitute.indexOf(";"));
					point_longitute = point_longitute.split(":")[1];
					point_longitute = point_longitute.replaceAll("\\.", "");
					point_longitute = point_longitute.replaceAll("��", ".");
					String _tempStrPart = "0." + point_longitute.split("\\.")[1];
					double _tempD = Double.parseDouble(_tempStrPart)/60*100;
					point_longitute = Integer.parseInt(point_longitute.split("\\.")[0]) + _tempD + "";
					
					String corner = pd.getInfo().substring(pd.getInfo().lastIndexOf("�����:"));
					corner = corner.substring(0,corner.indexOf(";"));
					corner = corner.split(":")[1];

					result += point_latitude + ";" + point_longitute + ";" + corner + "@";
				}
			}
		}
		if(result.lastIndexOf("@")>=0){
			result = result.substring(0,result.lastIndexOf("@"));
		}
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
