package com.fix.obd.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.tcp.thread.UploadTerminalDataTask;
import com.fix.obd.util.FaultCodeXMLUtil;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ThreadMap;
import com.fix.obd.web.dao.DTCDefectDao;
import com.fix.obd.web.dao.OBDDefectDao;
import com.fix.obd.web.dao.OBDTerminalInfoDao;
import com.fix.obd.web.model.DTCDefect;
import com.fix.obd.web.model.OBDDefect;
import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.model.util.FaultCodeIterator;
import com.fix.obd.web.model.util.VehicleExmnationReport;
import com.fix.obd.web.service.VehicleExmnationService;
@Component
public class VehicleExmnationServiceImpl implements VehicleExmnationService{
	private final String[] MAIN_SOLUTIONS = {"您正处于高危驾驶状态，车辆存在严重问题，请立刻到相关单位进行检修！",
			"您的爱车存在大量危险故障，这将直接威胁到您的人身安全，请尽快到相关单位进行车辆检修",
			"您的车辆处于不安全的状态下，这可能会对您的人身安全造成危害，抽时间去相关单位进行车辆检修吧！",
			"您的爱车存在可优化的项目，请注意车辆健康！",
			"您的爱车健康状况非常好，请继续保持！"
	};	
	@Resource
	private OBDTerminalInfoDao obdTerminalInfoDao;
	@Resource
	private DTCDefectDao dtcDefectDao;

	public DTCDefectDao getDtcDefectDao() {
		return dtcDefectDao;
	}

	public void setDtcDefectDao(DTCDefectDao dtcDefectDao) {
		this.dtcDefectDao = dtcDefectDao;
	}

	public OBDTerminalInfoDao getObdTerminalInfoDao() {
		return obdTerminalInfoDao;
	}

	public void setObdTerminalInfoDao(OBDTerminalInfoDao obdTerminalInfoDao) {
		this.obdTerminalInfoDao = obdTerminalInfoDao;
	}

	@Override
	public VehicleExmnationReport getVehicleExmnationReport(String terminalId) {
		// TODO Auto-generated method stub
		//Emergency-Danger-Risk-Warn-Notice
		int SCORE = 100;
		int COUNT_EMERGENCY = 0;
		int COUNT_DANGER = 0;
		int COUNT_RISK = 0;
		int COUNT_WARN = 0;
		int COUNT_NOTICE = 0;
		VehicleExmnationReport vehicleExmnationReport = new VehicleExmnationReport();
		DTCDefect dtc_defect = this.getDTCDefect(terminalId);
		String defect_info = dtc_defect.getInfo();
		ArrayList<FaultCodeIterator> list = this.getFaultCodeIteratorList(defect_info);
		String vehicle_errors = "";
		for(int i=0;i<list.size();i++){
			FaultCodeIterator temp = list.get(i);
			/*System.out.println(temp.getIndex());
			System.out.println(temp.getPriorty());
			System.out.println(temp.getClassify());
			System.out.println(temp.getFaultDetail());
			System.out.println(temp.getSolution());
			System.out.println("-----------------------");*/
			vehicle_errors += temp.getIndex() + "%%%" + temp.getPriorty() + "%%%" + temp.getClassify() + "%%%" + temp.getFaultDetail() + "%%%" + temp.getSolution() + "@@@";
			if(!temp.getFaultDetail().equals("未识别的故障码")){
				switch(temp.getPriorty()){
				case "1":
					COUNT_NOTICE++;
					break;
				case "2":
					COUNT_WARN++;
					break;
				case "3":
					COUNT_RISK++;
					break;
				case "4":
					COUNT_DANGER++;
					break;
				case "5":
					COUNT_EMERGENCY++;
					break;
				default:
					COUNT_NOTICE++;
				}
			}
		}
		if(COUNT_EMERGENCY>=1)
			SCORE = 0;
		else{
			SCORE = SCORE - COUNT_DANGER * 20 - COUNT_RISK * 10 - COUNT_WARN * 5 - COUNT_NOTICE * 1;
			SCORE = SCORE<0?0:SCORE;
		}
		System.out.println("您的体检评分为：" + SCORE);
		String main_solution = MAIN_SOLUTIONS[(int) Math.floor(SCORE/20)-1];
		System.out.println(main_solution);
		vehicleExmnationReport.setVehicle_exm_score(SCORE);
		vehicleExmnationReport.setVehicle_exm_main_solution(main_solution);
		vehicle_errors = vehicle_errors.substring(0,vehicle_errors.lastIndexOf("@@@"));
		System.out.println(vehicle_errors);
		vehicleExmnationReport.setVehicle_errors(vehicle_errors);
		//vehicleExmnationReport.setTotal_exm_num();
		return vehicleExmnationReport;
	}

	@Override
	public boolean askDTCDefect(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<OBDTerminalInfo> list = obdTerminalInfoDao.findByHQL("from OBDTerminalInfo where tid = '" + MessageUtil.frontCompWithZore(terminalId, 20) + "'");
			if(list.size()>0){
				OBDTerminalInfo obd = list.get(0);
				String ipAndPort = obd.getTerminalIp();
				String ip = ipAndPort.split(":")[0];
				String port = ipAndPort.split(":")[1];
				UploadTerminalDataTask u = ThreadMap.threadNameMap.get("/" + ip);				
				String bufferId = "78";
				System.out.println(terminalId);
				System.out.println(u);
				boolean result = u.SentReadDTC(terminalId, bufferId);
				return result;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public DTCDefect getDTCDefect(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<DTCDefect> list = dtcDefectDao.findByHQL("from DTCDefect where tid = '" + terminalId + "' order by date desc");
			if(list.size()>0){
				return list.get(0);
			}
			else
				return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<FaultCodeIterator> getFaultCodeIteratorList(String info) {
		// TODO Auto-generated method stub
		ArrayList<FaultCodeIterator> result_list = new ArrayList<FaultCodeIterator>();
		if(info==null||"".equals(info))
			return null;
		String[] indexes = info.split(",");
		for(int i=0;i<indexes.length;i++){
			String index = indexes[i];
			FaultCodeXMLUtil f = new FaultCodeXMLUtil();
			ArrayList<FaultCodeIterator> list = f.parseByIndex(index);
			if(list.size()==0){
				FaultCodeIterator fci = new FaultCodeIterator();
				fci.setIndex(index);
				fci.setFaultDetail("未识别的故障码");
				fci.setPriorty("1");
				fci.setClassify("Nil");
				fci.setSolution("Nil");
				result_list.add(fci);
			}
			else{
				for(int j=0;j<list.size();j++)
					result_list.add(list.get(j));
			}
		}
		return result_list;
	}
}
