package com.fix.obd.web.service;

import com.fix.obd.web.model.OBDTerminalInfo;
import com.fix.obd.web.model.YY_User;

public interface TerminalInfoService {
	public OBDTerminalInfo getTerminalInfo(String terminalId);
	public String getLastUpdateDate(String terminalId);
	public YY_User getTerminalUserInfo(String terminalId);
}
