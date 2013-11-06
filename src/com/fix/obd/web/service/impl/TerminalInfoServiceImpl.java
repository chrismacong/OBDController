package com.fix.obd.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.OBDTerminalInfoDao;
import com.fix.obd.web.dao.OnLineTerminalDao;
import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.model.OnLineTerminal;
import com.fix.obd.web.service.TerminalInfoService;

@Component
public class TerminalInfoServiceImpl implements TerminalInfoService{
	@Resource
	private OBDTerminalInfoDao obdTerminalInfoDao;
	@Resource
	private OnLineTerminalDao onlineTerminalDao;
	
	public OnLineTerminalDao getOnlineTerminalDao() {
		return onlineTerminalDao;
	}
	public void setOnlineTerminalDao(OnLineTerminalDao onlineTerminalDao) {
		this.onlineTerminalDao = onlineTerminalDao;
	}
	public OBDTerminalInfoDao getObdTerminalInfoDao() {
		return obdTerminalInfoDao;
	}
	public void setObdTerminalInfoDao(OBDTerminalInfoDao obdTerminalInfoDao) {
		this.obdTerminalInfoDao = obdTerminalInfoDao;
	}
	@Override
	public OBDTerminalInfo getTerminalInfo(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<OBDTerminalInfo> list = obdTerminalInfoDao.findByHQL("from OBDTerminalInfo where tid = '" + terminalId + "'");
			return list.get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public String getLastUpdateDate(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<OnLineTerminal> list = onlineTerminalDao.findByHQL("from OnLineTerminal where tid = '" + terminalId + "'");
			return list.get(0).getLastheartbeat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
