package com.fix.obd.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.OBDLogsDao;
import com.fix.obd.web.model.OBDLogs;
import com.fix.obd.web.service.TerminalLogService;
@Component
public class TerminalLogServiceImpl implements TerminalLogService{
	@Resource
	private OBDLogsDao obdLogsDao;
	public OBDLogsDao getObdLogsDao() {
		return obdLogsDao;
	}
	public void setObdLogsDao(OBDLogsDao obdLogsDao) {
		this.obdLogsDao = obdLogsDao;
	}
	@Override
	public List<OBDLogs> getTerminalLogsById(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<OBDLogs> logs_list = obdLogsDao.findByHQL("from OBDLogs where tid = '" + terminalId + "' order by date desc");
			return logs_list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void deleteAllLogsById(String terminalId) {
		// TODO Auto-generated method stub
		try {
			List<OBDLogs> logs_list = obdLogsDao.findByHQL("from OBDLogs where tid = '" + terminalId + "'");
			for(int i=0;i<logs_list.size();i++){
				OBDLogs log = logs_list.get(i);
				obdLogsDao.deleteOBDLogs(log);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String getLogDetailMessage(String logId) {
		// TODO Auto-generated method stub
		try {
			List<OBDLogs> logs_list = obdLogsDao.findByHQL("from OBDLogs where lid = " + logId);
			if(logs_list.size()>0){
				return logs_list.get(0).getMessage();
			}
			else
				return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
