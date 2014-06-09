package com.fix.obd.web.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.model.PositionData;
import com.fix.obd.web.model.TravelExmnation;
import com.fix.obd.web.model.util.OBDSeperateUtilModel;
import com.fix.obd.web.model.util.PositionSeperateUtilModel;
import com.fix.obd.web.service.PositionInfoService;
import com.fix.obd.web.service.TravelExmnationService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/positioninfopersonal")
public class PositionInfoPersonalControl {
	private static final Logger logger = LoggerFactory.getLogger(PositionInfoPersonalControl.class);
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
		return new ModelAndView("PositionInfoPersonalPage",model);
	}
}