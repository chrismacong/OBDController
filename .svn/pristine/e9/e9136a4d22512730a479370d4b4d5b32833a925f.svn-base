package com.fix.obd.web.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.util.GetAnnotationParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.model.TravelExmnation;
import com.fix.obd.web.model.util.OBDSeperateUtilModel;
import com.fix.obd.web.service.TravelExmnationService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/travelexm")
public class TravelExmControl {
	private static final Logger logger = LoggerFactory.getLogger(TravelExmControl.class);
	@Resource
	private TravelExmnationService travelExmnationService;
	public TravelExmnationService getTravelExmnationService() {
		return travelExmnationService;
	}
	public void setTravelExmnationService(
			TravelExmnationService travelExmnationService) {
		this.travelExmnationService = travelExmnationService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------travel exm:" + terminalId + "--------");
		TravelExmnation t = travelExmnationService.exmnationAndRecord(terminalId);
		ArrayList<OBDSeperateUtilModel> list = new ArrayList<OBDSeperateUtilModel>();
		OBDSeperateUtilModel tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("总距离");
		tmpModel.setContent(t.getTotalDistance());
		tmpModel.setExtra("km");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("最大距离");
		tmpModel.setContent(t.getLongestDistance());
		tmpModel.setExtra("km");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("最大速度");
		tmpModel.setContent(t.getMaxSpeed());
		tmpModel.setExtra("km/h");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("总超时时长");
		tmpModel.setContent(t.getTotalExceedSeconds());
		tmpModel.setExtra("s");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("总急刹车次数");
		tmpModel.setContent(t.getTotalBrakeTimes());
		tmpModel.setExtra("次");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("总紧急刹车次数");
		tmpModel.setContent(t.getTotalEmerBrakeTimes());
		tmpModel.setExtra("次");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("总急加速次数");
		tmpModel.setContent(t.getTotalSpeedUpTimes());
		tmpModel.setExtra("次");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("总紧急加速次数");
		tmpModel.setContent(t.getTotalEmerSpeedUpTimes());
		tmpModel.setExtra("次");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("平均速度");
		tmpModel.setContent(t.getAvgSpeed());
		tmpModel.setExtra("km/h");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("发动机最高水温");
		tmpModel.setContent(t.getMaxWaterTmp());
		tmpModel.setExtra("°C");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("发动机最高转速");
		tmpModel.setContent(t.getMaxRevolution());
		tmpModel.setExtra("转/分");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("总油耗");
		tmpModel.setContent(t.getTotalOilExpend());
		tmpModel.setExtra("升");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("平均油耗");
		tmpModel.setContent(t.getAvgOilExpend());
		tmpModel.setExtra("百公里升");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("总疲劳驾驶时间");
		tmpModel.setContent(t.getTotalTiredDrivingMinutes());
		tmpModel.setExtra("min");
		list.add(tmpModel);
		
//		System.out.println("总距离：" + t.getTotalDistance());
//		System.out.println("最大距离：" + t.getLongestDistance());
//		System.out.println("最大速度：" + t.getMaxSpeed());
//		System.out.println("总超时时长：" + t.getTotalExceedSeconds());
//		System.out.println("总急刹车次数：" + t.getTotalBrakeTimes());
//		System.out.println("总紧急刹车次数：" + t.getTotalEmerBrakeTimes());
//		System.out.println("总急加速次数：" + t.getTotalSpeedUpTimes());
//		System.out.println("总紧急加速次数：" + t.getTotalEmerSpeedUpTimes());
//		System.out.println("平均速度：" + t.getAvgSpeed());
//		System.out.println("发动机最高水温：" + t.getMaxWaterTmp());
//		System.out.println("发动机最高转速：" + t.getMaxRevolution());
//		System.out.println("总油耗：" + t.getTotalOilExpend());
//		System.out.println("平均油耗：" + t.getAvgOilExpend());
//		System.out.println("总疲劳驾驶时间：" + t.getTotalTiredDrivingMinutes());
		Map reply_map = travelExmnationService.exmnationScoreAmongFriends(terminalId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("terminalId", terminalId);
		model.put("oil_score", reply_map.get("oil_score"));
		model.put("mile_score", reply_map.get("mile_score"));
		model.put("stability_score", reply_map.get("stability_score"));
		model.put("speed_score", reply_map.get("speed_score"));
		model.put("tired_control_score", reply_map.get("tired_control_score"));
		model.put("character_list", list);
		model.put("oil_text", reply_map.get("oil_text"));
		model.put("mile_text", reply_map.get("mile_text"));
		model.put("stability_text", reply_map.get("stability_text"));
		model.put("speed_text", reply_map.get("speed_text"));
		model.put("tired_control_text", reply_map.get("tired_control_text"));
		return new ModelAndView("TravelExmPage",model);
	}
	@RequestMapping(value = "/speedhour", method = RequestMethod.GET)
	public ModelAndView speedHour(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------get graph of hour and speed:" + terminalId + "--------");
		Map map = travelExmnationService.statisticOfSpeedAndHour(terminalId);
		int[] speed_of_hour = (int[]) map.get("speed_of_hour");
		int[] max_speed_of_hour = (int[]) map.get("max_speed_of_hour");
		Map<String,Object> model = new HashMap<String,Object>();
		String speedOfHour = "[";
		for(int i=0;i<speed_of_hour.length;i++)
			speedOfHour += "" + speed_of_hour[i] + ",";
		speedOfHour = speedOfHour.substring(0,speedOfHour.lastIndexOf(","));
		speedOfHour += "]";
		String maxSpeedOfHour = "[";
		for(int i=0;i<max_speed_of_hour.length;i++)
			maxSpeedOfHour += "" + max_speed_of_hour[i] + ",";
		maxSpeedOfHour = maxSpeedOfHour.substring(0,maxSpeedOfHour.lastIndexOf(","));
		maxSpeedOfHour += "]";
		model.put("speed_of_hour", speedOfHour);
		model.put("max_speed_of_hour", maxSpeedOfHour);
		return new ModelAndView("SpeedHourStatistics",model);
	}
	@RequestMapping(value = "/hour", method = RequestMethod.GET)
	public ModelAndView hour(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------get graph of hours:" + terminalId + "--------");
		Map map = travelExmnationService.statisticOfHour(terminalId);
		int[] hour_count = (int[]) map.get("hour_count");
		Map<String,Object> model = new HashMap<String,Object>();
		String hours = "[";
		for(int i=0;i<hour_count.length;i++)
			hours += "" + hour_count[i] + ",";
		hours = hours.substring(0,hours.lastIndexOf(","));
		hours += "]";
		model.put("hour_count", hours);
		return new ModelAndView("HourStatistics",model);
	}
	@RequestMapping(value = "/brakehour", method = RequestMethod.GET)
	public ModelAndView brakeHour(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------get graph of brake and hour:" + terminalId + "--------");
		Map map = travelExmnationService.statisticOfBrakeAndHour(terminalId);
		int[] brake_hour = (int[]) map.get("brake_hour");
		int[] emer_brake_hour = (int[]) map.get("emer_brake_hour");
		Map<String,Object> model = new HashMap<String,Object>();
		String brake_hours = "[";
		for(int i=0;i<brake_hour.length;i++)
			brake_hours += "" + brake_hour[i] + ",";
		brake_hours = brake_hours.substring(0,brake_hours.lastIndexOf(","));
		brake_hours += "]";
		String emer_brake_hours = "[";
		for(int i=0;i<emer_brake_hour.length;i++)
			emer_brake_hours += "" + emer_brake_hour[i] + ",";
		emer_brake_hours = emer_brake_hours.substring(0,emer_brake_hours.lastIndexOf(","));
		emer_brake_hours += "]";
		model.put("brake_hour", brake_hours);
		model.put("emer_brake_hour", emer_brake_hours);
		return new ModelAndView("BrakeHourStatistics",model);
	}
	@RequestMapping(value = "/speeduphour", method = RequestMethod.GET)
	public ModelAndView speedupHour(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------get graph of speedup and hour:" + terminalId + "--------");
		Map map = travelExmnationService.statisticOfSpeedupAndHour(terminalId);
		int[] speedup_hour = (int[]) map.get("speedup_hour");
		int[] emer_speedup_hour = (int[]) map.get("emer_speedup_hour");
		Map<String,Object> model = new HashMap<String,Object>();
		String speedup_hours = "[";
		for(int i=0;i<speedup_hour.length;i++)
			speedup_hours += "" + speedup_hour[i] + ",";
		speedup_hours = speedup_hours.substring(0,speedup_hours.lastIndexOf(","));
		speedup_hours += "]";
		String emer_speedup_hours = "[";
		for(int i=0;i<emer_speedup_hour.length;i++)
			emer_speedup_hours += "" + emer_speedup_hour[i] + ",";
		emer_speedup_hours = emer_speedup_hours.substring(0,emer_speedup_hours.lastIndexOf(","));
		emer_speedup_hours += "]";
		model.put("speedup_hour", speedup_hours);
		model.put("emer_speedup_hour", emer_speedup_hours);
		return new ModelAndView("SpeedupHourStatistics",model);
	}
	@RequestMapping(value = "/speedplan", method = RequestMethod.GET)
	public ModelAndView speedPlan(HttpServletRequest request,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------get graph of speed plan:" + terminalId + "--------");
		Map map = travelExmnationService.speedPlan(terminalId);
		String brake_speed = (String) map.get("brake_speed");
		String speedup_speed = (String) map.get("speedup_speed");
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("brake_speed", brake_speed);
		model.put("speedup_speed", speedup_speed);
		return new ModelAndView("SpeedPlan",model);
	}
}
