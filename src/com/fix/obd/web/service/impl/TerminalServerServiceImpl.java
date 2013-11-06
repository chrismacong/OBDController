package com.fix.obd.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
			if(list.size()==0)
				obdDefectDao.addOBDDefect(obdDefect);
			else{
				obdDefect = list.get(0);
				obdDefect.setInfo(info);
				obdDefect.setDate(now);
				obdDefectDao.updateOBDDefect(obdDefect);
			}
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
			List<DTCDefect> list = dtcDefectDao.findByHQL("from DTCDefect where tid = '" + clientId + "'");
			if(list.size()==0)
				dtcDefectDao.addDTCDefect(dtcDefect);
			else{
				dtcDefect = list.get(0);
				dtcDefect.setInfo(info);
				dtcDefect.setDate(now);
				dtcDefectDao.updateDTCDefect(dtcDefect);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateTravelInfo(String clientId, String info) {
		// TODO Auto-generated method stub
		try {
			TravelInfo travelInfo = new TravelInfo();
			travelInfo.setTerminalId(clientId);
			travelInfo.setInfo(info);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df.format(new Date());
			travelInfo.setDate(now);
			List<TravelInfo> list = travelInfoDao.findByHQL("from TravelInfo where tid = '" + clientId + "'");
			if(list.size()==0)
				travelInfoDao.addTravelInfo(travelInfo);
			else{
				travelInfo = list.get(0);
				travelInfo.setInfo(info);
				travelInfoDao.updateTravelInfo(travelInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
