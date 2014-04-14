package com.fix.obd.web.service;

import java.util.List;

import com.fix.obd.web.model.OBDLogs;

public interface TerminalLogService {
	public List<OBDLogs> getTerminalLogsById(String terminalId, int pageSize);
	public void deleteAllLogsById(String terminalId);
	public String getLogDetailMessage(String logId);
}
