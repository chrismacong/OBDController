package com.fix.obd.web.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.tcp.thread.UploadTerminalDataTask;
import com.fix.obd.util.FaultCodeXMLUtil;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ThreadMap;
import com.fix.obd.web.dao.DTCDefectDao;
import com.fix.obd.web.dao.OBDDefectDao;
import com.fix.obd.web.dao.OBDTerminalInfoDao;
import com.fix.obd.web.dao.PositionDataDao;
import com.fix.obd.web.model.DTCDefect;
import com.fix.obd.web.model.OBDDefect;
import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.model.PositionData;
import com.fix.obd.web.model.util.FaultCodeIterator;
import com.fix.obd.web.service.DTCService;

@Component
public class DTCServiceImpl implements DTCService{
	@Resource
	private PositionDataDao positionDataDao;
	@Resource
	private DTCDefectDao dtcDefectDao;
	@Resource
	private OBDDefectDao obdDefectDao;
	@Resource
	private OBDTerminalInfoDao obdTerminalInfoDao;
	public OBDTerminalInfoDao getObdTerminalInfoDao() {
		return obdTerminalInfoDao;
	}

	public void setObdTerminalInfoDao(OBDTerminalInfoDao obdTerminalInfoDao) {
		this.obdTerminalInfoDao = obdTerminalInfoDao;
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

	public PositionDataDao getPositionDataDao() {
		return positionDataDao;
	}

	public void setPositionDataDao(PositionDataDao positionDataDao) {
		this.positionDataDao = positionDataDao;
	}

	@Override
	public boolean isACCOn(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<PositionData> list = positionDataDao.findByHQL("from PositionData where tid = '" + terminalId + "'");
			if(list.size()>0){
				PositionData p = list.get(0);
				String info = p.getInfo();
				String accStatus = info.substring(info.lastIndexOf("ACC状态:"));
				accStatus = accStatus.substring(6,10);
				if(accStatus.equals("ACC开"))
					return true;
				else
					return false;
			}
			else
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
	public OBDDefect getOBDDefect(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<OBDDefect> list = obdDefectDao.findByHQL("from OBDDefect where tid = '" + MessageUtil.frontCompWithZore(terminalId, 20) + "' order by date desc");
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
	public boolean askOBDDefect(String terminalId) {
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
				boolean result = u.SentReadDTCStatus(terminalId, bufferId);
				return result;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean askClearDTC(String terminalId) {
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
				boolean result = u.SentClearDTC(terminalId, bufferId);
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
				result_list.add(fci);
			}
			else{
				for(int j=0;j<list.size();j++)
					result_list.add(list.get(j));
			}
		}
		return result_list;
	}

	@Override
	public Map askDTCDefectInRespond(String terminalId) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		boolean isActual = false;
		boolean hasData = false;
		try {
			List<OBDTerminalInfo> list = obdTerminalInfoDao.findByHQL("from OBDTerminalInfo where tid = '" + MessageUtil.frontCompWithZore(terminalId, 20) + "'");
			if(list.size()>0){
				hasData = true;
				OBDTerminalInfo obd = list.get(0);
				String ipAndPort = obd.getTerminalIp();
				String ip = ipAndPort.split(":")[0];
				String port = ipAndPort.split(":")[1];
				UploadTerminalDataTask u = ThreadMap.threadNameMap.get("/" + ip);				
				String bufferId = "78";
				Properties p = new Properties();
				InputStream is=this.getClass().getResourceAsStream("/system.properties"); 
				p.load(is);  
				is.close();
				isActual = u.SentReadDTCInSeconds(terminalId, bufferId, p.getProperty("dtc_response_wait_time"));
				DTCDefect dtc = this.getDTCDefect(terminalId);
				map.put("dtc", dtc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("isActual", isActual);
		map.put("hasData", hasData);
		return map;
	}
}
