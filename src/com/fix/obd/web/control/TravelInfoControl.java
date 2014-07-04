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
import com.fix.obd.web.model.TravelInfo;
import com.fix.obd.web.model.util.OBDSeperateUtilModel;
import com.fix.obd.web.service.PositionInfoService;
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
		List<TravelInfo> review_list = travelInfoService.reviewTravelInfo(terminalId);
		if(review_list!=null){
			if(review_list.size()>0){
				TravelInfo[] reviews = new TravelInfo[review_list.size()];
				String review_position_info_str = "";
				System.out.println(review_list.size());
				for(int i=0;i<reviews.length;i++){
					reviews[i] = review_list.get(i);
					String start_time = reviews[i].getInfo().substring(26,38);
					String stop_time = reviews[i].getInfo().substring(5,17);
//					System.out.println("From:" + start_time + " to " + stop_time);
					Map map = positionInfoService.getStartandStopByGPS(terminalId, start_time,stop_time);
					if(map!=null){
						review_position_info_str += map.get("start_time_in_format") + " ~ " + map.get("stop_time_in_format") + 
								";" + review_list.get(i).getStart_address() + ";" + review_list.get(i).getStop_address() + "@";
					}
				}
				model.put("review_position_info_str", review_position_info_str.substring(0,review_position_info_str.lastIndexOf("@")));
//				System.out.println(review_position_info_str);
				model.put("reviews", reviews);
			}
		}
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
	@RequestMapping(value = "/getreviewedinfo", method = RequestMethod.GET)
	public void getReviewedInfo(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		try {
			logger.debug("--------getReviewedInfo--------");
			String terminalId = request.getParameter("terminalId");
			String starttime = request.getParameter("starttime");
//			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
//			boolean temp = travelInfoService.askLastestTravelInfo(terminalId);
//			Map<String,Object> model = new HashMap<String,Object>();
//			model.put("terminalId", terminalId);
			Map map = travelInfoService.getTravelInfoHtmlByStartTime(terminalId, starttime);
			String review_result_str = map.get("resultStr") + "";
			String score = map.get("score") + "";
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			jsonObject.put("success", "true");
			jsonObject.put("review_result_str", review_result_str);
			jsonObject.put("score", score);
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
