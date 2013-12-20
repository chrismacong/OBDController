package com.fix.obd.web.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.tcp.thread.UploadTerminalDataTask;
import com.fix.obd.util.MessageUtil;
import com.fix.obd.util.ThreadMap;
import com.fix.obd.web.dao.OBDDataDao;
import com.fix.obd.web.dao.OBDTerminalInfoDao;
import com.fix.obd.web.model.OBDData;
import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.service.OBDInfoService;
@Component
public class OBDInfoServiceImpl implements OBDInfoService{
	@Resource
	private OBDDataDao obdDataDao;
	@Resource
	private OBDTerminalInfoDao obdTerminalInfoDao;
	public OBDTerminalInfoDao getObdTerminalInfoDao() {
		return obdTerminalInfoDao;
	}
	public void setObdTerminalInfoDao(OBDTerminalInfoDao obdTerminalInfoDao) {
		this.obdTerminalInfoDao = obdTerminalInfoDao;
	}
	public OBDDataDao getObdDataDao() {
		return obdDataDao;
	}
	public void setObdDataDao(OBDDataDao obdDataDao) {
		this.obdDataDao = obdDataDao;
	}
	@Override
	public OBDData getLatestOBDData(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<OBDData> obd_list = obdDataDao.findByHQL("from OBDData where tid = '" + terminalId + "'");
			if(obd_list.size()>0){
				OBDData latest_data = obd_list.get(0);
				if(obd_list.size()>1){
					for(int i=1;i<obd_list.size();i++){
						if(obd_list.get(i).getDate().compareTo(latest_data.getDate())>0)
							latest_data = obd_list.get(i);
					}
				}
				return latest_data;
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
	public int getOBDDataRefreshMinute() {
		// TODO Auto-generated method stub
		try {
			Properties p = new Properties();
			InputStream is=this.getClass().getResourceAsStream("/system.properties"); 
			p.load(is);  
			is.close();
			return Integer.parseInt(p.getProperty("obd_info_refresh_millisecond"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public boolean askOBDInfo(String terminalId) {
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
				boolean result = u.SentRequestOBDInfo(terminalId, bufferId);
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
	public List<OBDData> getLatest10OBDInfo(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<OBDData> returnData = new ArrayList<OBDData>();
			List<OBDData> tempData = new ArrayList<OBDData>();
			List<OBDData> obd_list = new ArrayList<OBDData>();
			obd_list = obdDataDao.findByHQL("from OBDData where tid = '" + terminalId + "' order by date desc");
			for(int i=0;i<(obd_list.size()>10?10:obd_list.size());i++){
				tempData.add(obd_list.get(i));
			}
			for(int i=tempData.size()-1;i>=0;i--)
				returnData.add(tempData.get(i));
			return returnData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
