package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.OBDTerminalInfo;


public interface OBDTerminalInfoDao {
	public List<OBDTerminalInfo> getAllOBDTerminalInfo();

	public void addOBDTerminalInfo(OBDTerminalInfo obdt);

	public void removeAllOBDTerminalInfo();

	public void deleteOBDTerminalInfo(OBDTerminalInfo obdt);

	public void updateOBDTerminalInfo(OBDTerminalInfo obdt);

	public List<OBDTerminalInfo> findByHQL(String hql) throws Exception;
}
