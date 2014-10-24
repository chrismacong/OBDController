package com.fix.obd.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.DTCDefectDao;
import com.fix.obd.web.dao.OBDDataDao;
import com.fix.obd.web.dao.OBDDefectDao;
import com.fix.obd.web.dao.OBDLogsDao;
import com.fix.obd.web.dao.OBDTerminalInfoDao;
import com.fix.obd.web.dao.OnLineTerminalDao;
import com.fix.obd.web.dao.ParameterResponseDao;
import com.fix.obd.web.dao.PositionDataDao;
import com.fix.obd.web.dao.TravelInfoDao;
import com.fix.obd.web.model.DTCDefect;
import com.fix.obd.web.model.OBDData;
import com.fix.obd.web.model.OBDDefect;
import com.fix.obd.web.model.OBDLogs;
import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.model.OnLineTerminal;
import com.fix.obd.web.model.ParameterResponse;
import com.fix.obd.web.model.PositionData;
import com.fix.obd.web.model.TravelInfo;
import com.fix.obd.web.service.TerminalServerService;
import com.fix.obd.web.util.JSONHelper;
@Component
public class TerminalServerServiceImpl implements TerminalServerService{
	@Resource
	private OnLineTerminalDao onlineTerminalDao;
	@Resource
	private OBDLogsDao obdLogs;
	@Resource
	private OBDDataDao obddataDao;
	@Resource
	private PositionDataDao positiondataDao;
	@Resource
	private OBDTerminalInfoDao obdTerminalInfoDao;
	@Resource
	private ParameterResponseDao parameterResponseDao;
	@Resource
	private OBDDefectDao obdDefectDao;
	@Resource
	private DTCDefectDao dtcDefectDao;
	@Resource
	private TravelInfoDao travelInfoDao;
	
	private final String API_KEY = "15cf002106718ce6a60a7841ea39f127";
	public TravelInfoDao getTravelInfoDao() {
		return travelInfoDao;
	}

	public void setTravelInfoDao(TravelInfoDao travelInfoDao) {
		this.travelInfoDao = travelInfoDao;
	}

	public DTCDefectDao getDtcDefectDao() {
		return dtcDefectDao;
	}

	public void setDtcDefectDao(DTCDefectDao dtcDefectDao) {
		this.dtcDefectDao = dtcDefectDao;
	}

	public OBDDefectDao getObdDefectDao() {
		return obdDefectDao;
	}

	public void setObdDefectDao(OBDDefectDao obdDefectDao) {
		this.obdDefectDao = obdDefectDao;
	}

	public ParameterResponseDao getParameterResponseDao() {
		return parameterResponseDao;
	}

	public void setParameterResponseDao(ParameterResponseDao parameterResponseDao) {
		this.parameterResponseDao = parameterResponseDao;
	}

	public OBDTerminalInfoDao getObdTerminalInfoDao() {
		return obdTerminalInfoDao;
	}

	public void setObdTerminalInfoDao(OBDTerminalInfoDao obdTerminalInfoDao) {
		this.obdTerminalInfoDao = obdTerminalInfoDao;
	}

	public OBDDataDao getObddataDao() {
		return obddataDao;
	}

	public void setObddataDao(OBDDataDao obddataDao) {
		this.obddataDao = obddataDao;
	}
	public PositionDataDao getPositiondataDao() {
		return positiondataDao;
	}

	public void setPositiondataDao(PositionDataDao positiondataDao) {
		this.positiondataDao = positiondataDao;
	}

	public OBDLogsDao getObdLogs() {
		return obdLogs;
	}

	public void setObdLogs(OBDLogsDao obdLogs) {
		this.obdLogs = obdLogs;
	}

	public OnLineTerminalDao getOnlineTerminalDao() {
		return onlineTerminalDao;
	}

	public void setOnlineTerminalDao(OnLineTerminalDao onlineTerminalDao) {
		this.onlineTerminalDao = onlineTerminalDao;
	}

	@Override
	public void updateTerminalHeartBeat(String clientId,String bufferId) {
		// TODO Auto-generated method stub
		try {
			OnLineTerminal olt = new OnLineTerminal();
			olt.setTerminalId(clientId);
			olt.setTerminalBuffer(bufferId);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df.format(new Date());
			olt.setLastheartbeat(now);
			List<OnLineTerminal> list = onlineTerminalDao.findByHQL("from OnLineTerminal where tid = '" + clientId + "'");
			if(list.size()==0)
				onlineTerminalDao.addOnLineTerminal(olt);
			else{
				olt = list.get(0);
				olt.setLastheartbeat(now);
				onlineTerminalDao.updateOnLineTerminal(olt);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addOBDLog(String clientId, String info, String message) {
		// TODO Auto-generated method stub
		OBDLogs log = new OBDLogs();
		log.setTerminalId(clientId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
		log.setDate(now);
		log.setInfo(info);
		log.setMessage(message);
		obdLogs.addOBDLogs(log);
	}

	@Override
	public void addOBDData(String clientId, String info, String obdTime) {
		// TODO Auto-generated method stub
		OBDData data = new OBDData();
		data.setTerminalId(clientId);
		data.setDate(obdTime);
		data.setInfo(info);
		obddataDao.addOBDData(data);
	}

	@Override
	public void addPositionData(String clientId, String info, String gpsDate,String alertStr) {
		// TODO Auto-generated method stub
		PositionData data = new PositionData();
		data.setTerminalId(clientId);
		data.setDate(gpsDate);
		data.setInfo(info);
		data.setAlert(alertStr);
		positiondataDao.addPositionData(data);
	}

	@Override
	public void updateTerminalInfo(String clientId, String clientIp) {
		// TODO Auto-generated method stub
		try {
			OBDTerminalInfo obdtInfo = new OBDTerminalInfo();
			obdtInfo.setTerminalId(clientId);
			obdtInfo.setTerminalIp(clientIp);
//			Default name. Hard Code here.
			obdtInfo.setProduct("klx");
			List<OBDTerminalInfo> list = obdTerminalInfoDao.findByHQL("from OBDTerminalInfo where tid = '" + clientId + "'");
			if(list.size()==0)
				obdTerminalInfoDao.addOBDTerminalInfo(obdtInfo);
			else{
				obdtInfo = list.get(0);
				obdtInfo.setTerminalIp(clientIp);
				obdTerminalInfoDao.updateOBDTerminalInfo(obdtInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addParameterResponse(String clientId, String info) {
		// TODO Auto-generated method stub
		try {
			ParameterResponse parameterResponse = new ParameterResponse();
			parameterResponse.setTerminalId(clientId);
			parameterResponse.setInfo(info);
			List<ParameterResponse> list = parameterResponseDao.findByHQL("from ParameterResponse where tid = '" + clientId + "'");
			if(list.size()==0)
				parameterResponseDao.addParameterResponse(parameterResponse);
			else{
				parameterResponse = list.get(0);
				parameterResponse.setInfo(info);
				parameterResponseDao.updateParameterResponse(parameterResponse);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateOBDDefect(String clientId, String info) {
		// TODO Auto-generated method stub
		try {
			OBDDefect obdDefect = new OBDDefect();
			obdDefect.setTerminalId(clientId);
			obdDefect.setInfo(info);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df.format(new Date());
			obdDefect.setDate(now);
			List<OBDDefect> list = obdDefectDao.findByHQL("from OBDDefect where tid = '" + clientId + "'");
			obdDefectDao.addOBDDefect(obdDefect);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateDTCDefect(String clientId, String info) {
		// TODO Auto-generated method stub
		try {
			DTCDefect dtcDefect = new DTCDefect();
			dtcDefect.setTerminalId(clientId);
			dtcDefect.setInfo(info);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df.format(new Date());
			dtcDefect.setDate(now);
			dtcDefectDao.addDTCDefect(dtcDefect);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String updateTravelInfo(String clientId, String info) {
		// TODO Auto-generated method stub
		String start_address = "缺失有效DPS信息";
		String stop_address = "缺失有效GPS信息";
		try {
			TravelInfo travelInfo = new TravelInfo();
			travelInfo.setTerminalId(clientId);
			travelInfo.setInfo(info);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df.format(new Date());
			travelInfo.setDate(now);
			String infoStr = info.substring(0,info.lastIndexOf("@"));
			String[] characters = infoStr.split("@");
			String start_time = characters[1].split(";")[1];
			String start_time_in_format = start_time.substring(0,2) + "-" + start_time.substring(2,4) + "-" + start_time.substring(4,6) + " " + start_time.substring(6,8) + ":" + start_time.substring(8,10) + ":" + start_time.substring(10,12);
			String stop_time = characters[0].split(";")[1];
			String stop_time_in_format = stop_time.substring(0,2) + "-" + stop_time.substring(2,4) + "-" + stop_time.substring(4,6) + " " + stop_time.substring(6,8) + ":" + stop_time.substring(8,10) + ":" + stop_time.substring(10,12);
			System.out.println("from PositionData where tid = '" + clientId + "' and date>'" + start_time_in_format + "' and date<'" + stop_time_in_format + "' order by date desc");
			List<PositionData> position_between = positiondataDao.findByHQL("from PositionData where tid = '" + clientId + "' and date>'" + start_time_in_format + "' and date<'" + stop_time_in_format + "' order by date desc");
			System.out.println(position_between.size());
			for(int i=0;i<position_between.size();i++){
				if(position_between.get(i).getInfo().contains("GPS状态:GPS不定位;")){
					position_between.remove(i);
					i--;
				}
			}
			String start_point = "Nil";
			String stop_point = "Nil";
			System.out.println(position_between.size());
			if(position_between.size()>0){
				String stop_point_latitude = position_between.get(0).getInfo().substring(position_between.get(0).getInfo().lastIndexOf("纬度:"));
				stop_point_latitude = stop_point_latitude.substring(0,stop_point_latitude.indexOf(";"));
				stop_point_latitude = stop_point_latitude.split(":")[1];
				stop_point_latitude = stop_point_latitude.replaceAll("\\.", "");
				stop_point_latitude = stop_point_latitude.replaceAll("°", ".");
				String tempStrPart = stop_point_latitude.split("\\.")[1];
				tempStrPart = "0." + tempStrPart;
				double tempD = Double.parseDouble(tempStrPart)/60*100;
				stop_point_latitude = Integer.parseInt(stop_point_latitude.split("\\.")[0]) + tempD + "";
				
				String stop_point_longitute = position_between.get(0).getInfo().substring(position_between.get(0).getInfo().lastIndexOf("经度:"));
				stop_point_longitute = stop_point_longitute.substring(0,stop_point_longitute.indexOf(";"));
				stop_point_longitute = stop_point_longitute.split(":")[1];
				stop_point_longitute = stop_point_longitute.replaceAll("\\.", "");
				stop_point_longitute = stop_point_longitute.replaceAll("°", ".");
				String _tempStrPart = "0." + stop_point_longitute.split("\\.")[1];
				double _tempD = Double.parseDouble(_tempStrPart)/60*100;
				stop_point_longitute = Integer.parseInt(stop_point_longitute.split("\\.")[0]) + _tempD + "";
				stop_point = stop_point_longitute + "," + stop_point_latitude;
				
				String start_point_latitude = position_between.get(position_between.size()-1).getInfo().substring(position_between.get(position_between.size()-1).getInfo().lastIndexOf("纬度:"));
				start_point_latitude = start_point_latitude.substring(0,start_point_latitude.indexOf(";"));
				start_point_latitude = start_point_latitude.split(":")[1];
				start_point_latitude = start_point_latitude.replaceAll("\\.", "");
				start_point_latitude = start_point_latitude.replaceAll("°", ".");
				String tempStrPart1 = start_point_latitude.split("\\.")[1];
				tempStrPart1 = "0." + tempStrPart1;
				double tempD1 = Double.parseDouble(tempStrPart1)/60*100;
				start_point_latitude = Integer.parseInt(start_point_latitude.split("\\.")[0]) + tempD1 + "";
				
				String start_point_longitute = position_between.get(position_between.size()-1).getInfo().substring(position_between.get(position_between.size()-1).getInfo().lastIndexOf("经度:"));
				start_point_longitute = start_point_longitute.substring(0,start_point_longitute.indexOf(";"));
				start_point_longitute = start_point_longitute.split(":")[1];
				start_point_longitute = start_point_longitute.replaceAll("\\.", "");
				start_point_longitute = start_point_longitute.replaceAll("°", ".");
				String _tempStrPart1 = "0." + start_point_longitute.split("\\.")[1];
				double _tempD1 = Double.parseDouble(_tempStrPart1)/60*100;
				start_point_longitute = Integer.parseInt(start_point_longitute.split("\\.")[0]) + _tempD1 + "";
				start_point = start_point_longitute + "," + start_point_latitude;
				String start_point_str_for_api = "http://api.map.baidu.com/geoconv/v1/?coords=" + start_point + "&from=1&to=5&ak=" + API_KEY;
				JSONObject start_json1 = JSONHelper.readJsonFromUrl(start_point_str_for_api);
				String start_point_after = (String) (start_json1.getJSONArray("result").getString(0));
				JSONObject start_json_after = new JSONObject(start_point_after);
				String start_x = start_json_after.getString("x");
				String start_y = start_json_after.getString("y");
				String start_str = start_y + "," + start_x;
				String start_point_str2_for_api =  "http://api.map.baidu.com/geocoder?location=" + start_str + "&output=json&ak=" + API_KEY;
				JSONObject start_json2 = JSONHelper.readJsonFromUrl(start_point_str2_for_api);
				String start_address_after = (String) (start_json2.getString("result"));
				System.out.println(start_address_after);
				String start_temp_str = start_address_after.split("\"formatted_address\":\"")[1];
				start_address = start_temp_str.substring(0,start_temp_str.indexOf("\""));
				if("".equals(start_address))
					start_address = "无效地理位置";
				String stop_point_str_for_api = "http://api.map.baidu.com/geoconv/v1/?coords=" + stop_point + "&from=1&to=5&ak=" + API_KEY;
				JSONObject stop_json1 = JSONHelper.readJsonFromUrl(stop_point_str_for_api);
				String stop_point_after = (String) (stop_json1.getJSONArray("result").getString(0));
				JSONObject stop_json_after = new JSONObject(stop_point_after);
				String stop_x = stop_json_after.getString("x");
				String stop_y = stop_json_after.getString("y");
				String stop_str = stop_y + "," + stop_x;
				String stop_point_str2_for_api =  "http://api.map.baidu.com/geocoder?location=" + stop_str + "&output=json&ak=" + API_KEY;
				JSONObject stop_json2 = JSONHelper.readJsonFromUrl(stop_point_str2_for_api);
				String stop_address_after = (String) (stop_json2.getString("result"));
				System.out.println(stop_address_after);
				String stop_temp_str = stop_address_after.split("\"formatted_address\":\"")[1];
				stop_address = stop_temp_str.substring(0,stop_temp_str.indexOf("\""));
				if("".equals(stop_address))
					stop_address = "无效地理位置";
			}
			travelInfo.setStart_address(start_address);
			travelInfo.setStop_address(stop_address);
			travelInfoDao.addTravelInfo(travelInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return start_address + ";" + stop_address;
	}
	
}
