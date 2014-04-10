package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.fix.obd.web.model.util.VehicleExmnationReport;
import com.fix.obd.web.service.VehicleExmnationService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/vehicleexm")
public class VehicleExmControl {
	private static final Logger logger = LoggerFactory.getLogger(VehicleExmControl.class);
	@Resource
	private VehicleExmnationService vehicleExmnationService;
	public VehicleExmnationService getVehicleExmnationService() {
		return vehicleExmnationService;
	}
	public void setVehicleExmnationService(
			VehicleExmnationService vehicleExmnationService) {
		this.vehicleExmnationService = vehicleExmnationService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------vehicle exm:" + terminalId + "--------");
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("terminalId", terminalId);
		return new ModelAndView("VehicleExmPage",model);
	}
	@RequestMapping(value = "/makeexm", method = RequestMethod.GET)
	public void makeExm(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		logger.debug("--------making vehicle exm:" + terminalId + "--------");
		boolean replyOrNot = vehicleExmnationService.askDTCDefect(terminalId);
		PrintWriter pw = null;
		try {
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(replyOrNot){
				VehicleExmnationReport v = vehicleExmnationService.getVehicleExmnationReport(terminalId);
				jsonObject.put("success", "true");
				jsonObject.put("score", v.getVehicle_exm_score());
				jsonObject.put("solution", v.getVehicle_exm_main_solution());
				jsonObject.put("errors", v.getVehicle_errors());
			}
			else{
				jsonObject.put("success", "false");
			}
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
