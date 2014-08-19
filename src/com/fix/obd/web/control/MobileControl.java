package com.fix.obd.web.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.model.DTCDefect;
import com.fix.obd.web.model.TravelExmnation;
import com.fix.obd.web.model.TravelInfo;
import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.service.DTCService;
import com.fix.obd.web.service.TerminalInfoService;
import com.fix.obd.web.service.TravelExmnationService;
import com.fix.obd.web.service.TravelInfoService;
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
	@Resource
	private TravelInfoService travelInfoService;
	
	public TravelInfoService getTravelInfoService() {
		return travelInfoService;
	}
	public void setTravelInfoService(TravelInfoService travelInfoService) {
		this.travelInfoService = travelInfoService;
	}
	@Resource
	private TerminalInfoService terminalInfoService;
	
	public TerminalInfoService getTerminalInfoService() {
		return terminalInfoService;
	}
	public void setTerminalInfoService(TerminalInfoService terminalInfoService) {
		this.terminalInfoService = terminalInfoService;
	}
	@Resource
	private TravelExmnationService travelExmnationService;
	public TravelExmnationService getTravelExmnationService() {
		return travelExmnationService;
	}
	public void setTravelExmnationService(
			TravelExmnationService travelExmnationService) {
		this.travelExmnationService = travelExmnationService;
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
	@RequestMapping(value = "/mobilegettravelinfo", method = RequestMethod.POST)
	public void mobileGetTravelInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String terminalId = request.getParameter("terminalId");
		String from_time_point = request.getParameter("from_time_point");
		String to_time_point = request.getParameter("to_time_point");
		List<TravelInfo> list = travelInfoService.getTravelInfoBetweenTime(terminalId, from_time_point, to_time_point);
		String result = terminalId + "@";
		for(int i=0;i<list.size();i++){
			TravelInfo travelInfo = list.get(i);
			String info = travelInfo.getInfo();
			String[] splits_info = info.split("@");
			for(int j=0;j<splits_info.length;j++){
				String[] strs = splits_info[j].split(";");
				result += strs[1] + ";";
			}
			result += travelInfo.getStart_address() + ";";
			result += travelInfo.getStop_address() + "@";
		}
		result = result.substring(0,result.lastIndexOf("@"));
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/mobilegetterminalinfo", method = RequestMethod.POST)
	public void mobileGetTerminalInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String terminalId = request.getParameter("terminalId");
//		OBDTerminalInfo obdTerminalInfo = terminalInfoService.getTerminalInfo(terminalId);
		YY_User yy_user = terminalInfoService.getTerminalUserInfo(terminalId);
		String result = terminalId + ";";
		result += yy_user.getEmail() + ";";
		result += yy_user.getNickname() + ";";
		result += yy_user.getCarnumber() + ";";
		result += yy_user.getCartype() + ";";
		result += yy_user.getTel();
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/mobilegetmonthlytravelinfobydate", method = RequestMethod.POST)
	public void mobileGetMonthlyTravelInfobyDate(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String terminalId = request.getParameter("terminalId");
		String date = request.getParameter("date");
		Map map = travelInfoService.getTravelInfoMapByDateForMobileStatistics(terminalId, date);
		int[] distance_per_day = (int[]) map.get("distance_per_day");
		String[] oilspend_per_day = (String[]) map.get("oilspend_per_day");
		int[] brake_times_per_day = (int[]) map.get("brake_times_per_day");
		int[] speedup_times_per_day = (int[]) map.get("speedup_times_per_day");
		int[] driving_minutes_per_day = (int[]) map.get("driving_minutes_per_day");
		String[] average_oilspend_per_day = (String[]) map.get("average_oilspend_per_day");
		String[] average_speed_per_day = (String[]) map.get("average_speed_per_day");
		String result = "";
		result = this.makingStringSeperatedByComma(result, distance_per_day);
		result = this.makingStringSeperatedByComma(result, oilspend_per_day);
		result = this.makingStringSeperatedByComma(result, brake_times_per_day);
		result = this.makingStringSeperatedByComma(result, speedup_times_per_day);
		result = this.makingStringSeperatedByComma(result, driving_minutes_per_day);
		result = this.makingStringSeperatedByComma(result, average_oilspend_per_day);
		result = this.makingStringSeperatedByComma(result, average_speed_per_day);
		result = result.substring(0,result.lastIndexOf("@"));
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			JSONObject jsonObject = new JSONObject(); 
//			jsonObject.put("success", "true");
//			jsonObject.put("result", result);
//			PrintWriter pw = null;
//			pw=response.getWriter();
//			pw.print(jsonObject.toString());
//			pw.flush();
//			pw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	@RequestMapping(value = "/mobilegetmonthlytravelexmbydate", method = RequestMethod.POST)
	public void mobileGetMonthlyTravelExmbyDate(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String terminalId = request.getParameter("terminalId");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		TravelExmnation te = travelExmnationService.exmnationAndRecordByMonth(terminalId, year, month);
		Map map_hour = travelExmnationService.statisticOfHourByMonth(terminalId, year, month);
		Map map_speed_hour = travelExmnationService.statisticOfSpeedAndHourByMonth(terminalId, year, month);
		int[] hour_count = (int[]) map_hour.get("hour_count");
		int[] speed_of_hour = (int[]) map_speed_hour.get("speed_of_hour");
		String result = "";
		result += te.getTotalDistance() + "@";
		result += te.getLongestDistance() + "@";
		result += te.getMaxSpeed() + "@";
		result += te.getTotalExceedSeconds() + "@";
		result += te.getTotalBrakeTimes() + "@";
		result += te.getTotalEmerBrakeTimes() + "@";
		result += te.getTotalSpeedUpTimes() + "@";
		result += te.getTotalEmerSpeedUpTimes() + "@";
		result += te.getAvgSpeed() + "@";
		result += te.getMaxWaterTmp() + "@";
		result += te.getMaxRevolution() + "@";
		result += te.getTotalOilExpend() + "@";
		result += te.getAvgOilExpend() + "@";
		result += te.getTotalTiredDrivingMinutes() + "@";
		result = this.makingStringSeperatedByComma(result, hour_count);
		result = this.makingStringSeperatedByComma(result, speed_of_hour);
		result = result.substring(0,result.lastIndexOf("@"));
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			JSONObject jsonObject = new JSONObject(); 
//			jsonObject.put("success", "true");
//			jsonObject.put("result", result);
//			PrintWriter pw = null;
//			pw=response.getWriter();
//			pw.print(jsonObject.toString());
//			pw.flush();
//			pw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private String makingStringSeperatedByComma(String originalStr, String[] addStr){
		for(int i=0;i<addStr.length;i++){
			originalStr += addStr[i] + ",";
		}
		if(originalStr.indexOf(",")>-1){
			originalStr = originalStr.substring(0,originalStr.lastIndexOf(","));
		}
		originalStr += "@";
		return originalStr;
	}
	private String makingStringSeperatedByComma(String originalStr, int[] addStr){
		for(int i=0;i<addStr.length;i++){
			originalStr += addStr[i] + ",";
		}
		if(originalStr.indexOf(",")>-1){
			originalStr = originalStr.substring(0,originalStr.lastIndexOf(","));
		}
		originalStr += "@";
		return originalStr;
	}
}
