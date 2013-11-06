package com.fix.obd.web.service;

import com.fix.obd.web.model.OBDTerminalInfo;

public interface TerminalInfoService {
	public OBDTerminalInfo getTerminalInfo(String terminalId);
	public String getLastUpdateDate(String terminalId);
}
