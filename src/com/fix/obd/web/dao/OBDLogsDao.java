package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.OBDLogs;


public interface OBDLogsDao {
	public List<OBDLogs> getAllOBDLogs();

	public void addOBDLogs(OBDLogs obdLogs);

	public void removeAllOBDLogs();

	public void deleteOBDLogs(OBDLogs obdLogs);

	public void updateOBDLogs(OBDLogs obdLogs);

	public List<OBDLogs> findByHQL(String hql) throws Exception;
}
