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
import com.fix.obd.web.model.TravelExmnation;
import com.fix.obd.web.model.util.OBDSeperateUtilModel;
import com.fix.obd.web.service.TravelExmnationService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/travelexmpersonal")
public class TravelExmPersonalControl {
	private static final Logger logger = LoggerFactory.getLogger(TravelExmPersonalControl.class);
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
		tmpModel.setName("�ܾ���");
		tmpModel.setContent(t.getTotalDistance());
		tmpModel.setExtra("km");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("������");
		tmpModel.setContent(t.getLongestDistance());
		tmpModel.setExtra("km");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("����ٶ�");
		tmpModel.setContent(t.getMaxSpeed());
		tmpModel.setExtra("km/h");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("�ܳ�ʱʱ��");
		tmpModel.setContent(t.getTotalExceedSeconds());
		tmpModel.setExtra("s");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("�ܼ�ɲ������");
		tmpModel.setContent(t.getTotalBrakeTimes());
		tmpModel.setExtra("��");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("�ܽ���ɲ������");
		tmpModel.setContent(t.getTotalEmerBrakeTimes());
		tmpModel.setExtra("��");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("�ܼ����ٴ���");
		tmpModel.setContent(t.getTotalSpeedUpTimes());
		tmpModel.setExtra("��");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("�ܽ������ٴ���");
		tmpModel.setContent(t.getTotalEmerSpeedUpTimes());
		tmpModel.setExtra("��");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("ƽ���ٶ�");
		tmpModel.setContent(t.getAvgSpeed());
		tmpModel.setExtra("km/h");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("���������ˮ��");
		tmpModel.setContent(t.getMaxWaterTmp());
		tmpModel.setExtra("��C");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("���������ת��");
		tmpModel.setContent(t.getMaxRevolution());
		tmpModel.setExtra("ת/��");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("���ͺ�");
		tmpModel.setContent(t.getTotalOilExpend());
		tmpModel.setExtra("��");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("ƽ���ͺ�");
		tmpModel.setContent(t.getAvgOilExpend());
		tmpModel.setExtra("�ٹ�����");
		list.add(tmpModel);
		tmpModel = new OBDSeperateUtilModel();
		tmpModel.setName("��ƣ�ͼ�ʻʱ��");
		tmpModel.setContent(t.getTotalTiredDrivingMinutes());
		tmpModel.setExtra("min");
		list.add(tmpModel);
		
//		System.out.println("�ܾ��룺" + t.getTotalDistance());
//		System.out.println("�����룺" + t.getLongestDistance());
//		System.out.println("����ٶȣ�" + t.getMaxSpeed());
//		System.out.println("�ܳ�ʱʱ����" + t.getTotalExceedSeconds());
//		System.out.println("�ܼ�ɲ��������" + t.getTotalBrakeTimes());
//		System.out.println("�ܽ���ɲ��������" + t.getTotalEmerBrakeTimes());
//		System.out.println("�ܼ����ٴ�����" + t.getTotalSpeedUpTimes());
//		System.out.println("�ܽ������ٴ�����" + t.getTotalEmerSpeedUpTimes());
//		System.out.println("ƽ���ٶȣ�" + t.getAvgSpeed());
//		System.out.println("���������ˮ�£�" + t.getMaxWaterTmp());
//		System.out.println("���������ת�٣�" + t.getMaxRevolution());
//		System.out.println("���ͺģ�" + t.getTotalOilExpend());
//		System.out.println("ƽ���ͺģ�" + t.getAvgOilExpend());
//		System.out.println("��ƣ�ͼ�ʻʱ�䣺" + t.getTotalTiredDrivingMinutes());
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
		return new ModelAndView("TravelExmPersonalPage",model);
	}
}
