package com.fix.obd.web.service;

public interface UpdateConfigService {
	public boolean askUpdate(String terminalId, String index, String serverPort, String serverIp, String serverAPN);
}
